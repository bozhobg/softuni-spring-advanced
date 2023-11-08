package bg.softuni.pathfinder.repository;

import bg.softuni.pathfinder.model.entity.Role;
import bg.softuni.pathfinder.model.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findRoleByName(RoleName roleName);
}
