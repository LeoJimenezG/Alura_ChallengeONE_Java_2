package com.challenge.literalura.repository;

import com.challenge.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.language ILIKE %:lang%")
    List<Book> booksLanguage(String lang);

    @Query("SELECT COUNT(b) FROM Book b WHERE b.language ILIKE %:lang%")
    Long countBooksLanguage(String lang);

    @Query("SELECT b FROM Book b ORDER BY b.downloads DESC LIMIT 5")
    List<Book> top5Books();
}
