package ch.supsi.webapp.web.model;

import java.util.Arrays;

public enum Status {
    OPEN("Open"),
    IN_PROGRESS("In Progress"),
    DONE("Done"),
    CLOSED("Closed");

    private final String state;

    Status(String s) {
        state = s;
    }

    public boolean equals(String otherName) {
        return state.equals(otherName);
    }

    public static Status fromValue(String value) {
        for (Status state : values()) {
            if (state.state.equalsIgnoreCase(value)) {
                return state;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
    }

    @Override
    public String toString() {
        return this.state;
    }

    public String toFormatted() {
        return this.toString().toLowerCase().replace(' ', '-');
    }
}
