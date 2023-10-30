package bg.softuni.booksbe.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "authors")
public class Author extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;

//    TODO: fetch type etc... check diff approaches shown by tutor to avoid subselects when querying DB
    @OneToMany(mappedBy = "author")
    private Set<Book> books;

    public Author() {
        this.books = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Author setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
