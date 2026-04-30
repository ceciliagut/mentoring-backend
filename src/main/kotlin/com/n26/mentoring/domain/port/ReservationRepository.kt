package com.n26.mentoring.domain.port

import com.n26.mentoring.domain.model.Booking
import java.util.UUID

interface ReservationRepository {
    fun save(booking: Booking): Booking

    fun update(booking: Booking): Booking

    fun findById(id: UUID): Booking?

    fun findByMentorId(mentorId: UUID): List<Booking>
}
