package ie.atu.sample.dto;

import ie.atu.sample.model.Booking.BookingStatus;

public record BookingResponse(
        Long id,
        Long roomId,
        String roomName,
        String organiserName,
        int attendeeCount,
        BookingStatus status
) {}