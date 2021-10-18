package com.example.liquibasedemo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER_SOCIAL_NETWORK")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "first_name", length = 20)
    private String firstName;

    @Column(name = "last_name", length = 20)
    private String lastName;

    @Column(name = "age")
    private int age;

    @Column(name = "gender", length = 20)
    private String gender;

    @Column(name = "email", length = 35, unique = true)
    private String email;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST,
            CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "school_id")
    private School school;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<PostUser> postUserList;

    @OneToMany(mappedBy = "user",  cascade = CascadeType.ALL)
    private List<Friend> friends;

    @OneToMany(mappedBy = "userFor", cascade = CascadeType.ALL)
    private List<Dialog> dialogs;

    public User() {
    }

    public User(String firstName, String lastName, int age, String gender, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
    }

    public String getStringPosts() {
        StringBuilder builder = new StringBuilder();
        for (PostUser post : this.getPostUserList()) {
            builder.append(post.toString()).append("\n");
        }
        return builder.toString();
    }
//
//    /**
//     * Метод для добавления пользователя в друзья
//     *
//     * @param user пользователь
//     */
//    public void addFriendUser(User user) {
//        if (this.getFriends() == null) {
//            this.setFriends(new ArrayList<>());
//        }
//        this.getFriends().add(user);
//    }
//
//    public void deleteFriend(User user) {
//        this.getFriends().remove(user);
//    }

    public List<PostUser> getPostUserList() {
        return postUserList;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public void setPostUserList(List<PostUser> postUserList) {
        this.postUserList = postUserList;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
