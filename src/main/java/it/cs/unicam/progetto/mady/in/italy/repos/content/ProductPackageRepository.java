package it.cs.unicam.progetto.mady.in.italy.repos.content;

import it.cs.unicam.progetto.mady.in.italy.model.contents.products.productpackages.ProductPackage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPackageRepository extends JpaRepository<ProductPackage, Integer> {

    /**
     *
     * Approves the ProductPackage with the specified id
     *
     * @param id the id of the ProductPackage
     */
    @Modifying
    @Transactional
    @Query("UPDATE ProductPackage p SET p.approved = true WHERE p.id = :id")
    void approve(@Param("id") Integer id);

    /**
     *
     * Deletes the ProductPackage with the specified id due to it being rejected
     *
     * @param id the id of the ProductPackage
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM ProductPackage p WHERE p.id = :id")
    void reject(@Param("id") Integer id);

}
