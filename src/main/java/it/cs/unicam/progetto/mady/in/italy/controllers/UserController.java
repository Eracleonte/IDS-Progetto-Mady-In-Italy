package it.cs.unicam.progetto.mady.in.italy.controllers;

import it.cs.unicam.progetto.mady.in.italy.dto.input.InputUserDTO;
import it.cs.unicam.progetto.mady.in.italy.handlers.UserHandler;
import it.cs.unicam.progetto.mady.in.italy.model.user.Authorization;
import it.cs.unicam.progetto.mady.in.italy.model.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserHandler userHandler;

    @Autowired
    public UserController(UserHandler userHandler){
        this.userHandler = userHandler;
    }

    @PostMapping("/new")
    public ResponseEntity<Object> addNewUser(@RequestBody InputUserDTO user,
                                             @RequestParam List<Authorization> authorizations) {
        try {
            int id = userHandler.saveUser(user,authorizations);
            return new ResponseEntity<>("User saved with id: " + id, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable("userId") int id) {
        try {
            return new ResponseEntity<>(userHandler.getUserById(id), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/all")
    public ResponseEntity<Object> getAllUsers(@RequestParam boolean approved) {
        try {
            return new ResponseEntity<>(userHandler.getAllUsers(approved), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/approval/{userId}/by-admin/{adminId}")
    public ResponseEntity<String> approveUser(@PathVariable("userId") int userId,
                                              @PathVariable("adminId") int administratorId,
                                              @RequestParam boolean approvalChoice) {
        try {
            return new ResponseEntity<>(userHandler.approveUser(userId, administratorId, approvalChoice), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
