package co.com.hermes.calendar.shared.contract;

import java.util.UUID;

/**
 * Contrato interno: tenant inicial creado para un usuario registrado
 * (respuesta de Tenant Service).
 */
public record TenantProvisioningResponse(UUID tenantId, String tenantSlug, String tenantName) {
}
