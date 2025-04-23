package it.cs.unicam.progetto.mady.in.italy.repos.content;

import it.cs.unicam.progetto.mady.in.italy.model.contents.products.singles.RawProduct;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RawProductRepository extends JpaRepository<RawProduct, Integer> {

    /**
     *
     * Approves the RawProduct with the specified id
     *
     * @param id the id of the RawProduct
     */
    @Modifying
    @Transactional
    @Query("UPDATE RawProduct r SET r.approved = true WHERE r.id = :id")
    void approve(@Param("id") Integer id);

    /**
     *
     * Deletes the RawProduct with the specified id due to it being rejected
     *
     * @param id the id of the RawProduct
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM RawProduct r WHERE r.id = :id")
    void reject(@Param("id") Integer id);

}
