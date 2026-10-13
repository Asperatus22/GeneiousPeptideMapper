package fr.bigault.peptidemapper;

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

import com.biomatters.geneious.publicapi.documents.AnnotatedPluginDocument;
import com.biomatters.geneious.publicapi.documents.sequence.SequenceAnnotation;
import com.biomatters.geneious.publicapi.documents.sequence.SequenceAnnotationInterval;
import com.biomatters.geneious.publicapi.documents.sequence.AminoAcidSequenceDocument;
import com.biomatters.geneious.publicapi.documents.sequence.SequenceListDocument;
import com.biomatters.geneious.publicapi.plugin.DocumentOperationException;
import com.biomatters.geneious.publicapi.plugin.DocumentSelectionSignature;
import com.biomatters.geneious.publicapi.plugin.DocumentSelectionOption;
import com.biomatters.geneious.publicapi.plugin.GeneiousActionOptions;
import com.biomatters.geneious.publicapi.plugin.Options;
import com.biomatters.geneious.publicapi.plugin.SequenceAnnotationGenerator;

import jebl.util.ProgressListener;

public class PeptideMapperGenerator extends SequenceAnnotationGenerator {

    private static void debugLog(String message) {
        try (FileWriter writer =
            new FileWriter(
                "C:\\temp\\peptidemapper.log",
                true)) {
                    writer.write(message);
                    writer.write("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

    @Override
    public GeneiousActionOptions getActionOptions() {

        return new GeneiousActionOptions(
                "Peptide Mapper",
                "Maps peptides onto proteins")
                .setMainMenuLocation(
                        GeneiousActionOptions.MainMenu.AnnotateAndPredict
                );
    }

    @Override
    public String getHelp() {
        return "Peptide mapper";
    }

    @Override
    public Options getOptions(
        AnnotatedPluginDocument[] documents,
        SelectionRange selectionRange)
        throws DocumentOperationException {
            System.out.println(
            "Proteins selected: "
            + documents.length
        );
        return new PeptideMapperOptions();
    }

    @Override
    public DocumentSelectionSignature[] getSelectionSignatures() {

        return new DocumentSelectionSignature[]{
                new DocumentSelectionSignature(
                        AminoAcidSequenceDocument.class,
                        1,
                        Integer.MAX_VALUE
                )
        };
    }

   @Override
   public List<List<SequenceAnnotation>> generateAnnotations(
        AnnotatedPluginDocument[] documents,
        SelectionRange selectionRange,
        ProgressListener progressListener,
        Options options)
        throws DocumentOperationException {
            PeptideMapperOptions peptideOptions =
            (PeptideMapperOptions) options;
            
            List<AminoAcidSequenceDocument> proteinSequences = new ArrayList<>();
            List<AminoAcidSequenceDocument> peptideSequences = new ArrayList<>();

            //debugLog("===== NEW RUN =====");
            //debugLog(
            //    "Protein documents selected in Geneious = "
            //    + documents.length
            //);
            
            DocumentSelectionOption.FolderOrDocuments proteinSelection =
            peptideOptions
                .getProteinDocument()
                .getValue();

            DocumentSelectionOption.FolderOrDocuments peptideSelection =
            peptideOptions
                .getPeptideDocument()
                .getValue();

            //debugLog(
            //    "Protein selection object = "
            //    + proteinSelection
            //);

            //debugLog(
            //    "Peptide selection object = "
            //    + peptideSelection
            //);
            
            List<AnnotatedPluginDocument> selectedProteins =
                proteinSelection.getDocuments();

            List<AnnotatedPluginDocument> selectedPeptides =
                peptideSelection.getDocuments();

            //debugLog(
            //    "Protein list size = "
            //    + selectedProteins.size()
            //);

            //debugLog(
            //    "Peptide list size = "
            //    + selectedPeptides.size()
            //);

            for (AnnotatedPluginDocument docProtein : selectedProteins) {
                //debugLog(
                //   "Protein document : "
                //    + docProtein.getName()
                //);

                //debugLog(
                //    "Protein class : "
                //    + docProtein.getDocument().getClass().getName()
                //);
                
                Object internalDocumentProteins =
                    docProtein.getDocument();
                    
                if (internalDocumentProteins instanceof SequenceListDocument) {
                    SequenceListDocument sequenceList =
                        (SequenceListDocument) internalDocumentProteins;

                    proteinSequences.addAll(
                        sequenceList.getAminoAcidSequences()
                    );

                    //debugLog(
                    //    "Protein list document found"
                    //);

                    List<AminoAcidSequenceDocument> proteins =
                        sequenceList.getAminoAcidSequences();
                        //debugLog(
                        //    "Protein sequence count = "
                        //    + proteins.size()
                        //);
                        for (AminoAcidSequenceDocument protein : proteins) {
                            //debugLog(
                            //    "Protein name = "
                            //    + protein.getName()
                            //);

                            //debugLog(
                            //    "Protein length = "
                            //    + protein.getSequenceString().length()
                            //);
                        }
                }
            }

            for (AnnotatedPluginDocument docPeptide : selectedPeptides) {
                debugLog(
                    "Peptide document : "
                    + docPeptide.getName()
                );
                debugLog(
                    "Peptide class : "
                    + docPeptide.getDocument().getClass().getName()
                );
                
                Object internalDocumentPeptide =
                    docPeptide.getDocument();
                    
                if (internalDocumentPeptide instanceof SequenceListDocument) {
                    SequenceListDocument sequenceList =
                        (SequenceListDocument) internalDocumentPeptide;

                    peptideSequences.addAll(
                        sequenceList.getAminoAcidSequences()
                    );

                    debugLog(
                        "Peptide list document found"
                    );

                    List<AminoAcidSequenceDocument> peptides =
                        sequenceList.getAminoAcidSequences();

                        debugLog(
                            "Peptide sequence count = "
                        + peptides.size()
                        );
                        
                        for (AminoAcidSequenceDocument peptide : peptides) {
                            
                            debugLog(
                                "Peptide name = "
                                + peptide.getName()
                            );

                            debugLog(
                                "Peptide sequence = "
                                + peptide.getSequenceString()
                            );
                        }
                }
            }

            List<List<SequenceAnnotation>> results = new ArrayList<>();
            
            for (AminoAcidSequenceDocument protein : proteinSequences) {
                List<SequenceAnnotation> annotations = new ArrayList<>();
                String proteinSequence = protein.getSequenceString();
                for (AminoAcidSequenceDocument peptide : peptideSequences) {
                    String peptideSequence = peptide.getSequenceString();
                    int start = 0;
                    While ((start = proteinSequence.indexOf(peptideSequence, start)) >= 0) {
                        int end = start + peptideSequence.length();
                        SequenceAnnotation annotation = new SequenceAnnotation(
                            peptide.getName(),
                            SequenceAnnotation.TYPE_MISC_FEATURE,
                            new SequenceAnnotationInterval(
                                start + 1,
                                end
                            )
                        );
                       
                        annotations.add(annotation);

                        //debugLog(
                        //    protein.getName()
                        //    + " <- "
                        //    + peptide.getName()
                        //    + " @ "
                        //    + (start + 1)
                        //);
                        start++; // Move to the next character to find overlapping matches
                    }
                }
            
                results.add(annotations);
            }

        debugLog(
            "Results size = "
            + results.size()
        );

        return results;
    }

}