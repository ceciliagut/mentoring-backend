# mentoring-backend

A didactic backend for learning hexagonal architecture, DDD, and TDD with Kotlin and Spring Boot.

Read the [problem statement](docs/problem.md) first.

---

## Stack

| Technology | Version |
|---|---|
| Kotlin | 2.2.21 |
| Spring Boot | 4.0.3 |
| Java | 21 |
| Gradle (Kotlin DSL) | 9.4.0 |

---

## Run

```bash
./gradlew bootRun
```

## Test

```bash
./gradlew test              # Unit tests + architecture tests
./gradlew integrationTest   # Integration tests (requires Docker)
./gradlew check             # Everything
```

---

## Docs

- [Problem statement](docs/problem.md)
- [Architecture](docs/architecture.md)
- [Testing strategy](docs/testing.md)
- [Session plan](docs/sessions/)
