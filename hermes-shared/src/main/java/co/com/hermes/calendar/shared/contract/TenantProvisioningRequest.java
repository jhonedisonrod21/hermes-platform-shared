package co.com.hermes.calendar.shared.contract;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * Contrato interno: solicitud de aprovisionamiento de tenant inicial
 * (Identity Service -> Tenant Service) durante el registro de un usuario.
 */
public record TenantProvisioningRequest(
        @NotNull UUID userId,
        @Email String email
) {
}
