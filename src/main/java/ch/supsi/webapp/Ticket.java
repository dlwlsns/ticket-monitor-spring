package ch.supsi.webapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Ticket {
    private String title;
    private String description;
    private String author;

    public Ticket() {
        this.title = "";
        this.description = "";
        this.author = "";
    }

    public Ticket(String title, String description, String author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getJSON(ObjectMapper mapper) throws JsonProcessingException {
        return mapper.writeValueAsString(this);
    }
}
