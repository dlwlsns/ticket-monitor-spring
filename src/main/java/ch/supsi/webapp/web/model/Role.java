package ch.supsi.webapp.web.model;

import java.util.Arrays;

public enum Role {
    ROLE_ADMIN("Admin"),
    ROLE_USER("User");

    private final String role;

    private Role(String r) {
        role = r;
    }

    public boolean equals(String otherName) {
        return role.equals(otherName);
    }

    public static Role fromValue(String value) {
        for (Role role : values()) {
            if (role.role.equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
    }

    public String toFormattedString() {
        return this.role;
    }
}
