package com.cheko.app.dto;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Schema(description = "Error details for API responses")
public class ErrorDetailsDto {

    @Schema(description = "Timestamp when error occurred", example = "2023-12-01T10:15:30.000+00:00")
    private Date timestamp;

    @Schema(description = "HTTP status code", example = "400")
    private int status;

    @Schema(description = "HTTP status message", example = "Bad Request")
    private String error;

    @Schema(description = "Error message", example = "Invalid input provided")
    private String message;

    public ErrorDetailsDto(HttpStatus status, String message) {
        this.timestamp = new Date();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
    }

}
