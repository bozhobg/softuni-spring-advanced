package bg.softuni.booksbe.service.impl;

import bg.softuni.booksbe.model.dto.BookDto;
import bg.softuni.booksbe.model.entity.Author;
import bg.softuni.booksbe.model.entity.Book;
import bg.softuni.booksbe.repository.AuthorRepository;
import bg.softuni.booksbe.repository.BookRepository;
import bg.softuni.booksbe.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookDto> getAllAsBookDto() {

        return this.bookRepository.findAll()
                .stream()
                .map(e -> modelMapper.map(e, BookDto.class))
                .toList();
    }

    @Override
    public Optional<BookDto> getBookById(String id) {
        return this.bookRepository.findById(UUID.fromString(id))
                .map(e -> modelMapper.map(e, BookDto.class));
    }

    @Override
    public UUID createBook(BookDto bookDto) {
        Book entityBook = modelMapper.map(bookDto, Book.class);

        return this.bookRepository.save(
                entityBook.setAuthor(getCreateAuthor(bookDto.getAuthorName())))
                .getId();
    }

    @Override
    public boolean isIdValid(String id) {
        return this.bookRepository.existsById(UUID.fromString(id));
    }

    @Override
    public void updateBook(BookDto dto, String id) {
//        TODO: cases changed author, changed isbn, changed title
        Book bookEntity = modelMapper.map(dto, Book.class);
        bookEntity.setAuthor(getCreateAuthor(dto.getAuthorName()))
                .setId(UUID.fromString(id));

        this.bookRepository.save(bookEntity);
    }

    @Override
    public void deleteBookById(String id) {
        this.bookRepository.deleteById(UUID.fromString(id));
    }

    private Author getCreateAuthor(String authorName) {
        return this.authorRepository.findAuthorByName(authorName)
                .orElseGet(() -> this.authorRepository.save(
                        new Author().setName(authorName)));
    }
}
