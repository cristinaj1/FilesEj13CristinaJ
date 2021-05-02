/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;

/**
 *
 * @author cristina
 */
public class Programa {

    //Crea directorios que nos servirán cuando queramos crear archivos(contiene try(){}catch{})
    private static void creaDirectorio(String nombre) {

        Path gestionDeDirectorios = Paths.get(nombre);
        try {
            Files.createDirectory(gestionDeDirectorios);
        } catch (IOException e) {
            System.out.println("No se pudo crear");
            System.out.println(e.toString());
        }
    }

    public static void main(String[] args) throws IOException, JAXBException {

        //Añadimos los directorios(Si no, nos da error en la ruta)
        creaDirectorio("appstsv");
        creaDirectorio("appsxml");
        creaDirectorio("appsjson");
        creaDirectorio("copias");
        creaDirectorio("aplicaciones");

        //Genera 50 aplicaciones y la añado en una lista
        ArrayList<App> listaApps = new ArrayList<>();
        for (int i = 1; i < 51; i++) {
            listaApps.add(App.crearAppAleatoria());
        }
        for (App listaApp : listaApps) {
            System.out.println(listaApp);
        }
        //Creamos el fichero con el metodo de otra clase(Creada anteriormente).
        //pasamos lista y la ruta que queremos y lo genera
        ServicioFicheroTSV ficheroTSV = new ServicioFicheroTSV();
        ficheroTSV.generarFicheroDesdeLista(listaApps, "./appstsv/aplicaciones.tsv");

        //Creamos el archivo en xml
        //pasamos lista y la ruta que queremos y lo genera
        ServicioFicheroXML generarFicheroXML = new ServicioFicheroXML();
        generarFicheroXML.generarFicheroXML(listaApps, "./appsxml/aplicaciones.xml");

        //Creamos los fichero JSON individualmente de cada tipo de app
        //pasamos lista y la ruta que queremos y lo genera
        ServicioFicheroJSON ficheroJSON = new ServicioFicheroJSON();
        ficheroJSON.generarFicheroJSON(listaApps, "./appsjson/aplicaciones.json");
        for (int i = 0; i < listaApps.size(); i++) {
            ficheroJSON.generarFicheroJSON(listaApps.get(i));
        }
    }
}

