package co.com.hermes.calendar.shared.contract;

import java.util.List;
import java.util.UUID;

/**
 * Contrato interno: contexto de autorización del tenant activo de un usuario
 * (Tenant Service -> Auth Server / consumidores internos).
 */
public record TenantContextResponse(
        UUID tenantId,
        String tenantSlug,
        String tenantName,
        List<String> roles,
        List<String> permissions
) {
}
