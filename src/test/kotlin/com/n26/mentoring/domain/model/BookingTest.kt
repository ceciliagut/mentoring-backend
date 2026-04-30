package com.n26.mentoring.domain.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.Instant
import java.util.UUID

class BookingTest {
    private val slot =
        TimeSlot(
            start = Instant.parse("2026-05-01T10:00:00Z"),
            end = Instant.parse("2026-05-01T11:00:00Z"),
        )

    @Test
    fun `booking is created with PENDING status`() {
        val booking =
            Booking(
                mentorId = UUID.randomUUID(),
                menteeId = UUID.randomUUID(),
                timeSlot = slot,
            )
        assertEquals(BookingStatus.PENDING, booking.status)
    }

    @Test
    fun `booking cannot be created when mentor and mentee are the same person`() {
        val id = UUID.randomUUID()
        assertThrows<IllegalArgumentException> {
            Booking(mentorId = id, menteeId = id, timeSlot = slot)
        }
    }
}
