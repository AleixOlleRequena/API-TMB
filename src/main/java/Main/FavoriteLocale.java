package Main;

import com.google.gson.annotations.Expose;

import java.util.Date;

/**
 * Classe de les localitzacions que l'usuari guarda com a preferides
 */
public class FavoriteLocale {
    /**
     * Data en la que es guarda com a preferida
     */
    @Expose
    private Date date;
    /**
     * Tipus de localització preferida
     */
    @Expose
    private String type;
    @Expose
    /**
     * Informació de la localització
     */
    private Locale locale;
    /**
     * Informació de l'usuari
     */
    private Usuari user;

    /**
     * Constructor de la classe
     * @param type Tipus de localitazcio
     * @param locale Localitzacio
     * @param user Informacio de l'usuari
     */
    public FavoriteLocale(String type, Locale locale, Usuari user) {
        this.date = new Date();
        this.type = type;
        this.locale = locale;
        this.user = user;
    }

    /**
     * Retorna la informació de la localització
     * @return locale
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Introdueix la informació de l'usuari
     * @param user Informacio de l'usuari
     */
    public void setUser(Usuari user) {
        this.user = user;
    }
}
