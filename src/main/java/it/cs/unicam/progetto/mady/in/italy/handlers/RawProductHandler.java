package it.cs.unicam.progetto.mady.in.italy.handlers;

import it.cs.unicam.progetto.mady.in.italy.model.contents.ContentType;
import it.cs.unicam.progetto.mady.in.italy.model.contents.products.productpackages.ProductPackage;
import it.cs.unicam.progetto.mady.in.italy.model.contents.products.singles.RawProduct;
import it.cs.unicam.progetto.mady.in.italy.repos.content.RawProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * A Handler for RawProducts
 */
@Service
public class RawProductHandler implements ContentHandler<RawProduct> {

    private final RawProductRepository rawProductRepository;

    @Autowired
    public RawProductHandler(RawProductRepository rawProductRepository) {
        this.rawProductRepository = rawProductRepository;
    }

    @Override
    public int saveContent(RawProduct rawProduct) {
        return this.rawProductRepository.save(rawProduct).getId();
    }

    @Override
    public RawProduct findContentById(int id) {
        return this.rawProductRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find Raw Product with id: " + id));
    }

    @Override
    public List<RawProduct> findAllContents() {
        return this.rawProductRepository.findAll();
    }

    @Override
    public String approveContent(int id, boolean approvalChoice) {
        RawProduct toApprove = checkIfRawProductExists(id);
        if (!toApprove.isApproved()) {
            if (!approvalChoice) {
                this.rawProductRepository.reject(id);
                return "Raw Product with Id " + id + " has been rejected";
            } else {
                this.rawProductRepository.approve(id);
                return "Raw Product with Id " + id + " has been approved";
            }
        } else
            return "Raw Product with Id " + id + " is already approved";
    }

    @Override
    public ContentType supports() {
        return ContentType.RAW_PRODUCT;
    }

    // UTILITY

    /**
     * Checks if a Raw Product with the given ID exists.
     *
     * @param id the supposed ID of a Raw Product.
     * @throws NoSuchElementException if there's not a Raw Product for the given ID.
     * @return the Raw Product.
     */
    private RawProduct checkIfRawProductExists(int id) {
        RawProduct rawProduct = this.findContentById(id);
        if (rawProduct == null)
            throw new NoSuchElementException("Cannot find Raw Product with id: " + id);
        else
            return rawProduct;
    }

}
