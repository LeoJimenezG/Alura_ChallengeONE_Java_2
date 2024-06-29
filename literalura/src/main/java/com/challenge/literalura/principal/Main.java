package com.challenge.literalura.principal;

import com.challenge.literalura.model.Author;
import com.challenge.literalura.model.Book;
import com.challenge.literalura.model.ResponseAPI;
import com.challenge.literalura.model.BookData;
import com.challenge.literalura.service.BookService;
import com.challenge.literalura.service.GetAPIData;
import com.challenge.literalura.service.TransformData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Component
public class Main{
    // Variables to use
    private final GetAPIData getAPIData = new GetAPIData();
    private final TransformData transformData = new TransformData();
    private final Scanner input = new Scanner(System.in);
    private final BookService bookService;
    private final Random random = new Random();
    private final String endpoint = "https://gutendex.com/books/";

    @Autowired
    public Main(BookService bookService) {
        this.bookService = bookService;
    }

    public void showOptions(){
        int option = -1;
        // Main loop
        while (option != 0){
            // Show all the options
            var options = """
                    \n
                    1 - Find book by title
                    2 - Find book by id
                    3 - Show all books
                    4 - Show all authors
                    5 - Show authors by year
                    6 - Show books by a language
                    7 - Show top 5 books
                    0 - Exit
                    """;
            System.out.println(options);
            try {
                option = input.nextInt();
            } catch (Exception e) {
                option = -1;
            } finally {
                input.nextLine();
            }
            // Execute the option
            switch (option) {
                case 1:
                    System.out.println("Write the book title: ");
                    String book_title = input.nextLine().toLowerCase();
                    findByTitle(book_title);
                    break;
                case 2:
                    System.out.println("Write the book id: ");
                    String book_id = input.nextLine();
                    findById(book_id);
                    break;
                case 3:
                    showBooks();
                    break;
                case 4:
                    showAuthors();
                    break;
                case 5:
                    System.out.println("Write the year(ej. '1850-1905'): ");
                    String author_year = input.nextLine().toLowerCase();
                    showAuthorsByYear(author_year);
                    break;
                case 6:
                    System.out.println("Write the book language(ej. 'en'): ");
                    String book_language = input.nextLine().toLowerCase();
                    showBooksByLanguage(book_language);
                    break;
                case 7:
                    showTop5Books();
                    break;
                case 0:
                    System.out.println("Shutting down...");
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }

    }

    private void findByTitle(String title) {
        // Get the json from the API
        String json = getAPIData.getData(endpoint + "?search=" + title.replace(" ", "%20"));
        // Map the json into a List of BookData objects
        List<BookData> result = transformData.transformData(json, ResponseAPI.class).results();
        try {
            int random_index = random.nextInt(result.size());
            // Get the Author object
            Author author = new Author(result.get(random_index).id(), result.get(random_index).author().getFirst());
            // Get the Book object
            Book book_by_title = new Book(result.get(random_index));
            // Set attributes to the Book
            book_by_title.setAuthor(author);
            book_by_title.setLanguage(result.get(random_index).langs().getFirst());
            book_by_title.setSubjects(result.get(random_index).subjects().getFirst());
            // Save the books in the DB
            bookService.saveBookWithAuthor(book_by_title);
            // Show the results
            System.out.println("Book(s) by title:\n" + book_by_title);
        } catch (Exception e) {
            System.out.println("Book not found");
        }
    }

    private void findById(String bookId) {
        // Get the json from the API
        String json = getAPIData.getData(endpoint + bookId);
        // Map the json into a BookData object
        BookData result = transformData.transformData(json, BookData.class);
        try {
            // Get the Author object
            Author author = new Author(result.id(), result.author().getFirst());
            // Get the Book object
            Book book_by_id = new Book(result);
            // Set attributes to the Book
            book_by_id.setAuthor(author);
            book_by_id.setLanguage(result.langs().getFirst());
            book_by_id.setSubjects(result.subjects().getFirst());
            // Save the book in the DB
            bookService.saveBookWithAuthor(book_by_id);
            // Show the result
            System.out.println("Book by id:\n" + book_by_id);
        } catch (Exception e) {
            System.out.println("Book not found");
        }
    }

    private void showBooks() {
        // Get all books from the DB
        List<Book> books = bookService.searchAllBooks();
        System.out.println("All searched books:");
        books.forEach(System.out::println);
    }

    private void showAuthors() {
        // Get all authors from the DB
        List<Author> authors = bookService.searchAllAuthors();
        System.out.println("All searched authors:");
        authors.forEach(System.out::println);
    }

    private void showAuthorsByYear(String author_year) {
        // Get the authors where their birth and death year are in a certain range from the DB
        String[] years = author_year.replace(" ", "").split("-");
        if (years.length == 2){
            if (Integer.parseInt(years[0]) < Integer.parseInt(years[1])){
                List<Author> authorsByYear = bookService.
                        searchAuthorByYear(Integer.parseInt(years[0]), Integer.parseInt(years[1]));
                System.out.println("Authors from '" + author_year + "': ");
                authorsByYear.forEach(System.out::println);
            }
            else {
                System.out.println("Write a valid input");
            }
        }
        else {
            System.out.println("Write a valid input");
        }
    }

    private void showBooksByLanguage(String language) {
        // Get the books where language contains the specified language from the DB
        if (language.length() == 2){
            List<Book> booksByLanguage = bookService.searchBookByLanguage(language);
            Long totalBooks = bookService.countBooksByLanguage(language);
            System.out.println("Books in '" + language + "': ");
            System.out.println("Total of books -> " + totalBooks);
            booksByLanguage.forEach(System.out::println);
        }
        else {
            System.out.println("Write a valid input");
        }

    }

    private void showTop5Books() {
        // Get the books from the DB
        List<Book> topBooks = bookService.searchTop5Books();
        System.out.println("Top 5 downloaded books: ");
        topBooks.forEach(System.out::println);
    }
}
