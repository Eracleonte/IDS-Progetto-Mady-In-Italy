package it.cs.unicam.progetto.mady.in.italy.handlers;

import it.cs.unicam.progetto.mady.in.italy.model.contents.ContentType;
import it.cs.unicam.progetto.mady.in.italy.model.contents.products.singles.RawProduct;
import it.cs.unicam.progetto.mady.in.italy.model.contents.sale.Sale;
import it.cs.unicam.progetto.mady.in.italy.repos.content.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SaleHandler implements ContentHandler<Sale> {

    private final SaleRepository saleRepository;

    @Autowired
    public SaleHandler(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public int saveContent(Sale sale) {
        return this.saleRepository.save(sale).getId();
    }

    @Override
    public Sale findContentById(int id) {
        return this.saleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Sale not found"));
    }

    @Override
    public List<Sale> findAllContents() {
        return this.saleRepository.findAll();
    }

    @Override
    public String approveContent(int id, boolean approvalChoice) {
        Sale toApprove = checkIfSaleExists(id);
        if (!toApprove.isApproved()) {
            if (!approvalChoice) {
                this.saleRepository.reject(id);
                return "Sale with Id " + id + " has been rejected";
            } else {
                this.saleRepository.approve(id);
                return "Sale with Id " + id + " has been approved";
            }
        } else
            return "Sale with Id " + id + " is already approved";
    }

    @Override
    public ContentType supports() {
        return ContentType.SALE;
    }

    public String buyFromSale(int saleId, int quantity) {
        Sale toBuyFrom = checkIfSaleExists(saleId);
        if (toBuyFrom.isApproved()) {
            if (toBuyFrom.getQuantity() < quantity)
                return "Could not buy from sale due to stock shortage Stock: " + toBuyFrom.getQuantity() + " Desired quantity: " + quantity;
            else {
                this.saleRepository.buyFromSale(saleId, quantity);
                return "Purchase successful for quantity " + quantity + " from sale, " + saleId + " total price: " + toBuyFrom.getPrice() * quantity;
            }
        } else
            return "Could not buy from sale as it has not been approved yet";
    }

    public String updateSaleQuantity(int saleId, int quantity) {
        Sale toUpdate = checkIfSaleExists(saleId);
        if (toUpdate.isApproved()) {
            if (quantity <= 0)
                return "Could not update sale quantity due to incorrect quantity: " + quantity;
            else {
                this.saleRepository.updateSaleQuantity(saleId, quantity);
                return "Sale quantity for sale " + saleId + " has been updated, new quantity: " + toUpdate.getQuantity();
            }
        }
        return "Could not update sale quantity due to sale not being approved";
    }

    // UTILITY

    private Sale checkIfSaleExists(int id) {
        Sale sale = this.findContentById(id);
        if (sale == null)
            throw new NoSuchElementException("Cannot find Sale with id: " + id);
        else
            return sale;
    }

}
