package ch.supsi.webapp.web.model;

public enum Status {
    OPEN("Open"),
    IN_PROGRESS("In Progress"),
    DONE("Done"),
    CLOSED("Closed");

    private final String state;

    private Status(String s) {
        state = s;
    }

    public boolean equals(String otherName) {
        return state.equals(otherName);
    }

    @Override
    public String toString() {
        return this.state;
    }
}
