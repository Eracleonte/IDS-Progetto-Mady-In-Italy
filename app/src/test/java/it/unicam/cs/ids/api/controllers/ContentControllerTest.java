package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.input.*;
import it.unicam.cs.ids.api.handlers.*;
import it.unicam.cs.ids.api.model.builder.contentbuilders.ContentBuilder;
import it.unicam.cs.ids.api.model.builder.contentbuilders.ContentBuilderDirector;
import it.unicam.cs.ids.api.model.builder.contentbuilders.productbuilder.ProductPackageBuilder;
import it.unicam.cs.ids.api.model.builder.contentbuilders.productbuilder.RawProductBuilder;
import it.unicam.cs.ids.api.model.builder.contentbuilders.productbuilder.TransformedProductBuilder;
import it.unicam.cs.ids.api.model.builder.contentbuilders.salebuilder.SaleBuilder;
import it.unicam.cs.ids.api.model.builder.contentbuilders.transformationprocessbuilder.TransformationProcessBuilder;
import it.unicam.cs.ids.api.model.contents.Content;
import it.unicam.cs.ids.api.model.contents.ContentType;
import it.unicam.cs.ids.api.repos.content.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// IMPORTANT NOTE: tests work if run singularly due to issues with id handling.

class ContentControllerTest {

    private ContentController controllerWithFacade;

    // Used to watch how the system handles operations when core system elements are not present
    private ContentController controllerWithFacadeWithMissingDependencies;

    // Used to watch how the system handles operations when core system elements are not present
    private ContentFacade facadeWithMissingDependencies;

    private InputRawProductDTO inputRawProductDTO;

    private InputTransformedProductDTO inputTransformedProductDTO;

    private InputProductPackageDTO inputProductPackageDTO;

    private InputSaleDTO inputSaleDTO;

    private InputTransformationProcessDTO inputTransformationProcessDTO;

    @BeforeEach
    void setUp() {

        // Content Facade Setup

        List<ContentHandler<? extends Content>> handlers = new ArrayList<>();
        handlers.add(new RawProductHandler(RawProductRepository.getInstance()));
        handlers.add(new TransformedProductHandler(TransformedProductRepository.getInstance()));
        handlers.add(new ProductPackageHandler(ProductPackageRepository.getInstance()));
        handlers.add(new SaleHandler(SaleRepository.getInstance()));
        handlers.add(new TransformationProcessHandler(TransformationProcessRepository.getInstance()));

        List<ContentBuilder<? extends Content>> contentBuilders = new ArrayList<>();
        contentBuilders.add(new RawProductBuilder());
        contentBuilders.add(new TransformedProductBuilder());
        contentBuilders.add(new ProductPackageBuilder());
        contentBuilders.add(new SaleBuilder());
        contentBuilders.add(new TransformationProcessBuilder());

        ContentBuilderDirector contentBuilderDirector = new ContentBuilderDirector(contentBuilders);

        ContentFacade facade = new ContentFacade(handlers, contentBuilderDirector);

        controllerWithFacade = new ContentController(facade);

        // Input Raw Product DTO Setup

        inputRawProductDTO = new InputRawProductDTO(
                1,
                "test_name",
                "test_description",
                "test_author",
                "test_certification",
                "test_variety",
                "test_production_method"
        );

        // Input Transformed Product DTO Setup
        inputTransformedProductDTO = new InputTransformedProductDTO(1,
                "test_name",
                "test_description",
                "test_author",
                "test_certification",
                "test_variety",
                1
        );

        // Input Product Package DTO Setup

        List<InputProductPackageElementDTO> inputProductPackageElementDTOS = new ArrayList<>();
        inputProductPackageElementDTOS.add
                (new InputProductPackageElementDTO(1, ContentType.RAW_PRODUCT));
        inputProductPackageElementDTOS.add
                (new InputProductPackageElementDTO(1, ContentType.TRANSFORMED_PRODUCT));

        inputProductPackageDTO = new InputProductPackageDTO(
                1,
                "test_name",
                "test_description",
                "test_author",
                inputProductPackageElementDTOS
        );

        // Input Sale DTO Setup

        inputSaleDTO = InputSaleDTO.getRawProductInputSaleDto(
                1,
                1,
                "test_name",
                "test_description",
                "test_author",
                1.0f,
                10
        );

        // Input Transformation Process DTO Setup

        inputTransformationProcessDTO = new InputTransformationProcessDTO(1,
                "test_name",
                "test_description",
                "test_author",
                "test_certification",
                "test_phases"
        );

    }

    @Disabled
    @Test
    void saveRawProduct() {
        Assertions.assertThrows(NullPointerException.class, () -> controllerWithFacade.addNewRawProduct(null));
        // Initial check of raw products status in the system
        Assertions.assertEquals(0,controllerWithFacade.getAllContentsOfContentType(ContentType.RAW_PRODUCT,true).size());
        Assertions.assertEquals(0,controllerWithFacade.getAllContentsOfContentType(ContentType.RAW_PRODUCT,false).size());
        // Insertion
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewRawProduct(inputRawProductDTO));
        // Checking if system has been updated with a new raw product
        Assertions.assertEquals(1,controllerWithFacade.getAllContentsOfContentType(ContentType.RAW_PRODUCT,false).size());
        // Printing get results
        System.out.println(controllerWithFacade.getContentsByIdAndContentType(1,ContentType.RAW_PRODUCT).toString());
    }

    @Disabled
    @Test
    void failedSaveRawProduct() {
        List<ContentHandler<? extends Content>> handlers = new ArrayList<>();
        handlers.add(new TransformedProductHandler(TransformedProductRepository.getInstance()));
        List<ContentBuilder<? extends Content>> contentBuilders = new ArrayList<>();
        contentBuilders.add(new TransformedProductBuilder());
        ContentBuilderDirector contentBuilderDirector = new ContentBuilderDirector(contentBuilders);
        facadeWithMissingDependencies = new ContentFacade(handlers,contentBuilderDirector);
        controllerWithFacadeWithMissingDependencies = new ContentController(facadeWithMissingDependencies);

        Assertions.assertThrows(Exception.class,() -> controllerWithFacadeWithMissingDependencies.addNewRawProduct(inputRawProductDTO));
    }

    @Disabled
    @Test
    void saveTransformedProduct() {
        Assertions.assertThrows(NullPointerException.class, () -> controllerWithFacade.addNewTransformedProduct(null));
        // Initial check of transformed products status in the system
        Assertions.assertEquals(0,controllerWithFacade.getAllContentsOfContentType(ContentType.TRANSFORMED_PRODUCT,true).size());
        Assertions.assertEquals(0,controllerWithFacade.getAllContentsOfContentType(ContentType.TRANSFORMED_PRODUCT,false).size());
        // Insertion
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewTransformedProduct(inputTransformedProductDTO));
        // Checking if system has been updated with a new transformed product
        Assertions.assertEquals(1,controllerWithFacade.getAllContentsOfContentType(ContentType.TRANSFORMED_PRODUCT,false).size());
        // Printing get results
        System.out.println(controllerWithFacade.getContentsByIdAndContentType(1,ContentType.TRANSFORMED_PRODUCT).toString());
    }

    @Disabled
    @Test
    void failedSaveTransformedProduct() {
        List<ContentHandler<? extends Content>> handlers = new ArrayList<>();
        handlers.add(new RawProductHandler(RawProductRepository.getInstance()));
        List<ContentBuilder<? extends Content>> contentBuilders = new ArrayList<>();
        contentBuilders.add(new RawProductBuilder());
        ContentBuilderDirector contentBuilderDirector = new ContentBuilderDirector(contentBuilders);
        facadeWithMissingDependencies = new ContentFacade(handlers,contentBuilderDirector);
        controllerWithFacadeWithMissingDependencies = new ContentController(facadeWithMissingDependencies);

        Assertions.assertThrows(Exception.class,() -> controllerWithFacadeWithMissingDependencies.addNewTransformedProduct(inputTransformedProductDTO));
    }

    @Disabled
    @Test
    void saveProductPackage() {
        Assertions.assertThrows(NullPointerException.class, () -> controllerWithFacade.addNewProductPackage(null));
        // Insertion of a new raw product
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewRawProduct(inputRawProductDTO));
        // Insertion of a new transformed product
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewTransformedProduct(inputTransformedProductDTO));
        // Initial check of product packages status in the system
        Assertions.assertEquals(0,controllerWithFacade.getAllContentsOfContentType(ContentType.PRODUCT_PACKAGE,true).size());
        Assertions.assertEquals(0,controllerWithFacade.getAllContentsOfContentType(ContentType.PRODUCT_PACKAGE,false).size());
        // Insertion of a new product package
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewProductPackage(inputProductPackageDTO));
        // Checking if system has been updated with a new product package
        Assertions.assertEquals(1,controllerWithFacade.getAllContentsOfContentType(ContentType.PRODUCT_PACKAGE,false).size());
        // Printing get results
        System.out.println(controllerWithFacade.getContentsByIdAndContentType(1,ContentType.PRODUCT_PACKAGE).toString());
    }

    @Disabled
    @Test
    void failedSaveProductPackage() {
        List<ContentHandler<? extends Content>> handlers = new ArrayList<>();
        handlers.add(new RawProductHandler(RawProductRepository.getInstance()));
        List<ContentBuilder<? extends Content>> contentBuilders = new ArrayList<>();
        contentBuilders.add(new RawProductBuilder());
        ContentBuilderDirector contentBuilderDirector = new ContentBuilderDirector(contentBuilders);
        facadeWithMissingDependencies = new ContentFacade(handlers,contentBuilderDirector);
        controllerWithFacadeWithMissingDependencies = new ContentController(facadeWithMissingDependencies);

        Assertions.assertThrows(Exception.class,() -> controllerWithFacadeWithMissingDependencies.addNewProductPackage(inputProductPackageDTO));
    }

    @Disabled
    @Test
    void saveSale() {
        Assertions.assertThrows(NullPointerException.class, () -> controllerWithFacade.addNewSale(null));
        // Initial check of sales status in the system
        Assertions.assertEquals(0,controllerWithFacade.getAllContentsOfContentType(ContentType.SALE,true).size());
        Assertions.assertEquals(0,controllerWithFacade.getAllContentsOfContentType(ContentType.SALE,false).size());
        // Insertion of a new sale
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewSale(inputSaleDTO));
        // Checking if system has been updated with a new sale
        Assertions.assertEquals(1,controllerWithFacade.getAllContentsOfContentType(ContentType.SALE,false).size());
        // Printing get results
        System.out.println(controllerWithFacade.getContentsByIdAndContentType(1,ContentType.SALE).toString());
    }

    @Disabled
    @Test
    void failedSaveSale() {
        List<ContentHandler<? extends Content>> handlers = new ArrayList<>();
        handlers.add(new RawProductHandler(RawProductRepository.getInstance()));
        List<ContentBuilder<? extends Content>> contentBuilders = new ArrayList<>();
        contentBuilders.add(new RawProductBuilder());
        ContentBuilderDirector contentBuilderDirector = new ContentBuilderDirector(contentBuilders);
        facadeWithMissingDependencies = new ContentFacade(handlers,contentBuilderDirector);
        controllerWithFacadeWithMissingDependencies = new ContentController(facadeWithMissingDependencies);

        Assertions.assertThrows(Exception.class,() -> controllerWithFacadeWithMissingDependencies.addNewSale(inputSaleDTO));
    }

    @Disabled
    @Test
    void saveTransformationProcess() {
        Assertions.assertThrows(NullPointerException.class, () -> controllerWithFacade.addNewTransformationProcess(null));
        // Initial check of transformation processes status in the system
        Assertions.assertEquals(0,controllerWithFacade.getAllContentsOfContentType(ContentType.TRANSFORMATION_PROCESS,true).size());
        Assertions.assertEquals(0,controllerWithFacade.getAllContentsOfContentType(ContentType.TRANSFORMATION_PROCESS,false).size());
        // Insertion of a new transformation process
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewTransformationProcess(inputTransformationProcessDTO));
        // Checking if system has been updated with a new transformation process
        Assertions.assertEquals(1,controllerWithFacade.getAllContentsOfContentType(ContentType.TRANSFORMATION_PROCESS,false).size());
        // Printing get results
        System.out.println(controllerWithFacade.getContentsByIdAndContentType(1,ContentType.TRANSFORMATION_PROCESS).toString());
    }

    @Disabled
    @Test
    void failedSaveTransformationProcess() {
        List<ContentHandler<? extends Content>> handlers = new ArrayList<>();
        handlers.add(new RawProductHandler(RawProductRepository.getInstance()));
        List<ContentBuilder<? extends Content>> contentBuilders = new ArrayList<>();
        contentBuilders.add(new RawProductBuilder());
        ContentBuilderDirector contentBuilderDirector = new ContentBuilderDirector(contentBuilders);
        facadeWithMissingDependencies = new ContentFacade(handlers,contentBuilderDirector);
        controllerWithFacadeWithMissingDependencies = new ContentController(facadeWithMissingDependencies);

        Assertions.assertThrows(Exception.class,() -> controllerWithFacadeWithMissingDependencies
                .addNewTransformationProcess(inputTransformationProcessDTO));
    }

    @Disabled
    @Test
    void findContentByIdAndContentType() {
        Assertions.assertThrows(NullPointerException.class, () -> controllerWithFacade.getContentsByIdAndContentType(1,null));
        // Insertion
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewRawProduct(inputRawProductDTO));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewTransformedProduct(inputTransformedProductDTO));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewProductPackage(inputProductPackageDTO));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewSale(inputSaleDTO));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewTransformationProcess(inputTransformationProcessDTO));
        // Getting content by id and content type
        System.out.println(controllerWithFacade.getContentsByIdAndContentType(1,ContentType.RAW_PRODUCT).toString());
        System.out.println(controllerWithFacade.getContentsByIdAndContentType(1,ContentType.TRANSFORMED_PRODUCT).toString());
        System.out.println(controllerWithFacade.getContentsByIdAndContentType(1,ContentType.PRODUCT_PACKAGE).toString());
        System.out.println(controllerWithFacade.getContentsByIdAndContentType(1,ContentType.SALE).toString());
        System.out.println(controllerWithFacade.getContentsByIdAndContentType(1,ContentType.TRANSFORMATION_PROCESS).toString());
    }

    @Disabled
    @Test
    void findAllContents() {
        Assertions.assertThrows(NullPointerException.class, () -> controllerWithFacade.getAllContentsOfContentType(null,true));
        // Insertion
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewRawProduct(inputRawProductDTO));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewTransformedProduct(inputTransformedProductDTO));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewProductPackage(inputProductPackageDTO));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewSale(inputSaleDTO));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewTransformationProcess(inputTransformationProcessDTO));
        // Getting all contents
        System.out.println(controllerWithFacade.getAllContentsOfContentType(ContentType.RAW_PRODUCT,false).toString());
        System.out.println(controllerWithFacade.getAllContentsOfContentType(ContentType.TRANSFORMED_PRODUCT,false).toString());
        System.out.println(controllerWithFacade.getAllContentsOfContentType(ContentType.PRODUCT_PACKAGE,false).toString());
        System.out.println(controllerWithFacade.getAllContentsOfContentType(ContentType.SALE,false).toString());
        System.out.println(controllerWithFacade.getAllContentsOfContentType(ContentType.TRANSFORMATION_PROCESS,false).toString());
    }

    @Disabled
    @Test
    void findAllContentsFiltered() {
        Assertions.assertThrows(NullPointerException.class,
                () -> controllerWithFacade.getAllContentsOfContentTypeFiltered(null));
        ContentSearchFilterDTO filterDTO =
                ContentSearchFilterDTO.getRawProductsContentSearchFilterDTO(0,null,"Joe");
        InputRawProductDTO input1 = new InputRawProductDTO(10,"Apple","Tasty",
                "Joe","IGP","Red","This apple is caught when...");
        InputRawProductDTO input2 = new InputRawProductDTO(11,"Milk","Fresh",
                "Bill","IGP","Cow-Milk","To produce this milk we...");
        InputRawProductDTO input3 = new InputRawProductDTO(10,"Orange","Juicy",
                "Joe","IGP","Sicilian","At the start of the summer...");
        InputSaleDTO input4 = InputSaleDTO.getRawProductInputSaleDto(10,1,
                "Melinda Apple","Excelsior","John Melinda",2.09f,10);
        controllerWithFacade.addNewRawProduct(input1);
        controllerWithFacade.addNewRawProduct(input2);
        controllerWithFacade.addNewRawProduct(input3);
        controllerWithFacade.addNewSale(input4);
        // Asserting if the operation results in the return of a list containing 2 raw products (produced by "Joe")
        Assertions.assertEquals(2, controllerWithFacade.getAllContentsOfContentTypeFiltered(filterDTO).size());
    }

    @Disabled
    @Test
    void approveContent() {
        Assertions.assertThrows(NullPointerException.class,
                () -> controllerWithFacade.approveContent(1,null,true));
        // Insertion
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewRawProduct(inputRawProductDTO));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewTransformedProduct(inputTransformedProductDTO));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewProductPackage(inputProductPackageDTO));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewSale(inputSaleDTO));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewTransformationProcess(inputTransformationProcessDTO));
        // Approving content
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.approveContent(1,ContentType.RAW_PRODUCT,true));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.approveContent(1,ContentType.TRANSFORMED_PRODUCT,true));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.approveContent(1,ContentType.PRODUCT_PACKAGE,true));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.approveContent(1,ContentType.SALE,true));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.approveContent(1,ContentType.TRANSFORMATION_PROCESS,true));
        // Checking if content is actually approved
        // If it was rejected it wouldn't actually be possible to find the content by its id and ContentType
        // as, by contract definition, the approveContent method results in the content's deletion in case of rejection.
        System.out.println(controllerWithFacade.getContentsByIdAndContentType(1,ContentType.RAW_PRODUCT).toString());
        System.out.println(controllerWithFacade.getContentsByIdAndContentType(1,ContentType.TRANSFORMED_PRODUCT).toString());
        System.out.println(controllerWithFacade.getContentsByIdAndContentType(1,ContentType.PRODUCT_PACKAGE).toString());
        System.out.println(controllerWithFacade.getContentsByIdAndContentType(1,ContentType.SALE).toString());
        System.out.println(controllerWithFacade.getContentsByIdAndContentType(1,ContentType.TRANSFORMATION_PROCESS).toString());
    }

    @Disabled
    @Test
    void rejectContent() {
        Assertions.assertThrows(NullPointerException.class,
                () -> controllerWithFacade.approveContent(1,null,false));
        // Insertion
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewRawProduct(inputRawProductDTO));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewTransformedProduct(inputTransformedProductDTO));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewProductPackage(inputProductPackageDTO));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewSale(inputSaleDTO));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewTransformationProcess(inputTransformationProcessDTO));
        // Rejecting content
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.approveContent(1,ContentType.RAW_PRODUCT,false));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.approveContent(1,ContentType.TRANSFORMED_PRODUCT,false));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.approveContent(1,ContentType.PRODUCT_PACKAGE,false));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.approveContent(1,ContentType.SALE,false));
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.approveContent(1,ContentType.TRANSFORMATION_PROCESS,false));
        // Checking if content has been rejected.
        // If content has been rejected, it shouldn't be memorized in the system.
        Assertions.assertEquals(0,controllerWithFacade.getAllContentsOfContentType(ContentType.RAW_PRODUCT,false).size());
        Assertions.assertEquals(0,controllerWithFacade.getAllContentsOfContentType(ContentType.TRANSFORMED_PRODUCT,false).size());
        Assertions.assertEquals(0,controllerWithFacade.getAllContentsOfContentType(ContentType.PRODUCT_PACKAGE,false).size());
        Assertions.assertEquals(0,controllerWithFacade.getAllContentsOfContentType(ContentType.SALE,false).size());
        Assertions.assertEquals(0,controllerWithFacade.getAllContentsOfContentType(ContentType.TRANSFORMATION_PROCESS,false).size());
    }

    @Disabled
    @Test
    void buyFromSale() {
        // Insertion
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewSale(inputSaleDTO));
        // Approving content
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.approveContent(1,ContentType.SALE,true));
        // Initial check of sale quantity
        System.out.println(controllerWithFacade.getContentsByIdAndContentType(1,ContentType.SALE).toString());
        // Rejection of buying due to unexpected parameters
        //Assertions.assertThrows(() -> facade.buyFromSale(0,0));
        // Buying from the sale
        Assertions.assertEquals(1,controllerWithFacade.buyFromSale(1,3));
        // Checking if the quantity has been reduced
        System.out.println(controllerWithFacade.getContentsByIdAndContentType(1,ContentType.SALE).toString());
    }

    @Disabled
    @Test
    void updateSaleQuantity() {
        // Insertion
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.addNewSale(inputSaleDTO));
        // Approving content
        Assertions.assertDoesNotThrow(() -> controllerWithFacade.approveContent(1,ContentType.SALE,true));
        // Checking initial sale quantity
        System.out.println(controllerWithFacade.getContentsByIdAndContentType(1,ContentType.SALE).toString());
        // Updating a sale's quantity
        Assertions.assertEquals(1,controllerWithFacade.updateSaleQuantity(1,3));
        // Checking if the sale's quantity has been updated
        System.out.println(controllerWithFacade.getContentsByIdAndContentType(1,ContentType.SALE).toString());
    }

}