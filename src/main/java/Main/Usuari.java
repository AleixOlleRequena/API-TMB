package Main;

import com.google.gson.annotations.Expose;

import java.util.LinkedList;

/**
 * Classe on guardem la informació relativa al usuari i a les seves localitzacions preferides i les creades per ell
 */
public class Usuari {

    /**
     * Nom de l'usuari
     */
    @Expose
    private String username;
    /**
     * Email de l'usuari
     */
    @Expose
    private String email;
    /**
     * Any de naixement
     */
    @Expose
    private int birthYear;
    /**
     * Localitzacions creades per l'usuari
     */
    @Expose
    private LinkedList<Locale> user_locales;
    /**
     * Localitzacions que l'usuari ha marcat com a preferides
     */
    @Expose
    private LinkedList<FavoriteLocale> favoriteLocales;

    /**
     * Constructor de la classe, crea les LinkedList de les localitzacions preferides i les de l'usuari
     */
    public Usuari() {

        this.user_locales = new LinkedList<Locale>();
        this.favoriteLocales = new LinkedList<FavoriteLocale>();
    }

    /**
     * Retorna el nom d'usuari
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * introduím el nom d'usuari
     * @param username Nom d'usuari
     */
    public void setUsername(String username) {
        this.username = username;
    }


    /**
     * Introduïm l'email de l'usuari
     * @param email Email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna l'any de naixement
     * @return birthYear
     */
    public int getBirthYear() {
        return birthYear;
    }

    /**
     * Introduïm l'any de naixement
     * @param birthYear Any de naixement
     */
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    /**
     * Retorna les localitzacions de l'usuari
     * @return user_locales
     */
    public LinkedList<Locale> getUser_locales() {
        return user_locales;
    }

    /**
     * Retorna les localitzacions preferides
     * @return favoriteLocales
     */
    public LinkedList<FavoriteLocale> getFavoriteLocales() {
        return favoriteLocales;
    }


    /**
     * Afegeix una localitzacio d'usuari a la LinkedList
     * @param userLocale Localitzacio a introduir a les de l'usuari
     */
    public void addUserLocale(Locale userLocale) {

        this.user_locales.addLast(userLocale);
    }

    /**
     * Afegeix una localitzacio preferida a la LinkedList
     * @param favoriteLocale Localització favorita a introduir
     */
    public void addFavoriteLocales(FavoriteLocale favoriteLocale) {

        this.favoriteLocales.addLast(favoriteLocale);
    }

    /**
     * Busca si una localització ja està a la LinkedList de preferides
     * @param fav Localització
     * @return true: Ja està a preferides o false: No està a preferides
     */
    public boolean isFavorite(Locale fav) {

        boolean trobat = false;
        for (FavoriteLocale f : favoriteLocales) {

            if(f.getLocale().getName().equals(fav.getName())){

                trobat = true;
            }
        }
        return trobat;
    }


}
