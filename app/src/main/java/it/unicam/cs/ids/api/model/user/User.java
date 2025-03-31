package it.unicam.cs.ids.api.model.user;

import it.unicam.cs.ids.api.abstractions.Approvable;
import it.unicam.cs.ids.api.abstractions.Visualizable;
import it.unicam.cs.ids.api.dto.output.OutputUserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User implements Visualizable, Approvable {

   private int id;

   private String username;

   private String password;

   private String email;

   private boolean approved;

   private List<Role> roles;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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

    @Override
    public boolean isApproved() {
        return approved;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setId(int id) {
        this.id = id;
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
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean addRoles(List<Role> rolesToCover) {
        return this.roles.addAll(rolesToCover);
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
