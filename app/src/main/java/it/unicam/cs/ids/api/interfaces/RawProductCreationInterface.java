package it.unicam.cs.ids.api.interfaces;

import it.unicam.cs.ids.api.controllers.RawProductController;
import it.unicam.cs.ids.api.controllers.SupplyChainPointController;
import it.unicam.cs.ids.api.dto.RawProductDTO;
import it.unicam.cs.ids.api.model.contents.products.singles.RawProduct;
import it.unicam.cs.ids.api.model.supplychain.SupplyChainPoint;

import java.util.List;
import java.util.Scanner;

public class RawProductCreationInterface {

    private final Scanner scanner = new Scanner(System.in);

    private RawProductController rawProductController;

    private SupplyChainPointController supplyChainPointController;

    public RawProductCreationInterface(RawProductController rawProductController,
                                       SupplyChainPointController supplyChainPointController) {
        this.rawProductController = rawProductController;
        this.supplyChainPointController = supplyChainPointController;
    }

    /**
     * Starts the Raw Product creation procedure.
     *
     * @param author the author who started the Raw Product creation procedure.
     */
    public void startRawProductCreation(String author) {
        RawProductDTO rawProductDTO = new RawProductDTO();
        System.out.println("Welcome to Raw Product Creation Process " + author);
        int supplyChainPointId = selectSupplyChainPoint();
        if (supplyChainPointId == -1)
            System.out.println("Supply Chain Point ID not found, Raw Product creation failed");
        else {
            rawProductDTO.setSupplyChainPointId(supplyChainPointId); // TODO implement
            rawProductDTO.setName(insertProductName());
            rawProductDTO.setDescription(insertProductDescription());
            rawProductDTO.setAuthor(author);
            rawProductDTO.setCertification(insertProductCertification());
            rawProductDTO.setVariety(insertProductVariety());
            rawProductDTO.setProductionMethod(insertProductionMethod());
            RawProduct rawProduct = this.rawProductController.addNewRawProduct(rawProductDTO);
        }
    }

    public int selectSupplyChainPoint() {
        int choice = 0;
        System.out.println("Select a Supply Chain Point for the new Raw Product");
        List<SupplyChainPoint> supplyChainPoints = this.supplyChainPointController
                .getSupplyChainPointsIf(SupplyChainPoint::isProduction);
        if (!supplyChainPoints.isEmpty()) {
            System.out.println("Please select one of the following Supply Chain Points: ");
            for (int i = 0; i < supplyChainPoints.size(); i++)
                System.out.println("Select " + i + " for " + supplyChainPoints.get(i).toString());
            do {
                choice = scanner.nextInt();
            } while (choice < 0 || choice > supplyChainPoints.size());
            return supplyChainPoints.get(choice).getId();
        }
        else {
            System.out.println("No Supply Chain Point found matching requirements");
            return -1;
        }
    }

    public String insertProductName() {
        String productName = null;
        System.out.println("Insert a Product Name");
        do {
            productName = scanner.next();
        } while (productName == null || productName.isEmpty());
        return productName;
    }

    public String insertProductDescription() {
        String productDescription = null;
        System.out.println("Insert a Product Description");
        do {
            productDescription = scanner.next();
        } while (productDescription == null || productDescription.isEmpty());
        return productDescription;
    }

    public String insertProductCertification() {
        String productCertification = null;
        System.out.println("Insert a Product Certification");
        do {
            productCertification = scanner.next();
        } while (productCertification == null || productCertification.isEmpty());
        return productCertification;
    }

    public String insertProductVariety() {
        String productVariety = null;
        System.out.println("Insert a Product Variety");
        do {
            productVariety = scanner.next();
        } while (productVariety == null || productVariety.isEmpty());
        return productVariety;
    }

    public String insertProductionMethod() {
        String productionMethod = null;
        System.out.println("Insert a Production Method");
        do {
            productionMethod = scanner.next();
        } while (productionMethod == null || productionMethod.isEmpty());
        return productionMethod;
    }

}
