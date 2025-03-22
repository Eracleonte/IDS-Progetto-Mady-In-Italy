package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.dto.input.InputProductPackageDTO;
import it.unicam.cs.ids.api.dto.output.OutputProductPackageDTO;
import it.unicam.cs.ids.api.model.builder.contentbuilders.productbuilder.ProductPackageBuilder;
import it.unicam.cs.ids.api.model.contents.products.productpackages.ProductPackage;
import it.unicam.cs.ids.api.repos.content.ProductPackageRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductPackageHandler {

    private ProductPackageBuilder productPackageBuilder;

    private ProductPackageRepository productPackageRepository;

    private ValidationRequestHandler validationRequestHandler;

    public ProductPackageHandler(ProductPackageRepository productPackageRepository,
                                 ValidationRequestHandler validationRequestHandler) {
        this.productPackageBuilder = new ProductPackageBuilder();
        this.productPackageRepository = productPackageRepository;
        this.validationRequestHandler = validationRequestHandler;
    }

    // CREATE

    public int saveProductPackage(InputProductPackageDTO inputProductPackageDTO) {
        ProductPackage productPackage = this.productPackageRepository
                .save(this.productPackageBuilder.buildProductPackageFromDTO(inputProductPackageDTO));
        this.validationRequestHandler.saveValidationRequest(productPackage.getValidationRequest());
        return productPackage.getContentId();
    }

    // READ

    public OutputProductPackageDTO findProductPackageById(Integer id) {
        ProductPackage productPackage = this.productPackageRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find product package with id: " + id));
        return productPackage.getOutputProductPackageDTO();
    }

    public List<OutputProductPackageDTO> findAllProductPackages() {
        return this.productPackageRepository.findAll()
                .stream()
                .map(ProductPackage::getOutputProductPackageDTO)
                .toList();
    }

}
