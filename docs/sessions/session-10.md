# Session 10 — Observability, logging, and closing

## Goal

Understand that a backend is not finished when it "works".

---

## Concepts covered

- Structured logging
- Request ID and correlation ID
- Basic metrics
- Health checks
- How to review the operability of a service

---

## Key questions to discuss

- What makes a log useful in production? What makes it useless?
- What is a correlation ID and how does it flow through a request?
- What metrics would tell you that this service is healthy?
- What is the difference between liveness and readiness?
- What would you look at first if this service started failing in production?

---

## Expected outcome

- Structured logs with request and correlation IDs
- Basic metrics exposed (Spring Actuator)
- Health check endpoint working
- Repo in a professional and maintainable state

---

## Exercise for the next two weeks

- Add `spring-boot-starter-actuator` to `build.gradle.kts`
- Configure structured JSON logging
- Add a correlation ID filter in `infrastructure` that attaches a request ID to every log line
- Verify that `/actuator/health` responds correctly
- Bring a PR — review the full repo as if you were reviewing someone else's work for the first time

---

## Review questions

- What does a log line need to be useful when debugging a production incident?
- How does a correlation ID help when a request touches multiple services?
- What is the difference between `/actuator/health/liveness` and `/actuator/health/readiness`?
- Looking at the full repo: what would you improve? What are you proud of?

---

## Closing reflection

Take a moment to look at where you started (session 01) and where you are now:

- You modelled a domain from scratch
- You structured a backend following hexagonal architecture
- You wrote tests before writing code
- You exposed an HTTP API without putting business logic in the controller
- You added real persistence without touching the domain
- You introduced messaging without coupling the domain to Kafka
- You made the service observable

That is what professional backend engineering looks like.
