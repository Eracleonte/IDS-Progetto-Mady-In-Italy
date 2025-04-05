package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.model.contents.ContentType;
import it.unicam.cs.ids.api.model.contents.products.singles.TransformedProduct;
import it.unicam.cs.ids.api.repos.content.TransformedProductRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class TransformedProductHandler implements ContentHandler<TransformedProduct> {

    private TransformedProductRepository transformedProductRepository;

    public TransformedProductHandler(TransformedProductRepository transformedProductRepository) {
        if (transformedProductRepository == null)
            throw new NullPointerException("transformedProductRepository is null");
        this.transformedProductRepository = transformedProductRepository;
    }

    @Override
    public int saveContent(TransformedProduct transformedProduct) {
        transformedProduct.setContentId(this.transformedProductRepository.getNextId());
        return this.transformedProductRepository.save(transformedProduct).getId();
    }

    @Override
    public TransformedProduct findContentById(int id) {
        return this.transformedProductRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find raw product with id: " + id));
    }

    @Override
    public List<TransformedProduct> findAllContents() {
        return this.transformedProductRepository.findAll();
    }

    @Override
    public String approveContent(int id, boolean approvalChoice) {
        boolean result = this.transformedProductRepository.approve(id, approvalChoice);
        if (!result)
            return "Transformed Product with Id " + id + " has been rejected";
        else
            return "Transformed Product with Id " + id + " has been approved";
    }

    @Override
    public ContentType supports() {
        return ContentType.TRANSFORMED_PRODUCT;
    }

}
