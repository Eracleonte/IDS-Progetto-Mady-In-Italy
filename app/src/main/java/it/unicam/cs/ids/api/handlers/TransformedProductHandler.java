package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.dto.input.InputTransformedProductDTO;
import it.unicam.cs.ids.api.dto.output.OutputTransformedProductDTO;
import it.unicam.cs.ids.api.model.builder.contentbuilders.productbuilder.TransformedProductBuilder;
import it.unicam.cs.ids.api.model.contents.products.singles.TransformedProduct;
import it.unicam.cs.ids.api.repos.content.TransformedProductRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class TransformedProductHandler {

    private TransformedProductBuilder transformedProductBuilder;

    private TransformedProductRepository transformedProductRepository;

    public TransformedProductHandler(TransformedProductRepository transformedProductRepository) {
        this.transformedProductBuilder = new TransformedProductBuilder();
        this.transformedProductRepository = transformedProductRepository;
    }

    // CREATE

    public int saveTransformedProduct(InputTransformedProductDTO inputTransformedProductDTO) {
        TransformedProduct transformedProduct = this.transformedProductRepository
                .save(this.transformedProductBuilder.buildTransformedProductFromDTO(inputTransformedProductDTO));
        this.validationRequestHandler.saveValidationRequest(transformedProduct.getValidationRequest());
        return transformedProduct.getContentId();
    }

    // READ

    public OutputTransformedProductDTO findTransformedProductById(Integer id) {
        TransformedProduct transformedProduct = this.transformedProductRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find raw product with id: " + id));
        return transformedProduct.getOutputTransformedProductDTO();
    }

    public List<OutputTransformedProductDTO> findAllTransformedProducts() {
        return this.transformedProductRepository.findAll()
                .stream()
                .map(TransformedProduct::getOutputTransformedProductDTO)
                .toList();
    }

}
