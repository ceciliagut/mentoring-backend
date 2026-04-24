package com.n26.mentoring.domain.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import java.time.Instant

class TimeSlotTest {

    @Test
    fun `time slot requires end to be after start`() {
        assertThrows<InvalidTimeSlotException> {
            TimeSlot(
                start = Instant.parse("2026-05-01T11:00:00Z"),
                end = Instant.parse("2026-05-01T10:00:00Z"),
            )
        }
    }

    @Test
    fun `time slot with equal start and end is invalid`() {
        val time = Instant.parse("2026-05-01T10:00:00Z")
        assertThrows<InvalidTimeSlotException> {
            TimeSlot(start = time, end = time)
        }
    }

    @Test
    fun `overlapping time slots conflict`() {
        val a = TimeSlot(
            start = Instant.parse("2026-05-01T10:00:00Z"),
            end = Instant.parse("2026-05-01T11:00:00Z"),
        )
        val b = TimeSlot(
            start = Instant.parse("2026-05-01T10:30:00Z"),
            end = Instant.parse("2026-05-01T11:30:00Z"),
        )
        assertTrue(a.overlapsWith(b))
    }

    @Test
    fun `non-overlapping time slots do not conflict`() {
        val a = TimeSlot(
            start = Instant.parse("2026-05-01T10:00:00Z"),
            end = Instant.parse("2026-05-01T11:00:00Z"),
        )
        val b = TimeSlot(
            start = Instant.parse("2026-05-01T12:00:00Z"),
            end = Instant.parse("2026-05-01T13:00:00Z"),
        )
        assertFalse(a.overlapsWith(b))
    }

    @Test
    fun `back-to-back time slots do not conflict`() {
        val a = TimeSlot(
            start = Instant.parse("2026-05-01T10:00:00Z"),
            end = Instant.parse("2026-05-01T11:00:00Z"),
        )
        val b = TimeSlot(
            start = Instant.parse("2026-05-01T11:00:00Z"),
            end = Instant.parse("2026-05-01T12:00:00Z"),
        )
        assertFalse(a.overlapsWith(b))
    }

    @Test
    fun `one slot contained within another conflicts`() {
        val outer = TimeSlot(
            start = Instant.parse("2026-05-01T09:00:00Z"),
            end = Instant.parse("2026-05-01T12:00:00Z"),
        )
        val inner = TimeSlot(
            start = Instant.parse("2026-05-01T10:00:00Z"),
            end = Instant.parse("2026-05-01T11:00:00Z"),
        )
        assertTrue(outer.overlapsWith(inner))
    }
}
