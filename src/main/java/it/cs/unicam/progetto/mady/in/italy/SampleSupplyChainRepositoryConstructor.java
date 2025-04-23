package it.cs.unicam.progetto.mady.in.italy;

import it.cs.unicam.progetto.mady.in.italy.model.contents.ContentType;
import it.cs.unicam.progetto.mady.in.italy.model.contents.products.productpackages.ProductPackage;
import it.cs.unicam.progetto.mady.in.italy.model.contents.products.singles.RawProduct;
import it.cs.unicam.progetto.mady.in.italy.model.contents.products.singles.TransformedProduct;
import it.cs.unicam.progetto.mady.in.italy.model.contents.sale.Sale;
import it.cs.unicam.progetto.mady.in.italy.model.contents.transformationprocesses.TransformationProcess;
import it.cs.unicam.progetto.mady.in.italy.model.supplychain.SupplyChainPoint;
import it.cs.unicam.progetto.mady.in.italy.model.supplychain.SupplyChainPointManagement;
import it.cs.unicam.progetto.mady.in.italy.model.user.Role;
import it.cs.unicam.progetto.mady.in.italy.model.user.User;
import it.cs.unicam.progetto.mady.in.italy.repos.content.*;
import it.cs.unicam.progetto.mady.in.italy.repos.supplychain.SupplyChainPointManagementRepository;
import it.cs.unicam.progetto.mady.in.italy.repos.supplychain.SupplyChainPointRepository;
import it.cs.unicam.progetto.mady.in.italy.repos.users.RoleRepository;
import it.cs.unicam.progetto.mady.in.italy.repos.users.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SampleSupplyChainRepositoryConstructor {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SupplyChainPointRepository supplyChainPointRepository;
    @Autowired
    private SupplyChainPointManagementRepository pointManagementRepository;
    @Autowired
    private RawProductRepository rawProductRepository;
    @Autowired
    private TransformationProcessRepository transformationProcessRepository;
    @Autowired
    private TransformedProductRepository transformedProductRepository;
    @Autowired
    private ProductPackageRepository productPackageRepository;
    @Autowired
    private SaleRepository saleRepository;

    @PostConstruct
    void init() {
        setUpRoleRepository(roleRepository);
        setUpUsers(userRepository);
        setUpSupplyChainPoints(supplyChainPointRepository,pointManagementRepository);
        setUpRawProduct(rawProductRepository);
        setUpTransformationProcesses(transformationProcessRepository);
        setUpTransformedProducts(transformedProductRepository);
        setUpProductPackages(productPackageRepository);
        setUpSales(saleRepository);
    }

    private Role PRODUCER,TRANSFORMER,DISTRIBUTOR,CURATOR,GUEST,BUYER,ADMINISTRATOR;
    private User PRODUCER1,TRANSFORMER1,DISTRIBUTOR1,CURATOR1,GUEST1,BUYER1,SYSTEM_ADMINISTRATOR;
    private SupplyChainPoint SUPPLY_CHAIN_POINT_1,SUPPLY_CHAIN_POINT_2,SUPPLY_CHAIN_POINT_3;
    private SupplyChainPointManagement MANAGEMENT_1,MANAGEMENT_2,MANAGEMENT_3,MANAGEMENT_4;
    private RawProduct RAW_PRODUCT_1,RAW_PRODUCT_2,RAW_PRODUCT_3;
    private TransformationProcess TRANSFORMATION_PROCESS_1,TRANSFORMATION_PROCESS_2;
    private TransformedProduct TRANSFORMED_PRODUCT_1,TRANSFORMED_PRODUCT_2;
    private ProductPackage PRODUCT_PACKAGE_1;
    private Sale SALE_1,SALE_2,SALE_3,SALE_4;
    
    private void clearRoles(RoleRepository roleRepository) {
        roleRepository.deleteAll();
        roleRepository.flush();
    }

    private void setUpRoleRepository(RoleRepository roleRepository) {
        clearRoles(roleRepository);
        PRODUCER = roleRepository.saveAndFlush(Role.getProducer());
        TRANSFORMER = roleRepository.saveAndFlush(Role.getTransformer());
        DISTRIBUTOR = roleRepository.saveAndFlush(Role.getDistributor());
        CURATOR = roleRepository.saveAndFlush(Role.getCurator());
        GUEST = roleRepository.saveAndFlush(Role.getGuest());
        BUYER = roleRepository.saveAndFlush(Role.getBuyer());
        ADMINISTRATOR = roleRepository.saveAndFlush(Role.getAdministrator());
        roleRepository.flush();
    }

    private void clearUsers(UserRepository userRepository) {
        userRepository.deleteAll();
        userRepository.flush();
    }

    private void setUpUsers(UserRepository userRepository) {
        clearUsers(userRepository);

        PRODUCER1 = new User("Enzo Della Vigna","giaspetto331","enzo.dellavigna@gmail.com");
        TRANSFORMER1 = new User("Alberto Del Fiore","motelhotelnotell","alberto.delfiore@gmail.com");
        DISTRIBUTOR1 = new User("Nora Terzini","fiorellinFiorellino77","nora.terzini@gmail.com");
        CURATOR1 = new User("Alessandro Toraldo","pizzaAllApizza","alessandro.toraldo@gmail.com");
        GUEST1 = new User("Jean","yoSoyZorro732","jean.dujarden@gmail.com");
        BUYER1 = new User("Crazy Dave","zombiesAreEatingMyPlants","crazy.dave@gmail.com");
        SYSTEM_ADMINISTRATOR = new User("Giovanni_admin","dd2_22_04_24","giovanni.senatori@gmail.com");

        PRODUCER1.addRole(PRODUCER);
        PRODUCER1.addRole(TRANSFORMER);
        TRANSFORMER1.addRole(TRANSFORMER);
        DISTRIBUTOR1.addRole(DISTRIBUTOR);
        CURATOR1.addRole(CURATOR);
        GUEST1.addRole(GUEST);
        BUYER1.addRole(BUYER);
        SYSTEM_ADMINISTRATOR.addRole(ADMINISTRATOR);

        PRODUCER1 = userRepository.save(PRODUCER1);
        TRANSFORMER1 = userRepository.save(TRANSFORMER1);
        DISTRIBUTOR1 = userRepository.save(DISTRIBUTOR1);
        CURATOR1 = userRepository.save(CURATOR1);
        GUEST1 = userRepository.save(GUEST1);
        BUYER1 = userRepository.save(BUYER1);
        SYSTEM_ADMINISTRATOR = userRepository.save(SYSTEM_ADMINISTRATOR);

        userRepository.flush();

        userRepository.approve(PRODUCER1.getId());
        userRepository.approve(TRANSFORMER1.getId());
        userRepository.approve(DISTRIBUTOR1.getId());
        userRepository.approve(CURATOR1.getId());
        userRepository.approve(GUEST1.getId());
        userRepository.approve(BUYER1.getId());
        userRepository.approve(SYSTEM_ADMINISTRATOR.getId());

        userRepository.flush();
    }

    private void clearSupplyChainPoints(SupplyChainPointRepository supplyChainPointRepository) {
        supplyChainPointRepository.deleteAll();
        supplyChainPointRepository.flush();
    }

    private void clearSupplyChainPointManagement(SupplyChainPointManagementRepository supplyChainPointManagementRepository) {
        supplyChainPointManagementRepository.deleteAll();
        supplyChainPointManagementRepository.flush();
    }

    private void setUpSupplyChainPoints(SupplyChainPointRepository supplyChainPointRepository,
                                        SupplyChainPointManagementRepository supplyChainPointManagementRepository) {
        clearSupplyChainPoints(supplyChainPointRepository);
        clearSupplyChainPointManagement(supplyChainPointManagementRepository);

        // SUPPLY CHAIN POINT SETUP

        SUPPLY_CHAIN_POINT_1 = new SupplyChainPoint();
        SUPPLY_CHAIN_POINT_2 = new SupplyChainPoint();
        SUPPLY_CHAIN_POINT_3 = new SupplyChainPoint();

        SUPPLY_CHAIN_POINT_1.setLatitude(43.048164f);
        SUPPLY_CHAIN_POINT_1.setLongitude(13.748224f);
        SUPPLY_CHAIN_POINT_1.setName("Fattoria Fratelli Della Vigna");
        SUPPLY_CHAIN_POINT_1.setProduction(true);
        SUPPLY_CHAIN_POINT_1.setTransformation(false);
        SUPPLY_CHAIN_POINT_1.setDistribution(false);
        SUPPLY_CHAIN_POINT_1.setResale(true);

        SUPPLY_CHAIN_POINT_2.setLatitude(43.030600f);
        SUPPLY_CHAIN_POINT_2.setLongitude(13.444060f);
        SUPPLY_CHAIN_POINT_2.setName("Caseificio Del Fiore e Terzini");
        SUPPLY_CHAIN_POINT_2.setProduction(false);
        SUPPLY_CHAIN_POINT_2.setTransformation(true);
        SUPPLY_CHAIN_POINT_2.setDistribution(true);
        SUPPLY_CHAIN_POINT_2.setResale(true);

        SUPPLY_CHAIN_POINT_3.setLatitude(43.031270f);
        SUPPLY_CHAIN_POINT_3.setLongitude(13.444690f);
        SUPPLY_CHAIN_POINT_3.setName("Cantina della Vigna");
        SUPPLY_CHAIN_POINT_3.setProduction(true);
        SUPPLY_CHAIN_POINT_3.setTransformation(true);
        SUPPLY_CHAIN_POINT_3.setDistribution(true);
        SUPPLY_CHAIN_POINT_3.setResale(true);

        SUPPLY_CHAIN_POINT_1 = supplyChainPointRepository.save(SUPPLY_CHAIN_POINT_1);
        SUPPLY_CHAIN_POINT_2 = supplyChainPointRepository.save(SUPPLY_CHAIN_POINT_2);
        SUPPLY_CHAIN_POINT_3 = supplyChainPointRepository.save(SUPPLY_CHAIN_POINT_3);

        supplyChainPointRepository.flush();

        supplyChainPointRepository.approve(SUPPLY_CHAIN_POINT_1.getId());
        supplyChainPointRepository.approve(SUPPLY_CHAIN_POINT_2.getId());
        supplyChainPointRepository.approve(SUPPLY_CHAIN_POINT_3.getId());

        supplyChainPointRepository.flush();

        // MANAGEMENT SETUP

        MANAGEMENT_1 = new SupplyChainPointManagement(SUPPLY_CHAIN_POINT_1,PRODUCER1);
        MANAGEMENT_2 = new SupplyChainPointManagement(SUPPLY_CHAIN_POINT_2,TRANSFORMER1);
        MANAGEMENT_3 = new SupplyChainPointManagement(SUPPLY_CHAIN_POINT_2,DISTRIBUTOR1);
        MANAGEMENT_4 = new SupplyChainPointManagement(SUPPLY_CHAIN_POINT_3,PRODUCER1);

        MANAGEMENT_1 = supplyChainPointManagementRepository.save(MANAGEMENT_1);
        MANAGEMENT_2 = supplyChainPointManagementRepository.save(MANAGEMENT_2);
        MANAGEMENT_3 = supplyChainPointManagementRepository.save(MANAGEMENT_3);
        MANAGEMENT_4 = supplyChainPointManagementRepository.save(MANAGEMENT_4);

        supplyChainPointManagementRepository.flush();

        supplyChainPointManagementRepository.approve(MANAGEMENT_1.getId());
        supplyChainPointManagementRepository.approve(MANAGEMENT_2.getId());
        supplyChainPointManagementRepository.approve(MANAGEMENT_3.getId());
        supplyChainPointManagementRepository.approve(MANAGEMENT_4.getId());

        supplyChainPointManagementRepository.flush();
    }

    private void clearRawProducts(RawProductRepository rpRepository) {
        rpRepository.deleteAll();
        rpRepository.flush();
    }

    private void setUpRawProduct(RawProductRepository rpRepository) {
        clearRawProducts(rpRepository);

        RAW_PRODUCT_1 = new RawProduct();
        RAW_PRODUCT_2 = new RawProduct();
        RAW_PRODUCT_3 = new RawProduct();

        RAW_PRODUCT_1.setSupplyChainPoint(SUPPLY_CHAIN_POINT_1);
        RAW_PRODUCT_1.setName("Latte Vaccino");
        RAW_PRODUCT_1.setDescription("Latte di mucca di produzione locale");
        RAW_PRODUCT_1.setAuthor("Enzo Della Vigna");
        RAW_PRODUCT_1.setApproved(true);
        RAW_PRODUCT_1.setCertification("BIO - Prodotto Biologico");
        RAW_PRODUCT_1.setVariety("Intero");
        RAW_PRODUCT_1.setProductionMethod("Mungitura");

        RAW_PRODUCT_2.setSupplyChainPoint(SUPPLY_CHAIN_POINT_1);
        RAW_PRODUCT_2.setName("Lana Grezza");
        RAW_PRODUCT_2.setDescription("Lana grezza di pecora");
        RAW_PRODUCT_2.setAuthor("Enzo Della Vigna");
        RAW_PRODUCT_2.setApproved(true);
        RAW_PRODUCT_2.setCertification("RWS - Responsible Wool Standard");
        RAW_PRODUCT_2.setVariety("Lana di pecora");
        RAW_PRODUCT_2.setProductionMethod("Tosatura a mano con ausilio di cesoie");

        RAW_PRODUCT_3.setSupplyChainPoint(SUPPLY_CHAIN_POINT_3);
        RAW_PRODUCT_3.setName("Uva Rossa");
        RAW_PRODUCT_3.setDescription("Uva Rossa impiegata nella produzione del Vino Locale");
        RAW_PRODUCT_3.setAuthor("Enzo Della Vigna");
        RAW_PRODUCT_3.setApproved(true);
        RAW_PRODUCT_3.setCertification("BIO - Prodotto Biologico");
        RAW_PRODUCT_3.setVariety("Rossa");
        RAW_PRODUCT_3.setProductionMethod("Vendemmia");

        RAW_PRODUCT_1 = rpRepository.save(RAW_PRODUCT_1);
        RAW_PRODUCT_2 = rpRepository.save(RAW_PRODUCT_2);
        RAW_PRODUCT_3 = rpRepository.save(RAW_PRODUCT_3);

        rpRepository.flush();
    }

    private void clearTransformationProcesses(TransformationProcessRepository tpRepository) {
        tpRepository.deleteAll();
        tpRepository.flush();
    }

    private void setUpTransformationProcesses(TransformationProcessRepository tpRepository) {
        clearTransformationProcesses(tpRepository);

        TRANSFORMATION_PROCESS_1 = new TransformationProcess();
        TRANSFORMATION_PROCESS_2 = new TransformationProcess();

        TRANSFORMATION_PROCESS_1.setSupplyChainPoint(SUPPLY_CHAIN_POINT_2);
        TRANSFORMATION_PROCESS_1.setName("Produzione della Ricotta");
        TRANSFORMATION_PROCESS_1.setDescription("Dai grassi e dalle proteine di scarto presenti nel siero," +
                " si crea un delizioso surrogato del formaggio: la Ricotta!");
        TRANSFORMATION_PROCESS_1.setAuthor("Alberto Del Fiore");
        TRANSFORMATION_PROCESS_1.setApproved(true);
        TRANSFORMATION_PROCESS_1.setCertification("Denominazione Comunale");
        TRANSFORMATION_PROCESS_1.setTransformationPhases("La ricotta è un prodotto caseario che viene ottenuto " +
                "attraverso il riscaldamento (almeno 72°C) del siero di latte...");

        TRANSFORMATION_PROCESS_2.setSupplyChainPoint(SUPPLY_CHAIN_POINT_3);
        TRANSFORMATION_PROCESS_2.setName("Produzione del Vino Rosso");
        TRANSFORMATION_PROCESS_2.setDescription("La vinificazione è il processo che riguarda la trasformazione del mosto in vino.");
        TRANSFORMATION_PROCESS_2.setAuthor("Enzo Della Vigna");
        TRANSFORMATION_PROCESS_2.setApproved(true);
        TRANSFORMATION_PROCESS_2.setCertification("Denominazione Comunale");
        TRANSFORMATION_PROCESS_2.setTransformationPhases("1) Vendemmia" +
                "2) Pigiature degli acini di uva" +
                "3) Fermentazione" +
                "4) Maturazione" +
                "5) Imbottigliamento");

        TRANSFORMATION_PROCESS_1 = tpRepository.save(TRANSFORMATION_PROCESS_1);
        TRANSFORMATION_PROCESS_2 = tpRepository.save(TRANSFORMATION_PROCESS_2);

        tpRepository.flush();
    }

    private void clearTransformedProducts(TransformedProductRepository tpRepository) {
        tpRepository.deleteAll();
        tpRepository.flush();
    }

    private void setUpTransformedProducts(TransformedProductRepository tpRepository) {
        clearTransformedProducts(tpRepository);

        TRANSFORMED_PRODUCT_1 = new TransformedProduct();
        TRANSFORMED_PRODUCT_2 = new TransformedProduct();

        TRANSFORMED_PRODUCT_1.setSupplyChainPoint(SUPPLY_CHAIN_POINT_2);
        TRANSFORMED_PRODUCT_1.setName("Ricotta Del Fiore");
        TRANSFORMED_PRODUCT_1.setDescription("Deliziosa ricotta di latte vaccino perfetta per golosi spuntini");
        TRANSFORMED_PRODUCT_1.setAuthor("Alberto Del Fiore");
        TRANSFORMED_PRODUCT_1.setApproved(true);
        TRANSFORMED_PRODUCT_1.setCertification("Denominazione Comunale");
        TRANSFORMED_PRODUCT_1.setVariety("Di origine vaccina");
        TRANSFORMED_PRODUCT_1.setTransformationProcessId(TRANSFORMATION_PROCESS_1.getId());

        TRANSFORMED_PRODUCT_2.setSupplyChainPoint(SUPPLY_CHAIN_POINT_3);
        TRANSFORMED_PRODUCT_2.setName("Vino Rosso Locale: Fratelli La Vigna");
        TRANSFORMED_PRODUCT_2.setDescription("Un accompagno perfetto per pasti a base di carne");
        TRANSFORMED_PRODUCT_2.setAuthor("Enzo Della Vigna");
        TRANSFORMED_PRODUCT_2.setApproved(true);
        TRANSFORMED_PRODUCT_2.setCertification("Denominazione Comunale");
        TRANSFORMED_PRODUCT_2.setVariety("Vino Rosso");
        TRANSFORMED_PRODUCT_2.setTransformationProcessId(TRANSFORMATION_PROCESS_2.getId());

        TRANSFORMED_PRODUCT_1 = tpRepository.save(TRANSFORMED_PRODUCT_1);
        TRANSFORMED_PRODUCT_2 = tpRepository.save(TRANSFORMED_PRODUCT_2);

        tpRepository.flush();
    }

    private void clearProductPackages(ProductPackageRepository productPackageRepository) {
        productPackageRepository.deleteAll();
        productPackageRepository.flush();
    }

    private void setUpProductPackages(ProductPackageRepository productPackageRepository) {
        clearProductPackages(productPackageRepository);

        PRODUCT_PACKAGE_1 = new ProductPackage();

        PRODUCT_PACKAGE_1.setSupplyChainPoint(SUPPLY_CHAIN_POINT_2);
        PRODUCT_PACKAGE_1.setName("Pacchetto caseario locale");
        PRODUCT_PACKAGE_1.setDescription("Una raccolta di prodotti locali legati al mondo caseario");
        PRODUCT_PACKAGE_1.setAuthor("Nora Terzini");
        PRODUCT_PACKAGE_1.setApproved(true);
        PRODUCT_PACKAGE_1.addRawProduct(RAW_PRODUCT_1);
        PRODUCT_PACKAGE_1.addTransformedProduct(TRANSFORMED_PRODUCT_1);

        PRODUCT_PACKAGE_1 = productPackageRepository.save(PRODUCT_PACKAGE_1);

        productPackageRepository.flush();
    }

    private void clearSales(SaleRepository saleRepository) {
        saleRepository.deleteAll();
        saleRepository.flush();
    }

    private void setUpSales(SaleRepository saleRepository) {
        clearSales(saleRepository);

        SALE_1 = new Sale();
        SALE_2 = new Sale();
        SALE_3 = new Sale();
        SALE_4 = new Sale();

        SALE_1.setSupplyChainPoint(SUPPLY_CHAIN_POINT_1);
        SALE_1.setProductId(RAW_PRODUCT_1.getId());
        SALE_1.setProductType(ContentType.RAW_PRODUCT.getValue());
        SALE_1.setName("Latte Vaccino - Red Label");
        SALE_1.setDescription("Latte di mucca di produzione locale intero - Red Label");
        SALE_1.setAuthor(RAW_PRODUCT_1.getAuthor());
        SALE_1.setApproved(true);
        SALE_1.setPrice(1.21f);
        SALE_1.setQuantity(300);

        SALE_2.setSupplyChainPoint(SUPPLY_CHAIN_POINT_2);
        SALE_2.setProductId(TRANSFORMED_PRODUCT_1.getId());
        SALE_2.setProductType(ContentType.TRANSFORMED_PRODUCT.getValue());
        SALE_2.setName("Ricotta Del Fiore Excelsior");
        SALE_2.setDescription("La miglior ricotta di produzione artigianale, eccellenza Montefiorana");
        SALE_2.setAuthor(TRANSFORMED_PRODUCT_1.getAuthor());
        SALE_2.setApproved(true);
        SALE_2.setPrice(2.85f);
        SALE_2.setQuantity(270);

        SALE_3.setSupplyChainPoint(SUPPLY_CHAIN_POINT_2);
        SALE_3.setProductId(PRODUCT_PACKAGE_1.getId());
        SALE_3.setProductType(ContentType.PRODUCT_PACKAGE.getValue());
        SALE_3.setName("Pacchetto caseario eccellenza Montefiorana");
        SALE_3.setDescription("Eccellenze casearie del territorio Montefiorano");
        SALE_3.setAuthor(PRODUCT_PACKAGE_1.getAuthor());
        SALE_3.setApproved(true);
        SALE_3.setPrice(4.20f);
        SALE_3.setQuantity(80);

        SALE_4.setSupplyChainPoint(SUPPLY_CHAIN_POINT_3);
        SALE_4.setProductId(TRANSFORMED_PRODUCT_2.getId());
        SALE_4.setProductType(ContentType.TRANSFORMED_PRODUCT.getValue());
        SALE_4.setName("Vino Rosso Montefiorano");
        SALE_4.setDescription("Vino-Eccellenza territoriale");
        SALE_4.setAuthor(TRANSFORMED_PRODUCT_2.getAuthor());
        SALE_4.setApproved(true);
        SALE_4.setPrice(7.90f);
        SALE_4.setQuantity(80);

        SALE_1 = saleRepository.save(SALE_1);
        SALE_2 = saleRepository.save(SALE_2);
        SALE_3 = saleRepository.save(SALE_3);
        SALE_4 = saleRepository.save(SALE_4);

        saleRepository.flush();
    }

}
