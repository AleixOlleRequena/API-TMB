package tmb;

/**
 * Classe creada per poder fer la crida a l'API de TMB sobre les parades d'autobus
 */
public class PropertyBus {
    /**
     * Codi de la parada del bus
     */
    private int CODI_PARADA;
    /**
     * Nom de la parada del bus
     */
    private String NOM_PARADA;
    /**
     * Destí del bus en aquell sentit
     */
    private String DESTI_SENTIT;


    /**
     * Constructor de la classe
     */
    public PropertyBus() {
    }

    /**
     * Retorna el destí del bus en aquell sentit
     * @return DESTI_SENTIT
     */
    public String getDESTI_SENTIT() {
        return DESTI_SENTIT;
    }

    /**
     * Retorna el codi de la parada
     * @return CODI_PARADA
     */
    public int getCODI_PARADA() {
        return CODI_PARADA;
    }

    /**
     * Retorna el nom de la parada
     * @return NOM_PARADA
     */
    public String getNOM_PARADA() {
        return NOM_PARADA;
    }
}

