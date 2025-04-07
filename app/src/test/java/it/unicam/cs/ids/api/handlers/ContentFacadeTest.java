package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.dto.input.*;
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

class ContentFacadeTest {

    private ContentFacade facade;

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

        facade = new ContentFacade(handlers, contentBuilderDirector);

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
        // Initial check of raw products status in the system
        Assertions.assertEquals(0, facade.findAllContentsOfContentType(ContentType.RAW_PRODUCT).size());
        // Insertion
        Assertions.assertDoesNotThrow(() -> facade.saveRawProduct(inputRawProductDTO));
        // Checking if system has been updated with a new raw product
        Assertions.assertEquals(1, facade.findAllContentsOfContentType(ContentType.RAW_PRODUCT).size());
        // Printing get results
        System.out.println(facade.findContentByIdAndType(1,ContentType.RAW_PRODUCT).toString());
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

        Assertions.assertThrows(Exception.class,() -> facadeWithMissingDependencies.saveRawProduct(inputRawProductDTO));
    }

    @Disabled
    @Test
    void saveTransformedProduct() {
        // Initial check of transformed products status in the system
        Assertions.assertEquals(0, facade.findAllContentsOfContentType(ContentType.TRANSFORMED_PRODUCT).size());
        // Insertion
        Assertions.assertDoesNotThrow(() -> facade.saveTransformedProduct(inputTransformedProductDTO));
        // Checking if system has been updated with a new transformed product
        Assertions.assertEquals(1, facade.findAllContentsOfContentType(ContentType.TRANSFORMED_PRODUCT).size());
        // Printing get results
        System.out.println(facade.findContentByIdAndType(1,ContentType.TRANSFORMED_PRODUCT).toString());
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

        Assertions.assertThrows(Exception.class,() -> facadeWithMissingDependencies.saveTransformedProduct(inputTransformedProductDTO));
    }

    @Disabled
    @Test
    void saveProductPackage() {
        // Insertion of a new raw product
        Assertions.assertDoesNotThrow(() -> facade.saveRawProduct(inputRawProductDTO));
        // Insertion of a new transformed product
        Assertions.assertDoesNotThrow(() -> facade.saveTransformedProduct(inputTransformedProductDTO));
        // Initial check of product packages status in the system
        Assertions.assertEquals(0, facade.findAllContentsOfContentType(ContentType.PRODUCT_PACKAGE).size());
        // Insertion of a new product package
        Assertions.assertDoesNotThrow(() -> facade.saveProductPackage(inputProductPackageDTO));
        // Checking if system has been updated with a new product package
        Assertions.assertEquals(1, facade.findAllContentsOfContentType(ContentType.PRODUCT_PACKAGE).size());
        // Printing get results
        System.out.println(facade.findContentByIdAndType(1,ContentType.PRODUCT_PACKAGE).toString());
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

        Assertions.assertThrows(Exception.class,() -> facadeWithMissingDependencies.saveProductPackage(inputProductPackageDTO));
    }

    @Disabled
    @Test
    void saveSale() {
        // Initial check of sales status in the system
        Assertions.assertEquals(0, facade.findAllContentsOfContentType(ContentType.SALE).size());
        // Insertion of a new sale
        Assertions.assertDoesNotThrow(() -> facade.saveSale(inputSaleDTO));
        // Checking if system has been updated with a new sale
        Assertions.assertEquals(1, facade.findAllContentsOfContentType(ContentType.SALE).size());
        // Printing get results
        System.out.println(facade.findContentByIdAndType(1,ContentType.SALE).toString());
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

        Assertions.assertThrows(Exception.class,() -> facadeWithMissingDependencies.saveSale(inputSaleDTO));
    }

    @Disabled
    @Test
    void saveTransformationProcess() {
        // Initial check of transformation processes status in the system
        Assertions.assertEquals(0, facade.findAllContentsOfContentType(ContentType.TRANSFORMATION_PROCESS).size());
        // Insertion of a new transformation process
        Assertions.assertDoesNotThrow(() -> facade.saveTransformationProcess(inputTransformationProcessDTO));
        // Checking if system has been updated with a new transformation process
        Assertions.assertEquals(1, facade.findAllContentsOfContentType(ContentType.TRANSFORMATION_PROCESS).size());
        // Printing get results
        System.out.println(facade.findContentByIdAndType(1,ContentType.TRANSFORMATION_PROCESS).toString());
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

        Assertions.assertThrows(Exception.class,() -> facadeWithMissingDependencies
                .saveTransformationProcess(inputTransformationProcessDTO));
    }

    @Disabled
    @Test
    void findContentByIdAndType() {
        // Insertion
        Assertions.assertDoesNotThrow(() -> facade.saveRawProduct(inputRawProductDTO));
        Assertions.assertDoesNotThrow(() -> facade.saveTransformedProduct(inputTransformedProductDTO));
        Assertions.assertDoesNotThrow(() -> facade.saveProductPackage(inputProductPackageDTO));
        Assertions.assertDoesNotThrow(() -> facade.saveSale(inputSaleDTO));
        Assertions.assertDoesNotThrow(() -> facade.saveTransformationProcess(inputTransformationProcessDTO));
        // Getting content by id and content type
        System.out.println(facade.findContentByIdAndType(1,ContentType.RAW_PRODUCT).toString());
        System.out.println(facade.findContentByIdAndType(1,ContentType.TRANSFORMED_PRODUCT).toString());
        System.out.println(facade.findContentByIdAndType(1,ContentType.PRODUCT_PACKAGE).toString());
        System.out.println(facade.findContentByIdAndType(1,ContentType.SALE).toString());
        System.out.println(facade.findContentByIdAndType(1,ContentType.TRANSFORMATION_PROCESS).toString());
    }

    @Disabled
    @Test
    void findAllContents() {
        // Insertion
        Assertions.assertDoesNotThrow(() -> facade.saveRawProduct(inputRawProductDTO));
        Assertions.assertDoesNotThrow(() -> facade.saveTransformedProduct(inputTransformedProductDTO));
        Assertions.assertDoesNotThrow(() -> facade.saveProductPackage(inputProductPackageDTO));
        Assertions.assertDoesNotThrow(() -> facade.saveSale(inputSaleDTO));
        Assertions.assertDoesNotThrow(() -> facade.saveTransformationProcess(inputTransformationProcessDTO));
        // Getting all contents
        System.out.println(facade.findContentByIdAndType(1,ContentType.RAW_PRODUCT).toString());
        System.out.println(facade.findContentByIdAndType(1,ContentType.TRANSFORMED_PRODUCT).toString());
        System.out.println(facade.findContentByIdAndType(1,ContentType.PRODUCT_PACKAGE).toString());
        System.out.println(facade.findContentByIdAndType(1,ContentType.SALE).toString());
        System.out.println(facade.findContentByIdAndType(1,ContentType.TRANSFORMATION_PROCESS).toString());
    }

    @Disabled
    @Test
    void findAllContentsFiltered() {
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
        facade.saveRawProduct(input1);
        facade.saveRawProduct(input2);
        facade.saveRawProduct(input3);
        facade.saveSale(input4);
        // Asserting if the operation results in the return of a list containing 2 raw products (produced by "Joe")
        Assertions.assertEquals(2, facade.findAllContentsFiltered(filterDTO).size());
    }

    @Disabled
    @Test
    void approveContent() {
        // Insertion
        Assertions.assertDoesNotThrow(() -> facade.saveRawProduct(inputRawProductDTO));
        Assertions.assertDoesNotThrow(() -> facade.saveTransformedProduct(inputTransformedProductDTO));
        Assertions.assertDoesNotThrow(() -> facade.saveProductPackage(inputProductPackageDTO));
        Assertions.assertDoesNotThrow(() -> facade.saveSale(inputSaleDTO));
        Assertions.assertDoesNotThrow(() -> facade.saveTransformationProcess(inputTransformationProcessDTO));
        // Approving content
        Assertions.assertDoesNotThrow(() -> facade.approveContent(1,ContentType.RAW_PRODUCT,true));
        Assertions.assertDoesNotThrow(() -> facade.approveContent(1,ContentType.TRANSFORMED_PRODUCT,true));
        Assertions.assertDoesNotThrow(() -> facade.approveContent(1,ContentType.PRODUCT_PACKAGE,true));
        Assertions.assertDoesNotThrow(() -> facade.approveContent(1,ContentType.SALE,true));
        Assertions.assertDoesNotThrow(() -> facade.approveContent(1,ContentType.TRANSFORMATION_PROCESS,true));
        // Checking if content is actually approved
        // If it was rejected it wouldn't actually be possible to find the content by its id and ContentType
        // as, by contract definition, the approveContent method results in the content's deletion in case of rejection.
        System.out.println(facade.findContentByIdAndType(1,ContentType.RAW_PRODUCT).toString());
        System.out.println(facade.findContentByIdAndType(1,ContentType.TRANSFORMED_PRODUCT).toString());
        System.out.println(facade.findContentByIdAndType(1,ContentType.PRODUCT_PACKAGE).toString());
        System.out.println(facade.findContentByIdAndType(1,ContentType.SALE).toString());
        System.out.println(facade.findContentByIdAndType(1,ContentType.TRANSFORMATION_PROCESS).toString());
    }

    @Disabled
    @Test
    void rejectContent() {
        // Insertion
        Assertions.assertDoesNotThrow(() -> facade.saveRawProduct(inputRawProductDTO));
        Assertions.assertDoesNotThrow(() -> facade.saveTransformedProduct(inputTransformedProductDTO));
        Assertions.assertDoesNotThrow(() -> facade.saveProductPackage(inputProductPackageDTO));
        Assertions.assertDoesNotThrow(() -> facade.saveSale(inputSaleDTO));
        Assertions.assertDoesNotThrow(() -> facade.saveTransformationProcess(inputTransformationProcessDTO));
        // Rejecting content
        Assertions.assertDoesNotThrow(() -> facade.approveContent(1,ContentType.RAW_PRODUCT,false));
        Assertions.assertDoesNotThrow(() -> facade.approveContent(1,ContentType.TRANSFORMED_PRODUCT,false));
        Assertions.assertDoesNotThrow(() -> facade.approveContent(1,ContentType.PRODUCT_PACKAGE,false));
        Assertions.assertDoesNotThrow(() -> facade.approveContent(1,ContentType.SALE,false));
        Assertions.assertDoesNotThrow(() -> facade.approveContent(1,ContentType.TRANSFORMATION_PROCESS,false));
        // Checking if content has been rejected.
        // If content has been rejected, it shouldn't be memorized in the system.
        Assertions.assertEquals(0,facade.findAllContentsOfContentType(ContentType.RAW_PRODUCT).size());
        Assertions.assertEquals(0,facade.findAllContentsOfContentType(ContentType.TRANSFORMED_PRODUCT).size());
        Assertions.assertEquals(0,facade.findAllContentsOfContentType(ContentType.PRODUCT_PACKAGE).size());
        Assertions.assertEquals(0,facade.findAllContentsOfContentType(ContentType.SALE).size());
        Assertions.assertEquals(0,facade.findAllContentsOfContentType(ContentType.TRANSFORMATION_PROCESS).size());
    }

    @Disabled
    @Test
    void buyFromSale() {
        // Insertion
        Assertions.assertDoesNotThrow(() -> facade.saveSale(inputSaleDTO));
        // Approving content
        Assertions.assertDoesNotThrow(() -> facade.approveContent(1,ContentType.SALE,true));
        // Initial check of sale quantity
        System.out.println(facade.findContentByIdAndType(1,ContentType.SALE).toString());
        // Rejection of buying due to unexpected parameters
        //Assertions.assertThrows(() -> facade.buyFromSale(0,0));
        // Buying from the sale
        Assertions.assertEquals(1,facade.buyFromSale(1,3));
        // Checking if the quantity has been reduced
        System.out.println(facade.findContentByIdAndType(1,ContentType.SALE).toString());
    }

    @Disabled
    @Test
    void updateSaleQuantity() {
        // Insertion
        Assertions.assertDoesNotThrow(() -> facade.saveSale(inputSaleDTO));
        // Approving content
        Assertions.assertDoesNotThrow(() -> facade.approveContent(1,ContentType.SALE,true));
        // Checking initial sale quantity
        System.out.println(facade.findContentByIdAndType(1,ContentType.SALE).toString());
        // Updating a sale's quantity
        Assertions.assertEquals(1,facade.updateSaleQuantity(1,3));
        // Checking if the sale's quantity has been updated
        System.out.println(facade.findContentByIdAndType(1,ContentType.SALE).toString());
    }

}