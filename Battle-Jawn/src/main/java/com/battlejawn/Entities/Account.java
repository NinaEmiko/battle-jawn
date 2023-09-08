package com.battlejawn.Entities;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private ArrayList<Character> characters;
    private int maxCharacters;
    private Date creationDate;

    public Account(Long id, String username, String password, ArrayList<Character> characters, int maxCharacters, Date creationDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.characters = characters;
        this.maxCharacters = maxCharacters;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public ArrayList<Character> getCharacters() {
        return characters;
    }
    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }
    public int getMaxCharacters() {
        return maxCharacters;
    }
    public void setMaxCharacters(int maxCharacters) {
        this.maxCharacters = maxCharacters;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
}
