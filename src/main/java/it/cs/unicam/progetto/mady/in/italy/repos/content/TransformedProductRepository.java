package it.cs.unicam.progetto.mady.in.italy.repos.content;

import it.cs.unicam.progetto.mady.in.italy.model.contents.products.singles.TransformedProduct;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransformedProductRepository extends JpaRepository<TransformedProduct, Integer> {

    /**
     *
     * Approves the TransformedProduct with the specified id
     *
     * @param id the id of the TransformedProduct
     */
    @Modifying
    @Transactional
    @Query("UPDATE TransformedProduct t SET t.approved = true WHERE t.id = :id")
    void approve(@Param("id") Integer id);

    /**
     *
     * Deletes the TransformedProduct with the specified id due to it being rejected
     *
     * @param id the id of the TransformedProduct
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM TransformedProduct t WHERE t.id = :id")
    void reject(@Param("id") Integer id);

}
