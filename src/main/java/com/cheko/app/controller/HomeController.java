package com.cheko.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/")
@Tag(name = "Home", description = "Home Controller for basic operations")
public class HomeController {

    @GetMapping
    @Operation(summary = "Welcome message", description = "Returns a welcome message for Cheko application")
    @ApiResponse(responseCode = "200", description = "Successful response")
    public String home() {
        return "Welcome to Cheko Application!";
    }

    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Returns application health status")
    @ApiResponse(responseCode = "200", description = "Application is healthy")
    public String health() {
        return "Cheko App is running successfully!";
    }
}
