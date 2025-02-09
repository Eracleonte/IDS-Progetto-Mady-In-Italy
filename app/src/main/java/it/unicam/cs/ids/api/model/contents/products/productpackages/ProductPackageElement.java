package it.unicam.cs.ids.api.model.contents.products.productpackages;

import it.unicam.cs.ids.api.model.contents.ContentType;

public class ProductPackageElement {

    private int packageId;

    private int productId;

    private String contentType;

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

    public int getPackageId() {
        return packageId;
    }

    public int getProductId() {
        return productId;
    }

    public String getContentType() {
        return contentType;
    }

}
