package co.com.hermes.calendar.shared.contract;

import java.util.List;
import java.util.UUID;

/**
 * Contrato interno: resultado de la validación de credenciales y perfil básico del usuario.
 */
public record CredentialVerificationResponse(
        boolean authenticated,
        UUID userId,
        UUID tenantId,
        String username,
        String email,
        List<String> roles,
        List<String> permissions,
        boolean enabled,
        boolean locked
) {

    public static CredentialVerificationResponse failed() {
        return new CredentialVerificationResponse(false, null, null, null, null, List.of(), List.of(), false, false);
    }
}
