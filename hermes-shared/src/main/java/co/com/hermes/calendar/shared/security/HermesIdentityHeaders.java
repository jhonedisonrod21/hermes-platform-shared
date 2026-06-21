package co.com.hermes.calendar.shared.security;

/**
 * Nombres de las cabeceras de identidad confiables que el API Gateway deriva del JWT validado y
 * propaga a los microservicios destino (ver {@code GatewayIdentityHeaderFilter}).
 *
 * <p>Son la <b>fuente única</b> de esos nombres: el gateway las emite y los servicios de negocio
 * las leen (vía {@link HermesRequestContext#fromHeaders}). El gateway borra cualquier cabecera
 * {@code X-Hermes-*} entrante antes de reinyectarlas, de modo que un cliente no puede falsificarlas
 * <em>a través del gateway</em>; su confianza depende además del aislamiento de red (solo el
 * gateway debe alcanzar los servicios).</p>
 */
public final class HermesIdentityHeaders {

    public static final String USER_ID = "X-Hermes-User-Id";
    public static final String USERNAME = "X-Hermes-Username";
    public static final String ACCOUNT_SCOPE = "X-Hermes-Account-Scope";
    public static final String TENANT_ID = "X-Hermes-Tenant-Id";
    public static final String TENANT_SLUG = "X-Hermes-Tenant-Slug";
    public static final String ROLES = "X-Hermes-Roles";
    public static final String PERMISSIONS = "X-Hermes-Permissions";

    private HermesIdentityHeaders() {
    }
}
