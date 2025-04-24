package it.cs.unicam.progetto.mady.in.italy.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * Represents a Role
 */
@Entity
@NoArgsConstructor(force = true)
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_value", columnDefinition = "VARCHAR(50)")
    private Authorization authorization;

    public Role() {
    }

    private Role(Authorization authorization) {
        this.authorization = authorization;
    }

    public static Role getProducer() {
        return new Role(Authorization.PRODUCER);
    }

    public static Role getTransformer() {
        return new Role(Authorization.TRANSFORMER);
    }

    public static Role getDistributor() {
        return new Role(Authorization.DISTRIBUTOR);
    }

    public static Role getCurator() {
        return new Role(Authorization.CURATOR);
    }

    public static Role getGuest() {
        return new Role(Authorization.GUEST);
    }

    public static Role getBuyer() {
        return new Role(Authorization.BUYER);
    }

    public static Role getAdministrator() {
        return new Role(Authorization.ADMINISTRATOR);
    }

    public static Role fromAuthorization(Authorization authorization) {
        return new Role(authorization);
    }

    public Authorization getAuthorization() {
        return authorization;
    }

    // Utility
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return authorization == role.authorization;
    }

    // Required due to equals
    @Override
    public int hashCode() {
        return Objects.hash(id, authorization);
    }

}
