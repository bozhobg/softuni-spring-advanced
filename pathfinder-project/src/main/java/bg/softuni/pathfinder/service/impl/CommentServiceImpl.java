package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.exceptions.IllegalEntityException;
import bg.softuni.pathfinder.model.dto.CommentAddBindingModel;
import bg.softuni.pathfinder.model.entity.Comment;
import bg.softuni.pathfinder.repository.CommentRepository;
import bg.softuni.pathfinder.repository.RouteRepository;
import bg.softuni.pathfinder.repository.UserRepository;
import bg.softuni.pathfinder.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(
            CommentRepository commentRepository,
            UserRepository userRepository,
            RouteRepository routeRepository,
            ModelMapper modelMapper
    ) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
    }

//    TODO: save/post comment, approve comment (who and how?), load comments for a route, author, etc

    @Override
    public void addComment(CommentAddBindingModel commentDto) {
        Comment entity = new Comment()
                .setTextContent(commentDto.getMessage())
                .setRoute(
                        this.routeRepository.findById(commentDto.getRouteId())
                                .orElseThrow(() -> new IllegalEntityException("Invalid route!"))
                )
                .setAuthor(
                        this.userRepository.findById(commentDto.getCurrentUserId())
                                .orElseThrow(() -> new IllegalEntityException("Invalid user!"))
                )
                .setCreated(LocalDateTime.now())
//                TODO: additional logic when approved
                .setApproved(commentDto.getApproved());

        this.commentRepository.save(entity);
    }
}
