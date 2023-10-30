package bg.softuni.booksbe.service;

import bg.softuni.booksbe.model.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> getAllAsBookDto();
}
