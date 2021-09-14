package Main;


import com.google.gson.annotations.Expose;

/**
 * Classe que conté la informació bàsica de totes les localitzacions
 */
public class Locale {
    /**
     * Nom de la localització
     */
    @Expose
    protected String name;
    /**
     * Coordenades de la localització
     */
    @Expose
    protected double[] coordinates;
    /**
     * Descripció de la localització
     */
    @Expose
    protected String description;

    /**
     * Constructor de la classe
     */
    public Locale() {
    }

    /**
     * Introdueix el nom de la localització
     * @param name Nom de la localitzacio
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Introdueix la descripció de la localització
     * @param description Descripcio
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Introdueix les coordenades de la localització
     * @param coordinates Coordenades
     */
    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Retorna el nom de la localització
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Retorna les coordenades de la localització
     * @return coordinates
     */
    public double[] getCoordinates() {
        return coordinates;
    }

    /**
     * Retorna la descripció de la localització
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Printa tota la informació de les localitzacions bàsiques
     */
    public void printaTot(){

        System.out.println("\nPosició:" + this.coordinates[0] + "," +  this.coordinates[1]);
        System.out.println("Descripció:");
        System.out.println(this.description);
    }


}
