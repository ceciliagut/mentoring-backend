# Testing Strategy

```
        /\
       /  \
      / E2E\             <- out of scope
     /------\
    /        \
   /Integration\         <- src/integrationTest/
  /------------\
 /     Unit     \        <- src/test/
/ + Architecture \
/-----------------\
```

## Structure

| Source set | What lives here | Command |
|---|---|---|
| `src/test/` | Unit tests + ArchUnit architecture tests | `./gradlew test` |
| `src/integrationTest/` | Spring context + Testcontainers | `./gradlew integrationTest` |

## Unit tests

Fast and pure. No Spring context, no I/O.

Test domain logic, use case behaviour, and architecture rules.

## Integration tests

Start a real Spring context and a real Postgres container via Testcontainers.

Validate end-to-end HTTP behaviour and repository adapters.

## Architecture tests

ArchUnit rules that fail the build if hexagonal architecture is violated.

They live in `src/test/` because they are fast, pure, and must run on every commit.

## Coverage

Minimum **80% line coverage** enforced by Kover. The build fails if the threshold is not met.

```bash
./gradlew koverHtmlReport
# Report: build/reports/kover/html/index.html
```
