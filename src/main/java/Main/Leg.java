package Main;

/**
 * Classe feta per llegir el JSON que retorna l'API de TMB
 */
public class Leg {
    /**
     * Mode del tram (WALK/SUBWAY/BUS)
     */
    private String mode;
    /**
     * Línia del bus/metro
     */
    private String route;
    /**
     * Duració del tram
     */
    private int duration;
    /**
     * Informació de l'origen del tram
     */
    private To from;
    /**
     * Informació del destí del tram
     */
    private To to;

    /**
     * Retorna el mode de transport
     * @return mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * Retorna la línia de bus/metro
     * @return route serà null si el tram és a peu
     */
    public String getRoute() {
        return route;
    }

    /**
     * Retorna la duració del tram
     * @return duration en segons
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Retorna la informació de l'origen
     * @return from
     */
    public To getFrom() {
        return from;
    }

    /**
     * Retorna la informació del destí
     * @return to
     */
    public To getTo() {
        return to;
    }
}
