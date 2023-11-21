package ch.supsi.webapp.web.model;

import java.util.Arrays;

public enum Type {
    TASK("Task"),
    STORY("Story"),
    ISSUE("Issue"),
    BUG("Bug"),
    INVESTIGATION("Investigation");

    private final String type;

    private Type(String s) {
        type = s;
    }

    public boolean equals(String otherName) {
        return type.equals(otherName);
    }

    public static Type fromValue(String value) {
        for (Type type : values()) {
            if (type.type.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
    }

    @Override
    public String toString() {
        return this.type;
    }
}
