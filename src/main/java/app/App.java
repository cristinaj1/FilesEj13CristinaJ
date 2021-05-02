/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Cris
 */
// Anotación @XmlRootElement, nombre de la etiqueta XML raíz.
@XmlRootElement(name = "mueble")
// Anotación @XmlAccesorType define el elemento que usará JAXB durante el 
// procesamiento de datos (en este caso por atributo)
@XmlAccessorType(XmlAccessType.FIELD)
public class App {

    private int codUnico;
    private String nombre;
    private String descripcion;
    private double tamanioKb;
    @XmlJavaTypeAdapter(value = AdaptadorLocalDate.class)
    private LocalDate fechaCreacion;

    private static Random random = new Random();
    private static int contadorApps = 1;

    public App() {
        contadorApps++;
    }

    public App(int codUnico, String nombre, String descripcion, double tamanioKb, LocalDate fecha) {
        this.codUnico = codUnico;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tamanioKb = tamanioKb;
        this.fechaCreacion = fecha;
        contadorApps++;
    }

    //Genera una App de forma aleatoria.
    public static App crearAppAleatoria() {
        App aleatorio = new App(contadorApps, generarNombre(), descripcion(), generarTamanio(), fechaAleatoria());
        return aleatorio;
    }

    //Genera tamaño aleatoria entre 100 y 1024
    private static double generarTamanio() {
        final double MIN = 100.0;
        final double MAX = 1024.0;
        return random.doubles(1, MIN, MAX).sum();
    }

    //Genera el nombre como se indica("app"+ condUnico + letra(a-z))
    private static String generarNombre() {
        char letra = (char) (random.nextInt(122 - 97 + 1) + 97);
        return "app" + contadorApps + letra;
    }

    //Crea 10 descripciones aleatorio y me devuelve 1.
    private static String descripcion() {
        int numero = random.nextInt(10) + 1;
        String texto = "";
        switch (numero) {
            case 1:
                texto = "Recetario";
                break;
            case 2:
                texto = "Classroom";
                break;
            case 3:
                texto = "Spotify";
                break;
            case 4:
                texto = "Youtube";
                break;
            case 5:
                texto = "Netflix";
                break;
            case 6:
                texto = "Meet";
                break;
            case 7:
                texto = "Amazon Prime Video";
                break;
            case 8:
                texto = "HBO España";
                break;
            case 9:
                texto = "Juego de snake";
                break;
            case 10:
                texto = "Monopoly";
                break;
        }
        return texto;
    }

    //Crea una fecha aleatoria y me devuelve una(Con formato LocalDate)
    private static LocalDate fechaAleatoria() {
        int mes = random.nextInt(12 - 1 + 1) + 1;
        int dia;

        if (mes == 2) {
            dia = random.nextInt(28) + 1;
        } else if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
            dia = random.nextInt(31) + 1;
        } else {
            dia = random.nextInt(30) + 1;
        }
        int anio = random.nextInt(2021 - 2010 + 1) + 2010;

        LocalDate fecha = LocalDate.of(anio, mes, dia);
        fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return fecha;
    }

    //getters y setters
    public int getCodUnico() {
        return codUnico;
    }

    public void setCodUnico(int codUnico) {
        this.codUnico = codUnico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getTamanioKb() {
        return tamanioKb;
    }

    public void setTamanioKb(double tamanioKb) {
        this.tamanioKb = tamanioKb;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    //To String
    @Override
    public String toString() {
        return nombre + "\t" + descripcion + "\t" + tamanioKb + "\t" + fechaCreacion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.codUnico;
        hash = 79 * hash + Objects.hashCode(this.nombre);
        hash = 79 * hash + Objects.hashCode(this.descripcion);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.tamanioKb) ^ (Double.doubleToLongBits(this.tamanioKb) >>> 32));
        hash = 79 * hash + Objects.hashCode(this.fechaCreacion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final App other = (App) obj;
        if (this.codUnico != other.codUnico) {
            return false;
        }
        if (Double.doubleToLongBits(this.tamanioKb) != Double.doubleToLongBits(other.tamanioKb)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.fechaCreacion, other.fechaCreacion)) {
            return false;
        }
        return true;
    }

}
