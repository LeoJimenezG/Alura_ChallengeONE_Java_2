package com.challenge.literalura.repository;

import com.challenge.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a FROM Author a WHERE a.birthyear >= :beginning AND a.deathyear <= :end")
    List<Author> authorYear(int beginning, int end);
}
