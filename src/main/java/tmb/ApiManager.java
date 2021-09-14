package tmb;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * Classe que fa les crides a l'API de TMB quan els diferents mètodes d'altres classes ho necessiten
 */
public class ApiManager {
    /**
     * Temps que queda perquè vinguin els autobusos a una estació en concret
     */
    private Ibus[] ibus;
    /**
     * Informació sobre les parades d'autobus
     */
    private BusStop busStop;
    /**
     * Informació sobre les parades de metro
     */
    private MetroStation metroStation;
    /**
     * Informació sobre les parades de metro i de les línies a les que pertanyen
     */
    private MetroStationLine metroStationLine;
    /**
     * Informació sobre les rutes entre dos punts
     */
    private Ruta ruta;

    /**
     * Constructor de la classe
     */
    public ApiManager() {
    }

    /**
     * Retorna la informació dels busos que venen a una parada en concret
     * @return ibus
     */
    public Ibus[] getIbus() {
        return ibus;
    }

    /**
     * Mètode que crida a l'API de TMB per rebre la informació de totes les parades de bus
     * Retorna la informació que rep estructurada
     * @return busStop
     */
    public BusStop getBusStops(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.tmb.cat/v1/transit/parades?app_id=e5d9c882&app_key=7b3aa1e6d9c5d1c069ae20cbea23e58d")
                .build();
        Response responses;

        try {

            responses = client.newCall(request).execute();
            String jsonData = null;
            if (responses.body() != null) {
                jsonData = responses.body().string();
            }
            Gson gson = new Gson();
            busStop = gson.fromJson(jsonData, BusStop.class);
        } catch (
                IOException e) {

            e.printStackTrace();
        }
        return busStop;
    }

    /**
     * Mètode que crida a l'API de TMB per rebre la informació de totes les estacions de metro
     * Retorna la informació que rep estructurada
     * @return metroStation
     */
    public MetroStation getMetroStations(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.tmb.cat/v1/transit/estacions?app_id=e5d9c882&app_key=7b3aa1e6d9c5d1c069ae20cbea23e58d")
                .build();
        Response responses;

        try {

            responses = client.newCall(request).execute();
            String jsonData = null;
            if (responses.body() != null) {
                jsonData = responses.body().string();
            }

            Gson gson = new Gson();
            metroStation = gson.fromJson(jsonData, MetroStation.class);
        } catch (
                IOException e) {

            e.printStackTrace();
        }
        return metroStation;
    }

    /**
     * Mètode que crida a l'API de TMB per rebre la informació de totes les estacions de metro i les seves línies
     * Mètode creat per poder trobar els anys d'inauguracó de les estacions i mostrar-ho a l'opció e dins l'opció 1
     * del menú principal
     * Retorna la informació que rep estructurada
     * @return metroStationLine
     */
    public MetroStationLine getMetroStationsLine(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.tmb.cat/v1/transit/linies/metro/estacions?app_id=e5d9c882&app_key=7b3aa1e6d9c5d1c069ae20cbea23e58d")
                .build();
        Response responses;

        try {

            responses = client.newCall(request).execute();
            String jsonData = null;
            if (responses.body() != null) {
                jsonData = responses.body().string();
            }
            Gson gson = new Gson();
            metroStationLine = gson.fromJson(jsonData, MetroStationLine.class);
        } catch (
                IOException e) {

            e.printStackTrace();
        }
        return metroStationLine;
    }

    /**
     * Mètode que fa una crida a l'API de TMB per rebre la informació de l'origen i desti d'una linia en concret que
     * para en una determinada parada escollida per l'usuari
     * Retorna el destí de la línia de bus
     * @param line Linia de l'autobus
     * @param codiParada Codi de la Parada a cercar
     * @return sentit
     */
    public String getDesti(String line, String codiParada){

        String sentit = null;
        
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.tmb.cat/v1/transit/linies/bus/parades?filter=NOM_LINIA='" + line + "'&filter=CODI_PARADA=" + codiParada +
                        "&app_id=e5d9c882&app_key=7b3aa1e6d9c5d1c069ae20cbea23e58d")
                .build();
        Response responses;

        try {
            BusStop bus;
            responses = client.newCall(request).execute();
            String jsonData = null;
            if (responses.body() != null) {
                jsonData = responses.body().string();
            }
            Gson gson = new Gson();
            bus = gson.fromJson(jsonData, BusStop.class);
            
            sentit = bus.getFeatureBuses()[0].getProperties().getDESTI_SENTIT();
        } catch (
                IOException e) {

            e.printStackTrace();
        }
        return sentit;
    }

    /**
     * Mètode que fa una crida a l'API de TMB per rebre els busos que venen i el temps d'espera en una determinada parada
     * Guarda la informació en l'array Ibus
     * @param parada Codi de parada de bus
     * @return false si la parada no existeix o true si la parada existeix
     */
    public boolean getIbusData(String parada) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.tmb.cat/v1/ibus/stops/" + parada + "?app_id=e5d9c882&app_key=7b3aa1e6d9c5d1c069ae20cbea23e58d")
                .build();
        Response responses;
        try {

            responses = client.newCall(request).execute();
            String jsonData = null;
            if (responses.body() != null) {
                jsonData = responses.body().string();
            }

            //treiem del json les parts que no ens interessen per simplificar la deserlialitzacio.
            String jsonData2 = new String(jsonData.toCharArray(), 35, jsonData.length() - 37);
            Gson gson = new Gson();
            ibus = gson.fromJson(jsonData2, Ibus[].class);
        } catch (
                IOException e) {

            e.printStackTrace();
        }
        //com ja hem comprobat el codi de parAda, si ibus queda null es per que aquesta parada no existeix.
        if(ibus.length == 0){
            return false;
        }else{
            return true;
        }

    }


    /**
     * Mètode que fa una crida a l'API de TMB per rebre les rutes existents entre els dos punts marcats per l'usuari
     * Rep tota la informació necessària per poder fer la crida amb èxit
     * Retorna les diferents rutes
     * @param origen Origen de la ruta
     * @param desti Destí de la ruta
     * @param dia Dia de la ruta
     * @param hora Hora de sortida/arribada
     * @param arribada L'hora és de sortida o d'arribada
     * @param metres Metres màxims que l'usuari vol caminar
     * @return ruta
     */
    public Ruta buscaRuta(String origen, String desti, String dia, String hora, String arribada, String metres) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.tmb.cat/v1/planner/plan?app_id=e5d9c882&app_key=7b3aa1e6d9c5d1c069ae20cbea23e58d&" +
                        "fromPlace=" + origen +
                        "&toPlace=" + desti +
                        "&date=" + dia +
                        "&time=" + hora +
                        "&arriveBy=" + arribada +
                        "&mode=TRANSIT,WALK" +
                        "&maxWalkDistance=" + metres)
                .build();
        Response responses;
        try {

            responses = client.newCall(request).execute();
            String jsonData = null;
            if (responses.body() != null) {
                jsonData = responses.body().string();
            }

            //Comprovem que la informació que ens torni l'API és correcte, sinó mostrem missatge d'error
            if(jsonData.contains("Unexpected")){
                System.out.println("\nError, hi ha algun paràmetre erroni :(");
                return null;
            }else{
                if(jsonData.contains("No trip")){
                    System.out.println("\nTMB està fent tot el possible perquè el bus i el metro facin aquesta ruta en un futur.");
                    return null;
                }
            }

            Gson gson = new Gson();
            ruta = gson.fromJson(jsonData, Ruta.class);
        } catch (
                IOException e) {

            e.printStackTrace();
        }
        return ruta;
    }

}
