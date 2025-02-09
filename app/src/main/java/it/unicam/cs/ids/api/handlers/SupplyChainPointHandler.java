package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.model.contents.ManagedContent;
import it.unicam.cs.ids.api.model.supplychain.SupplyChainPoint;
import it.unicam.cs.ids.api.repos.supplychain.SupplyChainPointManagedContentsRepository;
import it.unicam.cs.ids.api.repos.supplychain.SupplyChainPointRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class SupplyChainPointHandler {

    private SupplyChainPointRepository scpRepository;

    private SupplyChainPointManagedContentsRepository scpManagedContentsRepository;

    public SupplyChainPointHandler(SupplyChainPointRepository scpRepository,
                                   SupplyChainPointManagedContentsRepository scpManagedContentsRepository) {
        this.scpRepository = scpRepository;
        this.scpManagedContentsRepository = scpManagedContentsRepository;
    }

    public SupplyChainPoint saveSupplyChainPoint(SupplyChainPoint scp) {
        if (scp == null)
            throw new NullPointerException("Cannot save a null supply chain point");
        return this.scpRepository.save(scp);
    }

    public SupplyChainPoint findSupplyChainPointById(Integer id) {
        return this.scpRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find supply chain point with id: " + id));
    }

    public List<SupplyChainPoint> findAllSupplyChainPoints() {
        return this.scpRepository.findAll();
    }

    public ManagedContent saveManagedContent(ManagedContent managedContent) {
        if (managedContent == null)
            throw new NullPointerException("Cannot save a null managed content");
        return this.scpManagedContentsRepository.save(managedContent);
    }

    public ManagedContent findManagedContentById(Integer id) {
        return this.scpManagedContentsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find managed content with id: " + id));
    }

    public List<ManagedContent> findAllManagedContents() {
        return this.scpManagedContentsRepository.findAll();
    }

}
