package tmb;


/**
 * Classe creada per poder fer les crides a l'API de TMB sobre les estacions de metro i les seves línies
 */
public class PropertyMetroLine {
    /**
     * Nom de l'estació de metro
     */
    private String NOM_ESTACIO;
    /**
     * Nom d'una línia de metro que passa per l'estació
     */
    private String NOM_LINIA;
    /**
     * Data d'inauguració de l'estació
     */
    private String DATA_INAUGURACIO;

    /**
     * Retorna el nom de l'estacio
     * @return NOM_ESTACIO
     */
    public String getNOM_ESTACIO() {
        return NOM_ESTACIO;
    }

    /**
     * Retorna el nom de la línia de metro
     * @return NOM_LINIA
     */
    public String getNOM_LINIA() {
        return NOM_LINIA;
    }


    /**
     * Retorna la data d'inauguració de l'estació
     * @return DATA_INAUGURACIÖ
     */
    public String getDATA_INAUGURACIO() {
        return DATA_INAUGURACIO;
    }

}

