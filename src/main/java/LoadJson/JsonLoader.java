package LoadJson;

import Main.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;

/**
 * Classe que interactua amb els diferents arxius JSON que l'applicació utilitza
 */
public class JsonLoader {

    /**
     * Retorna la informació del JSON localitzacions.json ja dividida en classes depenent del tipus de localització
     * @param locales Localitzacions
     * @return locales
     */
    public LinkedList<Locale> deserialize(LinkedList<Locale> locales){


        try {
            JsonReader jsonreader = new JsonReader(new FileReader("localitzacions.json"));

            jsonreader.beginObject();
            jsonreader.nextName();
            jsonreader.beginArray();

            while(jsonreader.hasNext()){

                Locale new_locale = new Locale();
                double[]  new_coordinates = new double[2];


                //begin reading an object and get it's first row.
                jsonreader.beginObject();
                jsonreader.nextName();
                new_locale.setName(jsonreader.nextString());

                //skip coordinate's name.
                jsonreader.nextName();

                //begin coordinates array and get it's data.
                jsonreader.beginArray();
                new_coordinates[0] = jsonreader.nextDouble();
                new_coordinates[1] = jsonreader.nextDouble();
                jsonreader.endArray();
                new_locale.setCoordinates(new_coordinates);

                //skip description name and get data.
                jsonreader.nextName();
                new_locale.setDescription(jsonreader.nextString());

                //if there is another object, we check what it is and save it accordingly.
                if(jsonreader.hasNext()){

                    String aux_string = jsonreader.nextName();
                    if(aux_string.equals("architect")){

                        String new_architect = jsonreader.nextString();
                        jsonreader.nextName();
                        locales.addFirst(new Monument(new_locale, new_architect, jsonreader.nextInt()));
                    }

                    if(aux_string.equals("stars")){

                        locales.addFirst(new Hotel(new_locale, jsonreader.nextInt()));
                    }

                    if(aux_string.equals("characteristics")){

                        LinkedList<String> new_characteristics = new LinkedList<String>();
                        jsonreader.beginArray();
                        while(jsonreader.hasNext()){

                            new_characteristics.addFirst(jsonreader.nextString());
                        }
                        jsonreader.endArray();

                        locales.addFirst(new Restaurant(new_locale, new_characteristics));
                    }
                }
                else{

                    locales.addFirst(new_locale);
                }

                jsonreader.endObject();
            }

            jsonreader.endArray();
            jsonreader.endObject();
            jsonreader.close();


        } catch (IOException e) {

            System.out.println("Error en la lectura del Json, més informació a sota.");

            e.printStackTrace();
        }

        return locales;
    }

    /**
     * Omple el JSON user.json amb la informació de l'usuari
     * @param user Informacio de l'usuari
     */
    public void fillJsonUser(Usuari user){

        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().excludeFieldsWithoutExposeAnnotation().create();
        try{


            FileWriter fileWriter = new FileWriter("user.json");
            gson.toJson(user, fileWriter);
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    /**
     * Carrega la informació del JSON user.json a l'aplicació
     * @return user
     */
    public Usuari loadUser(){

        Usuari user = new Usuari();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        try{

            Reader fileReader = new FileReader("user.json");
            user = gson.fromJson(fileReader, Usuari.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //al tenir favoriteLocale i Usuari apuntant-se l'un a l'altre es creava un json infinit. per solucionarho s'exclouen els camps no exposats i els introduim manualment.
        for(FavoriteLocale f: user.getFavoriteLocales()){

            f.setUser(user);
        }


        return user;
    }
}
