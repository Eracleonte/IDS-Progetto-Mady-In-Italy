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

    private UserRepository userRepository;

    public UserHandler(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public int saveUser(InputUserDTO user,List<Role> roles) {
        User newUser = new User(user.username(), user.email(), user.password());
        newUser.addRoles(roles);
        return this.userRepository.save(new User(user.username(), user.email(), user.password())).getId();
    }

    public OutputUserDTO getUserById(int id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        return user.toOutputUserDTO();
    }

    public List<OutputUserDTO> getAllUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(User::toOutputUserDTO)
                .collect(Collectors.toList());
    }

}
