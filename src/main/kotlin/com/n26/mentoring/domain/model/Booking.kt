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
}
