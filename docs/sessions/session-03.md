# Session 03 — First use case with TDD

## Goal

Build the first flow following TDD from domain to application layer.

---

## Concepts covered

- The `RequestReservation` use case
- The red-green-refactor cycle
- Unit test for the domain
- Unit test for the use case

---

## Key questions to discuss

- What does TDD actually mean in practice? Walk through a cycle together.
- What is the difference between testing the domain and testing the use case?
- What do we pass into `RequestReservation`? What does it return?
- What happens if the input is invalid? Who is responsible for that?
- Should the use case know about HTTP? About Postgres?

---

## Expected outcome

- `RequestReservation` use case implemented
- In-memory repository used in tests
- Unit tests for the domain model passing
- Unit tests for the use case passing

---

## Exercise for the next two weeks

- Implement `RequestReservation` following TDD (write the test first)
- The use case should accept a command object and return a result
- Write at least one test that covers a happy path
- Write at least one test that covers an invalid input
- Bring a PR with the tests written before the implementation

---

## Review questions

- Why do we write the test before the implementation?
- What makes a unit test "pure"?
- Should `RequestReservation` receive a `ReservationRepository` or a Spring `@Autowired` bean?
- How do you test the use case without starting Spring?
