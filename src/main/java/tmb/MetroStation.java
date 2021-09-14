package tmb;

/**
 * Classe creada per poder fer les crides a l'API de TMB sobre les estacions de metro
 */
public class MetroStation {
    /**
     * Guarda les característiques de les estacions de metro
     */
    private FeatureMetro[] features;

    /**
     * Constructor de la classe
     */
    public MetroStation() {
    }

    /**
     * Retorna les característiques de les estacions de metro
     * @return features
     */
    public FeatureMetro[] getFeatures() {
        return features;
    }
}
