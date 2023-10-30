package bg.softuni.booksbe.service.impl;

import bg.softuni.booksbe.model.dto.BookDto;
import bg.softuni.booksbe.repository.BookRepository;
import bg.softuni.booksbe.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BookServiceImpl(
            BookRepository bookRepository,
            ModelMapper modelMapper
    ) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookDto> getAllAsBookDto() {


        return null;
    }
}
