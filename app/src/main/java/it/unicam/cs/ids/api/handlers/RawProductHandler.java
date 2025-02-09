package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.model.contents.products.singles.RawProduct;
import it.unicam.cs.ids.api.repos.content.RawProductRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class RawProductHandler {

    private RawProductRepository rawProductRepository;

    public RawProductHandler(RawProductRepository rawProductRepository) {
        this.rawProductRepository = rawProductRepository;
    }

    public RawProduct saveRawProduct(RawProduct rawProduct) {
        if (rawProduct == null)
            throw new NullPointerException("Cannot save a null raw product");
        return this.rawProductRepository.save(rawProduct);
    }

    public RawProduct findRawProductById(Integer id) {
        return this.rawProductRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find raw product with id: " + id));
    }

    public List<RawProduct> findAllRawProducts() {
        return this.rawProductRepository.findAll();
    }

}
