package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.model.contents.Content;

import java.util.List;

/**
 * TODO, fare in modo che gli handler implementino questa interfaccia
 */
public interface ContentHandler<C extends Content> {

    int saveContent(C content); // TODO approvato

    C findContentById(int id); // TODO approvato

    List<C> findAllContents(); // TODO approvato

    List<C> findContentsFiltered(); // TODO introdurre il FiltroDTO

    String approveContent(int id, boolean approvalChoice); // TODO approvato

}
