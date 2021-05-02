/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Cris
 */
public class ServicioFicheroTSV {
    
    //Ayuda a generar un fichero TSV a partir de una lista, para ello también tenemos que especificar la ruta
    public void generarFicheroDesdeLista(ArrayList<App> lista, String idFichero) {
        
        try ( BufferedWriter flujo = new BufferedWriter(new FileWriter(idFichero))){
            
            for (App app : lista) {
                flujo.write(app.toString());
                flujo.newLine();
            }
            //Llamada -explicita- para que se guarde lo que se llama en el disco
            //Sin flush funciona pero se recomienda.
            flujo.flush();
            
            System.out.println("El fichero " + idFichero + " se ha creado correctamente");
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }

}
