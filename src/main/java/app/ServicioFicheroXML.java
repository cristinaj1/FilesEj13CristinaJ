/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author cristina
 */
public class ServicioFicheroXML {

    //Metodo que genera un archivo XML pasandole una lista y el nombre del fichero a crear
    public void generarFicheroXML(ArrayList<App> listaApp, String idFichero) throws JAXBException {

        CatalogoAplicaciones catalogo = new CatalogoAplicaciones();
        catalogo.setLista(listaApp);
        catalogo.setDescripcion("Mi catalogo de aplicaciones");

        // Crea el contexto JAXB. Se encarga de definir los objetos
        // que vamos a guardar, que será el objeto catalogo de la
        //clase CatalogoApp
        JAXBContext contexto = JAXBContext.newInstance(CatalogoAplicaciones.class);

        // El contexto JAXB permite crear un objeto Marshaller, que sirve para
        // generar la estructura del fichero XML 
        // El proceso de pasar objetos Java (CatalogoApp) a ficheros XML 
        // se conoce como "marshalling" o "serialización"
        Marshaller serializador = contexto.createMarshaller();

        // Especificamos que la propiedad del formato de salida
        // del serializador sea true, lo que implica que el formato se 
        // realiza con indentación y saltos de línea
        serializador.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Llamando al método de serialización marshal (sobrecargado) se pueden
        // serializar objetos java en formato XML y volcarlos donde necesitemos:
        // consola, ficheros. El proceso consiste en que el contexto es el 
        // encargo de leer los objetos java, pasarlos al serializador y éste 
        // crear la salida de serialización. Serialización y salida por consola
        serializador.marshal(catalogo, System.out);

        // Volcado al fichero xml
        serializador.marshal(catalogo, new File(idFichero));
        System.out.println("Archivo XML creado correctamente");

    }
}
