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

/**
 * A Controller used to treat any call that is destined to Create/Read/Update/Delete
 * users in the system.
 */
@RestController
@RequestMapping("user")
public class UserController {

    private final UserHandler userHandler;

    @Autowired
    public UserController(UserHandler userHandler){
        this.userHandler = userHandler;
    }

    /**
     * Adds a new User from the given parameters.
     * The new User will count as not approved.
     *
     * @param user the dto bearing all information necessary for the creation of the new User.
     * @param authorizations the authorizations the new User will have.
     * @return a message in String format that will communicate the operation's result.
     */
    @PostMapping("/new")
    public ResponseEntity<String> addNewUser(@RequestBody InputUserDTO user,
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

    /**
     * Retrieves a User by its ID.
     *
     * @param id the ID of the User wished to be retrieved.
     * @return the desired User if existent.
     */
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

    /**
     * Retrieves all Users based on the approval status.
     *
     * @param approved is set to true if only approved Users are subject of the retrieval operation,
     *                 is set to false if only Users yet to be approved are subject of the retrieval operation.
     * @return all Users based on the approval status.
     */
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

    /**
     * Approves or Rejects a User.
     *
     * @param userId the ID of the User to approve or reject.
     * @param administratorId the ID of the Administrator that is performing this operation.
     * @param approvalChoice true if the User needs to be approved,
     *                       false if the User needs to be rejected.
     * @return a message in String format that will communicate the operation's result.
     */
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
