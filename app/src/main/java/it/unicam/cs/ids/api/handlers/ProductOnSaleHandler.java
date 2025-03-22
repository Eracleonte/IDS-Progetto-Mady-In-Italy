package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.dto.input.InputProductOnSaleDTO;
import it.unicam.cs.ids.api.dto.output.OutputProductOnSaleDTO;
import it.unicam.cs.ids.api.model.builder.contentbuilders.productonsalebuilder.ProductOnSaleBuilder;
import it.unicam.cs.ids.api.model.contents.productsonsale.ProductOnSale;
import it.unicam.cs.ids.api.repos.content.ProductOnSaleRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductOnSaleHandler {

    private ProductOnSaleBuilder productOnSaleBuilder;

    private ProductOnSaleRepository productOnSaleRepository;

    private ValidationRequestHandler validationRequestHandler;

    public ProductOnSaleHandler(ProductOnSaleRepository productOnSaleRepository,
                                ValidationRequestHandler validationRequestHandler) {
        this.productOnSaleBuilder = new ProductOnSaleBuilder();
        this.productOnSaleRepository = productOnSaleRepository;
        this.validationRequestHandler = validationRequestHandler;
    }

    public int saveProductOnSale(InputProductOnSaleDTO inputProductOnSaleDTO) {
        ProductOnSale productOnSale = this.productOnSaleRepository
                .save(this.productOnSaleBuilder.buildProductOnSaleFromDTO(inputProductOnSaleDTO));
        this.validationRequestHandler.saveValidationRequest(productOnSale.getValidationRequest());
        return productOnSale.getContentId();
    }

    //READ

    public OutputProductOnSaleDTO findProductOnSaleById(int id) {
        ProductOnSale productOnSale = this.productOnSaleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product on sale not found"));
        return productOnSale.getOutputProductOnSaleDTO();
    }

    public List<OutputProductOnSaleDTO> findAllProductsOnSale() {
        return this.productOnSaleRepository.findAll()
                .stream()
                .map(ProductOnSale::getOutputProductOnSaleDTO)
                .toList();
    }

}
