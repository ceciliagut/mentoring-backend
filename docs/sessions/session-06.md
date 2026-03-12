# Session 06 — Testing pyramid and test strategy

## Goal

Understand what to test at each level and why.

---

## Concepts covered

- Unit tests vs integration tests
- What not to test twice
- When to use mocks
- How to keep tests fast and reliable
- The testing pyramid in the context of this project

---

## Key questions to discuss

- What have we tested so far? At what level?
- Are we testing the same thing in multiple places? Where is the overlap?
- When is a mock the right tool? When does it hide a problem?
- What makes a test slow? How do we avoid it?
- What does a good test name look like?

---

## Expected outcome

- Clear testing strategy understood and documented
- Test structure in the repo is coherent and justified
- `docs/testing.md` reviewed and updated to reflect what we have built

---

## Exercise for the next two weeks

- Review all existing tests — identify any duplicated coverage
- Make sure unit tests do not use `@SpringBootTest`
- Make sure integration tests do not test domain logic that is already covered by unit tests
- Refactor any test that is testing the wrong thing at the wrong level
- Bring a PR with improved test coverage and a short comment explaining each test's purpose

---

## Review questions

- What is the testing pyramid? Where do most of our tests live?
- If a use case is already covered by unit tests, what should the integration test validate?
- What is the difference between a mock and a fake (in-memory implementation)?
- Why are slow tests a problem beyond just CI time?
