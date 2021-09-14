package tmb;

/**
 * Classe creada per poder fer les crides a l'API de TMB sobre les estacions de metro
 */
public class FeatureMetro {
    /**
     * Conté la informació de les coordenades de les estacions de metro
     */
    private Geometry geometry;
    /**
     * Conté la informació de les propietats de les estacions
     */
    private PropertyMetro properties;

    /**
     * Retorna la informació de les coordenades de l'estació
     * @return geometry
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * Retorna la informació de les propietats de l'estaió
     * @return properties
     */
    public PropertyMetro getProperties() {
        return properties;
    }
}
