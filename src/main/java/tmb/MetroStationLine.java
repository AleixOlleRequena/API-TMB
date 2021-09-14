package tmb;

/**
 * Classe creada per poder fer les crides a l'API de TMB sobre les estacions de metro i les seves línies
 */
public class MetroStationLine {
    /**
     * Guarda les característiques de les estacions de metro i les línies
     */
    private FeatureMetroLine[] features;

    /**
     * Constructor de la classe
     */
    public MetroStationLine() {
    }

    /**
     * Retorna les característiques de les estacions de metro i les línies
     * @return features
     */
    public FeatureMetroLine[] getFeatures() {
        return features;
    }
}
