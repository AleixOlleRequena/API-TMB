package Main;

/**
 * Classe creada per poder llegir el JSON retornat per l'API de TMB
 */
public class Itinerary implements Comparable<Itinerary> {
    /**
     * duració del recorregut
     */
    private int duration;
    /**
     * trams que es fan en el recorregut
     */
    private Leg[] legs;

    /**
     * Retorna la duració del recorregut
     * @return duration en segons
     */
    public int getDuration() {
        return duration;
    }


    /**
     * Retorna els trams del recorregut
     * @return legs
     */
    public Leg[] getLegs() {
        return legs;
    }

    /**
     * Ordena diferents recorreguts de més ràpid a més lent
     * @param i Recorregut
     * @return -1 o 1 depenent si és mes petit o més gran
     */
    @Override
    public int compareTo(Itinerary i) {
        if (getDuration() < i.getDuration()) {
            return -1;
        }
        if (getDuration() > i.getDuration()) {
            return 1;
        }
        return 0;
    }
}
