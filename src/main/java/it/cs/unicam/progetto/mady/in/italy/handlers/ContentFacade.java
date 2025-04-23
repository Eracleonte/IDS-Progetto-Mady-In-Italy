package it.cs.unicam.progetto.mady.in.italy.handlers;

import it.cs.unicam.progetto.mady.in.italy.abstractions.Identifiable;
import it.cs.unicam.progetto.mady.in.italy.dto.input.*;
import it.cs.unicam.progetto.mady.in.italy.model.contentbuilders.ContentBuilderDirector;
import it.cs.unicam.progetto.mady.in.italy.model.contents.Content;
import it.cs.unicam.progetto.mady.in.italy.model.contents.ContentType;
import it.cs.unicam.progetto.mady.in.italy.model.contents.products.Product;
import it.cs.unicam.progetto.mady.in.italy.model.contents.products.productpackages.ProductPackage;
import it.cs.unicam.progetto.mady.in.italy.model.contents.products.singles.RawProduct;
import it.cs.unicam.progetto.mady.in.italy.model.contents.products.singles.TransformedProduct;
import it.cs.unicam.progetto.mady.in.italy.model.contents.sale.Sale;
import it.cs.unicam.progetto.mady.in.italy.model.contents.transformationprocesses.TransformationProcess;
import it.cs.unicam.progetto.mady.in.italy.model.supplychain.SupplyChainPoint;
import it.cs.unicam.progetto.mady.in.italy.model.user.Role;
import it.cs.unicam.progetto.mady.in.italy.model.user.User;
import it.cs.unicam.progetto.mady.in.italy.repos.supplychain.SupplyChainPointManagementRepository;
import it.cs.unicam.progetto.mady.in.italy.repos.supplychain.SupplyChainPointRepository;
import it.cs.unicam.progetto.mady.in.italy.repos.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a facade for general content handling related behaviours.
 */
@Service
public class ContentFacade {

    // Only used for behaviour access purposes
    private final UserRepository userRepository;

    // Only used for creation purposes, to check if a supply chain point exists for the id specified by an input_X_DTO
    private final SupplyChainPointRepository supplyChainPointRepository;

    // Only user for creation purposes, to check if a supply chain point is managed by a certain user
    private final SupplyChainPointManagementRepository supplyChainPointManagementRepository;

    private final Map<ContentType, ContentHandler<? extends Content>> contentHandlers;

    private final ContentBuilderDirector director;

    @Autowired
    public ContentFacade(UserRepository userRepository,
                         SupplyChainPointRepository supplyChainPointRepository,
                         SupplyChainPointManagementRepository supplyChainPointManagementRepository,
                         List<ContentHandler<? extends Content>> contentHandlers,
                         ContentBuilderDirector director) {
        if (contentHandlers == null)
            throw new NullPointerException("handlers is null");
        if (contentHandlers.isEmpty())
            throw new IllegalArgumentException("handlers is empty");
        if (director == null)
            throw new NullPointerException("director is null");
        List<ContentHandler<? extends Content>> validHandlers = List.copyOf(contentHandlers.stream().filter(Objects::nonNull).toList());
        for (ContentHandler<? extends Content> handler : validHandlers) {
            if (! director.supportedContentTypes().contains(handler.supports()))
                throw new IllegalStateException("Attempt at creating a [ContentFacade] where a [ContentHandler] " +
                        "does not have a corresponding [ContentBuilder] for save operations");
        }
        this.userRepository = userRepository;
        this.supplyChainPointRepository = supplyChainPointRepository;
        this.supplyChainPointManagementRepository = supplyChainPointManagementRepository;
        this.contentHandlers = Map.copyOf(validHandlers.stream().collect(Collectors.toMap(ContentHandler::supports, h -> h)));
        this.director = director;
    }

    // Create
    // TODO check if to be created contents are identical to already persisted ones

    public int saveRawProduct(InputRawProductDTO inputRawProductDTO, int userId) {
        RawProductHandler handler = (RawProductHandler) this.contentHandlers.get(ContentType.RAW_PRODUCT);
        if (handler != null) {
            try {
                User user = checkIfUserExists(userId);
                checkIfUserHasRequiredAuthorization(user, Role.getProducer());
                SupplyChainPoint supplyChainPoint = this.checkIfSupplyChainPointExists(inputRawProductDTO.supplyChainPointId());
                checkIfRecoveredSupplyChainPointIsSpecializedInProduction(supplyChainPoint);
                checkIfRecoveredSupplyChainPointIsManagedByTheUser(supplyChainPoint,user);
                RawProduct rawProduct = this.director.getRawProductFromDto(inputRawProductDTO,supplyChainPoint);
                return handler.saveContent(rawProduct);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else throw new IllegalStateException("Attempt at creating a [RawProduct]" +
                " when a [RawProductHandler] is not available for this [ContentFacade] instance");
    }

    public int saveTransformedProduct(InputTransformedProductDTO inputTransformedProductDTO, int userId) {
        TransformedProductHandler handler = (TransformedProductHandler) this.contentHandlers.get(ContentType.TRANSFORMED_PRODUCT);
        if (handler != null) {
            try {
                User user = checkIfUserExists(userId);
                checkIfUserHasRequiredAuthorization(user, Role.getTransformer());
                SupplyChainPoint supplyChainPoint = this.checkIfSupplyChainPointExists(inputTransformedProductDTO.supplyChainPointId());
                checkIfRecoveredSupplyChainPointIsSpecializedInTransformation(supplyChainPoint);
                checkIfRecoveredSupplyChainPointIsManagedByTheUser(supplyChainPoint,user);
                checkIfTransformationProcessExists(inputTransformedProductDTO.transformationProcessId(),
                        inputTransformedProductDTO.supplyChainPointId(),user);
                TransformedProduct transformedProduct = this.director.getTransformedProductFromDto(inputTransformedProductDTO,supplyChainPoint);
                return handler.saveContent(transformedProduct);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else throw new IllegalStateException("Attempt at creating a [TransformedProduct]" +
                " when a [TransformedProductHandler] is not available for this [ContentFacade] instance");
    }

    /**
     *
     * Checks if the transformation process exists.
     *
     * @param transformationProcessId the id of the transformation process.
     * @param supplyChainPointId the id of the supply chain point.
     * @param user the user.
     */
    private void checkIfTransformationProcessExists(int transformationProcessId, int supplyChainPointId, User user) {
        TransformationProcess transformationProcess =
                (TransformationProcess) this.contentHandlers.get(ContentType.TRANSFORMATION_PROCESS).findContentById(transformationProcessId);
        if (transformationProcess == null)
            throw new IllegalStateException("Transformation process does not exist");
        if (transformationProcess.getSupplyChainPoint().getId() != supplyChainPointId)
            throw new IllegalStateException("Transformation process is not associated with supply chain point");
        if (!Objects.equals(transformationProcess.getAuthor().toUpperCase(), user.getUsername().toUpperCase()))
            throw new IllegalStateException("Transformation process is not associated with user");
    }

    public int saveProductPackage(InputProductPackageDTO inputProductPackageDTO, int userId) {
        ProductPackageHandler handler = (ProductPackageHandler) this.contentHandlers.get(ContentType.PRODUCT_PACKAGE);
        if (handler != null) {
            try {
                User user = checkIfUserExists(userId);
                checkIfUserHasRequiredAuthorization(user, Role.getDistributor());
                SupplyChainPoint supplyChainPoint = this.checkIfSupplyChainPointExists(inputProductPackageDTO.supplyChainPointId());
                checkIfRecoveredSupplyChainPointIsSpecializedInDistribution(supplyChainPoint);
                checkIfRecoveredSupplyChainPointIsManagedByTheUser(supplyChainPoint,user);
                ProductPackage productPackage = this.director.getProductPackageFromDto(inputProductPackageDTO,supplyChainPoint);
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
        RawProductHandler rpHandler = (RawProductHandler) this.contentHandlers.get(ContentType.RAW_PRODUCT);
        TransformedProductHandler tpHandler = (TransformedProductHandler) this.contentHandlers.get(ContentType.TRANSFORMED_PRODUCT);
        if (rpHandler == null || tpHandler == null)
            throw new IllegalStateException("Rejected attempt at adding products to a product package due to missing product handlers");
        for (InputProductPackageElementDTO dto : productPackageElements) {
            if (dto.productType().equals(ContentType.RAW_PRODUCT))
                productPackage.addRawProduct(rpHandler.findContentById(dto.productId()));
            else if (dto.productType().equals(ContentType.TRANSFORMED_PRODUCT))
                productPackage.addTransformedProduct(tpHandler.findContentById(dto.productId()));
            else
                throw new IllegalArgumentException("Unsupported product type: " + dto.productType());
        }
    }

    public int saveSale(InputSaleDTO inputSaleDTO, int userId) {
        SaleHandler handler = (SaleHandler) this.contentHandlers.get(ContentType.SALE);
        if (handler != null) {
            try {
                User user = checkIfUserExists(userId);
                checkIfUserHasAtLeastOneOfTheAuthorizations(user,List.of(Role.getProducer(),Role.getTransformer(),Role.getDistributor()));
                SupplyChainPoint supplyChainPoint = this.checkIfSupplyChainPointExists(inputSaleDTO.supplyChainPointId());
                checkIfRecoveredSupplyChainPointIsSpecializedInResale(supplyChainPoint);
                checkProductExistenceAndAuthor(inputSaleDTO.productId(),inputSaleDTO.productType(),user.getUsername());
                Sale sale = this.director.getSaleFromDto(inputSaleDTO,supplyChainPoint);
                return handler.saveContent(sale);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else
            throw new IllegalStateException("Attempt at creating a [Sale]" +
                    " when a [SaleHandler] is not available for this [ContentFacade] instance");
    }

    private void checkProductExistenceAndAuthor(int productId,
                                                ContentType contentType,
                                                String author) {
        Content toCheck = this.contentHandlers.get(contentType).findContentById(productId);
        if (toCheck == null)
            throw new IllegalStateException("Product does not exist");
        if (!toCheck.getAuthor().equalsIgnoreCase(author))
            throw new IllegalStateException("Product is not associated with required author");
    }

    public int saveTransformationProcess(InputTransformationProcessDTO inputTransformationProcessDTO, int userId) {
        TransformationProcessHandler handler = (TransformationProcessHandler) this.contentHandlers.get(ContentType.TRANSFORMATION_PROCESS);
        if (handler != null) {
            try {
                User user = checkIfUserExists(userId);
                checkIfUserHasRequiredAuthorization(user, Role.getTransformer());
                SupplyChainPoint supplyChainPoint = this.checkIfSupplyChainPointExists(inputTransformationProcessDTO.supplyChainPointId());
                checkIfRecoveredSupplyChainPointIsSpecializedInTransformation(supplyChainPoint);
                checkIfRecoveredSupplyChainPointIsManagedByTheUser(supplyChainPoint,user);
                TransformationProcess process = this.director.getTransformationProcessFromDto(inputTransformationProcessDTO,supplyChainPoint);
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

    public Identifiable findContentByIdAndContentType(int id, ContentType contentType) {
        ContentHandler<? extends Content> contentHandler = this.contentHandlers.get(contentType);
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

    /**
     * Gets all contents of a certain type based on the approval status.
     *
     * @param contentType the type of contents researched.
     * @param approved is set to true if only approved contents are subject of the retrieval operation,
     *                 is set to false if only contents yet to be approved are subject of the retrieval operation.
     * @return a List of Identifiable.
     */
    public List<Identifiable> findAllContentsOfContentType(ContentType contentType, boolean approved) {
        ContentHandler<? extends Content> contentHandler = this.contentHandlers.get(contentType);
        if (contentHandler != null) {
            try {
                return contentHandler.findAllContents().stream()
                        .filter((c) -> c.isApproved() == approved)
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

    /**
     * Returns a list of contents filtered via a content search filter.
     *
     * @param filter the filter to apply.
     * @return a list of contents filtered via a content search filter.
     */
    public List<Identifiable> findAllContentsFiltered(ContentSearchFilterDTO filter) {
        ContentHandler<? extends Content> contentHandler = this.contentHandlers.get(filter.contentType());
        if (contentHandler != null) {
            try {
                List<? extends Content> contents = contentHandler.findAllContents();
                if (filter.supplyChainPointId() != null)
                    contents = contents.stream().filter((c) -> c.getSupplyChainPoint().getId() == filter.supplyChainPointId()).toList();
                if (filter.contentName() != null)
                    contents = contents.stream().filter((c) -> c.getName().toUpperCase().contains(filter.contentName().toUpperCase())).toList();
                if (filter.authorName() != null)
                    contents = contents.stream().filter((c) -> c.getAuthor().equalsIgnoreCase(filter.authorName())).toList();
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

    public String approveContent(int id, ContentType contentType, boolean approvalChoice, int curatorId) {
        ContentHandler<? extends Content> contentHandler = this.contentHandlers.get(contentType);
        if (contentHandler != null) {
            try {
                User user = checkIfUserExists(curatorId);
                checkIfUserHasRequiredAuthorization(user, Role.getCurator());
                return contentHandler.approveContent(id, approvalChoice);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else
            throw new IllegalStateException("Attempt at Approving/Rejecting a [Content] of a certain [ContentType]" +
                    " when the [ContentHandler] for said [ContentType] is not available for this [ContentFacade] instance");
    }

    public String buyFromSale(int saleId, int quantity, int buyerId) {
        SaleHandler saleHandler = (SaleHandler) this.contentHandlers.get(ContentType.SALE);
        if (saleHandler != null) {
            try {
                User user = checkIfUserExists(buyerId);
                checkIfUserHasRequiredAuthorization(user, Role.getBuyer());
                return saleHandler.buyFromSale(saleId, quantity);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else
            throw new IllegalStateException("Attempt at buying from a [Sale]" +
                    " when the [SaleHandler] is not available for this [ContentFacade] instance");
    }

    public String updateSaleQuantity(int saleId, int quantity, int sellerId) {
        SaleHandler saleHandler = (SaleHandler) this.contentHandlers.get(ContentType.SALE);
        if (saleHandler != null) {
            try {
                User user = checkIfUserExists(sellerId);
                checkIfUserHasAtLeastOneOfTheAuthorizations(user,List.of(Role.getProducer(),Role.getTransformer(),Role.getDistributor()));
                if (!saleHandler.findContentById(saleId).getAuthor().equals(user.getUsername()))
                    throw new IllegalStateException("Sale has not been created by this user");
                return saleHandler.updateSaleQuantity(saleId, quantity);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else
            throw new IllegalStateException("Attempt at updating a [Sale] quantity" +
                    " when the [SaleHandler] is not available for this [ContentFacade] instance");
    }

    // All utilities

    /**
     *
     * Checks if user has the required authorization.
     *
     * @param user the user subject to the check.
     * @param role the role bearing the authorization
     *              that the user subject to the check is required to have.
     */
    private void checkIfUserHasRequiredAuthorization(User user, Role role) {
        Optional.ofNullable(role)
                .filter(r -> user.getRoles().contains(r))
                .orElseThrow(() -> new IllegalStateException("User has no required authorization for role: " + role));
    }

    /**
     *
     * Checks if user has at least one of the required authorizations.
     *
     * @param user the user subject to the check.
     * @param roles the roles bearing the authorizations.
     */
    private void checkIfUserHasAtLeastOneOfTheAuthorizations(User user, List<Role> roles) {
        boolean hasAtLeastOne = roles.stream()
                .anyMatch(role -> user.getRoles().contains(role));
        if (!hasAtLeastOne)
            throw new IllegalStateException("User lacks all required authorizations: " + roles);
    }

    /**
     *
     * Checks if there's a user for the given id and returns it.
     *
     * @param id the supposed user id.
     * @throws IllegalStateException if there is not a user for the given id
     *                               or the recovered user is not approved.
     * @return the user with the given id if it exists.
     */
    private User checkIfUserExists(int id) {
        User toReturn = userRepository.findById(id).orElse(null);
        if (toReturn == null)
            throw new IllegalStateException("User with Id: " + id + " not found");
        if (!toReturn.isApproved())
            throw new IllegalStateException("User with Id: " + id + " is not approved");
        else
            return toReturn;
    }

    /**
     *
     * Support method for raw product creation.
     * Checks if the given supply chain point is specialized in production.
     *
     * @param supplyChainPoint the supply chain point to check.
     * @throws IllegalStateException if the supply chain point is not specialized in production.
     */
    private void checkIfRecoveredSupplyChainPointIsSpecializedInProduction(SupplyChainPoint supplyChainPoint) {
        if (!supplyChainPoint.isProduction())
            throw new IllegalStateException("Supply chain point with id " + supplyChainPoint.getId() +
                    " is not specialized in Production and cannot be subject to raw product creation");
    }

    /**
     *
     * Support method for transformed product and transformation processes creation.
     * Checks if the given supply chain point is specialized in transformation.
     *
     * @param supplyChainPoint the supply chain point to check.
     * @throws IllegalStateException if the supply chain point is not specialized in transformation.
     */
    private void checkIfRecoveredSupplyChainPointIsSpecializedInTransformation(SupplyChainPoint supplyChainPoint) {
        if (!supplyChainPoint.isTransformation())
            throw new IllegalStateException("Supply chain point with id " + supplyChainPoint.getId() +
                    " is not specialized in Transformation and cannot be subject to transformed product and transformation processes creation");
    }

    /**
     *
     * Support method for product package creation.
     * Checks if the given supply chain point is specialized in distribution.
     *
     * @param supplyChainPoint the supply chain point to check.
     * @throws IllegalStateException if the supply chain point is not specialized in distribution.
     */
    private void checkIfRecoveredSupplyChainPointIsSpecializedInDistribution(SupplyChainPoint supplyChainPoint) {
        if (!supplyChainPoint.isDistribution())
            throw new IllegalStateException("Supply chain point with id " + supplyChainPoint.getId() +
                    " is not specialized in Distribution and cannot be subject to product package creation");
    }

    /**
     *
     * Support method for sale creation.
     * Checks if the given supply chain point is specialized in resale.
     *
     * @param supplyChainPoint the supply chain point to check.
     * @throws IllegalStateException if the supply chain point is not specialized in resale.
     */
    private void checkIfRecoveredSupplyChainPointIsSpecializedInResale(SupplyChainPoint supplyChainPoint) {
        if (!supplyChainPoint.isResale())
            throw new IllegalStateException("Supply chain point with id " + supplyChainPoint.getId() +
                    " is not specialized in Resale and cannot be subject to sale creation");
    }

    /**
     *
     * Checks if there's a supply chain point for the given id and returns it for content creation.
     *
     * @param id the supposed supply chain point id.
     * @throws IllegalStateException if there is not a supply chain point for the given id
     *                               or the recovered supply chain point is not approved.
     * @return the supply chain point with the given id if it exists.
     */
    private SupplyChainPoint checkIfSupplyChainPointExists(int id) {
        SupplyChainPoint toReturn = this.supplyChainPointRepository.findById(id).orElse(null);
        if (toReturn == null)
            throw new IllegalStateException("Supply chain point with id " + id + " not found");
        if (!toReturn.isApproved())
            throw new IllegalStateException("Supply chain point with id " + id + " is not approved and cannot be subject to content creation");
        else
            return toReturn;
    }

    /**
     *
     * Checks if the supplyChainPoint is managed by the user.
     *
     * @param supplyChainPoint the supply chain point.
     * @param user the user.
     * @throws IllegalStateException if no management is found.
     */
    private void checkIfRecoveredSupplyChainPointIsManagedByTheUser(SupplyChainPoint supplyChainPoint, User user) {
        if (supplyChainPointManagementRepository
                .findSupplyChainPointManagementBySupplyChainPointIdAndUserId(supplyChainPoint.getId(), user.getId()).isEmpty())
            throw new IllegalStateException("No supply chain point management found for: SupplyChainPointId = "
                    + supplyChainPoint.getId() + " UserId: " + user.getId());
    }

}
