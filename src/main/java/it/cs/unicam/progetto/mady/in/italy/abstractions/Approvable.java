package it.cs.unicam.progetto.mady.in.italy.abstractions;

/**
 * Represents an Approvable element of the system.
 */
public interface Approvable {

    /**
     * Returns true if this Approvable instance has been approved,
     * false otherwise
     *
     * @return true if this Approvable has been approved,
     *         false otherwise
     */
    boolean isApproved();

    /**
     * Allows to set an Approvable to approved or not approved.
     *
     * @param approved may be true or false depending on how this
     *                 Approvable instance should be considered (approved or not approved).
     */
    void setApproved(boolean approved);

}
