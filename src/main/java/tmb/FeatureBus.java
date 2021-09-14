package tmb;

/**
 * Classe creada per poder fer la crida a l'API de TMB sobre les parades d'autobus
 */
public class FeatureBus {
    /**
     * Coordenades de les parades de bus
     */
    private Geometry geometry;
    /**
     * Propietats de les parades de bus
     */
    private PropertyBus properties;


    /**
     * Retorna les coordenades de les parades de bus
     * @return geometry
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * Retorna les propietats de les parades de bus
     * @return properties
     */
    public PropertyBus getProperties() {
        return properties;
    }
}
