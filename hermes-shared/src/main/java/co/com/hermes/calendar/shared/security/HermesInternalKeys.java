package co.com.hermes.calendar.shared.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Utilidades para validar la clave compartida de llamadas internas ({@link HermesInternalHeaders#INTERNAL_KEY}).
 */
public final class HermesInternalKeys {

    private HermesInternalKeys() {
    }

    /**
     * Compara la clave esperada con la recibida en <b>tiempo constante</b> (evita timing attacks).
     * Devuelve {@code false} si la esperada está vacía/nula o si la recibida es nula.
     */
    public static boolean matches(String expected, String provided) {
        if (expected == null || expected.isBlank() || provided == null) {
            return false;
        }
        return MessageDigest.isEqual(
                expected.getBytes(StandardCharsets.UTF_8),
                provided.getBytes(StandardCharsets.UTF_8));
    }
}
