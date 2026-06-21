package co.com.hermes.calendar.shared.security;

/**
 * Alcance de autorización de una cuenta dentro de la plataforma Hermes.
 *
 * <p>Es la dimensión de <b>tenencia</b> (con/sin tenant), no de privilegio: el nivel de
 * acceso lo determinan los roles (p. ej. {@code SYSTEM_ADMIN} vs {@code GUEST_USER}).</p>
 *
 * <ul>
 *   <li>{@link #PLATFORM}: cuenta que no pertenece a ninguna organización. Incluye tanto
 *       al administrador del sistema como al invitado autorregistrado ({@code GUEST_USER}).
 *       Sus roles y permisos provienen de Identity Service y el token no lleva tenant.</li>
 *   <li>{@link #TENANT}: cuenta vinculada a una organización. Sus roles y permisos
 *       provienen de la membresía activa en Tenant Service.</li>
 * </ul>
 */
public enum AccountScope {
    PLATFORM,
    TENANT
}
