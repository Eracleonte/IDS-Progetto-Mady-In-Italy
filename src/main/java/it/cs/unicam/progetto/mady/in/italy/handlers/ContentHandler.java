package it.cs.unicam.progetto.mady.in.italy.handlers;

import it.cs.unicam.progetto.mady.in.italy.model.contents.Content;
import it.cs.unicam.progetto.mady.in.italy.model.contents.ContentType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Represents a Handler for Content entities.
 *
 * @param <C> the specific type of Content this ContentHandler manages.
 */
@Service
public interface ContentHandler<C extends Content> {

    /**
     * Saves a new Content.
     *
     * @param content the content to save.
     * @return the id of the saved content.
     */
    int saveContent(C content);

    /**
     * Finds a Content with the specified id.
     *
     * @param id the id of the content to find.
     * @return the Content with the specified id.
     */
    C findContentById(int id);

    /**
     * Finds all Contents.
     *
     * @return a list of Contents.
     */
    List<C> findAllContents();

    /**
     * Approves (or rejects) the Content of the specified id.
     * In order to approve a Content, the approvalChoice parameter must be set to true.
     * In order to reject a Content, the approvalChoice parameter must be set to false.
     *
     * @param id the id of the Content to approve/reject.
     * @param approvalChoice the choice to approve or reject a new Content.
     * @return a message regarding the operation's result in String format.
     */
    String approveContent(int id, boolean approvalChoice);

    /**
     * Returns the ContentType supported by this ContentHandler
     *
     * @return the ContentType supported by this ContentHandler
     */
    ContentType supports();

}

