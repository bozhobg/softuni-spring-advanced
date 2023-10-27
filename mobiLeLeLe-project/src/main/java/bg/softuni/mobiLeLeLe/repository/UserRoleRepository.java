package bg.softuni.mobiLeLeLe.repository;

import bg.softuni.mobiLeLeLe.model.entity.UserRole;
import bg.softuni.mobiLeLeLe.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> getUserRoleByRole(Role role);
}
