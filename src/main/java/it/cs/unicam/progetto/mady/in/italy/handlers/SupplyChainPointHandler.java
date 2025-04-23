package it.cs.unicam.progetto.mady.in.italy.handlers;

import it.cs.unicam.progetto.mady.in.italy.dto.input.InputSupplyChainPointDTO;
import it.cs.unicam.progetto.mady.in.italy.dto.input.SupplyChainPointSearchFilterDTO;
import it.cs.unicam.progetto.mady.in.italy.dto.output.OutputSupplyChainPointDTO;
import it.cs.unicam.progetto.mady.in.italy.dto.output.OutputSupplyChainPointManagementDTO;
import it.cs.unicam.progetto.mady.in.italy.model.supplychain.SupplyChainPoint;
import it.cs.unicam.progetto.mady.in.italy.model.supplychain.SupplyChainPointManagement;
import it.cs.unicam.progetto.mady.in.italy.model.user.Role;
import it.cs.unicam.progetto.mady.in.italy.model.user.User;
import it.cs.unicam.progetto.mady.in.italy.repos.supplychain.SupplyChainPointManagementRepository;
import it.cs.unicam.progetto.mady.in.italy.repos.supplychain.SupplyChainPointRepository;
import it.cs.unicam.progetto.mady.in.italy.repos.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SupplyChainPointHandler {

    // Utility, used for behaviour access and checks.
    private final UserRepository userRepository;

    private final SupplyChainPointRepository scpRepository;

    private final SupplyChainPointManagementRepository scpmRepository;

    @Autowired
    public SupplyChainPointHandler(UserRepository userRepository,
                                   SupplyChainPointRepository scpRepository,
                                   SupplyChainPointManagementRepository scpmRepository) {
        this.userRepository = userRepository;
        this.scpRepository = scpRepository;
        this.scpmRepository = scpmRepository;
    }

    /**
     *
     * Saves a new SupplyChainPoint in the system and generates the initial SupplyChainPointManagement.
     * Initially the new SupplyChainPoint will result as "to be managed" by the User having the userId.
     *
     * @param dto the InputSupplyChainPointDTO that will be used in order to create the new SupplyChainPoint.
     * @param userId the id of the user creating the new SupplyChainPoint and that should be considered as its first manager.
     * @return the id of the new SupplyChainPoint.
     */
    public int saveSupplyChainPoint(InputSupplyChainPointDTO dto, int userId) {
        User user = checkIfUserExists(userId);
        checkIfUserHasAtLeastOneOfTheAuthorizations(user,List.of(Role.getProducer(),Role.getTransformer(),Role.getDistributor()));
        SupplyChainPoint toSave = SupplyChainPoint.getSupplyChainPointFromInputSupplyChainPointDTO(dto);
        int scpId = this.scpRepository.save(toSave).getId();
        saveSupplyChainPointManagement(scpId, userId);
        return scpId;
    }

    public OutputSupplyChainPointDTO findSupplyChainPointById(Integer id) {
        SupplyChainPoint scp = this.scpRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find supply chain point with id: " + id));
        return scp.getOutputDTO();
    }

    public List<OutputSupplyChainPointDTO> findAllSupplyChainPoints(boolean approved) {
        return this.scpRepository.findAll()
                .stream()
                .filter((scp) -> scp.isApproved() == approved)
                .map(SupplyChainPoint::getOutputDTO)
                .toList();
    }

    public List<OutputSupplyChainPointDTO> findAllSupplyChainPointsFiltered(SupplyChainPointSearchFilterDTO filter,
                                                                            boolean approved) {
        List<OutputSupplyChainPointDTO> scps = this.scpRepository.findAll().stream()
                .filter((scp) -> scp.isApproved() == approved)
                .map(SupplyChainPoint::getOutputDTO)
                .toList();
        if (filter.supplyChainPointName() != null) {
            scps = scps.stream()
                    .filter((scp) -> scp.name().toUpperCase().contains(filter.supplyChainPointName().toUpperCase()))
                    .toList();
        }
        scps = retrieveSupplyChainPointsSpecializedIn(scps, filter.isProduction(), filter.isTransformation(),filter.isDistribution(),filter.isResale());
        if (filter.managerId() != null && filter.managerId() > 0)
            scps = retrieveSupplyChainPointsManagedBy(scps, filter.managerId(), approved);
        return scps;
    }

    /**
     *
     * Retrieves, from a List of OutputSupplyChainPointDTOS, only the OutputSupplyChainPointDTOS
     * that respect the given business specializations.
     *
     * @param scps the input List of OutputSupplyChainPointDTOS.
     * @param isProduction equals true if Production supply chain points are being searched.
     * @param isTransformation equals true if Production supply chain points are being searched.
     * @param isDistribution equals true if Production supply chain points are being searched.
     * @param isResale equals true if Production supply chain points are being searched.
     * @return a List of OutputSupplyChainPointDTOS that respect the given business specializations.
     */
    private List<OutputSupplyChainPointDTO> retrieveSupplyChainPointsSpecializedIn(List<OutputSupplyChainPointDTO> scps,
                                                                                   Boolean isProduction,
                                                                                   Boolean isTransformation,
                                                                                   Boolean isDistribution,
                                                                                   Boolean isResale) {
        return scps.stream()
                .filter(scp -> isProduction == null || scp.isProduction() == isProduction)
                .filter(scp -> isTransformation == null || scp.isTransformation() == isTransformation)
                .filter(scp -> isDistribution == null || scp.isDistribution() == isDistribution)
                .filter(scp -> isResale == null || scp.isResale() == isResale)
                .toList();
    }

    /**
     *
     * Retrieves, from a List of OutputSupplyChainPointDTOS, only the OutputSupplyChainPointDTOS
     * that are managed by the required user.
     *
     * @param scps the input List of OutputSupplyChainPointDTOS.
     * @param userId the id of the user.
     * @param approved depends on which supply chain points are being searched.
     *                 (Ex. if looking for approved supply chain points,
     *                 only supply chain points that have approved management for the specified user will be gathered)
     * @return the List of OutputSupplyChainPointDTOS that are managed by the required user.
     */
    private List<OutputSupplyChainPointDTO> retrieveSupplyChainPointsManagedBy(List<OutputSupplyChainPointDTO> scps,
                                                                               int userId,
                                                                               boolean approved) {
        checkIfUserExists(userId);
        List<Integer> scpms = this.findAllSupplyChainPointManagementByUserId(userId, approved).stream()
                .map(OutputSupplyChainPointManagementDTO::supplyChainPointId).toList();
        return scps.stream().filter((scp) -> scpms.contains(scp.getId())).toList();
    }

    public String approveSupplyChainPoint(int id,
                                          int curatorId,
                                          boolean approvalChoice) {
        User user = checkIfUserExists(curatorId);
        checkIfUserHasRequiredAuthorization(user, Role.getCurator());
        SupplyChainPoint toApprove = checkIfSupplyChainPointExists(id);
        if (!toApprove.isApproved()) {
            if (!approvalChoice) {
                this.scpmRepository.rejectSupplyChainPointManagementBySupplyChainPointId(id);
                this.scpRepository.reject(id);
                return "Supply Chain Point with Id " + id + " has been rejected";
            } else {
                this.scpRepository.approve(id);
                this.scpmRepository.approveSupplyChainPointManagementBySupplyChainPointId(id);
                return "Supply Chain Point with Id " + id + " has been approved";
            }
        } else
            return "Supply Chain Point with Id " + id + " was already approved";
    }

    /**
     *
     * Saves a new SupplyChainPointManagement in the system.
     *
     * @param supplyChainPointId the id of the SupplyChainPoint the user wishes to manage.
     * @param userId the id of the user creating the new SupplyChainPointManagement.
     * @throws IllegalStateException if a management is already present with the given input parameters.
     * @return the id of the new SupplyChainPointManagement.
     */
    public int saveSupplyChainPointManagement(int supplyChainPointId, int userId) {
        User user = checkIfUserExists(userId);
        checkIfUserHasAtLeastOneOfTheAuthorizations(user,List.of(Role.getProducer(),Role.getTransformer(),Role.getDistributor()));
        SupplyChainPoint supplyChainPoint = checkIfSupplyChainPointExists(supplyChainPointId);
        Optional<SupplyChainPointManagement> toCheck =
                scpmRepository.findSupplyChainPointManagementBySupplyChainPointIdAndUserId(supplyChainPointId, userId);
        if (toCheck.isPresent())
            throw new IllegalStateException("Management already exists for SupplyChainPoint: " + supplyChainPointId + " User: " + userId);
        SupplyChainPointManagement supplyChainPointManagement = new SupplyChainPointManagement(supplyChainPoint, user);
        return this.scpmRepository.save(supplyChainPointManagement).getId();
    }

    public List<OutputSupplyChainPointManagementDTO> findAllSupplyChainPointManagementByUserId(int userId, boolean approved) {
        User user = checkIfUserExists(userId);
        checkIfUserHasAtLeastOneOfTheAuthorizations(user,List.of(Role.getProducer(),Role.getTransformer(),Role.getDistributor()));
        return this.scpmRepository.findAll()
                .stream()
                .filter((management) -> management.getUser().getId() == userId)
                .filter((management) -> management.isApproved() == approved)
                .map(SupplyChainPointManagement::getOutputDTO)
                .toList();
    }

    public List<OutputSupplyChainPointManagementDTO> findAllSupplyChainPointManagementBySupplyChainPointId(int supplyChainPointId) {
        checkIfSupplyChainPointExists(supplyChainPointId);
        return this.scpmRepository.findAll()
                .stream()
                .filter((management) -> management.getSupplyChainPoint().getId() == supplyChainPointId)
                .map(SupplyChainPointManagement::getOutputDTO)
                .toList();
    }

    public List<OutputSupplyChainPointManagementDTO> findAllSupplyChainPointManagement(boolean approved) {
        return this.scpmRepository.findAll()
                .stream()
                .filter((management) -> management.isApproved() == approved)
                .map(SupplyChainPointManagement::getOutputDTO)
                .toList();
    }

    public String approveSupplyChainPointManagement(int id,
                                                    int curatorId,
                                                    boolean approvalChoice) {
        User user = checkIfUserExists(curatorId);
        checkIfUserHasRequiredAuthorization(user, Role.getCurator());
        SupplyChainPointManagement toApprove = checkIfSupplyChainPointManagementExists(id);
        if (!toApprove.isApproved()) {
            if (!approvalChoice) {
                this.scpmRepository.reject(id);
                return "Supply Chain Point Management with Id " + id + " has been rejected";
            } else {
                this.scpmRepository.approve(id);
                return "Supply Chain Point Management with Id " + id + " has been approved";
            }
        } else
            return "Supply Chain Point Management with Id " + id + " was already approved";
    }

    // UTILITIES

    /**
     *
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
     *
     * Checks if user has at least one of the required authorizations.
     *
     * @param user the user subject to the check.
     * @param roles the roles bearing the authorizations.
     */
    private void checkIfUserHasAtLeastOneOfTheAuthorizations(User user, List<Role> roles) {
        boolean hasAtLeastOne = roles.stream()
                .anyMatch(role -> user.getRoles().contains(role));
        if (!hasAtLeastOne)
            throw new IllegalStateException("User lacks all required authorizations: " + roles);
    }

    /**
     *
     * Checks if there's a user for the given id and returns it.
     *
     * @param id the supposed user id.
     * @throws IllegalStateException if there is not a user for the given id
     *                               or the recovered user is not approved.
     * @return the user with the given id if it exists.
     */
    private User checkIfUserExists(int id) {
        User toReturn = userRepository.findById(id).orElse(null);
        if (toReturn == null)
            throw new IllegalStateException("User with Id: " + id + " not found");
        if (!toReturn.isApproved())
            throw new IllegalStateException("User with Id: " + id + " is not approved");
        else
            return toReturn;
    }

    /**
     *
     * Checks if there's a supply chain point for the given id and returns it for content creation.
     *
     * @param id the supposed supply chain point id.
     * @throws IllegalStateException if there is not a supply chain point for the given id.
     * @return the supply chain point with the given id if it exists.
     */
    private SupplyChainPoint checkIfSupplyChainPointExists(int id) {
        SupplyChainPoint toReturn = this.scpRepository.findById(id).orElse(null);
        if (toReturn == null)
            throw new IllegalStateException("Supply chain point with id " + id + " not found");
        else
            return toReturn;
    }

    /**
     *
     * Checks if there's a supply chain point management for the given id and returns it for content creation.
     *
     * @param id the supposed supply chain point management id.
     * @throws IllegalStateException if there is not a supply chain point management for the given id.
     * @return the supply chain point management with the given id if it exists.
     */
    private SupplyChainPointManagement checkIfSupplyChainPointManagementExists(int id) {
        SupplyChainPointManagement toReturn = this.scpmRepository.findById(id).orElse(null);
        if (toReturn == null)
            throw new IllegalStateException("Supply chain point with id " + id + " not found");
        else
            return toReturn;
    }

}
