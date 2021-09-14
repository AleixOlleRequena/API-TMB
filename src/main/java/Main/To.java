package Main;

/**
 * Classe creada per poder llegir el JSON retornat per l'API de TMB
 */
public class To {
    /**
     * Nom d'una parada
     */
    private String name;
    /**
     * Codi de la parada
     */
    private int stopCode;

    /**
     * Retorna el nom de la parada
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Retorna el codi de la parada
     * @return stopCode
     */
    public int getStopCode() {
        return stopCode;
    }
}
