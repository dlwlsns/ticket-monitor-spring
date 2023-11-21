package ch.supsi.webapp.web.model;

import org.apache.commons.lang3.RandomStringUtils;

public class Ticket {
    private String id;
    private String title;
    private String description;
    private String author;

    public Ticket() {
        this.id = RandomStringUtils.randomAlphanumeric(8);
        this.title = "";
        this.description = "";
        this.author = "";
    }

    public Ticket(String title, String description, String author) {
        this.id = RandomStringUtils.randomAlphanumeric(8);;
        this.title = title;
        this.description = description;
        this.author = author;
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
}
