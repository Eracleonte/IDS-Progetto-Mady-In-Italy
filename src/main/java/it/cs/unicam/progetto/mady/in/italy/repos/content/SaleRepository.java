package it.cs.unicam.progetto.mady.in.italy.repos.content;

import it.cs.unicam.progetto.mady.in.italy.model.contents.sale.Sale;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {

    /**
     * Buys a certain quantity of products of a sale that has the specified id
     *
     * @param saleId the id of the sale from where to buy products
     * @param quantity the desired quantity to buy
     * @return the id of the sale
     */
    @Modifying
    @Transactional
    @Query("UPDATE Sale s SET s.quantity = s.quantity - :quantity WHERE s.id = :saleId AND s.quantity >= :quantity")
    int buyFromSale(@Param("saleId") int saleId, @Param("quantity") int quantity);

    /**
     * Updates the quantity of the products of a sale that has the specified id
     *
     * @param saleId the id of the sale
     * @param quantity the desired quantity that will be added to the current one
     * @return the id of the sale
     */
    @Modifying
    @Transactional
    @Query("UPDATE Sale s SET s.quantity = s.quantity + :quantity WHERE s.id = :saleId")
    int updateSaleQuantity(@Param("saleId") int saleId, @Param("quantity") int quantity);

    /**
     * Approves the Sale with the specified id
     *
     * @param id the id of the Sale
     */
    @Modifying
    @Transactional
    @Query("UPDATE Sale s SET s.approved = true WHERE s.id = :id")
    void approve(@Param("id") Integer id);

    /**
     * Deletes the Sale with the specified id due to it being rejected
     *
     * @param id the id of the Sale
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM Sale s WHERE s.id = :id")
    void reject(@Param("id") Integer id);

}
