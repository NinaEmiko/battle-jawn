package com.battlejawn.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    @OneToMany(mappedBy = "account")
    private List<Hero> heroList = new ArrayList<>();
    @Column
    private LocalDateTime creationDate;

    public Account() {
    
    }

    public Account(Long id, String username, String password, ArrayList<Hero> heroList, LocalDateTime creationDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.heroList = heroList;
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
    public List<Hero> getHeroList() {
        return heroList;
    }
    public void setHeroList(ArrayList<Hero> heroList) {
        this.heroList = heroList;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    
}
