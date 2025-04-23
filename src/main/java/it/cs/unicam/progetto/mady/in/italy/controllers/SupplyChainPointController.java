package it.cs.unicam.progetto.mady.in.italy.controllers;

import it.cs.unicam.progetto.mady.in.italy.dto.input.InputSupplyChainPointDTO;
import it.cs.unicam.progetto.mady.in.italy.dto.input.SupplyChainPointSearchFilterDTO;
import it.cs.unicam.progetto.mady.in.italy.handlers.SupplyChainPointHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     *
     * Returns a list of OutputSupplyChainPointDTO that respect the given SupplyChainPointSearchFilterDTO.
     *
     * @param filter the filter to apply for the research.
     * @param approved equals true if only approved supply chain points are supposed to be gathered.
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
