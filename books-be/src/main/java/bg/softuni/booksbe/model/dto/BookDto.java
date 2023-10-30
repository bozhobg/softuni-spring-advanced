package bg.softuni.booksbe.model.dto;

import bg.softuni.booksbe.model.entity.Author;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

public class BookDto {
    private UUID id;
    private String title;
    private String authorName;
    private UUID isbn;

    public BookDto(){}

    public UUID getId() {
        return id;
    }

    public BookDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public BookDto setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public UUID getIsbn() {
        return isbn;
    }

    public BookDto setIsbn(UUID isbn) {
        this.isbn = isbn;
        return this;
    }
}
