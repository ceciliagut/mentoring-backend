# Session 02 — Real hexagonal architecture

## Goal

Understand how to organise a backend so that the domain does not depend on the framework.

---

## Concepts covered

- The three layers: `domain`, `application`, `infrastructure`
- Input and output ports
- Adapters
- What is allowed and what is forbidden in each layer

---

## Key questions to discuss

- Why should the domain not know about Spring?
- What is a port? What is an adapter?
- Where does `ReservationRepository` live — and why is it an interface?
- What would break if we put `@Entity` on a domain class?
- How do we replace an in-memory repository with Postgres without touching the domain?

---

## Expected outcome

- Package structure of the repo understood and justified
- Architecture rules documented (see `docs/architecture.md`)
- First clean project skeleton with the three layers in place

---

## Exercise for the next two weeks

- Create the package structure: `domain`, `application`, `infrastructure`
- Move the domain model from session 01 into `domain/model`
- Define the output port `ReservationRepository` as an interface in `domain/port`
- Write an in-memory implementation in `infrastructure/persistence`
- Bring a PR and be ready to explain why each file lives where it does

---

## Review questions

- Can a class in `domain` import anything from `org.springframework.*`? Why not?
- Can a use case import `ResponseEntity`? Why not?
- What is the difference between a port and an adapter?
- If we add Postgres tomorrow, which files change and which stay the same?
