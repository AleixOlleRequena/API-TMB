
package Main;

import tmb.*;

import java.util.*;

/**
 *Classe que conté la informació de les localitzacions a més de la d'usuari
 * Durà a terme els mètodes relacionats amb el funcionament de les opcions del programa
 */
public class DataModel {

    private LinkedList<Locale> locales;
    private Usuari user;
    private LinkedList<String> localesBuscades;
    private LinkedList<Itinerary> rutesBuscades;
    private LinkedList<InfoRuta> infoRutesBuscades;

    /**
     * Constructor de la classe, crea les LinkedList necessàries per l'execució correcte del programa
     */
    public DataModel() {

        locales = new LinkedList<Locale>();
        localesBuscades = new LinkedList<String>();
        rutesBuscades = new LinkedList<Itinerary>();
        infoRutesBuscades = new LinkedList<InfoRuta>();
    }

    /**
     * Retorna les localitzacions guardades en localitzacions.json
     * @return locales
     */
    public LinkedList<Locale> getLocales() {
        return locales;
    }

    /**
     * Permet introduir les localitzacions a la LinkedList pertinent
     * @param locales Diferents localitzacions
     */
    public void setLocales(LinkedList<Locale> locales) {
        this.locales = locales;
    }

    /**
     * Retorna la informació de l'usuari
     * @return user
     */
    public Usuari getUser() {
        return user;
    }

    /**
     * Permet introduïr un usuari
     * @param user Informacio d'usuari
     */
    public void setUser(Usuari user) {
        this.user = user;
    }

    /**
     * Mètode de l'opció 2 del menú, busca les localitzacions que vol l'usuari
     * Un cop trobades pregunta si les vol guardar a preferides i ho fa en cas afirmatiu
     */
    public void buscaLocale(){

        String nom_buscat;
        Locale locale_buscat = new Locale();        //cal fer un new o surt error.
        boolean trobat = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el nom d'una localització:");

        nom_buscat = sc.nextLine();

        //Mirem a les localitzacions generals si està la que busquem.
        for(Locale l: locales){

           if (nom_buscat.compareTo(l.getName()) == 0){

            trobat = true;
            locale_buscat = l;
            localesBuscades.addFirst(nom_buscat);
           }
        }
        //si no ho està busquem a les del usuari.
        if(!trobat) {
            for (Locale m : user.getUser_locales()) {

                if (nom_buscat.compareTo(m.getName()) == 0) {

                    trobat = true;
                    locale_buscat = m;
                    localesBuscades.addFirst(nom_buscat);
                }
            }
        }

        if(!trobat){

            System.out.println("\nHo sentim, no hi ha cap localització amb aquest nom.");
        }
        else {

            //Un cop trobat la printem per pantalla!
            locale_buscat.printaTot();
            System.out.println("\nVols guardar la localització trobada com a preferida? (sí/no)");

            String resposta = sc.nextLine();
            boolean correcte = false;
            //demanem si volen guardar com a preferit i revisem la resposta.
            while(!correcte){

                if(resposta.equalsIgnoreCase("No")){

                    correcte = true;
                }
                if(resposta.equalsIgnoreCase("Sí") || resposta.equalsIgnoreCase("Si")){

                    correcte = true;

                    if(user.isFavorite(locale_buscat)) {

                        System.out.println("Aquesta localització ja es preferida!\n");
                    }
                    else {

                        System.out.println("Tipus(casa/feina/estudis/oci/cultura):\n");
                        String tipus = sc.nextLine();
                        while (!tipus.equalsIgnoreCase("casa") &&
                                !tipus.equalsIgnoreCase("feina") &&
                                !tipus.equalsIgnoreCase("estudis") &&
                                !tipus.equalsIgnoreCase("oci") &&
                                !tipus.equalsIgnoreCase("cultura")) {

                            System.out.println("Error! S'ha d'introduir \"casa\", \"feina\", \"estudis\", \"oci\" o \"cultura\"");
                            System.out.println("Tipus(casa/feina/estudis/oci/cultura):\n");
                            tipus = sc.nextLine();
                        }

                        FavoriteLocale favoriteLocale = new FavoriteLocale(tipus, locale_buscat, user);
                        user.addFavoriteLocales(favoriteLocale);
                        System.out.println("Localització guardada com a preferida");
                    }
                    }
                if(!correcte){
                    System.out.println("Opció incorrecta, introdueix \"sí\" o \"no\".");
                    resposta = sc.nextLine();
                }
            }
        }
    }

    /**
     * Classe que dur a terme la opció 4 del menú principal
     * Amb un codi de parada fa una crida a l'API i retorna el temps d'espera per els següents busos
     * Si la parada és preferida també ho mostra
     * @see ApiManager
     */
    public void printaTempsEspera(){

        Scanner sc = new Scanner(System.in);
        String codiParada;
        String desti;
        LinkedList<Integer> paradesPreferides;
        ApiManager apiManager = new ApiManager();
        boolean correcte = false;

        System.out.println("Introdueix el codi de parada:");
        do {
            codiParada = sc.nextLine();
            if(codiParada.length()<= 4 && (codiParada.compareTo("0000") >= 0 & codiParada.compareTo("9999") <= 0) ){


                if(apiManager.getIbusData(codiParada)) {
                    paradesPreferides = paradesProperes(true);
                    correcte = true;
                    int j = 0;
                    while(j < paradesPreferides.size()){
                        if(paradesPreferides.get(j) == Integer.parseInt(codiParada)){
                            System.out.println("Parada preferida!");
                        }
                        j++;
                    }

                    for(Ibus i : apiManager.getIbus()){

                        desti = apiManager.getDesti(i.getLine(), codiParada);
                        System.out.println(i.getLine() + " - " + desti + " - " + i.getText_ca());
                    }
                }
                else{
                    correcte = false;
                    System.out.println("\nAquesta parada no existeix!");
                    System.out.println("Introdueix el codi de parada:");
                }


            }
            else{
                System.out.println("\nError, codi de parada no vàlid!");
                System.out.println("Introdueix el codi de parada:");
            }


        }while(!correcte);

    }

    /**
     * Mètode de la opció a dins la opció 1 del menú principal
     * Permet que l'usuari insereixi una nova localització
     * Si ja n'ha creat, les mostra per pantalla
     */
    public void insereixLocale(){
        String nom_buscat;
        String opcio;
        String description;
        boolean trobat;
        boolean error = false;
        double [] coordinates = new double[2];

        Locale userLocale = new Locale();

        Scanner sc = new Scanner(System.in);

        //Mostrem les localitzacions creades per l'usuari
        if(user.getUser_locales().size() == 0){
            System.out.println("No tens cap localització creada.");

        }else{

            for (Locale m : user.getUser_locales()) {
                System.out.println("- " + m.getName());
            }
        }


        do{
            System.out.println("Vols crear una nova localització? (sí/no)");
            opcio = sc.nextLine();
            if(opcio.equalsIgnoreCase("Sí") || opcio.equalsIgnoreCase("Si")){
                //Demanem el nom de la nova localització i comprovem si existeix
                do{
                    trobat = false;
                    System.out.println("Nom de la localització:");
                    nom_buscat = sc.nextLine();
                    //Busquem la localització a les creades per l'usuari
                    for (Locale m : user.getUser_locales()) {
                        if (nom_buscat.compareTo(m.getName()) == 0) {
                            trobat = true;
                            System.out.println("Error! Aquesta localització ja existeix");
                        }
                    }

                    //Busquem la localizació a les generals
                    for (Locale m : getLocales()) {
                        if (nom_buscat.compareTo(m.getName()) == 0) {
                            trobat = true;
                            System.out.println("Error! Aquesta localització ja existeix");
                        }
                    }

                }while (trobat);

                //Demanem latitud i longitud
                do{
                    error = false;
                    System.out.println("longitud:");
                    coordinates[0] = Double.parseDouble(sc.nextLine());

                    if(coordinates[0] < -90|| coordinates[0] > 90){
                        error = true;
                        System.out.println("Error! La longitud no és correcte");
                    }
                }while (error);

                do{
                    error = false;
                    System.out.println("latitud:");
                    coordinates[1] =  Double.parseDouble(sc.nextLine());

                    if(coordinates[1] < -180 || coordinates[1] > 180){
                        error = true;
                        System.out.println("Error! La latitud no és correcte");
                    }
                }while (error);

                //Demanem la descipció de la localització.
                System.out.println("Descripció:");
                description = sc.nextLine();

                //Inserim la informació en una Localització per a continuació guardar-ho com a userLocale
                userLocale.setCoordinates(coordinates);
                userLocale.setDescription(description);
                userLocale.setName(nom_buscat);

                user.addUserLocale(userLocale);
                System.out.println("La informació s'ha registrat amb èxit!");

                //Printem totes les localitzacions amb la nova incorporada
                for (Locale m : user.getUser_locales()) {
                    System.out.println("- " + m.getName());
                }

            }else {
                if(opcio.equalsIgnoreCase("No")){
                    error = false;
                }else {
                    error = true;
                    System.out.println("Error! S'ha d'introduir \"sí\" o \"no\".");
                }
            }


        }while(error);

    }

    /**
     * Mètode de la opció b dins la opció 1 del menú principal
     * Mostra les localitzacions que l'usuari ha buscat amb èxit
     */
    public void localesBuscades(){

        if (localesBuscades.size() == 0){
            System.out.println("Encara no has buscat cap localització!\nPer buscar-ne una, accedeix a l'opció 2 del menú principal.");
        }else{
            System.out.println("Localitzacions buscades:");
            for (String s: localesBuscades) {
                System.out.println("    -" + s);
            }
        }
    }

    /**
     * Mètode de la opció d dins la opció 1 del menú principal
     * Retorna les parades que es troben a menys de 500 metres de les localitzacions preferides de l'usuari
     * La utilitzem dins un altre mètode, amb el paràmetre tempsEspera indiquem si ens trobem a la opció d o dins del mètode
     * Així el mètode no mostra missatges quan està sent utilitzat dins d'un altre mètode
     * @param tempsEspera Boolea per saber a quin mètode estem. True = opcio 3 menu Principal
     * @return codiParadesPreferides
     */
    public LinkedList<Integer> paradesProperes(boolean tempsEspera){

         LinkedList<Integer> codiParadaPreferides = new LinkedList<Integer>();

        if (user.getFavoriteLocales().size() == 0 && !tempsEspera){
            System.out.println("Per tenir parades i estacions preferides es requereix haver creat una localització preferida anteriorment.");
        }else{
            MetroStation metros = new MetroStation();
            BusStop busos = new BusStop();
            ApiManager apiManager = new ApiManager();

            metros = apiManager.getMetroStations();
            busos = apiManager.getBusStops();

            for(FavoriteLocale f: user.getFavoriteLocales()){
                LinkedList<InfoParades> paradesProperes = new LinkedList<InfoParades>();

                //Busquem les estacions de metro més properes a les localitzacions preferides
                for(FeatureMetro m: metros.getFeatures()){
                    InfoParades parada = new InfoParades();
                    if(Math.abs(distanciaCoord(f.getLocale().getCoordinates()[1],f.getLocale().getCoordinates()[0],m.getGeometry().getCoordinates()[1], m.getGeometry().getCoordinates()[0])
                    )<= 0.5){
                        parada.setNomParada(m.getProperties().getNOM_ESTACIO());
                        parada.setCodiParada(Integer.parseInt(m.getProperties().getCODI_GRUP_ESTACIO()));
                        parada.setType("METRO");
                        parada.setDistancia(Math.abs(distanciaCoord(f.getLocale().getCoordinates()[1],f.getLocale().getCoordinates()[0],m.getGeometry().getCoordinates()[1], m.getGeometry().getCoordinates()[0])));

                        paradesProperes.addFirst(parada);

                    }

                }

                //Busquem les parades de bus més properes a les localitzacions preferides
                for (FeatureBus b: busos.getFeatureBuses()){
                    InfoParades parada = new InfoParades();
                    if(Math.abs(distanciaCoord(f.getLocale().getCoordinates()[1],f.getLocale().getCoordinates()[0],b.getGeometry().getCoordinates()[1], b.getGeometry().getCoordinates()[0])
                    )<= 0.5){
                        parada.setNomParada(b.getProperties().getNOM_PARADA());
                        parada.setCodiParada((b.getProperties().getCODI_PARADA()));
                        parada.setType("BUS");
                        parada.setDistancia(Math.abs(distanciaCoord(f.getLocale().getCoordinates()[1],f.getLocale().getCoordinates()[0],b.getGeometry().getCoordinates()[1], b.getGeometry().getCoordinates()[0])));

                        paradesProperes.addFirst(parada);
                        if(!codiParadaPreferides.contains(parada.getCodiParada()) && tempsEspera){
                            codiParadaPreferides.addFirst(parada.getCodiParada());
                        }

                    }
                }



                if(paradesProperes.size() == 0 && !tempsEspera){
                    System.out.println("-" + f.getLocale().getName());
                    System.out.println("TMB està fent tot el possible perquè el bus i el metro arribin fins aquí.");
                }else{
                    //Ordenem les parades segons la distancia a la localització
                    paradesProperes.sort(Comparator.comparing(InfoParades::getDistancia));
                    if(!tempsEspera){
                        System.out.println("-" + f.getLocale().getName());
                        int i = 0;
                        for(InfoParades p: paradesProperes){
                            i++;
                            System.out.println(i + ") " + p.getNomParada() + " (" + p.getCodiParada() + ") " + p.getType());
                        }
                    }

                }




            }


        }

    return codiParadaPreferides;
    }

    /**
     * Mètode de la opció e dins la opció 1 del menú principal
     * Retorna les estacions que es van inaugurar l'any de naixement de l'usuari
     * Si no es va inaugurar capp estació es mostra un missatge al respecte
     */
    public void estacionsInaugurades(){
        ApiManager apiManager = new ApiManager();
        MetroStationLine metros = new MetroStationLine();

        metros = apiManager.getMetroStationsLine();
        boolean ok = false;
        System.out.println("Estacions inaugurades l'any " +  user.getBirthYear() + ":");

        for(FeatureMetroLine m: metros.getFeatures()){
            String any ;

            any = m.getProperties().getDATA_INAUGURACIO().substring(0,4);

            if( any.equals(Integer.toString(user.getBirthYear())) ){
                ok = true;
                System.out.println("    -" + m.getProperties().getNOM_ESTACIO() + " " + m.getProperties().getNOM_LINIA());
            }
        }

        if (!ok){
            System.out.println("Cap estació de metro es va inaugurar el teu any de naixement :(");
        }
    }


    /**
     * Mètode de la opció 3 del menú principal
     * Demana a l'usuari la informació necessària per fer una crida a l'API i mostra quina és la ruta més ràpida fins al destí
     * En cas que la informació sigui errònia o no hi hagi ruta es mostra un missatge.
     */
    public void planejarRuta(){
        String aux;
        String origen;
        String origenInfo;
        String desti;
        String destiInfo;
        String arribada = null;
        String dia;
        String hora;
        String metres;
        boolean correcte = false;
        Ruta ruta;

        Scanner sc = new Scanner(System.in);
        ApiManager apiManager = new ApiManager();

        //Preguntem l'origen a l'usuari
        do{
            System.out.println("Origen? (lat,lon/nom localització)");
            aux = sc.nextLine();

            origen = origenDesti(aux);
            origenInfo = aux;
            if(origen.equals("NO")){
                System.out.println("Ho sentim, aquesta localització no és vàlida :(\n");
            }
        }while (origen.equals("NO"));

        //Preguntem el destí a l'usuari
        do{
            System.out.println("Destí? (lat,lon/nom localització)");
            aux = sc.nextLine();

            desti = origenDesti(aux);
            destiInfo = aux;

            if(desti.equals("NO")){
                System.out.println("Ho sentim, aquesta localització no és vàlida :(\n");
            }
        }while (desti.equals("NO"));

        //Demanem si el dia/hora serà de sortida o arribada
        do{
            System.out.println("Dia/hora seran de sortida o d'arribada? (s/a)");
            aux = sc.nextLine();

            if(aux.equalsIgnoreCase("s") || aux.equalsIgnoreCase("a")){
                arribada = aux;
                correcte = true;
            }else{
                System.out.println("Error! S'ha d'introduir \"s\" o \"a\"!");
            }
        }while(!correcte);
        
        //Passem arribada a true o false per poder-la utilitzar en l'API
        if(arribada.equalsIgnoreCase("s")){
            arribada = "false";
        }else{
            arribada = "true";
        }
        //Demanem el dia
        System.out.println("\nDia? (MM-DD-YYYY)");
        dia = sc.nextLine();

        //Demanem l'hora
        System.out.println("\nHora? (HH:MMam/HH:MMpm)");
        hora = sc.nextLine();

        //Demanem els metres maxims a caminar
        System.out.println("\nMàxima distància caminant en metres?");
        metres = sc.nextLine();

        ruta = apiManager.buscaRuta(origen, desti, dia, hora, arribada, metres);

        if(ruta != null){
            //Ordenem les rutes de més ràpida a més lenta per poder quedar-nos amb la més ràpida
            Arrays.sort(ruta.getPlan().getItineraries());

            System.out.println("\nCombinació més ràpida:");
            System.out.println("    Temps del trajecte: " + (ruta.getPlan().getItineraries()[0].getDuration()/60) + " min");
            System.out.println("\n    Origen");
            System.out.println("   |");

            for(Leg l: ruta.getPlan().getItineraries()[0].getLegs()){
                if(l.getMode().equalsIgnoreCase("walk")){
                    System.out.println("    caminar " + l.getDuration()/60 + " min");
                    System.out.println("   |");
                    System.out.println("   |");

                }else {
                    if (l.getMode().equalsIgnoreCase("subway") || l.getMode().equalsIgnoreCase("bus")) {

                        System.out.println("    " + l.getRoute() + " " + l.getFrom().getName() + "(" + l.getFrom().getStopCode() + ")" +
                                " -> " + l.getTo().getName() + "(" + l.getTo().getStopCode() + ") " + l.getDuration() / 60 + " min");
                        System.out.println("   |");
                    }
                }
            }

            System.out.println("    Destí");

            //Guardem la ruta per poder mostrar-la més endavant
            InfoRuta info = new InfoRuta(origenInfo, destiInfo, arribada, dia, hora, metres);
            infoRutesBuscades.addFirst(info);
            rutesBuscades.addFirst(ruta.getPlan().getItineraries()[0]);
        }

    }

    /**
     * Mètode de la opció c dins la opció 1 del menú principal
     * Mostra a l'usuari les diferents rutes que ha buscat durant l'execució del programa
     */
    public void lesMevesRutes(){
        int i = 0;
        if(rutesBuscades.size() == 0){
            System.out.println("Encara no has realitzat cap ruta :(");
            System.out.println("Per buscar-ne una, accedeix a l'opció 3 del menú principal.");
        }else{
            for(Itinerary r: rutesBuscades){

                System.out.println("->Ruta " + (i+1) + ":");
                System.out.println("    -Origen: " + infoRutesBuscades.get(i).getOrigen());
                System.out.println("    -Destí: " + infoRutesBuscades.get(i).getDesti());
                if(infoRutesBuscades.get(i).getArribada() == "true"){
                    System.out.println("    -Dia d'arribada: " + infoRutesBuscades.get(i).getDia() + " a les " + infoRutesBuscades.get(i).getHora());
                }else{
                    System.out.println("    -Dia de sortida: " + infoRutesBuscades.get(i).getDia() + " a les " + infoRutesBuscades.get(i).getHora());

                }
                System.out.println("    -Màxima distància caminant: " + infoRutesBuscades.get(i).getMetres() + " metres");
                System.out.println("        -Combinació més ràpida:");
                System.out.println("            Temps del trajecte: " + (r.getDuration()/60) + " min");
                System.out.println("\n          Origen");
                System.out.println("            |");

                for(Leg l: r.getLegs()){
                    if(l.getMode().equalsIgnoreCase("walk")){
                        System.out.println("            caminar " + l.getDuration()/60 + " min");
                        System.out.println("            |");
                        System.out.println("            |");

                    }else {
                        if (l.getMode().equalsIgnoreCase("subway") || l.getMode().equalsIgnoreCase("bus")) {

                            System.out.println("            " + l.getRoute() + " " + l.getFrom().getName() + "(" + l.getFrom().getStopCode() + ")" +
                                    " -> " + l.getTo().getName() + "(" + l.getTo().getStopCode() + ") " + l.getDuration() / 60 + " min");
                            System.out.println("            |");
                        }
                    }
                }

                System.out.println("            Destí");
                i++;
            }
        }
    }

    /**
     * Mètode utilitzat dins d'altres mètodes que rep una localització (nom/coordenades)
     * Retorna les coordenades preparades per l'API en cas que la localització existeixi
     * @param localitzacio Nom o coordenades d'una localitzacio
     * @return origen
     */
    public String origenDesti(String localitzacio){
        String origen = null;
        boolean correcte = false;
        String[] coordenades;
        String ajuda;

        
        coordenades = localitzacio.split(",");


        //Mirem que el que ens hagi introduït l'usuari siguin coordenades o un nom
        if(coordenades.length > 2){
            System.out.println("Ho sentim, aquesta localització no és vàlida :(");
        }else{

            //Si introdueix coordenades fem una recerca per aquestes
            if(coordenades.length == 2){
                coordenades[0] = coordenades[0].trim();
                coordenades[1] = coordenades[1].trim();

                if(Double.parseDouble(coordenades[0]) > 180 || Double.parseDouble(coordenades[0]) < -180){
                    System.out.println(Double.parseDouble(coordenades[0]));
                    System.out.println("Ho sentim, aquesta localització no és vàlida :(");
                    

                }else{
                    if(Double.parseDouble(coordenades[1]) > 90 || Double.parseDouble(coordenades[1]) < -90){
                        System.out.println("Ho sentim, aquesta localització no és vàlida :(");
                    }
                }
                origen = coordenades[0] + "," + coordenades[1];
                correcte = true;
            }else{
                //Si introdueix un nom buscarem per nom

                //Busquem entre les localitzacions del JSON
                for(Locale l: locales){
                    if(localitzacio.compareTo(l.getName()) == 0){
                        origen = Arrays.toString(l.getCoordinates());
                        origen = origen.substring(1, origen.length() - 1);
                        origen = origen.replace(" ", "");

                        //Fem això perquè en el JSON la longitud està abans que la latitud i nosaltres ho necessitem al revés
                        coordenades = origen.split(",");
                        ajuda = coordenades[0];
                        coordenades[0] = coordenades[1];
                        coordenades[1] = ajuda;
                        origen = coordenades[0] + "," + coordenades[1];
                        correcte = true;
                    }
                }

                //Busquem entre les localitzacions creades per l'usuari
                for(Locale l: user.getUser_locales()){
                    if(localitzacio.compareTo(l.getName()) == 0){
                        origen = Arrays.toString(l.getCoordinates());
                        origen = origen.substring(1, origen.length() - 1);
                        origen = origen.replace(" ", "");
                        //Fem això perquè en el JSON la longitud està abans que la latitud i nosaltres ho necessitem al revés
                        coordenades = origen.split(",");
                        ajuda = coordenades[0];
                        coordenades[0] = coordenades[1];
                        coordenades[1] = ajuda;
                        origen = coordenades[0] + "," + coordenades[1];
                        correcte = true;                    }
                }

            }
        }
        
        if(correcte){
            return origen;
        }else{
            return "NO";
        }
        
    }


    /**
     * Mètode que calcula la distància entre dos punts utilitzant el mètode de la distància del haversine
     * @param lat1 latitud 1
     * @param lng1 longitud 1
     * @param lat2 latitud 2
     * @param lng2 longitud 2
     * @return distancia en kilometres
     */
    //PER CALCULAR DISTANCIES
    public double distanciaCoord(double lat1, double lng1, double lat2, double lng2) {
        double radioTierra = 6371;//en kilómetros
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        double distancia = radioTierra * va2;

        return distancia;
    }


}
