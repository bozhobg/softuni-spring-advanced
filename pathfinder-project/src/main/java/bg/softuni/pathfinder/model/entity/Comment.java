package bg.softuni.pathfinder.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity{
    @Column(name = "approved", nullable = false)
    private Boolean isApproved;
    @Column(nullable = false)
    private LocalDateTime created;
    @Column(name = "text_content", columnDefinition = "TEXT", nullable = false)
    private String textContent;

    //    TODO: how to make author and route, columns in general indexed???
    //     How is it going to optimize retrival?
    @ManyToOne
    private User author;
    @ManyToOne
    private Route route;

    public Comment() {}

    public Boolean getApproved() {
        return isApproved;
    }

    public Comment setApproved(Boolean approved) {
        isApproved = approved;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Comment setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

    public Comment setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public Comment setAuthor(User author) {
        this.author = author;
        return this;
    }

    public Route getRoute() {
        return route;
    }

    public Comment setRoute(Route route) {
        this.route = route;
        return this;
    }
}
