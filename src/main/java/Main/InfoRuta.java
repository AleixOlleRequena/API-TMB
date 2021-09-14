package Main;

/**
 * Classe creada per facilitar el tractament de la informació
 * en el mètode de la opció c dins la opció 1 del menú principal
 * Per mostrar les rutes que ha fet l'usuari
 */
public class InfoRuta {
    /**
     * Origen de la ruta
     */
    String origen;
    /**
     * Destí de la ruta
     */
    String desti;
    /**
     * L'hora és de sortida o d'arribada
     */
    String arribada;
    /**
     * Dia de la ruta
     */
    String dia;
    /**
     * Hora de sortida/arribada
     */
    String hora;
    /**
     * Metres màxims que vol caminar l'usuari
     */
    String metres;

    /**
     * Constructor de la classe
     * @param origen Origen de la ruta
     * @param desti Desti de la ruta
     * @param arribada L'hora serà de sortida o d'arribada
     * @param dia Dia de la ruta
     * @param hora Hora de la ruta
     * @param metres Metres màxims que l'usuari vol caminar
     */
    public InfoRuta(String origen, String desti, String arribada, String dia, String hora, String metres) {
        this.origen = origen;
        this.desti = desti;
        this.arribada = arribada;
        this.dia = dia;
        this.hora = hora;
        this.metres = metres;
    }

    /**
     * Retorna l'origen de la ruta
     * @return origen
     */
    public String getOrigen() {
        return origen;
    }


    /**
     * Retorna el destí de la ruta
     * @return desti
     */
    public String getDesti() {
        return desti;
    }


    /**
     * Retorna si l'usuari vol que l'hora sigui de sortida o d'arribada
     * @return arribada
     */
    public String getArribada() {
        return arribada;
    }

    /**
     * Retorna el dia de la ruta
     * @return dia
     */
    public String getDia() {
        return dia;
    }

    /**
     * Retorna l'hora de sortida/arribada
     * @return hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * Retorna els metres màxims que l'usuari vol caminar
     * @return metres
     */
    public String getMetres() {
        return metres;
    }

}
