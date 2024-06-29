package com.challenge.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="books")
public class Book {
    // Book attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int bookId;
    private String title;
    private String subjects;
    private String language;
    private boolean copyright;
    private int downloads;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authorBookId", referencedColumnName = "authorBookId", insertable = false, updatable = false)
    private Author author;

    // Empty initializer
    public Book(){}

    // Initializer
    public Book(BookData book_data){
        this.bookId = book_data.id();
        this.title = book_data.title();
        this.copyright = book_data.copyright();
        this.downloads = book_data.downloads();
    }

    // Show all attributes
    @Override
    public String toString() {
        return "Id='" + bookId +
                "Title='" + title + '\'' +
                ", Authors='" + author +
                ", Subjects='" + subjects +
                ", Languages='" + language + '\'' +
                ", Copyright='" + copyright + '\'' +
                ", Downloads='" + downloads + '\'';
    }

    // Setters
    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    // Getters
    public int getBookId() {
        return bookId;
    }

    public int getDownloads() {
        return downloads;
    }

    public boolean isCopyright() {
        return copyright;
    }

    public String getLanguage() {
        return language;
    }

    public String getSubjects() {
        return subjects;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }
}
