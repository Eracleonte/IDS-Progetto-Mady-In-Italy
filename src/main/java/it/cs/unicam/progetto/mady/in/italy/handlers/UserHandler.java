package it.cs.unicam.progetto.mady.in.italy.handlers;

import it.cs.unicam.progetto.mady.in.italy.dto.input.InputUserDTO;
import it.cs.unicam.progetto.mady.in.italy.dto.output.OutputUserDTO;
import it.cs.unicam.progetto.mady.in.italy.model.user.Authorization;
import it.cs.unicam.progetto.mady.in.italy.model.user.Role;
import it.cs.unicam.progetto.mady.in.italy.model.user.User;
import it.cs.unicam.progetto.mady.in.italy.repos.users.RoleRepository;
import it.cs.unicam.progetto.mady.in.italy.repos.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A Handler for Users
 */
@Service
public class UserHandler {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Autowired
    public UserHandler(UserRepository userRepository,
                       RoleRepository roleRepository) {
        if (userRepository == null)
            throw new NullPointerException("UserRepository is null");
        if (roleRepository == null)
            throw new NullPointerException("RoleRepository is null");
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * Saves a new User from the given parameters.
     * The new User will count as not approved.
     *
     * @param user the dto bearing all information necessary for the creation of the new User.
     * @param authorizations the authorizations the new User will have.
     * @return an int value representing the id of the new user if the operation was successful.
     */
    public int saveUser(InputUserDTO user, List<Authorization> authorizations) {
        checkIfUserCanBeCreated(user.username(),user.email());
        User newUser = new User(user.username(), user.email(), user.password());
        newUser.addRoles(roleRepository.findByAuthorizationIn(authorizations));
        return this.userRepository.save(newUser).getId();
    }

    /**
     * Checks if a new user can be created with the given info.
     *
     * @param username name of the new user.
     * @param email email of the new user.
     */
    private void checkIfUserCanBeCreated(String username,String email) {
        boolean isUsernameTaken = this.userRepository.findAll().stream()
                .anyMatch(u -> u.getUsername().equals(username));
        boolean isEmailTaken = this.userRepository.findAll().stream()
                .anyMatch(u -> u.getEmail().equals(email));
        if (isUsernameTaken || isEmailTaken)
            throw new IllegalStateException("User cannot be created: username or email already in use");
    }

    /**
     * Retrieves a User by its ID.
     *
     * @param id the ID of the User wished to be retrieved.
     * @throws NoSuchElementException if there isn't a User with the given id.
     * @return the desired User if existent.
     */
    public OutputUserDTO getUserById(int id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        return user.getOutputDTO();
    }

    /**
     * Retrieves all Users based on the approval status.
     *
     * @param approved is set to true if only approved Users are subject of the retrieval operation,
     *                 is set to false if only Users yet to be approved are subject of the retrieval operation.
     * @return all Users based on the approval status.
     */
    public List<OutputUserDTO> getAllUsers(boolean approved) {
        return this.userRepository.findAll()
                .stream()
                .filter(u -> u.isApproved() == approved)
                .map(User::getOutputDTO)
                .collect(Collectors.toList());
    }

    /**
     * Approves or Rejects a User.
     *
     * @param userId the ID of the User to approve or reject.
     * @param administratorId the ID of the Administrator that is performing this operation.
     * @param approvalChoice true if the User needs to be approved,
     *                       false if the User needs to be rejected.
     * @return a message in String format that will communicate the operation's result.
     */
    public String approveUser(int userId,
                              int administratorId,
                              boolean approvalChoice) {
        User administrator = checkIfApprovedUserExists(administratorId);
        checkIfUserHasRequiredAuthorization(administrator,Role.getAdministrator());
        User toApprove = checkIfUserExists(userId);
        if (!toApprove.isApproved()) {
            if (!approvalChoice) {
                this.userRepository.reject(userId);
                return "User with Id " + userId + " has been rejected";
            } else {
                this.userRepository.approve(userId);
                return "User with Id " + userId + " has been approved";
            }
        }
        else
            return "User with Id " + userId + " was already approved";
    }

    // UTILITIES

    /**
     * Checks if user has the required authorization.
     *
     * @param user the user subject to the check.
     * @param role the role bearing the authorization
     *              that the user subject to the check is required to have.
     */
    private void checkIfUserHasRequiredAuthorization(User user, Role role) {
        Optional.ofNullable(role)
                .filter(r -> user.getRoles().contains(r))
                .orElseThrow(() -> new IllegalStateException("User has no required authorization for role: " + role));
    }

    /**
     * Checks if there's a user for the given id and returns it.
     *
     * @param id the supposed user id.
     * @throws IllegalStateException if there is not a user for the given id
     *                               or the recovered user is not approved.
     * @return the user with the given id if it exists.
     */
    private User checkIfApprovedUserExists(int id) {
        User toReturn = userRepository.findById(id).orElse(null);
        if (toReturn == null)
            throw new IllegalStateException("User with Id: " + id + " not found");
        if (!toReturn.isApproved())
            throw new IllegalStateException("User with Id: " + id + " is not approved");
        else
            return toReturn;
    }

    /**
     * Checks if there's a user for the given id and returns it.
     *
     * @param id the supposed user id.
     * @throws IllegalStateException if there is not a user for the given id.
     * @return the user with the given id if it exists.
     */
    private User checkIfUserExists(int id) {
        User toReturn = userRepository.findById(id).orElse(null);
        if (toReturn == null)
            throw new IllegalStateException("User with Id: " + id + " not found");
        else
            return toReturn;
    }

}
