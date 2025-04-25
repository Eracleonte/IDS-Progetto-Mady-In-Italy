package it.unicam.cs.ids.api.abstractions;

/**
 * Represents an approvable entity of the system.
 */
public interface Approvable {

    boolean isApproved();

    void setApproved(boolean approved);

}
