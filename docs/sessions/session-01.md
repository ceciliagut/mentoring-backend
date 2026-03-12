# Session 01 — Understanding the problem and modelling the domain

## Preparation

Read the [problem statement](../problem.md) before this session. Come ready to discuss it.

---

## Goal

Understand the problem, the ubiquitous language, and why we start from the domain — not from Spring.

---

## Concepts covered

- What is a booking and what does it represent in the real world
- Actors and concepts of the domain (mentor, attendee, session)
- Booking states and what each one means
- Basic business rules
- Aggregate, entity, and value objects

---

## Key questions to discuss

- Who are the actors in this domain?
- What lifecycle does a booking have? Draw it.
- What is the difference between an entity and a value object?
- What rules must the domain enforce? What rules belong elsewhere?

---

## Expected outcome

- Initial domain model sketched on paper or a whiteboard
- State diagram for the booking lifecycle
- First domain classes written by hand (no Spring, no framework)

---

## Exercise for the next two weeks

- Model the core booking concept as a Kotlin class with the fields you think it needs
- Define the status enum with all the states from the problem statement
- Identify at least one value object and implement it
- Bring a PR with your model and be ready to explain every decision

---

## Review questions

- What states can a booking transition to from the initial state?
- Can a cancelled booking be confirmed? Why not?
- Where does the rule "a mentor cannot have two overlapping bookings" live?
- What would you lose if you used a plain `String` for identifiers instead of a dedicated type?
