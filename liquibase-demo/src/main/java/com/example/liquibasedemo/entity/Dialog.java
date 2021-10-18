package com.example.liquibasedemo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DIALOG")
public class Dialog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_for_id")
    private User userFor;

    @ManyToOne
    @JoinColumn(name = "user_to_id")
    private User userTo;

    @OneToMany(mappedBy = "dialog", cascade = CascadeType.ALL)
    private List<Message> message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserFor() {
        return userFor;
    }

    public void setUserFor(User userFor) {
        this.userFor = userFor;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }

    public List<Message> getMessage() {
        return message;
    }

    public void setMessage(List<Message> message) {
        this.message = message;
    }
}
