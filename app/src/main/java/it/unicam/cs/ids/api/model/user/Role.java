package it.unicam.cs.ids.api.model.user;

public class Role {

    private final Authorization authorization;

    private Role(Authorization authorization) {
        this.authorization = authorization;
    }

    public static Role getProducer() {
        return new Role(Authorization.PRODUCER);
    }

    public static Role getTransformer() {
        return new Role(Authorization.TRANSFORMER);
    }

    public static Role getDistributor() {
        return new Role(Authorization.DISTRIBUTOR);
    }

    public static Role getCurator() {
        return new Role(Authorization.CURATOR);
    }

    public static Role getGuest() {
        return new Role(Authorization.GUEST);
    }

    public static Role getBuyer() {
        return new Role(Authorization.BUYER);
    }

    public static Role getAdministrator() {
        return new Role(Authorization.ADMINISTRATOR);
    }

    public Authorization getAuthorization() {
        return authorization;
    }

}
