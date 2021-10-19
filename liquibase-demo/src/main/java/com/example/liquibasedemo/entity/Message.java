package com.example.liquibasedemo.entity;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGE")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "content", length = 2000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_for_id")
    private User userFor;

    @ManyToOne
    @JoinColumn(name = "dialog_id")
    private Dialog dialog;

    public Message(String content, User userFor, Dialog dialog) {
        this.content = content;
        this.userFor = userFor;
        this.dialog = dialog;
    }

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUserFor() {
        return userFor;
    }

    public void setUserFor(User userFor) {
        this.userFor = userFor;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }
}
