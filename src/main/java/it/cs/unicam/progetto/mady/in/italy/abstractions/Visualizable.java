package it.cs.unicam.progetto.mady.in.italy.abstractions;

/**
 * Represents a Visualizable element of the system.
 */
public interface Visualizable extends Identifiable {

    /**
     * Returns an output data transfer object of this Visualizable instance.
     *
     * @return an output data transfer object of this Visualizable instance.
     */
    Identifiable getOutputDTO();

}
