package it.cs.unicam.progetto.mady.in.italy.repos.supplychain;

import it.cs.unicam.progetto.mady.in.italy.model.supplychain.SupplyChainPointManagement;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplyChainPointManagementRepository extends JpaRepository<SupplyChainPointManagement,Integer> {

    /**
     * Approves the SupplyChainPointManagement with the specified id
     *
     * @param id the id of the SupplyChainPointManagement
     */
    @Modifying
    @Transactional
    @Query("UPDATE SupplyChainPointManagement m SET m.approved = true WHERE m.id = :id")
    void approve(@Param("id") Integer id);

    /**
     * Utility used when approving a supply chain point.
     * Approves all supply chain point management with the specified supply chain point id.
     *
     * @param id the id of the SupplyChainPointManagement
     */
    @Modifying
    @Transactional
    @Query("UPDATE SupplyChainPointManagement m SET m.approved = true WHERE m.supplyChainPoint.id = :id")
    void approveSupplyChainPointManagementBySupplyChainPointId(@Param("id") Integer id);

    /**
     * Deletes the SupplyChainPointManagement with the specified id due to it being rejected
     *
     * @param id the id of the SupplyChainPointManagement
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM SupplyChainPointManagement m WHERE m.id = :id")
    void reject(@Param("id") Integer id);

    // UTILITIES

    /**
     * Utility used when deleting a supply chain point.
     * Deletes all supply chain point management with the specified supply chain point id.
     *
     * @param id the id of the SupplyChainPointManagement
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM SupplyChainPointManagement m WHERE m.supplyChainPoint.id = :id")
    void rejectSupplyChainPointManagementBySupplyChainPointId(@Param("id") Integer id);

    /**
     * Utility used when creating a new content.
     * Returns the supply chain point management if it exists.
     *
     * @param supplyChainPointId the id of the supply chain point.
     * @param userId the id of the user.
     * @return the supply chain point management if it exists.
     */
    @Query("SELECT m FROM SupplyChainPointManagement m WHERE m.supplyChainPoint.id = :scpId AND m.user.id = :userId")
    Optional<SupplyChainPointManagement> findSupplyChainPointManagementBySupplyChainPointIdAndUserId(@Param("scpId") Integer supplyChainPointId,
                                                                                                     @Param("userId") Integer userId);

}
