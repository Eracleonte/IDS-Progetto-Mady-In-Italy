package it.unicam.cs.ids.api.model.user;

public enum Authorization {

    PRODUCER("PRODUCER"),
    TRANSFORMER("TRANSFORMER"),
    DISTRIBUTOR("DISTRIBUTOR"),
    CURATOR("CURATOR"),
    GUEST("GUEST"),
    BUYER("BUYER"),
    ADMINISTRATOR("ADMINISTRATOR");

    private final String value;

    Authorization(String value) {
        this.value = value;
    }

    public String getValue() { return value; }

}
