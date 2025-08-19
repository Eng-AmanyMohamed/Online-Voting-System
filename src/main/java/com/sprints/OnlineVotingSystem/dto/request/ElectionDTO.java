package com.sprints.OnlineVotingSystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

//Create/update/delete election
public class ElectionDTO {
    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Start time is required")
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    private LocalDateTime endTime;

    @NotNull(message = "City is required")
    private String city;

    public ElectionDTO() {
    }

    public ElectionDTO(String title, LocalDateTime startTime, LocalDateTime endTime, String city) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.city = city;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public @NotNull(message = "City is required") String getCity() {
        return city;
    }

    public void setCity(@NotNull(message = "City is required") String city) {
        this.city = city;
    }
}
