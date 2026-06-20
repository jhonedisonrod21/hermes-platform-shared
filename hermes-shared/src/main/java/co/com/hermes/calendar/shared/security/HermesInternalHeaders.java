package co.com.hermes.calendar.shared.security;

/**
 * Cabeceras HTTP de las llamadas internas entre servicios Hermes.
 */
public final class HermesInternalHeaders {

    /** Clave compartida que autoriza las llamadas a endpoints {@code /internal/**}. */
    public static final String INTERNAL_KEY = "X-Hermes-Internal-Key";

    private HermesInternalHeaders() {
    }
}
