package bg.softuni.booksbe.init;

import bg.softuni.booksbe.model.entity.Author;
import bg.softuni.booksbe.model.entity.Book;
import bg.softuni.booksbe.repository.AuthorRepository;
import bg.softuni.booksbe.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DbInit implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public DbInit(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.bookRepository.count() > 0) {
            return;
        }

        String stringAuthor1 = "J. K. Rowling";
        Author author1 = new Author()
                .setName(stringAuthor1);

        String stringAuthor2 = "George R. R. Martin";
        Author author2 = new Author()
                .setName(stringAuthor2);

        this.authorRepository.saveAll(List.of(author1, author2));

        List<Book> books = List.of(
                new Book()
                        .setTitle("Game of Thrones")
                        .setAuthor(
                                this.authorRepository.findAuthorByName(stringAuthor2)
                                        .orElse(null)
                        )
                        .setRandomIsbn(),
                new Book()
                        .setAuthor(
                                this.authorRepository.findAuthorByName(stringAuthor1)
                                        .orElse(null)
                        )
                        .setTitle("Harry Potter")
                        .setRandomIsbn()
        );

        this.bookRepository.saveAll(books);
    }
}
