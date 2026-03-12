# Session 04 — First REST endpoint

## Goal

Expose the use case over HTTP without putting business logic in the controller.

---

## Concepts covered

- `POST /reservations`
- Request and response DTOs
- Mapping between HTTP and application layer
- Basic input validation
- What belongs in the controller and what does not

---

## Key questions to discuss

- What is the controller's only responsibility?
- Where do we validate the request body? Who owns that logic?
- What HTTP status codes should this endpoint return and when?
- What is a DTO? Why do we not pass domain objects directly to the client?
- How do we test an HTTP endpoint without testing the use case again?

---

## Expected outcome

- `POST /reservations` endpoint working
- Request and response DTOs defined in `infrastructure/rest`
- Clean separation between controller and use case
- Integration test for the endpoint passing

---

## Exercise for the next two weeks

- Implement the `POST /reservations` controller
- Define `CreateReservationRequest` and `ReservationResponse` as data classes
- Map the request to the use case command and the result back to the response
- Write an integration test using `@SpringBootTest` that validates the full HTTP behaviour
- Bring a PR — the controller should have zero business logic

---

## Review questions

- What is the difference between a unit test and an integration test for this endpoint?
- Should the controller catch domain exceptions or let Spring handle them?
- Why do we map to DTOs instead of returning domain objects directly?
- What would happen if we put validation logic inside the use case instead of the controller?
