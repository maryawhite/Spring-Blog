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

    @OneToOne(cascade = CascadeType.ALL) //CascadeType.ALL means to apply all cascading operations to the related entity. Cascading operations are applied when you delete or update the parent entity.
    @JoinColumn(name = "user_id")  //this will create a foreign key named user_id in the post table
    private User user;

//    @OneToOne(mappedBy = "post")
//    private User user;


    //The inverse-side of the relationship sets the @OneToOne's mappedBy parameter to indicate that the relationship is mapped by the other entity.
    //mappedBy — Defines the entity that owns the relationship which is the post entity in our case? //Usually, the child entity is one that owns the relationship and the parent entity is the inverse side of the relationship.
    //the one to one relationship can be unidirectional or bidirectional

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
