package com.codeup.springblog;

import javax.persistence.*;

@Entity
@Table(name="posts")   //this will create a table named posts in the adlister_db
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")  //this will create a foreign key named user_id in the post table...double check-drop tables and re-create, it may make a new Join Table
    private User user;

    //mappedBy — Defines the entity that owns the relationship which is the post entity in our case? //Usually, the child entity is one that owns the relationship and the parent entity is the inverse side of the relationship.

    //default constructor
    public Post() {
    }

    //2 constructors, one with id, one without id
    public Post(Long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
