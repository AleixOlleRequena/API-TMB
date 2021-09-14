package tmb;

/**
 * Classe creada per poder fer les crides a l'API de TMB sobre les estacions de metro i les seves línies
 */
public class FeatureMetroLine {
    /**
     * Informació sobre l'estació i la línia de metro
     */
    private PropertyMetroLine properties;

    /**
     * Retorna la informació de les estacions i línies
     * @return properties
     */
    public PropertyMetroLine getProperties() {
        return properties;
    }
}
