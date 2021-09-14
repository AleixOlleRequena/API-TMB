package Main;

import LoadJson.JsonLoader;

import java.io.File;
import java.util.Scanner;

/**
 * Classe encarregada dels diferents menús del programa
 */
public class Menu {
    /**
     * En cas que user.json no existeixi demana la informació a l'usuari.
     * Si existeix, dóna la benvinguda al usuari.
     * @return user
     */
    public Usuari printaIDemanaDadesInici(){

        Usuari user = new Usuari();

        File tempFile = new File("user.json");
        boolean exists = tempFile.exists();

        if(!exists) {

            Scanner sc = new Scanner(System.in);

            System.out.println("Benvingut a l'aplicació de TMBJson! Si us plau, introdueix les dades que se't demanen.\n");
            System.out.println("Nom d'usuari:");
            user.setUsername(sc.nextLine());

            System.out.println("\nCorreu electrònic:");
            user.setEmail(sc.nextLine());

            System.out.println("\nAny de naixement:");
            user.setBirthYear(sc.nextInt());

            System.out.println("La informació s'ha registrat amb èxit! \n");

        }
        else{

            JsonLoader jsonLoader = new JsonLoader();

            user = jsonLoader.loadUser();
            System.out.println("Benvingut de nou a l'aplicació de TMBJson " + user.getUsername() + "!\n");
        }

        return user;
    }

    /**
     * Printa el menu principal del programa i llegeix quina opció vol l'usuari
     * @return opcio
     */
    public int printaMenuInici(){

        Scanner sc = new Scanner(System.in);
        int opcio = 0;
        boolean correcte = false;

        System.out.println("\n1. Gestió d'usuari");
        System.out.println("2. Buscar localitzacions");
        System.out.println("3. Planejar ruta");
        System.out.println("4. Temps d'espera del bus");
        System.out.println("5. Sortir");
        System.out.println("\nSelecciona una opció:");

        while(!correcte) {
            if (!sc.hasNextInt()) {

                sc.next();
                System.out.println("Opció no vàlida!");
                System.out.println("L'opcio ha de ser un valor real");
                System.out.println("\nSelecciona una opció:");
            } else {

                opcio = sc.nextInt();
                if (opcio < 1 || opcio > 5) {

                    System.out.println("Opció no vàlida!");
                    System.out.println("L'opcio ha de ser un valor entre " + 1 + " i " + 5 + ".");
                    System.out.println("\nSelecciona una opció:");
                } else {
                    correcte = true;
                }
            }
        }

        return opcio;
    }

    /**
     * Printa el menu de l'opció 1 i llegeix quina opció vol l'usuari
     * @return opcio
     */
    public char printaMenuOpcio1(){

        Scanner sc = new Scanner(System.in);
        String opcio;
        boolean correcte = false;

        System.out.println("\nMenu Gestio Usuari");
        System.out.println("a)Les meves localitzacions");
        System.out.println("b)Historial de localitzacions");
        System.out.println("c)Les meves rutes");
        System.out.println("d)Parades i estacions preferides");
        System.out.println("e)Estacions inaugurades el meu any de naixement");
        System.out.println("f)Tornar al menú principal");
        System.out.println("\nSelecciona una opció:");


        do{

            opcio = sc.nextLine();
            if(opcio.equalsIgnoreCase("a") ||
                    opcio.equalsIgnoreCase("b") ||
                    opcio.equalsIgnoreCase("c") ||
                    opcio.equalsIgnoreCase("d") ||
                    opcio.equalsIgnoreCase("e") ||
                    opcio.equalsIgnoreCase("f") ){

                correcte = true;

            }
            else{

                System.out.println("Opció no vàlida!");
                System.out.println("L'opcio ha de ser un valor caracter entre a) i f).");
                System.out.println("\nSelecciona una opció:");
            }

        }while(!correcte);

        return (opcio.toLowerCase().charAt(0));
    }
}
