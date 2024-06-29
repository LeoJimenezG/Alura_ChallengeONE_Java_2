package com.challenge.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name="authors")
public class Author {
    // Author attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private int authorBookId;
    private String name;
    private int birthyear;
    private int deathyear;

    // Empty initializer
    public Author(){}

    // Initializer
    public Author(int authorBookId, AuthorData author_data){
        this.authorBookId = authorBookId;
        this.name = author_data.name();
        this.birthyear = author_data.birth();
        this.deathyear = author_data.death();
    }

    // Show all attributes
    @Override
    public String toString() {
        return "Name='" + name +
                "Birth year='" + birthyear + '\'' +
                ", Death year='" + deathyear + '\'';
    }

    // Getters

    public Long getId() {
        return id;
    }

    public int getAuthorBookId(){
        return authorBookId;
    }

    public String getName() {
        return name;
    }

    public int getBirthyear() {
        return birthyear;
    }

    public int getDeathyear() {
        return deathyear;
    }
}
