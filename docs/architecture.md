# Architecture

This project follows **hexagonal architecture** (Ports & Adapters).

## Package structure

```
com.n26.mentoring/
├── domain/
│   ├── model/       # Entities and value objects
│   └── port/        # Output port interfaces
├── application/
│   └── usecase/     # Use cases
└── infrastructure/
    ├── persistence/ # Repository implementations
    └── rest/        # REST controllers and DTOs
```

## Rules

- `domain` has no dependencies on Spring, frameworks, or other layers
- `application` has no dependencies on `infrastructure` or Spring Web
- `infrastructure` is the only layer that knows about Spring, databases, and HTTP
- Dependency direction is always inward: `infrastructure` → `application` → `domain`
- Controllers translate HTTP to use cases — no business logic inside them
- Ports are interfaces. Implementations live in `infrastructure`

These rules are enforced automatically by the architecture tests in `src/test/`.
