package bg.softuni.booksbe.init;

import bg.softuni.booksbe.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInit implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Autowired
    public DbInit(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.bookRepository.count() == 0) {
            
        }
    }
}
