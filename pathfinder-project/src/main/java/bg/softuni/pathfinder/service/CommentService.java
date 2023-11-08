package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.CommentAddBindingModel;

public interface CommentService {
    void addComment(CommentAddBindingModel commentDto);
}
