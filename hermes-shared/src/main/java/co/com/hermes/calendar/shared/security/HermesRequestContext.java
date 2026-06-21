package co.com.hermes.calendar.shared.security;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

/**
 * Identidad confiable de la petición, reconstruida desde las cabeceras {@link HermesIdentityHeaders}
 * que pone el gateway. Es la <b>primitiva de aislamiento multi-tenant</b>: los servicios de negocio
 * deben filtrar TODA consulta de datos por {@link #tenantId()} (derivado del token, nunca de input
 * del cliente) para impedir acceso cruzado entre organizaciones.
 *
 * <p>Uso típico en un servicio (un filtro/intercepta por petición):</p>
 * <pre>{@code
 * HermesRequestContext ctx = HermesRequestContext.fromHeaders(request::getHeader);
 * UUID tenantId = ctx.requireTenant();           // 403 si la cuenta no tiene tenant
 * repo.findByTenantIdAnd...(tenantId, ...);       // SIEMPRE acotado por tenant
 * }</pre>
 */
public record HermesRequestContext(
        UUID userId,
        String username,
        AccountScope accountScope,
        UUID tenantId,
        String tenantSlug,
        List<String> roles,
        List<String> permissions
) {

    public boolean isTenantScoped() {
        return tenantId != null;
    }

    public boolean hasRole(String role) {
        return roles.contains(role);
    }

    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }

    /**
     * Devuelve el tenant de la petición o lanza si la cuenta no pertenece a una organización
     * (cuenta de plataforma: SYSTEM_ADMIN o invitado). Úsese en endpoints de datos de tenant.
     */
    public UUID requireTenant() {
        if (tenantId == null) {
            throw new IllegalStateException("Request has no tenant context");
        }
        return tenantId;
    }

    /** Construye el contexto desde un accesor de cabeceras (p. ej. {@code request::getHeader}). */
    public static HermesRequestContext fromHeaders(Function<String, String> headers) {
        return new HermesRequestContext(
                parseUuid(headers.apply(HermesIdentityHeaders.USER_ID)),
                headers.apply(HermesIdentityHeaders.USERNAME),
                parseScope(headers.apply(HermesIdentityHeaders.ACCOUNT_SCOPE)),
                parseUuid(headers.apply(HermesIdentityHeaders.TENANT_ID)),
                headers.apply(HermesIdentityHeaders.TENANT_SLUG),
                parseCsv(headers.apply(HermesIdentityHeaders.ROLES)),
                parseCsv(headers.apply(HermesIdentityHeaders.PERMISSIONS))
        );
    }

    private static UUID parseUuid(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return UUID.fromString(value.trim());
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    private static AccountScope parseScope(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return AccountScope.valueOf(value.trim());
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    private static List<String> parseCsv(String value) {
        if (value == null || value.isBlank()) {
            return List.of();
        }
        return List.of(value.split(",")).stream().map(String::trim).filter(s -> !s.isEmpty()).toList();
    }
}
