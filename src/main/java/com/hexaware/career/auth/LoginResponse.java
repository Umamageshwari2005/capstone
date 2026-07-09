package com.hexaware.career.auth;

public class LoginResponse {

    private String token;

    private String role;

    private int userId;

    private String fullName;

    public LoginResponse() {

    }

    public LoginResponse(String token,
                         String role,
                         int userId,
                         String fullName) {

        this.token = token;
        this.role = role;
        this.userId = userId;
        this.fullName = fullName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}