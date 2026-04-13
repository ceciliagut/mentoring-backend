package com.n26.mentoring.domain.model

import java.util.UUID

data class Booking(
    val id: UUID = UUID.randomUUID(),
    val status: BookingStatus = BookingStatus.PENDING,
    val timeSlot: TimeSlot,
    val mentorId: UUID,
    val menteeId: UUID,
) {
    init {
        require(mentorId != menteeId) { "Mentor and mentee cannot be the same person" }
    }

    fun confirm(): Booking {
        check(!status.isTerminal) { "Cannot confirm a booking in terminal state $status" }
        check(status == BookingStatus.PENDING) { "Only pending bookings can be confirmed" }
        return copy(status = BookingStatus.CONFIRMED)
    }

    fun cancel(): Booking {
        check(!status.isTerminal) { "Cannot cancel a booking in terminal state $status" }
        return copy(status = BookingStatus.CANCELLED)
    }

    fun expire(): Booking {
        check(!status.isTerminal) { "Cannot expire a booking in terminal state $status" }
        check(status == BookingStatus.PENDING) { "Only pending bookings can expire" }
        return copy(status = BookingStatus.EXPIRED)
    }

    fun complete(): Booking {
        check(!status.isTerminal) { "Cannot complete a booking in terminal state $status" }
        check(status == BookingStatus.CONFIRMED) { "Only confirmed bookings can be completed" }
        return copy(status = BookingStatus.COMPLETED)
    }
}
