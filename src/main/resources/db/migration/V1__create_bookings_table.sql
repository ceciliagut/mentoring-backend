CREATE TABLE bookings (
    id         UUID        NOT NULL,
    status     TEXT        NOT NULL,
    slot_start TIMESTAMPTZ NOT NULL,
    slot_end   TIMESTAMPTZ NOT NULL,
    mentor_id  UUID        NOT NULL,
    mentee_id  UUID        NOT NULL,
    CONSTRAINT pk_bookings PRIMARY KEY (id)
);
