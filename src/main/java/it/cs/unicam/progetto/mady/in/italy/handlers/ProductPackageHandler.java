package it.cs.unicam.progetto.mady.in.italy.handlers;

import it.cs.unicam.progetto.mady.in.italy.model.contents.ContentType;
import it.cs.unicam.progetto.mady.in.italy.model.contents.products.productpackages.ProductPackage;
import it.cs.unicam.progetto.mady.in.italy.repos.content.ProductPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductPackageHandler implements ContentHandler<ProductPackage> {

    private final ProductPackageRepository productPackageRepository;

    @Autowired
    public ProductPackageHandler(ProductPackageRepository productPackageRepository) {
        this.productPackageRepository = productPackageRepository;
    }

    @Override
    public int saveContent(ProductPackage productPackage) {
        return this.productPackageRepository.save(productPackage).getId();
    }

    @Override
    public ProductPackage findContentById(int id) {
        return this.productPackageRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find Product Package with id: " + id));
    }

    @Override
    public List<ProductPackage> findAllContents() {
        return this.productPackageRepository.findAll();
    }

    @Override
    public String approveContent(int id, boolean approvalChoice) {
        ProductPackage toApprove = checkIfProductPackageExists(id);
        if (!toApprove.isApproved()) {
            if (!approvalChoice) {
                this.productPackageRepository.reject(id);
                return "Product Package with Id " + id + " has been rejected";
            } else {
                this.productPackageRepository.approve(id);
                return "Product Package with Id " + id + " has been approved";
            }
        } else
            return "Product Package with Id " + id + " is already approved";
    }

    @Override
    public ContentType supports() {
        return ContentType.PRODUCT_PACKAGE;
    }

    // UTILITY

    private ProductPackage checkIfProductPackageExists(int id) {
        ProductPackage productPackage = this.findContentById(id);
        if (productPackage == null)
            throw new NoSuchElementException("Cannot find Product Package with id: " + id);
        else
            return productPackage;
    }

}
