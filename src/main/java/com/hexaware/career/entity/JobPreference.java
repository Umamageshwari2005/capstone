package com.hexaware.career.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "job_preferences")
public class JobPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int preferenceId;

    @Column(nullable = false)
    private String preferredRole;

    @Column(nullable = false)
    private String preferredLocation;

    private double expectedSalary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public JobPreference() {

    }

    public JobPreference(int preferenceId, String preferredRole,
            String preferredLocation, double expectedSalary, User user) {

        super();
        this.preferenceId = preferenceId;
        this.preferredRole = preferredRole;
        this.preferredLocation = preferredLocation;
        this.expectedSalary = expectedSalary;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "JobPreference [preferenceId=" + preferenceId
                + ", preferredRole=" + preferredRole
                + ", preferredLocation=" + preferredLocation
                + ", expectedSalary=" + expectedSalary + "]";
    }

}