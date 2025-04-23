package it.cs.unicam.progetto.mady.in.italy.dto.input;

/**
 *
 * Represents a filter for filtering supply chain points.
 * This filter can be used when gathering supply chain
 * points that meet a specific search criteria.
 * (Ex. searching for Production supply chain points when creating a new raw product)
 * </p>
 * Note: Wrapper classes are used so that it will be possible to skip the definition of certain parameters
 * when attempting to search via a filter.
 *
 * @param supplyChainPointName the name of the supply chain point to search.
 * @param isProduction equals true if supply chain points that are specialized in Production are being searched.
 * @param isTransformation equals true if supply chain points that are specialized in Transformation are being searched.
 * @param isDistribution equals true if supply chain points that are specialized in Distribution are being searched.
 * @param isResale equals true if supply chain points that are specialized in Resale are being searched.
 * @param managerId the id of the manager of a set of supply chain points (useful for content creation).
 */
public record SupplyChainPointSearchFilterDTO(String supplyChainPointName,
                                              Boolean isProduction,
                                              Boolean isTransformation,
                                              Boolean isDistribution,
                                              Boolean isResale,
                                              Integer managerId) {
}
