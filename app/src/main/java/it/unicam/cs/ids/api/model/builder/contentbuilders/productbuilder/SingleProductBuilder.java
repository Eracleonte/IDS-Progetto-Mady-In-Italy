package it.unicam.cs.ids.api.model.builder.contentbuilders.productbuilder;

import it.unicam.cs.ids.api.model.builder.contentbuilders.ContentBuilder;

public interface SingleProductBuilder extends ContentBuilder {

    void setCertification(String certification);

    void setVariety(String variety);

}
