package bg.softuni.pathfinder.model.entity;

import bg.softuni.pathfinder.model.entity.enums.RoleName;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{
    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private RoleName name;

    public Role() {}

    public RoleName getName() {
        return this.name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}
