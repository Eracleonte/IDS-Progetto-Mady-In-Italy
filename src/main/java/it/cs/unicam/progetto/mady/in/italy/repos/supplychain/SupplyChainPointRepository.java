package it.cs.unicam.progetto.mady.in.italy.repos.supplychain;

import it.cs.unicam.progetto.mady.in.italy.model.supplychain.SupplyChainPoint;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplyChainPointRepository extends JpaRepository<SupplyChainPoint,Integer> {

    /**
     *
     * Approves the SupplyChainPoint with the specified id
     *
     * @param id the id of the SupplyChainPoint
     */
    @Modifying
    @Transactional
    @Query("UPDATE SupplyChainPoint s SET s.approved = true WHERE s.id = :id")
    void approve(@Param("id") Integer id);

    /**
     *
     * Deletes the SupplyChainPoint with the specified id due to it being rejected
     *
     * @param id the id of the SupplyChainPoint
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM SupplyChainPoint s WHERE s.id = :id")
    void reject(@Param("id") Integer id);

}
