package Main;

/**
 * Classe herència de Locale que conté la informació extra dels hotels
 */
public class Hotel extends Locale{
    /**
     * Estrelles que té l'hotel
     */
    private int stars;

    /**
     * Constructor de la classe
     * @param locale Localitzacio
     * @param stars Estrelles de l'hotel
     */
    public Hotel(Locale locale, int stars) {
        this.stars = stars;

        super.coordinates = locale.getCoordinates();
        super.description = locale.getDescription();
        super.name = locale.getName();
    }

    /**
     * Printa tota la informació de l'hotel
     */
    public void printaTot(){

        System.out.println("\nPosició:" + this.coordinates[0] + "," +  this.coordinates[1]);
        System.out.println("Descripció:");
        System.out.println(this.description);
        System.out.println("Estrelles: " +  stars);
    }
}
