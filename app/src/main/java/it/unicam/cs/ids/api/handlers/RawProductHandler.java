package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.model.contents.ContentType;
import it.unicam.cs.ids.api.model.contents.products.singles.RawProduct;
import it.unicam.cs.ids.api.repos.content.RawProductRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class RawProductHandler implements ContentHandler<RawProduct> {

    private RawProductRepository rawProductRepository;

    public RawProductHandler(RawProductRepository rawProductRepository) {
        if (rawProductRepository == null)
            throw new NullPointerException("RawProductRepository is null");
        this.rawProductRepository = rawProductRepository;
    }

    @Override
    public int saveContent(RawProduct rawProduct) {
        rawProduct.setContentId(this.rawProductRepository.getNextId());
        return this.rawProductRepository.save(rawProduct).getId();
    }

    @Override
    public RawProduct findContentById(int id) {
        return this.rawProductRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find raw product with id: " + id));
    }

    @Override
    public List<RawProduct> findAllContents() {
        return this.rawProductRepository.findAll();
    }

    @Override
    public String approveContent(int id, boolean approvalChoice) {
        boolean result = this.rawProductRepository.approve(id, approvalChoice);
        if (!result)
            return "Raw Product with Id " + id + " has been rejected";
        else
            return "Raw Product with Id " + id + " has been approved";
    }

    @Override
    public ContentType supports() {
        return ContentType.RAW_PRODUCT;
    }

}
