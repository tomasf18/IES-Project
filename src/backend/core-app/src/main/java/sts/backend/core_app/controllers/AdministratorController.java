package sts.backend.core_app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sts.backend.core_app.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;

import sts.backend.core_app.services.business.AdministratorService;

@RestController
@RequestMapping("/api/v1")
public class AdministratorController {
    
    private final AdministratorService administratorService;

    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @GetMapping("/last-minute-logs")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> api_get_logs() throws ResourceNotFoundException {
        System.out.println("GET /api/v1/logs");
        try {
            return new ResponseEntity<>(administratorService.getLogs(), HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error fetching logs: " + e.getMessage());
            return new ResponseEntity<>("Error fetching logs", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/administrator/sensors-team-weak")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> api_get_sensors() throws ResourceNotFoundException {
        System.out.println("GET /api/v1/administrator/sensors");
        try {
            return new ResponseEntity<>(administratorService.getSensors(), HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error fetching sensors: " + e.getMessage());
            return new ResponseEntity<>("Error fetching sensors", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/administrator/sensors-last-5-days")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> api_get_sensors_last_5_days() throws ResourceNotFoundException {
        System.out.println("GET /api/v1/administrator/sensors-last-5-days");
        try {
            return new ResponseEntity<>(administratorService.getSensorsLast5Days(), HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error fetching sensors: " + e.getMessage());
            return new ResponseEntity<>("Error fetching sensors", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}