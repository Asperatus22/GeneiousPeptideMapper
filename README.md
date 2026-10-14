# Geneious Peptide Mapper

A Geneious Prime plugin for mapping peptide sequences onto protein sequences and automatically creating peptide annotations.

## Features

- Import a protein sequence list.
- Import a peptide sequence list.
- Automatically search each peptide against all protein sequences.
- Create Geneious annotations for every peptide match.
- Supports multiple proteins.
- Supports multiple peptides.
- Supports overlapping peptides.
- Supports nested peptides.
- Supports multiple peptide matches within the same protein.

## Requirements

- Geneious Prime 2026.1.4 or compatible version
- Geneious Public API SDK
- Java 11
- Apache Ant

## Installation

### Clone repository

```bash
git clone https://github.com/Asperatus22/GeneiousPeptideMapper.git
cd GeneiousPeptideMapper
```

### Build

The build is available in the build folder. You can buil it on your own.

```bash
ant clean
ant distribute
```

### Install in Geneious

1. Open Geneious Prime.
2. Go to:
    * Tools
    * Plugins
    * Install Plugin
3. Select the generated plugin JAR file.
4. Restart Geneious.

## Usage

1. Select one or more protein sequences in Geneious.
2. Launch:
    Annotate & Predict → Peptide Mapper
3. Select:
    * a protein sequence list
    * a peptide sequence list
4. Run the plugin.

The plugin will create peptide annotations on matching proteins.

## Current behavior
* All peptide matches are reported.
* Multiple occurrences are annotated.
* Nested peptide matches are retained.
* Peptide names are used as annotation names.

## Development

The project was developed using:
* Java 11
* Apache Ant
* Geneious Public API 2026.1.4

## License

MIT License