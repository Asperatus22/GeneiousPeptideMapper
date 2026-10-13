package fr.bigault.peptidemapper;

import com.biomatters.geneious.publicapi.plugin.DocumentSelectionOption;
import com.biomatters.geneious.publicapi.plugin.DocumentType;
import com.biomatters.geneious.publicapi.plugin.Options;
import java.util.Collections;

public class PeptideMapperOptions extends Options {

    private final DocumentSelectionOption peptideDocument;
    private final DocumentSelectionOption proteinDocument;

    public PeptideMapperOptions() {

        peptideDocument = addDocumentSelectionOption(
            "peptideDocument",
            "peptide list",
            DocumentSelectionOption.FolderOrDocuments.EMPTY,
            DocumentType.PROTEIN_SEQUENCE_LIST_TYPE,
            Collections.emptyList(),
            true,
            Collections.emptyList()
        );

        proteinDocument = addDocumentSelectionOption(
            "proteinDocument",
            "Protein list",
            DocumentSelectionOption.FolderOrDocuments.EMPTY,
            DocumentType.PROTEIN_SEQUENCE_LIST_TYPE,
            Collections.emptyList(),
            true,
            Collections.emptyList()
        );

    }    

    public DocumentSelectionOption getPeptideDocument() {
        return peptideDocument;
    }

    public DocumentSelectionOption getProteinDocument() {
        return proteinDocument;
    }

}