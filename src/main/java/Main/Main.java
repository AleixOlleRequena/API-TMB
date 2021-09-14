package Main;

        import LoadJson.JsonLoader;
        import tmb.Ibus;

/**
 * Classe principal de l'aplicació
 * @author Aleix Ollé Requena - aleix.olle
 * @author Ferran Mateu Solans - ferran.mateu
 */
public class Main {
    /**
     * Mètode principal de l'aplicació, a partir d'ell funciona tot el programa
     * @param args
     */
    public static void main(String[] args){

        DataModel dataModel = new DataModel();
        JsonLoader jsonLoader = new JsonLoader();
        Menu menu = new Menu();

        int opcio = 0;
        char opcio_2;

        //inicialitzacio del programa.
        dataModel.setLocales(jsonLoader.deserialize(dataModel.getLocales()));
        dataModel.setUser(menu.printaIDemanaDadesInici());

        //bucle principal i menu.
        do{
           opcio = menu.printaMenuInici();

           switch(opcio){
               case 1:

                    do {
                        opcio_2 = menu.printaMenuOpcio1();
                        switch (opcio_2) {
                            case 'a':
                                dataModel.insereixLocale();
                                break;

                            case 'b':
                                dataModel.localesBuscades();
                                break;

                            case 'c':
                                dataModel.lesMevesRutes();
                                break;

                            case 'd':
                                dataModel.paradesProperes(false);
                                break;

                            case 'e':
                                dataModel.estacionsInaugurades();
                                break;

                            default:
                                break;
                        }
                    }while(opcio_2 != 'f');

            break;

               case 2:

                   dataModel.buscaLocale();
                   break;

               case 3:
                   dataModel.planejarRuta();
                   break;

               case 4:

                   dataModel.printaTempsEspera();
                   break;

               default:
                   break;
            }
        }while(opcio != 5);

        jsonLoader.fillJsonUser(dataModel.getUser());
    }
}
