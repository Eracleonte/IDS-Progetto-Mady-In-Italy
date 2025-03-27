package it.unicam.cs.ids.api.abstractions;

/**
 * Represents a Visualizable entity of the system
 */
public interface Visualizable extends Identifiable {

    /**
     * Returns an output data transfer object of this Visualizable instance
     * @return an output data transfer object of this Visualizable instance
     */
    Identifiable getOutputDTO();

}
