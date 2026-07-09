package com.hexaware.career.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class JobPreferenceDTO {

    private int preferenceId;

    @NotBlank(message = "Preferred Role is required")
    private String preferredRole;

    @NotBlank(message = "Preferred Location is required")
    private String preferredLocation;

    @PositiveOrZero(message = "Expected Salary cannot be negative")
    private double expectedSalary;

    @NotNull(message = "User Id is required")
    private Integer userId;

    public JobPreferenceDTO() {

    }

    public JobPreferenceDTO(int preferenceId, String preferredRole,
            String preferredLocation, double expectedSalary,
            Integer userId) {

        super();
        this.preferenceId = preferenceId;
        this.preferredRole = preferredRole;
        this.preferredLocation = preferredLocation;
        this.expectedSalary = expectedSalary;
        this.userId = userId;
    }

    public int getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(int preferenceId) {
        this.preferenceId = preferenceId;
    }

    public String getPreferredRole() {
        return preferredRole;
    }

    public void setPreferredRole(String preferredRole) {
        this.preferredRole = preferredRole;
    }

    public String getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public double getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(double expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "JobPreferenceDTO [preferenceId=" + preferenceId
                + ", preferredRole=" + preferredRole
                + ", preferredLocation=" + preferredLocation
                + ", expectedSalary=" + expectedSalary
                + ", userId=" + userId + "]";
    }

}