package Main;

import com.google.gson.annotations.Expose;

import java.util.LinkedList;
/**
 * Classe herència de Locale que conté la informació extra dels restaurants
 */
public class Restaurant extends Locale{
    /**
     * Llista de carcterístiques del restaurant
     */
    @Expose
    private LinkedList<String> characteristics;

    /**
     * Constructor de la classe
     * @param locale Localitzacio
     * @param characteristics caracteristiques
     */
    public Restaurant(Locale locale, LinkedList<String> characteristics) {
        this.characteristics = characteristics;

        super.coordinates = locale.getCoordinates();
        super.description = locale.getDescription();
        super.name = locale.getName();
    }

    /**
     * Printa tota la informació del restaurant
     */
    public void printaTot(){

        System.out.println("\nPosició:" + this.coordinates[0] + "," +  this.coordinates[1]);
        System.out.println("Descripció:");
        System.out.println(this.description);
        System.out.println("Caracteristiques: " +  characteristics);
    }

}
