package com.n26.mentoring.infrastructure.persistence

import com.n26.mentoring.domain.model.Booking
import com.n26.mentoring.domain.model.BookingStatus
import com.n26.mentoring.domain.model.TimeSlot
import com.n26.mentoring.domain.port.ReservationRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.UUID

@Repository
class PostgresReservationRepository(
    private val jdbc: JdbcTemplate,
) : ReservationRepository {
    override fun save(booking: Booking): Booking {
        jdbc.update(
            "INSERT INTO bookings (id, status, slot_start, slot_end, mentor_id, mentee_id) VALUES (?, ?, ?, ?, ?, ?)",
            booking.id,
            booking.status.name,
            booking.timeSlot.start.toOffsetDateTime(),
            booking.timeSlot.end.toOffsetDateTime(),
            booking.mentorId,
            booking.menteeId,
        )
        return booking
    }

    override fun update(booking: Booking): Booking {
        val updated =
            jdbc.update(
                "UPDATE bookings SET status = ?, slot_start = ?, slot_end = ?, mentor_id = ?, mentee_id = ? WHERE id = ?",
                booking.status.name,
                booking.timeSlot.start.toOffsetDateTime(),
                booking.timeSlot.end.toOffsetDateTime(),
                booking.mentorId,
                booking.menteeId,
                booking.id,
            )
        check(updated == 1) { "Booking ${booking.id} not found" }
        return booking
    }

    override fun findById(id: UUID): Booking? =
        jdbc
            .query("SELECT * FROM bookings WHERE id = ?", ::toBooking, id)
            .firstOrNull()

    override fun findByMentorId(mentorId: UUID): List<Booking> =
        jdbc.query("SELECT * FROM bookings WHERE mentor_id = ?", ::toBooking, mentorId)

    private fun Instant.toOffsetDateTime(): OffsetDateTime = OffsetDateTime.ofInstant(this, ZoneOffset.UTC)

    private fun toBooking(
        rs: ResultSet,
        @Suppress("UNUSED_PARAMETER") rowNum: Int,
    ): Booking =
        Booking(
            id = rs.getObject("id", UUID::class.java),
            status = BookingStatus.valueOf(rs.getString("status")),
            timeSlot =
                TimeSlot(
                    start = rs.getObject("slot_start", OffsetDateTime::class.java).toInstant(),
                    end = rs.getObject("slot_end", OffsetDateTime::class.java).toInstant(),
                ),
            mentorId = rs.getObject("mentor_id", UUID::class.java),
            menteeId = rs.getObject("mentee_id", UUID::class.java),
        )
}
