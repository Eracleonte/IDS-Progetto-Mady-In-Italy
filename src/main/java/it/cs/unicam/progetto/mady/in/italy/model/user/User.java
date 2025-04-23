package it.cs.unicam.progetto.mady.in.italy.model.user;

import it.cs.unicam.progetto.mady.in.italy.abstractions.Approvable;
import it.cs.unicam.progetto.mady.in.italy.abstractions.Visualizable;
import it.cs.unicam.progetto.mady.in.italy.dto.output.OutputUserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "app_users")
public class User implements Visualizable, Approvable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String email;

    private boolean approved;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private final List<Role> roles;

    public User(String username, String password, String email) {
        if (username == null)
            throw new NullPointerException("Null username");
        if (password == null)
            throw new NullPointerException("Null password");
        if (email == null)
            throw new NullPointerException("Null email");
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = new ArrayList<>();
    }

    public User() {
        this.roles = new ArrayList<>();
    }

    @Override
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean isApproved() {
        return approved;
    }

    @Override
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void addRoles(List<Role> rolesToCover) {
        this.roles.addAll(rolesToCover);
    }

    /**
     * Add a new role for this user instance.
     *
     * @param roleToCover the new Role the user will cover
     * @throws NullPointerException if roleToCover is null
     * @return true if the role has been added to this user instance,
     *         false if this user instance already covers this role.
     */
    public boolean addRole(Role roleToCover) {
        if (roleToCover == null)
            throw new NullPointerException("The role cannot be null");
        if (this.roles.contains(roleToCover))
            return false;
        else
            return this.roles.add(roleToCover);
    }

    @Override
    public OutputUserDTO getOutputDTO() {
        return new OutputUserDTO(this.id,
                this.username,
                this.email,
                this.getRolesAsString());
    }

    private List<String> getRolesAsString() {
        return this.roles.stream()
                .map(Role::getAuthorization)
                .map(Authorization::getValue)
                .collect(Collectors.toList());
    }

}
