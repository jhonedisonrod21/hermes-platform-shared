package co.com.hermes.calendar.shared.contract;

import java.util.List;
import java.util.UUID;

/**
 * Contrato interno: resultado de la validación de credenciales y perfil básico del usuario.
 *
 * <p>{@code platformAnchored} indica que la cuenta está <b>anclada a plataforma</b> (p. ej. el
 * administrador del sistema): nunca pertenece a un tenant, por lo que el Auth Server resuelve su
 * alcance como PLATFORM sin consultar membresías. Para el resto de cuentas el alcance lo decide la
 * membresía activa (TENANT si la hay; PLATFORM/invitado si no).</p>
 */
public record CredentialVerificationResponse(
        boolean authenticated,
        UUID userId,
        UUID tenantId,
        String username,
        String email,
        String name,
        List<String> roles,
        List<String> permissions,
        boolean platformAnchored,
        boolean enabled,
        boolean locked
) {

    public static CredentialVerificationResponse failed() {
        return new CredentialVerificationResponse(false, null, null, null, null, null, List.of(), List.of(), false, false, false);
    }
}
