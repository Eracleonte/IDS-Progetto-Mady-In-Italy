package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.model.contents.ContentType;
import it.unicam.cs.ids.api.model.contents.sale.Sale;
import it.unicam.cs.ids.api.repos.content.SaleRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class SaleHandler implements ContentHandler<Sale> {

    private final SaleRepository saleRepository;

    public SaleHandler(SaleRepository saleRepository) {
        if (saleRepository == null)
            throw new NullPointerException("sale repository is null");
        this.saleRepository = saleRepository;
    }

    @Override
    public int saveContent(Sale sale) {
        sale.setContentId(this.saleRepository.getNextId());
        return this.saleRepository.save(sale).getId();
    }

    @Override
    public Sale findContentById(int id) {
        return this.saleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product on sale not found"));
    }

    @Override
    public List<Sale> findAllContents() {
        return this.saleRepository.findAll();
    }

    @Override
    public String approveContent(int id, boolean approvalChoice) {
        boolean result = this.saleRepository.approve(id, approvalChoice);
        if (!result)
            return "Sale with Id " + id + " has been rejected";
        else
            return "Sale with Id " + id + " has been approved";
    }

    @Override
    public ContentType supports() {
        return ContentType.SALE;
    }

    public int buyFromSale(int saleId, int quantity) {
        return this.saleRepository.buyFromSale(saleId, quantity);
    }

    public int updateSaleQuantity(int saleId, int quantity) {
        return this.saleRepository.updateSaleQuantity(saleId, quantity);
    }

}
