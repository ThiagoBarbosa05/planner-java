package com.planner.domain.trip;

import java.time.LocalDate;

import java.util.UUID;

public class Trip {
    private UUID id;
    private UUID ownerId;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;


    private Trip(UUID id, UUID ownerId, String destination, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.ownerId = ownerId;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    public UUID getId() {
        return id;
    }

    public UUID getOwnerId() {
        return ownerId;
    }


    public String getDestination() {
        return destination;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void update(String destination, LocalDate startDate, LocalDate endDate) {
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public static Trip create(
            UUID id,
            UUID ownerId,
            String destination,
            LocalDate startDate,
            LocalDate endDate
    ) {
        UUID tripId = UUID.randomUUID();

        if(id == null) {
            id = tripId;
        }

        return new Trip(id, ownerId, destination, startDate, endDate);
    }

    public boolean isStartDateBeforeCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        if (startDate.isBefore(currentDate)) {
            return true;
        }

        return false;
    }

    public boolean isEndDateBeforeStartDate() {
        if (endDate.isBefore(startDate)) {
            return true;
        }

        return false;
    }

}
