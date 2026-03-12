# Session 07 — Real persistence with Postgres

## Goal

Switch from the in-memory repository to real persistence without touching the domain.

---

## Concepts covered

- The repository output port in practice
- Postgres adapter
- Database migrations
- Mapping between domain and persistence models
- Integration tests with a real database

---

## Key questions to discuss

- What changes when we add Postgres? What stays exactly the same?
- Why do we have a separate persistence model instead of annotating the domain class?
- What is a database migration? Why do we version them?
- How do we test the repository adapter with a real database?
- Where does the overlap validation for a mentor's schedule live now?

---

## Expected outcome

- `ReservationRepository` implemented with Postgres
- Domain model completely free of persistence annotations
- Database migrations in place
- Integration tests for the repository adapter passing
- Overlap validation added using a database query

---

## Exercise for the next two weeks

- Add `spring-boot-starter-data-jpa` and the Postgres driver to `build.gradle.kts`
- Create a `ReservationJpaEntity` in `infrastructure/persistence` (separate from the domain model)
- Implement `ReservationRepository` using JPA
- Write a migration for the `reservations` table
- Write an integration test for the Postgres adapter using Testcontainers or an embedded DB
- Bring a PR — the domain must remain completely free of JPA annotations

---

## Review questions

- Why do we have two representations of a reservation (domain model + JPA entity)?
- What would break if we added `@Entity` to `Reservation`?
- How does the application start using Postgres instead of the in-memory repository? What changes?
- Where is the overlap validation query implemented and why there?
