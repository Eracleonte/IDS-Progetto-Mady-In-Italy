package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.dto.input.InputRawProductDTO;
import it.unicam.cs.ids.api.dto.output.OutputRawProductDTO;
import it.unicam.cs.ids.api.model.builder.contentbuilders.productbuilder.RawProductBuilder;
import it.unicam.cs.ids.api.model.contents.products.singles.RawProduct;
import it.unicam.cs.ids.api.repos.content.RawProductRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class RawProductHandler {

    private RawProductBuilder rawProductBuilder;

    private RawProductRepository rawProductRepository;

    public RawProductHandler(RawProductRepository rawProductRepository) {
        this.rawProductBuilder = new RawProductBuilder();
        this.rawProductRepository = rawProductRepository;
    }

    // CREATE

    public int saveRawProduct(InputRawProductDTO inputRawProductDTO) {
        RawProduct rawProduct = this.rawProductRepository
                .save(this.rawProductBuilder.buildRawProductFromDTO(inputRawProductDTO));
        return rawProduct.getId();
    }

    // READ

    public OutputRawProductDTO findRawProductById(Integer id) {
        RawProduct rawProduct = this.rawProductRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find raw product with id: " + id));
        return rawProduct.getOutputRawProductDTO();
    }

    public List<OutputRawProductDTO> findAllRawProducts() {
        return this.rawProductRepository.findAll()
                .stream()
                .map(RawProduct::getOutputRawProductDTO)
                .toList();
    }

}
