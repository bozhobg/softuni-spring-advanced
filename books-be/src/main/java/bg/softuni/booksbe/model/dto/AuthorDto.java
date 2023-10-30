package bg.softuni.booksbe.model.dto;

import java.util.UUID;

public class AuthorDto {

    private UUID id;

    private String name;

    public AuthorDto() {
    }

    public UUID getId() {
        return id;
    }

    public AuthorDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AuthorDto setName(String name) {
        this.name = name;
        return this;
    }
}
