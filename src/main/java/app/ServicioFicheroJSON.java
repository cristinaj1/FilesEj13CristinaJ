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
}
