package com.n26.mentoring.infrastructure.persistence

import com.n26.mentoring.PostgresTestContainer
import com.n26.mentoring.domain.model.Booking
import com.n26.mentoring.domain.model.BookingStatus
import com.n26.mentoring.domain.model.TimeSlot
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.jdbc.core.JdbcTemplate
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.UUID

class PostgresReservationRepositoryIT {
    private lateinit var repository: PostgresReservationRepository

    @BeforeEach
    fun setUp() {
        PostgresTestContainer.migrateClean()
        repository = PostgresReservationRepository(JdbcTemplate(PostgresTestContainer.dataSource()))
    }

    @Test
    fun `save stores a booking and findById returns it`() {
        val booking = aBooking()

        repository.save(booking)

        val found = repository.findById(booking.id)
        assertThat(found).isEqualTo(booking)
    }

    @Test
    fun `update changes the booking status`() {
        val booking = aBooking()
        repository.save(booking)

        val updated = booking.copy(status = BookingStatus.CONFIRMED)
        repository.update(updated)

        val found = repository.findById(booking.id)
        assertThat(found?.status).isEqualTo(BookingStatus.CONFIRMED)
    }

    @Test
    fun `findById returns null when booking does not exist`() {
        val found = repository.findById(UUID.randomUUID())
        assertThat(found).isNull()
    }

    @Test
    fun `findByMentorId returns only bookings for that mentor`() {
        val mentorId = UUID.randomUUID()
        val booking1 = aBooking(mentorId = mentorId)
        val booking2 = aBooking(mentorId = mentorId)
        val otherBooking = aBooking()

        repository.save(booking1)
        repository.save(booking2)
        repository.save(otherBooking)

        val found = repository.findByMentorId(mentorId)
        assertThat(found).containsExactlyInAnyOrder(booking1, booking2)
    }

    private fun aBooking(mentorId: UUID = UUID.randomUUID()): Booking {
        val now = Instant.now().truncatedTo(ChronoUnit.MICROS)
        return Booking(
            id = UUID.randomUUID(),
            status = BookingStatus.PENDING,
            timeSlot = TimeSlot(start = now, end = now.plusSeconds(3600)),
            mentorId = mentorId,
            menteeId = UUID.randomUUID(),
        )
    }
}
