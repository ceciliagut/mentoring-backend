# Session 05 — States and business rules

## Goal

Model state transitions correctly and protect domain invariants.

---

## Concepts covered

- `confirm`, `cancel`, `expire`, `complete`
- Valid and invalid transitions
- Domain errors
- Richer aggregate behaviour

---

## Key questions to discuss

- What transitions are valid from each state? Draw the full state machine.
- What happens when an invalid transition is attempted? What does the domain return or throw?
- Where does the transition logic live — in the aggregate or in the use case?
- What is a domain exception? How is it different from a validation error?
- How do we test that invalid transitions are rejected?

---

## Expected outcome

- `Reservation` aggregate with transition methods (`confirm()`, `cancel()`, `expire()`, `complete()`)
- Domain exceptions for invalid transitions
- Unit tests covering valid and invalid transitions
- Richer understanding of tactical DDD

---

## Exercise for the next two weeks

- Add `confirm()`, `cancel()`, `expire()`, and `complete()` methods to `Reservation`
- Each method should enforce that the transition is valid from the current state
- Define domain exceptions (e.g. `InvalidReservationTransitionException`)
- Write unit tests for every valid transition and every invalid one
- Bring a PR — all state logic must live inside the aggregate, not in the use case

---

## Review questions

- Can a `CANCELLED` reservation be confirmed? Where is that rule enforced?
- Should the use case check the current state before calling `confirm()`? Why not?
- What is the difference between a domain exception and an HTTP 400?
- How does the controller know which HTTP status to return for a domain exception?

---

## Note on overlap validation

The rule "a mentor cannot have two overlapping reservations" is intentionally left for session 07, when we have Postgres. Validating overlaps with a real query is more natural and teaches the right lesson about where that logic lives with persistence.
