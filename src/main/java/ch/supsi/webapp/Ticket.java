package ch.supsi.webapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Ticket {
    private String id;
    private String title;
    private String description;
    private String author;
    private static int counter = 0;

    public Ticket() {
        this.id = ""+counter;
        this.title = "";
        this.description = "";
        this.author = "";

        counter++;
    }

    public Ticket(String title, String description, String author) {
        this.id = ""+counter;
        this.title = title;
        this.description = description;
        this.author = author;

        counter++;
    }

    public void setTicket(Ticket newTicket){
        this.title = newTicket.getTitle();
        this.description = newTicket.getDescription();
        this.author = newTicket.getAuthor();
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
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
