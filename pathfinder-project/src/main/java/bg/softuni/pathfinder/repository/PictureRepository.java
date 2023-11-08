package bg.softuni.pathfinder.repository;

import bg.softuni.pathfinder.model.entity.Picture;
import bg.softuni.pathfinder.model.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

     Optional<Picture> findFirstByRoute(Route route);

     List<Picture> findAllByRoute(Route route);
}
