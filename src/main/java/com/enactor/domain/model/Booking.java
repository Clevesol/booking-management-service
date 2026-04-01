package com.enactor.domain.model;

import com.enactor.domain.enums.BookingStatusEnum;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Booking {

    private UUID id;

    private LocalDateTime createdDateTime;

    private LocalDateTime bookingDateTime;

    private List<Ticket> tickets;

    private BookingStatusEnum status;
}
