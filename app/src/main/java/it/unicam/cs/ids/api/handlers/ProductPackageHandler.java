package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.model.contents.ContentType;
import it.unicam.cs.ids.api.model.contents.products.productpackages.ProductPackage;
import it.unicam.cs.ids.api.repos.content.ProductPackageRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductPackageHandler implements ContentHandler<ProductPackage> {

    private ProductPackageRepository productPackageRepository;

    public ProductPackageHandler(ProductPackageRepository productPackageRepository) {
        if (productPackageRepository == null)
            throw new NullPointerException("productPackageRepository is null");
        this.productPackageRepository = productPackageRepository;
    }

    @Override
    public int saveContent(ProductPackage productPackage) {
        productPackage.setContentId(this.productPackageRepository.getNextId());
        return this.productPackageRepository.save(productPackage).getId();
    }

    @Override
    public ProductPackage findContentById(int id) {
        return this.productPackageRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find product package with id: " + id));
    }

    @Override
    public List<ProductPackage> findAllContents() {
        return this.productPackageRepository.findAll();
    }

    @Override
    public String approveContent(int id, boolean approvalChoice) {
        boolean result = this.productPackageRepository.approve(id, approvalChoice);
        if (!result)
            return "Product Package with Id " + id + " has been rejected";
        else
            return "Product Package with Id " + id + " has been approved";
    }

    @Override
    public ContentType supports() {
        return ContentType.PRODUCT_PACKAGE;
    }

}
