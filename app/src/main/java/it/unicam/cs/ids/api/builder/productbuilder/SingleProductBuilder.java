package it.unicam.cs.ids.api.builder.productbuilder;

import it.unicam.cs.ids.api.builder.ContentBuilder;

public interface SingleProductBuilder extends ContentBuilder {

    void setCertification(String certification);

    void setVariety(String variety);

}
