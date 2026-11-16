package ie.atu.sample.dto;

public record ErrorResponse(
        int status,
        String message
) {}
