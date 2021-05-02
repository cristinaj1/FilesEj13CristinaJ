/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author cristina
 */
public class PruebaEj12 {

    public static void main(String[] args) throws JAXBException, FileNotFoundException {

        // Crea el contexto JAXB 
        JAXBContext contexto = JAXBContext.newInstance(CatalogoAplicaciones.class);
        // Crea el objeto Unmarshaller
        Unmarshaller um = contexto.createUnmarshaller();

        // Llama al método de unmarshalling
        CatalogoAplicaciones catalogo = (CatalogoAplicaciones) um.unmarshal(new File("./appsxml/aplicaciones.xml"));

        ArrayList<App> listaAplicaciones = catalogo.getListaMuebles();

        listaAplicaciones.forEach(System.out::println);
    }

}
