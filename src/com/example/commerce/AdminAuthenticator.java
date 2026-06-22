package com.example.commerce;

public class AdminAuthenticator {
    private static final String ADMIN_PASSWORD = "admin123";

    public boolean authenticate(String password) {
        return ADMIN_PASSWORD.equals(password);
    }
}
