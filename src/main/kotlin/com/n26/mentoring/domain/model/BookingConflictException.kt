package com.n26.mentoring.domain.model

class BookingConflictException(
    message: String,
) : RuntimeException(message)
