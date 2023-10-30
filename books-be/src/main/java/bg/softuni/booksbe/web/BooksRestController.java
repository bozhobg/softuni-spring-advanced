package bg.softuni.booksbe.web;

import bg.softuni.booksbe.model.dto.BookDto;
import bg.softuni.booksbe.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/books")
public class BooksRestController {

    private final BookService bookService;

    @Autowired
    public BooksRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> loadBooks() {
        return ResponseEntity.ok(this.bookService.getAllAsBookDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> loadBook(
            @PathVariable String id
    ) {
        Optional<BookDto> optBookDto = this.bookService.getBookById(id);

        return optBookDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookDto> postBook(
            @RequestBody BookDto bookDto,
            UriComponentsBuilder ucb
    ) {
        UUID bookId = this.bookService.createBook(bookDto);

        return ResponseEntity.created(
                ucb.path("/api/books/{id}").build(bookId)
        ).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(
            @PathVariable String id,
            @RequestBody BookDto dto
    ) {
        if (!this.bookService.isIdValid(id)) return ResponseEntity.notFound().build();

        this.bookService.updateBook(dto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDto> deleteBook(
            @PathVariable(name = "id") String id
    ) {
        if (!this.bookService.isIdValid(id)) {
            return ResponseEntity.notFound().build();
        }

        this.bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }
}
