package it.cs.unicam.progetto.mady.in.italy.handlers;

import it.cs.unicam.progetto.mady.in.italy.model.contents.ContentType;
import it.cs.unicam.progetto.mady.in.italy.model.contents.products.singles.TransformedProduct;
import it.cs.unicam.progetto.mady.in.italy.model.contents.transformationprocesses.TransformationProcess;
import it.cs.unicam.progetto.mady.in.italy.repos.content.TransformedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TransformedProductHandler implements ContentHandler<TransformedProduct> {

    private final TransformedProductRepository transformedProductRepository;

    @Autowired
    public TransformedProductHandler(TransformedProductRepository transformedProductRepository) {
        this.transformedProductRepository = transformedProductRepository;
    }

    @Override
    public int saveContent(TransformedProduct transformedProduct) {
        return this.transformedProductRepository.save(transformedProduct).getId();
    }

    @Override
    public TransformedProduct findContentById(int id) {
        return this.transformedProductRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find Transformed Product with id: " + id));
    }

    @Override
    public List<TransformedProduct> findAllContents() {
        return this.transformedProductRepository.findAll();
    }

    @Override
    public String approveContent(int id, boolean approvalChoice) {
        TransformedProduct toApprove = checkIfTransformedProductExists(id);
        if (!toApprove.isApproved()) {
            if (!approvalChoice) {
                this.transformedProductRepository.reject(id);
                return "Transformed Product with Id " + id + " has been rejected";
            } else {
                this.transformedProductRepository.approve(id);
                return "Transformed Product with Id " + id + " has been approved";
            }
        } else
            return "Transformed Product with Id " + id + " is already approved";
    }

    @Override
    public ContentType supports() {
        return ContentType.TRANSFORMED_PRODUCT;
    }

    // UTILITY

    private TransformedProduct checkIfTransformedProductExists(int id) {
        TransformedProduct transformedProduct = this.findContentById(id);
        if (transformedProduct == null)
            throw new NoSuchElementException("Cannot find Transformed Product with id: " + id);
        else
            return transformedProduct;
    }

}
