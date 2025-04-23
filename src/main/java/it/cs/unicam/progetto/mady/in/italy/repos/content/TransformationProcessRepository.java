package it.cs.unicam.progetto.mady.in.italy.repos.content;

import it.cs.unicam.progetto.mady.in.italy.model.contents.transformationprocesses.TransformationProcess;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransformationProcessRepository extends JpaRepository<TransformationProcess, Integer> {

    /**
     *
     * Approves the TransformationProcess with the specified id
     *
     * @param id the id of the TransformationProcess
     */
    @Modifying
    @Transactional
    @Query("UPDATE TransformationProcess t SET t.approved = true WHERE t.id = :id")
    void approve(@Param("id") Integer id);

    /**
     *
     * Deletes the TransformationProcess with the specified id due to it being rejected
     *
     * @param id the id of the TransformationProcess
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM TransformationProcess t WHERE t.id = :id")
    void reject(@Param("id") Integer id);

}
