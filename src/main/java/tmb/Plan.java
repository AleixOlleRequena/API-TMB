package tmb;

import Main.Itinerary;

/**
 * Classe creada per poder fer les crides a l'API de TMB sobre les rutes entre dos punts
 */
public class Plan {
    /**
     * Diferents itineraris que pot seguir l'usuari
     */
    private Itinerary[] itineraries;

    /**
     * Retorna els itineraris que pot seguir l'usuari
     * @return itineraries
     */
    public Itinerary[] getItineraries() {
        return itineraries;
    }

}
