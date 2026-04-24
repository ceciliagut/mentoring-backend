package com.n26.mentoring.infrastructure.persistence

import com.n26.mentoring.domain.model.Booking
import com.n26.mentoring.domain.port.ReservationRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class InMemoryReservationRepository : ReservationRepository {
    private val store = mutableMapOf<UUID, Booking>()

    override fun save(booking: Booking): Booking {
        store[booking.id] = booking
        return booking
    }

    override fun update(booking: Booking): Booking {
        check(store.containsKey(booking.id)) { "Booking ${booking.id} not found" }
        store[booking.id] = booking
        return booking
    }

    override fun findById(id: UUID): Booking? = store[id]

    override fun findByMentorId(mentorId: UUID): List<Booking> =
        store.values.filter { it.mentorId == mentorId }
}
