package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.dto.input.InputUserDTO;
import it.unicam.cs.ids.api.dto.output.OutputUserDTO;
import it.unicam.cs.ids.api.model.user.Role;
import it.unicam.cs.ids.api.model.user.User;
import it.unicam.cs.ids.api.repos.users.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class UserHandler {

    private final UserRepository userRepository;

    public UserHandler(UserRepository userRepository) {
        if (userRepository == null)
            throw new NullPointerException("UserRepository is null");
        this.userRepository = userRepository;
    }

    public int saveUser(InputUserDTO user,List<Role> roles) {
        User newUser = new User(user.username(), user.email(), user.password());
        newUser.addRoles(roles);
        newUser.setId(this.userRepository.getNextId());
        return this.userRepository.save(newUser).getId();
    }

    public OutputUserDTO getUserById(int id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        return user.getOutputDTO();
    }

    public List<OutputUserDTO> getAllUsers(boolean approved) {
        return this.userRepository.findAll()
                .stream()
                .filter(u -> u.isApproved() == approved)
                .map(User::getOutputDTO)
                .collect(Collectors.toList());
    }

    public String approveUser(int id, boolean approvalChoice) {
        boolean result = this.userRepository.approve(id, approvalChoice);
        if (!result)
            return "User with Id " + id + " has been rejected";
        else
            return "User with Id " + id + " has been approved";
    }

}
