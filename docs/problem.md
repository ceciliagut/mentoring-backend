# Mentoring Booking System

N26 is launching an internal mentoring programme. Engineers can offer mentoring sessions, and other engineers can request time with them.

Your job is to build the backend service that manages these bookings.

---

## The problem

An attendee can request a mentoring session with a mentor for a specific time slot. The mentor can accept the request or not respond to it. If no response is given in time, the booking expires. Either party can cancel before the session takes place. Once the session has happened, it is marked as completed.

The business has one hard constraint: a mentor cannot have two overlapping bookings at the same time. Back-to-back bookings are allowed — a booking ending at 11:00 and another starting at 11:00 do not conflict.

---

## Business rules

- A booking request must identify the mentor, the attendee, and the time slot.
- The time slot must have a valid start and end time. The end must be after the start.
- A mentor cannot be double-booked.
- Once a booking is finished — whether completed, cancelled, or expired — it cannot change state again.
- Expiring a booking and completing a booking are system responsibilities, not user actions.

---

## Out of scope

Authentication, notifications, payment, calendar integrations, and frontend concerns are out of scope.

---

## Starting point

Start by thinking about the concepts: what is a booking, what data does it carry, what can happen to it, and what rules must always hold.

Write Kotlin classes. No framework annotations, no database, no HTTP — just the business logic.
