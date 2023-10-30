package bg.softuni.booksbe.service;

import bg.softuni.booksbe.model.dto.BookDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {

    List<BookDto> getAllAsBookDto();

    Optional<BookDto> getBookById(String id);

    UUID createBook(BookDto bookDto);

    boolean isIdValid(String id);

    void updateBook(BookDto dto, String id);

    void deleteBookById(String id);
}
