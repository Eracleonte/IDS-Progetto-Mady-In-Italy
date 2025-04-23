package it.cs.unicam.progetto.mady.in.italy.abstractions;

/**
 * Represents an approvable entity of the system.
 */
public interface Approvable {

    boolean isApproved();

    void setApproved(boolean approved);

}
