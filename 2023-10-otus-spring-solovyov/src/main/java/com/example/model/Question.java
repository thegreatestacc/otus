package com.example.model;

public record Question(int id, String text) {
    @Override
    public String toString() {
        return String.format("%s - %s", id, text);
    }
}
