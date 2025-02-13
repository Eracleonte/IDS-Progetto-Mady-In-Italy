package it.unicam.cs.ids.api.model.contents.products.productpackages;

import it.unicam.cs.ids.api.dto.output.OutputProductPackageElementDTO;
import it.unicam.cs.ids.api.model.contents.ContentType;

public class ProductPackageElement {

    private int packageId;

    private int productId;

    private String contentType;

    public ProductPackageElement() {
    }

    /**
    public ProductPackageElement(int packageId, int productId, ContentType contentType) {
        if (packageId < 0)
            throw new IllegalArgumentException("PackageId cannot be lesser than 0");
        if (productId < 0)
            throw new IllegalArgumentException("ProductId cannot be lesser than 0");
        if (! contentType.equals(ContentType.RAW_PRODUCT)
                && ! contentType.equals(ContentType.TRANSFORMED_PRODUCT))
            throw new IllegalArgumentException("ContentType must be RAW_PRODUCT or TRANSFORMED_PRODUCT");
        this.packageId = packageId;
        this.productId = productId;
        this.contentType = contentType.getValue();
    }
     */

    public int getPackageId() {
        return packageId;
    }

    public int getProductId() {
        return productId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setPackageId(int packageId) {
        if (packageId < 0)
            throw new IllegalArgumentException("PackageId cannot be lesser than 0");
        this.packageId = packageId;
    }

    public void setProductId(int productId) {
        if (productId < 0)
            throw new IllegalArgumentException("ProductId cannot be lesser than 0");
        this.productId = productId;
    }

    public void setContentType(String contentType) {
        if (! contentType.equals(ContentType.RAW_PRODUCT.getValue())
                && ! contentType.equals(ContentType.TRANSFORMED_PRODUCT.getValue()))
            throw new IllegalArgumentException("ContentType not supported");
        this.contentType = contentType;
    }

    public OutputProductPackageElementDTO getOutputProductPackageElementDTO() {
        return new OutputProductPackageElementDTO(productId, contentType);
    }

}
