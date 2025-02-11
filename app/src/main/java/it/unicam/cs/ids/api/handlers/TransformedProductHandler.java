package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.model.contents.products.singles.TransformedProduct;
import it.unicam.cs.ids.api.repos.content.TransformedProductRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class TransformedProductHandler {

    private TransformedProductRepository transformedProductRepository;

    public TransformedProductHandler(TransformedProductRepository transformedProductRepository) {
        this.transformedProductRepository = transformedProductRepository;
    }

    public TransformedProduct saveTransformedProduct(TransformedProduct transformedProduct) {
        if (transformedProduct == null)
            throw new NullPointerException("Cannot save a null transformed product");
        return this.transformedProductRepository.save(transformedProduct);
    }

    public TransformedProduct findTransformedProductById(Integer id) {
        return transformedProductRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find transformed product with id: " + id));
    }

    public List<TransformedProduct> findAllTransformedProduct() {
        return this.transformedProductRepository.findAll();
    }

}
