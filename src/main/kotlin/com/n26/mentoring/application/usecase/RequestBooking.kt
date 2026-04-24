package com.n26.mentoring.application.usecase

import com.n26.mentoring.domain.model.Booking
import com.n26.mentoring.domain.model.BookingConflictException
import com.n26.mentoring.domain.model.BookingStatus
import com.n26.mentoring.domain.model.TimeSlot
import com.n26.mentoring.domain.port.ReservationRepository
import java.util.UUID

class RequestBooking(private val repository: ReservationRepository) {

    fun execute(
        mentorId: UUID,
        menteeId: UUID,
        timeSlot: TimeSlot,
    ): Booking {
        val hasConflict = repository.findByMentorId(mentorId)
            .any { it.timeSlot.overlapsWith(timeSlot) }

        if (hasConflict) throw BookingConflictException("Mentor already has a booking that overlaps with the requested time slot")

        val booking = Booking(
            mentorId = mentorId,
            menteeId = menteeId,
            timeSlot = timeSlot,
            status = BookingStatus.REQUESTED,
        )
        return repository.save(booking)
    }
}
