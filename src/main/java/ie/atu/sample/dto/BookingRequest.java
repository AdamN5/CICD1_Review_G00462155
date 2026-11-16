package ie.atu.sample.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record BookingRequest(

        @NotNull(message = "Room ID is required")
        Long roomId,

        @NotBlank(message = "Organiser name is required")
        @Size(min = 2, max = 60,
                message = "Organiser name must be 2-60 characters")
        String organiserName,

        @Positive(message = "Attendee count must be positive")
        int attendeeCount

) {}
