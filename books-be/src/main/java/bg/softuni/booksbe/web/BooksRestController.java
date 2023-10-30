package bg.softuni.booksbe.web;

import bg.softuni.booksbe.model.dto.BookDto;
import bg.softuni.booksbe.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksRestController {

    private final BookService bookService;

    @Autowired
    public BooksRestController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    public List<BookDto> loadBooks() {

    }

}
