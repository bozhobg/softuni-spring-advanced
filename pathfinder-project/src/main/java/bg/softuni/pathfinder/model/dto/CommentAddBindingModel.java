package bg.softuni.pathfinder.model.dto;


import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class CommentAddBindingModel {
    private Boolean isApproved;
    @Size(min = 10, message = "Message should be at least 10 characters.")
    private String message;
    private Long currentUserId;
    private Long routeId;

    public CommentAddBindingModel() {
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public CommentAddBindingModel setApproved(Boolean approved) {
        isApproved = approved;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentAddBindingModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public Long getCurrentUserId() {
        return currentUserId;
    }

    public CommentAddBindingModel setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
        return this;
    }

    public Long getRouteId() {
        return routeId;
    }

    public CommentAddBindingModel setRouteId(Long routeId) {
        this.routeId = routeId;
        return this;
    }
}
