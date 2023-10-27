package bg.softuni.mobiLeLeLe.model.entity;

import bg.softuni.mobiLeLeLe.model.enums.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class UserRole extends BasicEntity{
    @Column(name = "name", unique = true)
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserRole() {}

    public Role getRole() {
        return role;
    }

    public UserRole setRole(Role role) {
        this.role = role;
        return this;
    }
}
