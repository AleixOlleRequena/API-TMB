package Main;

/**
 * Classe creada per fer més fàcil el tractament de les dades en el programa
 * Concretament en la opció d dins la opció 1 del menú principal
 * La utilitzem per tal fer més fàcil la ordenació per distància a les localitzacions preferides
 * i el tractament de la resta de dades a mostrar per pantalla
 */
public class InfoParades {
    /**
     * Nom de la parada
     */
    private String nomParada;
    /**
     * Codi de la Parada
     */
    private int codiParada;
    /**
     * Tipus de parada (BUS/METRO)
     */
    private String type;
    /**
     * Distància amb la localització preferida
     */
    private double distancia;

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    /*public InfoParades(String nomParada, int codiParada, String type, double distancia) {
        this.nomParada = nomParada;
        this.codiParada = codiParada;
        this.type = type;
        this.distancia = distancia;
    }*/

    /**
     * Cosntructor de la classe
     */
    public InfoParades() {
    }

    /**
     * Retorna la distància amb la localització preferida
     * @return distancia
     */
    public double getDistancia() {
        return distancia;
    }

    /**
     * Retorna el nom de la parada
     * @return nomParada
     */
    public String getNomParada() {
        return nomParada;
    }

    /**
     * Introdueix el nom de la parada
     * @param nomParada Nom de la parada
     */
    public void setNomParada(String nomParada) {
        this.nomParada = nomParada;
    }

    /**
     * Retorna el codi de la parada
     * @return codiParada
     */
    public int getCodiParada() {
        return codiParada;
    }

    /**
     * Introdueix le codi de la parada
     * @param codiParada Codi de parada
     */
    public void setCodiParada(int codiParada) {
        this.codiParada = codiParada;
    }

    /**
     * Retorna el tipus de parada
     * @return type (BUS/METRO)
     */
    public String getType() {
        return type;
    }

    /**
     * Introdueix el tipus de parada
     * @param type Tipus de parada
     */
    public void setType(String type) {
        this.type = type;
    }
}
