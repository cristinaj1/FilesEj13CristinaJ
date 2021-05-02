/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Cris
 */
public class PruebaEj13 {

    //Borra ficheros y directorios de la ruta que se pasa por parámetros
    //Para que se borre tiene que estar vacio
    public static void borrarFicherosYDirectoriosVacios(String ruta) {
        Path archivo = Paths.get(ruta);
        try {
            Files.delete(archivo);
        } catch (NoSuchFileException nsfe) {
            System.out.println("-----------La ruta " + ruta + " no existe---------");
        } catch (DirectoryNotEmptyException dnee) {
            System.out.println("--------No está vacío, elija otra opción---------");
        } catch (IOException ioe) {
            System.out.println("---------Problema borrando la ruta " + ruta + "--------");
        }
    }

    //Usando el stream guarda en una estructura Map el nombre de la app (como clave) 
    //y la fecha de creación (como valor) de aquellas apps cuyo tamaño esté entre 200 y 500 kb.
    public static void listaMap(ArrayList<App> lista) {
        //Api Stream tipo map
        Map<String, LocalDate> listaUsandoMap = lista.stream()
                .filter(a -> a.getTamanioKb() >= 200 && a.getTamanioKb() <= 500)
                .collect(Collectors.toMap(a -> a.getNombre(), a -> a.getFechaCreacion()));
        System.out.println("-------------App con nombre y fecha de creación-----------");
        for (Map.Entry<String, LocalDate> listaMap : listaUsandoMap.entrySet()) {
            System.out.println(listaMap.getKey() + " " + listaMap.getValue());
        }

    }

    //Crea un listado a partir de los ficheros que haya en la carpeta.
    public static void listaAPartirDeDirectorio(String ruta) {

        File archivo = new File(ruta);
        if (archivo.exists()) {
            File[] ficheros = archivo.listFiles();
            for (File file : ficheros) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("----------El directorio no existe----------");
        }
    }

    //Muestra una app la cual se pide por teclado y hace todo lo que especifica el ejercicio(borra,lista un directorio) menos el stream
    public static void mostrarAppMetidoPorTeclado() {

        Scanner teclado = new Scanner(System.in);
        String intro;
        boolean repetir = true;
        ServicioFicheroJSON generarJson = new ServicioFicheroJSON();

        ArrayList<App> listaApps = generarJson.leerConjuntoJSON("./appsjson/aplicaciones.json");
        listaAPartirDeDirectorio("./aplicaciones");
        System.out.println("------------Seleccione algún archivo (sin .json)-----------");
        intro = teclado.nextLine();

        do {

            for (int i = 0; i < listaApps.size(); i++) {

                if (listaApps.get(i).getNombre().matches(intro)) {

                    App aplic = generarJson.leerJsonIndividual("./aplicaciones/".concat(intro).concat(".json"));
                    System.out.println(aplic.toString());
                    System.out.println("---------El objeto ha sido eliminado correctamente--------");
                    borrarFicherosYDirectoriosVacios("./aplicaciones/".concat(intro).concat(".json"));
                    System.out.println("----------------Mostrando los archivos de ./aplicaciones de nuevo--------------");
                    listaAPartirDeDirectorio("./aplicaciones");
                    repetir = false;
                    break;
                }
            }
            if (repetir) {

                System.out.println("-------Ninguno de los archivos se llama así, introduce otro nombre.-------");
                intro = teclado.nextLine();
            }
        } while (repetir);
    }

    //Método main en el cual se ejecuta todo el programa
    public static void main(String[] args) {

        mostrarAppMetidoPorTeclado();

        ServicioFicheroJSON generarJson = new ServicioFicheroJSON();
        ArrayList<App> listaAplicaciones = generarJson.leerConjuntoJSON("./appsjson/aplicaciones.json");
        listaMap(listaAplicaciones);

    }
}
