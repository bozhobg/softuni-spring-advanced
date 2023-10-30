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

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public UUID getIsbn() {
        return isbn;
    }

    public void setIsbn() {
        this.isbn = UUID.randomUUID();
    }
}
