package tmb;

/**
 * Classe creada per poder fer la crida a l'API de TMB sobre les parades d'autobus
 */
public class BusStop {
    /**
     * Característiques de les parades de bus
     */
    private FeatureBus[] features;

    /**
     * Constructor de la classe
     */
    public BusStop() {
    }

    /**
     * Retorna les característiques de les parades de bus
     * @return features
     */
    public FeatureBus[] getFeatureBuses() {
        return features;
    }
}
