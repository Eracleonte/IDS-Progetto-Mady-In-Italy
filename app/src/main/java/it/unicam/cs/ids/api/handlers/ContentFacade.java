package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.abstractions.Identifiable;
import it.unicam.cs.ids.api.dto.input.*;
import it.unicam.cs.ids.api.model.builder.contentbuilders.ContentBuilderDirector;
import it.unicam.cs.ids.api.model.contents.Content;
import it.unicam.cs.ids.api.model.contents.ContentType;
import it.unicam.cs.ids.api.model.contents.products.productpackages.ProductPackage;
import it.unicam.cs.ids.api.model.contents.products.singles.RawProduct;
import it.unicam.cs.ids.api.model.contents.products.singles.TransformedProduct;
import it.unicam.cs.ids.api.model.contents.sale.Sale;
import it.unicam.cs.ids.api.model.contents.transformationprocesses.TransformationProcess;
import it.unicam.cs.ids.api.repos.content.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a facade for general content handling related behaviours.
 */
public class ContentFacade {

    private final Map<ContentType, ContentHandler<? extends Content>> handlers;

    private final ContentBuilderDirector director;

    // TODO add check to avoid creation of Facade where handlers and builder are not linked in a one to one relation (Ex. RawProductHandler - RawProductBuilder)
    public ContentFacade(List<ContentHandler<? extends Content>> handlers,
                         ContentBuilderDirector director) {
        if (handlers == null)
            throw new NullPointerException("handlers is null");
        if (handlers.isEmpty())
            throw new IllegalArgumentException("handlers is empty");
        if (director == null)
            throw new NullPointerException("director is null");
        List<ContentHandler<? extends Content>> validHandlers = List.copyOf(handlers.stream().filter(Objects::nonNull).toList());

        // TODO added at 05/04/2025 15:12
        for (ContentHandler<? extends Content> handler : validHandlers) {
            if (! director.supportedContentTypes().contains(handler.supports()))
                throw new IllegalStateException("Attempt at creating a [ContentFacade] where a [ContentHandler] " +
                        "does not have a corresponding [ContentBuilder] for save operations");
        }
        // TODO end

        this.handlers = Map.copyOf(validHandlers.stream().collect(Collectors.toMap(ContentHandler::supports, h -> h)));
        this.director = director;
    }

    // Create

    public int saveRawProduct(InputRawProductDTO inputRawProductDTO) {
        RawProductHandler handler = (RawProductHandler) this.handlers.get(ContentType.RAW_PRODUCT);
        if (handler != null) {
            try {
                RawProduct rawProduct = this.director.getRawProductFromDto(inputRawProductDTO);
                return handler.saveContent(rawProduct);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else throw new IllegalStateException("Attempt at creating a [RawProduct]" +
                " when a [RawProductHandler] is not available for this [ContentFacade] instance");
    }

    public int saveTransformedProduct(InputTransformedProductDTO inputTransformedProductDTO) {
        TransformedProductHandler handler = (TransformedProductHandler) this.handlers.get(ContentType.TRANSFORMED_PRODUCT);
        if (handler != null) {
            try {
                TransformedProduct transformedProduct = this.director.getTransformedProductFromDto(inputTransformedProductDTO);
                return handler.saveContent(transformedProduct);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else throw new IllegalStateException("Attempt at creating a [TransformedProduct]" +
                " when a [TransformedProductHandler] is not available for this [ContentFacade] instance");
    }

    public int saveProductPackage(InputProductPackageDTO inputProductPackageDTO) {
        ProductPackageHandler handler = (ProductPackageHandler) this.handlers.get(ContentType.PRODUCT_PACKAGE);
        if (handler != null) {
            try {
                ProductPackage productPackage = this.director.getProductPackageFromDto(inputProductPackageDTO);
                this.addProductsToPackage(productPackage,inputProductPackageDTO.packageElements());
                return handler.saveContent(productPackage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else
            throw new IllegalStateException("Attempt at creating a [ProductPackage]" +
                    " when a [ProductPackageHandler] is not available for this [ContentFacade] instance");
    }

    /**
     * Support method used to add products to a product package.
     *
     * @param productPackage the product package that will contain the products.
     * @param productPackageElements the minimal product information that are required in order to add
     *                               products to the product package.
     */
    private void addProductsToPackage(ProductPackage productPackage,
                                      List<InputProductPackageElementDTO> productPackageElements) {
        RawProductHandler rpHandler = (RawProductHandler) this.handlers.get(ContentType.RAW_PRODUCT);
        TransformedProductHandler tpHandler = (TransformedProductHandler) this.handlers.get(ContentType.TRANSFORMED_PRODUCT);
        if (rpHandler == null || tpHandler == null)
            throw new IllegalStateException("Rejected attempt at adding products to a product package due to missing product handlers");
        for (InputProductPackageElementDTO dto : productPackageElements) {
            if (dto.productType().equals(ContentType.RAW_PRODUCT.getValue()))
                productPackage.addRawProduct(rpHandler.findContentById(dto.productId()));
            else if (dto.productType().equals(ContentType.TRANSFORMED_PRODUCT.getValue()))
                productPackage.addTransformedProduct(tpHandler.findContentById(dto.productId()));
            else
                throw new IllegalArgumentException("Unsupported product type: " + dto.productType());
        }
    }

    public int saveSale(InputSaleDTO inputSaleDTO) {
        SaleHandler handler = (SaleHandler) this.handlers.get(ContentType.SALE);
        if (handler != null) {
            try {
                Sale sale = this.director.getSaleFromDto(inputSaleDTO);
                return handler.saveContent(sale);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else
            throw new IllegalStateException("Attempt at creating a [Sale]" +
                    " when a [SaleHandler] is not available for this [ContentFacade] instance");
    }

    public int saveTransformationProcess(InputTransformationProcessDTO inputTransformationProcessDTO) {
        TransformationProcessHandler handler = (TransformationProcessHandler) this.handlers.get(ContentType.TRANSFORMATION_PROCESS);
        if (handler != null) {
            try {
                TransformationProcess process = this.director.getTransformationProcessFromDto(inputTransformationProcessDTO);
                return handler.saveContent(process);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else
            throw new IllegalStateException("Attempt at creating a [TransformationProcess]" +
                    " when a [TransformationProcessHandler] is not available for this [ContentFacade] instance");
    }

    // Read

    public Identifiable findContentByIdAndType(int id, ContentType contentType) {
        ContentHandler<? extends Content> contentHandler = this.handlers.get(contentType);
        if (contentHandler != null) {
            try {
                return contentHandler.findContentById(id).getOutputDTO();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else
            throw new IllegalStateException("Attempt at finding a [Content] of a certain [ContentType]" +
                    " when the [ContentHandler] for said [ContentType] is not available for this [ContentFacade] instance");
    }

    public List<Identifiable> findAllContentsOfContentType(ContentType contentType) {
        ContentHandler<? extends Content> contentHandler = this.handlers.get(contentType);
        if (contentHandler != null) {
            try {
                return contentHandler.findAllContents().stream()
                        .map(Content::getOutputDTO)
                        .toList();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else
            throw new IllegalStateException("Attempt at finding all [Content] of a certain [ContentType]" +
                    " when the [ContentHandler] for said [ContentType] is not available for this [ContentFacade] instance");
    }

    // TODO review
    /**
     * Returns a list of contents filtered via a content search filter.
     *
     * @param filter the filter to apply.
     * @return a list of contents filtered via a content search filter.
     */
    public List<Identifiable> findAllContentsFiltered(ContentSearchFilterDTO filter) {
        ContentHandler<? extends Content> contentHandler = this.handlers.get(filter.contentType());
        if (contentHandler != null) {
            try {
                List<? extends Content> contents = contentHandler.findAllContents();
                if (filter.contentName() != null)
                    contents = contents.stream().filter((c) -> c.getName().equals(filter.contentName())).toList();
                if (filter.authorName() != null)
                    contents = contents.stream().filter((c) -> c.getName().equals(filter.authorName())).toList();
                return contents.stream().map(Content::getOutputDTO).toList();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else
            throw new IllegalStateException("Attempt at finding all [Content] of a certain [ContentType]" +
                    " that respect a certain search filter when the [ContentHandler] for said [ContentType]" +
                    " is not available for this [ContentFacade] instance");
    }

    // Update

    public String approveContent(int id, ContentType contentType, boolean approvalChoice) {
        ContentHandler<? extends Content> contentHandler = this.handlers.get(contentType);
        if (contentHandler != null) {
            try {
                return contentHandler.approveContent(id, approvalChoice);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else
            throw new IllegalStateException("Attempt at Approving/Rejecting a [Content] of a certain [ContentType]" +
                    " when the [ContentHandler] for said [ContentType] is not available for this [ContentFacade] instance");
    }

    public int buyFromSale(int saleId, int quantity) {
        SaleHandler saleHandler = (SaleHandler) this.handlers.get(ContentType.SALE);
        if (saleHandler != null) {
            try {
                return saleHandler.buyFromSale(saleId, quantity);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else
            throw new IllegalStateException("Attempt at buying from a [Sale]" +
                    " when the [SaleHandler] is not available for this [ContentFacade] instance");
    }

    public int updateSaleQuantity(int saleId, int quantity) {
        SaleHandler saleHandler = (SaleHandler) this.handlers.get(ContentType.SALE);
        if (saleHandler != null) {
            try {
                return saleHandler.updateSaleQuantity(saleId, quantity);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else
            throw new IllegalStateException("Attempt at updating a [Sale] quantity" +
                    " when the [SaleHandler] is not available for this [ContentFacade] instance");
    }

}
