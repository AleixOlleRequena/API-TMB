package Main;

import com.google.gson.annotations.Expose;

/**
 * Classe herència de Locale que conté la informació extra dels monuments
 */
public class Monument extends Locale {
    /**
     * Arquitecte del monument
     */
    @Expose
    private String architect;
    /**
     * Any d'inauguració del monument
     */
    @Expose
    private int inauguration;

    /**
     * Constructor de la classe
     * @param locale Localitzacio
     * @param architect Arquitecte
     * @param inauguration Any inauguració
     */
    public Monument(Locale locale, String architect, int inauguration) {
        this.architect = architect;
        this.inauguration = inauguration;

        super.coordinates = locale.getCoordinates();
        super.description = locale.getDescription();
        super.name = locale.getName();
    }

    /**
     * Printa tota la informació del monument
     */
    public void printaTot(){

        System.out.println("\nPosició:" + this.coordinates[0] + "," +  this.coordinates[1]);
        System.out.println("Descripció:");
        System.out.println(this.description);
        System.out.println("Arquitecte: " + architect);
        System.out.println("Inauguracio: " + inauguration);
    }
}
