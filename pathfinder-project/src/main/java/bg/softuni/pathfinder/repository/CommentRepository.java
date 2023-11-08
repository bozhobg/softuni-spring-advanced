package bg.softuni.pathfinder.repository;

import bg.softuni.pathfinder.model.entity.Comment;
import bg.softuni.pathfinder.model.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByRouteAndIsApprovedIsTrue(Route route);
}
