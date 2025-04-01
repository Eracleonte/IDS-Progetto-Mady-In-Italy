package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.dto.input.InputSaleDTO;
import it.unicam.cs.ids.api.dto.output.OutputSaleDTO;
import it.unicam.cs.ids.api.model.builder.contentbuilders.salebuilder.SaleBuilder;
import it.unicam.cs.ids.api.model.contents.sale.Sale;
import it.unicam.cs.ids.api.repos.content.SaleRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class SaleHandler {

    private SaleBuilder saleBuilder;

    private SaleRepository saleRepository;

    public SaleHandler(SaleRepository saleRepository) {
        this.saleBuilder = new SaleBuilder();
        this.saleRepository = saleRepository;
    }

    public int saveProductOnSale(InputSaleDTO inputSaleDTO) {
        Sale sale = this.saleBuilder.buildProductOnSaleFromDTO(inputSaleDTO);
        sale.setContentId(this.saleRepository.getNextId());
        return this.saleRepository.save(sale).getId();
    }

    //READ

    public OutputSaleDTO findProductOnSaleById(int id) {
        Sale sale = this.saleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product on sale not found"));
        return sale.getOutputDTO();
    }

    public List<OutputSaleDTO> findAllProductsOnSale() {
        return this.saleRepository.findAll()
                .stream()
                .map(Sale::getOutputDTO)
                .toList();
    }

    public String approveSale(int id, boolean approvalChoice) {
        boolean result = this.saleRepository.approve(id, approvalChoice);
        if (!result)
            return "Sale with Id " + id + " has been rejected";
        else
            return "Sale with Id " + id + " has been approved";
    }

}
