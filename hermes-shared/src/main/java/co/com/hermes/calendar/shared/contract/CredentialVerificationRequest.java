package co.com.hermes.calendar.shared.contract;

import jakarta.validation.constraints.NotBlank;

/**
 * Contrato interno: solicitud de validación de credenciales (Auth Server -> Identity Service).
 */
public record CredentialVerificationRequest(
        @NotBlank String username,
        @NotBlank String password
) {
}
