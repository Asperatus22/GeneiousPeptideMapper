package fr.bigault.peptidemapper;

import com.biomatters.geneious.publicapi.plugin.GeneiousPlugin;
import com.biomatters.geneious.publicapi.plugin.SequenceAnnotationGenerator;

public class PeptideMapperPlugin extends GeneiousPlugin {
    
    @Override
    public SequenceAnnotationGenerator[] getSequenceAnnotationGenerators() {
        System.out.println("PeptideMapper: Generator loaded");
        return new SequenceAnnotationGenerator[]{
                new PeptideMapperGenerator()
        };
    }
    
    @Override 
    public String getHelp() {
        return "This plugin maps peptides to proteins.";
    }
    
    @Override 
    public String getName() {
        return "Peptide Mapper";
    }

    @Override 
    public String getDescription() {
        return "This plugin maps peptides to proteins.";
    }

    @Override
    public String getAuthors() {
            return "Lionel Bigault";
        }

    @Override
    public String getVersion() {
            return "0.1";
        }

    @Override
    public String getMinimumApiVersion() {
            return "4.1";
        }

    @Override
    public int getMaximumApiVersion() {
            return 4;
        }

}