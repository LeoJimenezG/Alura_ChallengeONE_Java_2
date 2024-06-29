package com.challenge.literalura.service;

import com.challenge.literalura.model.Author;
import com.challenge.literalura.model.Book;
import com.challenge.literalura.repository.AuthorRepository;
import com.challenge.literalura.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    public void saveBookWithAuthor(Book book) {
        // Ensure the author is persisted
        Author author = book.getAuthor();
        if (author != null && author.getId() == null) {
            authorRepository.save(author);
        }
        // Save the book, which will also save the author due to CascadeType.ALL
        bookRepository.save(book);
    }

    public List<Book> searchAllBooks(){
        return bookRepository.findAll();
    }

    public List<Author> searchAllAuthors(){
        return authorRepository.findAll();
    }

    public List<Book> searchBookByLanguage(String language){
        return bookRepository.booksLanguage(language);
    }

    public Long countBooksByLanguage(String language){
        return bookRepository.countBooksLanguage(language);
    }

    public List<Author> searchAuthorByYear(int beginning, int end){
        return authorRepository.authorYear(beginning, end);
    }

    public List<Book> searchTop5Books(){
        return bookRepository.top5Books();
    }

}
