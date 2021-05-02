/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cristina
 */
public class ServicioFicheroJSON {

    public void generarFicheroJSON(ArrayList<App> listaApps, String idFichero) throws IOException {
        ObjectMapper mapeador = new ObjectMapper();

        mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);

        mapeador.writeValue(new File("./appsjson/aplicaciones.json"), listaApps);

    }

    //Lo sobreescribo para que pueda haber 2 que nos puedan servir
    public static void generarFicheroJSON(App aplicacion) throws IOException {

        ObjectMapper mapeador = new ObjectMapper();

        mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);

        // Escribe en un fichero JSON el objeto que le pasamos
        mapeador.writeValue(new File("./aplicaciones/" + aplicacion.getNombre() + ".json"), aplicacion);

    }

    //lee el fichero JSON el cual está lleno de objetos y devuelve la lista.
    //Hay que pasarle la ruta del archivo que queremos leer como parámetro 
    public ArrayList leerConjuntoJSON(String idFichero) {

        ArrayList<App> catalogoAplicaciones = new ArrayList<>();
        try {
            ObjectMapper mapeador = new ObjectMapper();

            catalogoAplicaciones = mapeador.readValue(new File(idFichero),
                    mapeador.getTypeFactory().constructCollectionType(ArrayList.class, App.class));

        } catch (IOException ioe) {
            Logger.getLogger(ServicioFicheroJSON.class.getName()).log(Level.SEVERE, null, ioe);
        }

        return catalogoAplicaciones;
    }

    //Lee sólamente una App desde un fichero json cuyo nombre se pasa como parámetro y devolverá un objeto de tipo App.
    public App leerJsonIndividual(String idFichero) {

        App aplicacionPrueba = new App();
        try {
            ObjectMapper mapeador = new ObjectMapper();

            aplicacionPrueba = mapeador.readValue(new File(idFichero), App.class);
        } catch (IOException ioe) {
            System.out.println("Archivo no encontrado");
        }

        return aplicacionPrueba;
    }

}
