package it.cs.unicam.progetto.mady.in.italy.controllers;

import it.cs.unicam.progetto.mady.in.italy.dto.input.InputSupplyChainPointDTO;
import it.cs.unicam.progetto.mady.in.italy.dto.input.SupplyChainPointSearchFilterDTO;
import it.cs.unicam.progetto.mady.in.italy.handlers.SupplyChainPointHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A Controller used to treat any call that is destined to Create/Read/Update/Delete
 * SupplyChainPoints and SupplyChainPointManagement in the system.
 */
@RestController
@RequestMapping("supply-chain-point")
public class SupplyChainPointController {

    private final SupplyChainPointHandler supplyChainPointHandler;

    @Autowired
    public SupplyChainPointController(SupplyChainPointHandler supplyChainPointHandler) {
        if (supplyChainPointHandler == null)
            throw new NullPointerException("Cannot create a SupplyChainController due to supplyChainPointHandler being null");
        this.supplyChainPointHandler = supplyChainPointHandler;
    }

    // Supply Chain Point operations

    /**
     * Adds a new SupplyChainPoint from the given parameters.
     * The new SupplyChainPoint will result as managed by the User creating it and will count as not approved.
     * <p>
     * Note: The generation of a new SupplyChainPoint by a User will result in the subsequent generation of a
     *       SupplyChainPointManagement element.
     *
     * @param inputSupplyChainPointDTO the dto bearing all information necessary
     *                                 for the creation of the new SupplyChainPoint.
     * @param userId the ID of the User that is creating the new SupplyChainPoint.
     * @return a message in String format that will communicate the operation's result.
     */
    @PostMapping("/new/supply-chain-point/by/{userId}")
    public ResponseEntity<String> addSupplyChainPoint(@RequestBody InputSupplyChainPointDTO inputSupplyChainPointDTO,
                                                      @PathVariable("userId") int userId) {
        try {
            int id = supplyChainPointHandler.saveSupplyChainPoint(inputSupplyChainPointDTO,userId);
            return new ResponseEntity<>("Supply Chain Point saved with id: " + id, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a SupplyChainPoint by its ID.
     *
     * @param id the ID of the SupplyChainPoint wished to be retrieved.
     * @return the desired SupplyChainPoint if existent.
     */
    @GetMapping("/search/supply-chain-point/{supplyChainPointId}")
    public ResponseEntity<Object> getSupplyChainPointById(@PathVariable("supplyChainPointId") int id) {
        try {
            return new ResponseEntity<>(supplyChainPointHandler.findSupplyChainPointById(id), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves all SupplyChainPoints based on the approval status.
     *
     * @param approved is set to true if only approved SupplyChainPoints are subject of the retrieval operation,
     *                 is set to false if only SupplyChainPoints yet to be approved are subject of the retrieval operation.
     * @return all SupplyChainPoints based on the approval status.
     */
    @GetMapping("/search/supply-chain-point/all")
    public ResponseEntity<Object> getAllSupplyChainPoints(@RequestParam boolean approved) {
        try {
            return new ResponseEntity<>(supplyChainPointHandler.findAllSupplyChainPoints(approved), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves all SupplyChainPoints that match the given SupplyChainPointSearchFilter.
     *
     * @param filter the SupplyChainPointSearchFilter to apply for this retrieval operation.
     * @param approved equals true if only approved SupplyChainPoints are supposed to be gathered.
     * @return all SupplyChainPoints that match the SupplyChainPointSearchFilter if present.
     */
    @PostMapping("/search/supply-chain-point/filtered")
    public ResponseEntity<Object> getAllSupplyChainPointsFiltered(@RequestBody SupplyChainPointSearchFilterDTO filter,
                                                                  @RequestParam boolean approved) {
        try {
            return new ResponseEntity<>(supplyChainPointHandler.findAllSupplyChainPointsFiltered(filter, approved), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Approves or Rejects a SupplyChainPoint.
     *
     * @param id the ID of the SupplyChainPoint to approve or reject.
     * @param curatorId the ID of the Curator that is performing this operation.
     * @param approvalChoice true if the SupplyChainPoint needs to be approved,
     *                       false if the SupplyChainPoint needs to be rejected.
     * @return a message in String format that will communicate the operation's result.
     */
    @PatchMapping("/approval/supply-chain-point/{supplyChainPointId}/by-curator/{curatorId}")
    public ResponseEntity<String> approveSupplyChainPoint(@PathVariable("supplyChainPointId") int id,
                                                          @PathVariable("curatorId") int curatorId,
                                                          @RequestParam boolean approvalChoice) {
        try {
            return new ResponseEntity<>(supplyChainPointHandler.approveSupplyChainPoint(id, curatorId, approvalChoice), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Supply Chain Point Management operations

    /**
     * Adds a new SupplyChainPointManagement from the given parameters.
     * The new SupplyChainPointManagement will result as not approved.
     *
     * @param supplyChainPointId the ID of the SupplyChainPoint the User wishes to manage.
     * @param userId the ID of the User that is creating the new SupplyChainPointManagement.
     * @return a message in String format that will communicate the operation's result.
     */
    @PostMapping("/new/management/of/{supplyChainPointId}/by/{userId}")
    public ResponseEntity<String> addSupplyChainPointManagement(@PathVariable("supplyChainPointId") int supplyChainPointId,
                                                                @PathVariable("userId") int userId) {
        try {
            int id = supplyChainPointHandler.saveSupplyChainPointManagement(supplyChainPointId,userId);
            return new ResponseEntity<>("Supply Chain Point Management saved with id: " + id,HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves all SupplyChainPointManagement of a certain User based on the approval status.
     *
     * @param userId the ID of the User managing the SupplyChainPoints.
     * @param approved is set to true if only approved SupplyChainPointManagement are subject of the retrieval operation,
     *                 is set to false if only SupplyChainPointManagement yet to be approved are subject of the retrieval operation.
     * @return all SupplyChainPointManagement of a certain User based on the approval status.
     */
    @GetMapping("/search/management/by/user/{userId}")
    public ResponseEntity<Object> getAllSupplyChainPointManagementByUserId(@PathVariable("userId") int userId,
                                                                           @RequestParam boolean approved) {
        try {
            return new ResponseEntity<>(supplyChainPointHandler.findAllSupplyChainPointManagementByUserId(userId, approved), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves all SupplyChainPointManagement of a certain SupplyChainPoint.
     *
     * @param supplyChainPointId the ID of the SupplyChainPoint being managed.
     * @return all SupplyChainPointManagement of a certain SupplyChainPoint.
     */
    @GetMapping("/search/management/by/supply-chain-point/{supplyChainPointId}")
    public ResponseEntity<Object> getAllSupplyChainPointManagementBySupplyChainPointId(@PathVariable("supplyChainPointId") int supplyChainPointId) {
        try {
            return new ResponseEntity<>(supplyChainPointHandler.findAllSupplyChainPointManagementBySupplyChainPointId(supplyChainPointId), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves all SupplyChainPointManagement based on the approval status.
     *
     * @param approved is set to true if only approved SupplyChainPointManagement are subject of the retrieval operation,
     *                 is set to false if only SupplyChainPointManagement yet to be approved are subject of the retrieval operation.
     * @return all SupplyChainPointManagement based on the approval status.
     */
    @GetMapping("/search/management/all")
    public ResponseEntity<Object> getAllSupplyChainPointManagement(@RequestParam boolean approved) {
        try {
            return new ResponseEntity<>(supplyChainPointHandler.findAllSupplyChainPointManagement(approved), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Approves or Rejects a SupplyChainPointManagement.
     *
     * @param id the ID of the SupplyChainPointManagement to approve or reject.
     * @param curatorId the ID of the Curator that is performing this operation.
     * @param approvalChoice true if the SupplyChainPointManagement needs to be approved,
     *                       false if the SupplyChainPointManagement needs to be rejected.
     * @return a message in String format that will communicate the operation's result.
     */
    @PatchMapping("/approval/management/{managementId}/by-curator/{curatorId}")
    public ResponseEntity<String> approveSupplyChainPointManagement(@PathVariable("managementId") int id,
                                                                    @PathVariable("curatorId") int curatorId,
                                                                    @RequestParam boolean approvalChoice) {
        try {
            return new ResponseEntity<>(supplyChainPointHandler.approveSupplyChainPointManagement(id, curatorId, approvalChoice), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
