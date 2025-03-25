package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.input.InputUserDTO;
import it.unicam.cs.ids.api.handlers.UserHandler;
import it.unicam.cs.ids.api.model.user.Role;
import it.unicam.cs.ids.api.repos.users.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class UserControllerTest {

    private UserController controller;

    private InputUserDTO inputUserDTO;

    private List<Role> roleList;

    @BeforeEach
    void setUp() {

        // Setup UserController

        UserRepository userRepository = UserRepository.getInstance();
        UserHandler userHandler = new UserHandler(userRepository);
        controller = new UserController(userHandler);

        // DTO setup

        inputUserDTO = new InputUserDTO("test_username", "test_email", "test_password");

        roleList = new ArrayList<>();

        roleList.add(Role.getProducer());

    }

    @Test
    //@Disabled
    void addUser() {
        Assertions.assertEquals(0, controller.getAllUsers().size());
        Assertions.assertDoesNotThrow(() -> controller.addNewUser(inputUserDTO, roleList));
        Assertions.assertEquals(1, controller.getAllUsers().size());
        System.out.println(controller.getUserById(1));
    }

    // Issues with id handling

    @Test
    @Disabled
    void rejectUser() {
        controller.addNewUser(inputUserDTO, roleList);
        System.out.println(controller.approveUser(1, false));
        Assertions.assertEquals(0, controller.getAllUsers().size());
    }

    @Test
    @Disabled
    void approveUser() {
        controller.addNewUser(inputUserDTO, roleList);
        System.out.println(controller.approveUser(1, true));
        Assertions.assertEquals(1, controller.getAllUsers().size());
    }

}