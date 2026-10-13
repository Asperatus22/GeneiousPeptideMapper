package fr.bigault.peptidemapper.model;

public class MappingHit {
    private String peptide;
    private String protein;
    private int start;
    private int end;

    public MappingHit(String peptide, String protein, int start, int end) {
        this.peptide = peptide;
        this.protein = protein;
        this.start = start;
        this.end = end;
    }

    public String getPeptide() {
        return peptide;
    }

    public String getProtein() {
        return protein;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}