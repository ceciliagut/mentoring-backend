package com.n26.mentoring.domain.model

import java.time.Instant

data class TimeSlot(
    val start: Instant,
    val end: Instant,
) {
    init {
        if (end <= start) {
            throw InvalidTimeSlotException("End time must be after start time")
        }
    }

    fun overlapsWith(other: TimeSlot): Boolean = start < other.end && other.start < end
}
