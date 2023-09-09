package com.battlejawn.Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Transient
    private String password;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Character> characters = new ArrayList<>();
    @Column
    private Date creationDate;

    public Account() {
    
    }

    public Account(Long id, String username, String password, ArrayList<Character> characters, Date creationDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.characters = characters;
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
    public List<Character> getCharacters() {
        return characters;
    }
    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
}
