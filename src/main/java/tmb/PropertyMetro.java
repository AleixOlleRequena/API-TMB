package tmb;

/**
 * Classe creada per poder fer les crides a l'API de TMB sobre les estacions de metro
 */
public class PropertyMetro {
    /**
     * Conté el codi de l'estació
     */
    private String CODI_GRUP_ESTACIO;
    /**
     * Conté el nom de l'estació
     */
    private String NOM_ESTACIO;

    /**
     * Retorna el nom de l'estació
     * @return NOM_ESTACIO
     */
    public String getNOM_ESTACIO() {
        return NOM_ESTACIO;
    }

    /**
     * Retorna el codi de l'estació
     * @return CODI_GRUP_ESTACIO
     */
    public String getCODI_GRUP_ESTACIO() {
        return CODI_GRUP_ESTACIO;
    }
}
