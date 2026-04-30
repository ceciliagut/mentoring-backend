package com.n26.mentoring.domain.model

enum class BookingStatus {
    REQUESTED,
    PENDING,
    CONFIRMED,
    CANCELLED,
    EXPIRED,
    COMPLETED,
    ;

    val isTerminal: Boolean get() = this in setOf(CANCELLED, EXPIRED, COMPLETED)
}
