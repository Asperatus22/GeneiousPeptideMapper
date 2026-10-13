package fr.bigault.peptidemapper.core;

import fr.bigault.peptidemapper.model.MappingHit;

import java.util.ArrayList;
import java.util.List;

public class MappingAlgorithm {
    
    public List<MappingHit> mapPeptidesToProteins(List<String> peptides, List<String> proteins) {
        
        List<MappingHit> mappingHits = new ArrayList<>();
        for (String protein : proteins) {
            protein = protein.toUpperCase();
        }

        for (String peptide : peptides) {
            for (String protein : proteins) {
                int startIndex = protein.indexOf(peptide);
                if (startIndex != -1) {
                    int endIndex = startIndex + peptide.length() - 1;
                    mappingHits.add(new MappingHit(peptide, protein, startIndex, endIndex));
                }
            }
        }

        return mappingHits;
    }
}