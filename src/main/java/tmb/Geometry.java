package tmb;

/**
 * Classe creada per poder fer les crides a l'API de TMB sobre les estacions de metro i les parades de bus
 */
public class Geometry {
    /**
     * Coordenades de l'estació/parada
     */
    private double[] coordinates;

    /**
     * Constructor de la classe
     */
    public Geometry() {
    }

    /**
     * Retorna les coordenades d'una estació/parada
     * @return coordinates
     */
    public double[] getCoordinates() {
        return coordinates;
    }
}
