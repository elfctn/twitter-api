package com.workintech.twitterapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tweet", schema = "public") // Tweet, rezerve bir kelime olmadığı için tırnak işareti gerekmez.
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 280, message = "Tweet içeriği en fazla 280 karakter olabilir.")
    @Column(name = "content", nullable = false, length = 280)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference //json'a dahil EDILMEZ
    private User user;

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "tweet-comments")//json'a dahil edilir
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "tweet-likes")//json'a dahil edilir
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "tweet-retweets") //json'a dahil edilir
    private List<Retweet> retweets = new ArrayList<>();

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    //@CreationTimestamp
    //private LocalDateTime createdAt;
    //
    //@UpdateTimestamp
    //private LocalDateTime updatedAt;
}