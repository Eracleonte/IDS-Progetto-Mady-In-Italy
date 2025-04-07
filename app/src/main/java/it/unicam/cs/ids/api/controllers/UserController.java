package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.input.InputUserDTO;
import it.unicam.cs.ids.api.dto.output.OutputUserDTO;
import it.unicam.cs.ids.api.handlers.UserHandler;
import it.unicam.cs.ids.api.model.user.Role;

import java.util.List;

public class UserController {

    private final UserHandler userHandler;

    public UserController(UserHandler userHandler){
        this.userHandler = userHandler;
    }

    public int addNewUser(InputUserDTO user, List<Role> roles) {
        return this.userHandler.saveUser(user, roles);
    }

    public OutputUserDTO getUserById(int id) {
        return this.userHandler.getUserById(id);
    }

    public List<OutputUserDTO> getAllUsers() {
        return this.userHandler.getAllUsers();
    }

    public String approveUser(int id, boolean approvalChoice) {
        return this.userHandler.approveUser(id, approvalChoice);
    }

}
