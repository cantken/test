package com.example.test.constants;

public enum UserType {
    ADMIN("ADMIN");

    private final String type;

    UserType(String string) {
        type = string;
    }

    @Override
    public String toString() {
        return type;
    }
}