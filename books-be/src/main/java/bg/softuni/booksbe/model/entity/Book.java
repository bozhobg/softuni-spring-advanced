package bg.softuni.booksbe.model.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String title;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "author_id")
    private Author author;
    @Column(nullable = false, unique = true)
    private UUID isbn;

    public Book(){}

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public Author getAuthor() {
        return author;
    }

    public Book setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public UUID getIsbn() {
        return isbn;
    }

    public Book setIsbn(UUID isbn) {
        this.isbn = isbn;
        return this;
    }

    public Book setRandomIsbn() {
        this.isbn = UUID.randomUUID();
        return this;
    }
}
