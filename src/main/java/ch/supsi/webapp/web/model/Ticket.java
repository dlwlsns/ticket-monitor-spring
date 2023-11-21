package ch.supsi.webapp.web.model;

import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;

@Entity
public class Ticket {
    @Id
    private String id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Ticket() {
        this.id = RandomStringUtils.randomAlphanumeric(8);
        this.title = "";
        this.description = "";
        this.author = null;
        this.status = Status.OPEN;
    }

    public Ticket(String title, String description, User author, Status status) {
        this.id = RandomStringUtils.randomAlphanumeric(8);
        this.title = title;
        this.description = description;
        this.author = author;
        this.status = status;
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

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
