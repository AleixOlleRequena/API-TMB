package tmb;

import com.google.gson.annotations.SerializedName;

/**
 * Classe creada per poder fer les crides a l'API de TMB sobre el temps d'espera en les parades de bus
 */
public class Ibus {
    /**
     * Id de la ruta
     */
    private String routeId;
    /**
     * Línia de bus
     */
    private String line;
    /**
     * Temps que queda per el següent bus escrit en text
     */
    @SerializedName("text-ca")
    private String text_ca;
    /**
     * Temps que queda per el següent bus en segons
     */
    @SerializedName("t-in-s")
    private int t_in_s;
    /**
     * Temps que queda per el següent bus en minuts
     */
    @SerializedName("t-in-min")
    private int t_in_m;

    /**
     * Cosntructor de la classe
     */
    public Ibus() {
    }

    /**
     * Retorna la línia del bus
     * @return line
     */
    public String getLine() {
        return line;
    }

    /**
     * Retorna el temps que queda per el següent bus escrit en text
     * @return text_ca
     */
    public String getText_ca() {
        return text_ca;
    }

}
