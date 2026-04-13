package com.n26.mentoring.domain.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.Instant
import org.junit.jupiter.api.Assertions.assertEquals
import java.util.UUID

class BookingTest {

    private val slot = TimeSlot(
        start = Instant.parse("2026-05-01T10:00:00Z"),
        end = Instant.parse("2026-05-01T11:00:00Z"),
    )

    private fun booking() = Booking(
        mentorId = UUID.randomUUID(),
        menteeId = UUID.randomUUID(),
        timeSlot = slot,
    )

    // --- creation ---

    @Test
    fun `booking is created with PENDING status`() {
        assertEquals(BookingStatus.PENDING, booking().status)
    }

    @Test
    fun `booking cannot be created when mentor and mentee are the same person`() {
        val id = UUID.randomUUID()
        assertThrows<IllegalArgumentException> {
            Booking(mentorId = id, menteeId = id, timeSlot = slot)
        }
    }

    // --- confirm ---

    @Test
    fun `pending booking can be confirmed`() {
        assertEquals(BookingStatus.CONFIRMED, booking().confirm().status)
    }

    @Test
    fun `confirmed booking cannot be confirmed again`() {
        assertThrows<IllegalStateException> {
            booking().confirm().confirm()
        }
    }

    @Test
    fun `cancelled booking cannot be confirmed`() {
        assertThrows<IllegalStateException> {
            booking().cancel().confirm()
        }
    }

    // --- cancel ---

    @Test
    fun `pending booking can be cancelled`() {
        assertEquals(BookingStatus.CANCELLED, booking().cancel().status)
    }

    @Test
    fun `confirmed booking can be cancelled`() {
        assertEquals(BookingStatus.CANCELLED, booking().confirm().cancel().status)
    }

    @Test
    fun `completed booking cannot be cancelled`() {
        assertThrows<IllegalStateException> {
            booking().confirm().complete().cancel()
        }
    }

    // --- expire ---

    @Test
    fun `pending booking can expire`() {
        assertEquals(BookingStatus.EXPIRED, booking().expire().status)
    }

    @Test
    fun `confirmed booking cannot expire`() {
        assertThrows<IllegalStateException> {
            booking().confirm().expire()
        }
    }

    @Test
    fun `cancelled booking cannot expire`() {
        assertThrows<IllegalStateException> {
            booking().cancel().expire()
        }
    }

    // --- complete ---

    @Test
    fun `confirmed booking can be completed`() {
        assertEquals(BookingStatus.COMPLETED, booking().confirm().complete().status)
    }

    @Test
    fun `pending booking cannot be completed`() {
        assertThrows<IllegalStateException> {
            booking().complete()
        }
    }

    @Test
    fun `cancelled booking cannot be completed`() {
        assertThrows<IllegalStateException> {
            booking().cancel().complete()
        }
    }
}
