package ch.supsi.webapp.web.model;

import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private String id;
    private String name;
    private String surname;
    private String username;
    private String salt;
    private String hash_psw;

    public User(){
        this.id = RandomStringUtils.randomAlphanumeric(8);
        this.salt = RandomStringUtils.randomAlphanumeric(20);
        this.hash_psw = RandomStringUtils.randomAlphanumeric(20);

        this.name = "";
        this.surname = surname = "";
        this.username = username = "";
    }

    public User(String name, String surname, String username) {
        this.id = RandomStringUtils.randomAlphanumeric(8);
        this.salt = RandomStringUtils.randomAlphanumeric(20);
        this.hash_psw = RandomStringUtils.randomAlphanumeric(20);
        //this.id = RandomStringUtils.randomAlphanumeric(8);
        this.name = name;
        this.surname = surname;
        this.username = username;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String getSalt() {
        return this.salt;
    }

    private String getHash_psw() {
        return this.hash_psw;
    }

    private void setHash_psw(String hash_psw) {
        this.hash_psw = hash_psw;
    }
}
