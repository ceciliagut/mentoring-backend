package com.n26.mentoring.application.usecase

import com.n26.mentoring.domain.model.Booking
import com.n26.mentoring.domain.model.BookingStatus
import com.n26.mentoring.domain.model.TimeSlot
import com.n26.mentoring.domain.port.ReservationRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.time.Instant
import java.util.UUID

class RequestBookingTest {

    private val repository: ReservationRepository = mock()
    private val requestBooking = RequestBooking(repository)

    private val mentorId = UUID.randomUUID()
    private val menteeId = UUID.randomUUID()
    private val timeSlot = TimeSlot(
        start = Instant.parse("2026-05-01T10:00:00Z"),
        end = Instant.parse("2026-05-01T11:00:00Z"),
    )

    @Test
    fun `a valid booking request is created with REQUESTED status`() {
        whenever(repository.save(any())).thenAnswer { it.arguments[0] }

        val booking = requestBooking.execute(mentorId, menteeId, timeSlot)

        assertEquals(BookingStatus.REQUESTED, booking.status)
    }
}
