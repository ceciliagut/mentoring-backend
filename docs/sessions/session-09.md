# Session 09 — Kafka and event-driven integration

## Goal

Introduce messaging without breaking the design.

---

## Concepts covered

- Kafka adapter
- The difference between domain events and integration events
- Publishing events from the application layer
- Idempotency, duplicates, and consistency at a conceptual level

---

## Key questions to discuss

- How does the Kafka adapter fit into the hexagonal architecture?
- Why do we map domain events to integration events before publishing?
- What happens if the application crashes after saving to the database but before publishing to Kafka?
- What is idempotency and why does it matter for consumers?
- What is the outbox pattern and when would we need it?

---

## Expected outcome

- Kafka adapter implemented in `infrastructure`
- Domain events mapped to integration events and published
- Application layer unaware of Kafka specifics
- Basic understanding of event-driven consistency challenges

---

## Exercise for the next two weeks

- Add the Kafka dependency to `build.gradle.kts`
- Define integration event DTOs in `infrastructure/messaging`
- Implement a `KafkaEventPublisher` that maps domain events and sends them
- Wire the publisher into the use cases via an output port
- Bring a PR — the domain and application layers must not import anything Kafka-related

---

## Review questions

- What is the output port that the Kafka adapter implements?
- Why do we map domain events to a separate integration event before sending?
- What would the outbox pattern change about this implementation?
- Can a consumer rely on receiving every event exactly once? What should it do if it receives a duplicate?
