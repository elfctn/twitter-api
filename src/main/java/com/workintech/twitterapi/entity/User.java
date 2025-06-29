package com.workintech.twitterapi.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data; // Lombok kütüphanesi için import
import lombok.NoArgsConstructor; // Lombok kütüphanesi için import

import java.time.LocalDateTime; // Tarih ve saat için import
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"User\"", schema = "public") // Veritabanı tablo adı ve şema
public class User {

    @Id // Birincil anahtar olduğunu belirtir
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Otomatik artan ID
    @Column(name = "id") // Kolon adı
    private Long id;

    @Column(name = "email", nullable = false, unique = true) // Boş olamaz, benzersiz
    private String email;

    @Column(name = "password", nullable = false) // Boş olamaz
    private String password;

    @Column(name = "username", nullable = false, unique = true) // Boş olamaz, benzersiz
    private String username;

    @Column(name = "created_at") // Oluşturulma tarihi kolonu
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Tweet> tweets = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "user-comments")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "user-likes")
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "user-retweets")
    private List<Retweet> retweets = new ArrayList<>();

    @PrePersist // Kaydetme işleminden önce çalışır, kaydın oluşturulma zamanı otomatik olarak ayarlan
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}






