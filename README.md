# hermes-platform-shared

Librería de **contratos internos** compartidos por los servicios Hermes. No es un
servicio ejecutable.

## Propósito
Única fuente de verdad para los DTOs y constantes que viajan en las llamadas
internas entre microservicios, evitando duplicar contratos entre repos.

## Contenido (`co.com.hermes.calendar.shared`)
| Tipo | Uso |
|------|-----|
| `contract.CredentialVerificationRequest` / `Response` | Validación de credenciales (Auth → Identity) |
| `contract.TenantContextResponse` | Contexto de tenant activo (Tenant → Auth) |
| `contract.TenantProvisioningRequest` / `Response` | Aprovisionamiento de tenant (Identity → Tenant) |
| `security.HermesInternalHeaders` | Constante de la cabecera `X-Hermes-Internal-Key` |

## Consideraciones técnicas
- Plugins `java-library` + `maven-publish`. **No** genera bootJar (no es app).
- Única dependencia: `jakarta.validation-api` (los records llevan validación
  `@NotBlank`/`@NotNull`/`@Email`). Sin Swagger para no acoplar versiones.
- Se publica con versiones **resueltas** (`versionMapping`) porque la versión de
  `jakarta.validation-api` la fija el BOM de Spring.
- Coordenada publicada: `co.com.hermes.calendar:hermes-shared:0.0.1-SNAPSHOT`.

## Uso
```bash
./gradlew publishToMavenLocal     # lo consume hermes-security desde mavenLocal
```
En CI conviene publicar a un Nexus/Artifactory en lugar de mavenLocal.

## Stack
Java 25 · Gradle 9.5 · Spring Boot 4.0.6 / Spring Cloud 2025.1.1 (BOM).
