# mentoring-backend

A didactic backend for learning hexagonal architecture, DDD, and TDD with Kotlin and Spring Boot.

Read the [problem statement](docs/problem.md) first.

---

## Stack

| Technology | Version |
|---|---|
| Kotlin | 1.9.25 |
| Spring Boot | 3.3.4 |
| Java | 21 |
| Gradle (Kotlin DSL) | 8.10.2 |

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
