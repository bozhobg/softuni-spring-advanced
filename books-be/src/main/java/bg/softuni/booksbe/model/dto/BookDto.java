package bg.softuni.booksbe.model.dto;

import bg.softuni.booksbe.model.entity.Author;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

public class BookDto {
    private UUID id;
    private String title;
    private UUID author;
    private UUID isbn;

    public BookDto(){}



}
