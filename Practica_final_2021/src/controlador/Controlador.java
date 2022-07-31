package controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import modelo.Modelo;

/**
 *
 * @author david
 */
public class Controlador {

    Modelo m = new Modelo();

    /*
            ░█▀▀░█▀▀░█▀█░█▀▀░█▀▄░█▀█░█░░░█▀▀░█▀▀
            ░█░█░█▀▀░█░█░█▀▀░█▀▄░█▀█░█░░░█▀▀░▀▀█
            ░▀▀▀░▀▀▀░▀░▀░▀▀▀░▀░▀░▀░▀░▀▀▀░▀▀▀░▀▀▀
     */
    public LocalDate cadenaALocalDate(String cadenaNacimiento) {
        return m.cadenaALocalDate(cadenaNacimiento);
    }

    public boolean eliminarFichero(String nombreFichero) {
        return m.eliminarFichero(nombreFichero);
    }

    public int comprobarFichero(String nombreFichero, String extension) {
        return m.comprobarFichero(nombreFichero, extension);
    }
    
    public void recortarArray(String[] array) {
        m.recortarArray(array);
    }

    /*
            ░█▀█░█▀▄░█▀▄░█▀█░█▀█░█▀▀░█▀█░█▀▄░
            ░█▀█░█▀▄░█▀▄░█▀█░█░█░█░░░█▀█░█▀▄░
            ░▀░▀░▀░▀░▀░▀░▀░▀░▀░▀░▀▀▀░▀░▀░▀░▀░
     */
    public boolean hayAlmacenado(String tipo) {
        return m.hayAlmacenado(tipo);
    }
    
    public void vaciarColeccionDeRegistros(String tipo) {
        m.vaciarColeccionDeRegistros(tipo);
    }
    
    public boolean importarDeBin(String tipo) {
        return m.importarDeBin(tipo);
    }

    public int importarDeAlternativo(String fileName, String datos) {
        return m.importarDeAlternativo(fileName, datos);
    }

    /*
            ░█▀█░█▀▄░█▀▀░█░█░▀█▀░█░█░█▀█░█▀▀░
            ░█▀█░█▀▄░█░░░█▀█░░█░░▀▄▀░█░█░▀▀█░
            ░▀░▀░▀░▀░▀▀▀░▀░▀░▀▀▀░░▀░░▀▀▀░▀▀▀░
     */
    public int peliculasHtml(String nombreFichero) {
        return m.peliculasHtml(nombreFichero);
    }

    public int directoresCol(String nombreFichero, boolean completo) {
        return m.directoresCol(nombreFichero, completo);
    }

    /*
            ░█▀█░█▀▀░█░░░▀█▀░█▀▀░█░█░█░░░█▀█░█▀▀░
            ░█▀▀░█▀▀░█░░░░█░░█░░░█░█░█░░░█▀█░▀▀█░
            ░▀░░░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░
     */
    public String consultaPelicula(String titulo) {
        return m.consultaPelicula(titulo);
    }

    public ArrayList<String> consultaPeliculasSimilares(String titulo) {
        return m.consultaPeliculasSimilares(titulo);
    }

    public boolean altasPeliculas(String titulo, int año, int duracion, String pais,
            String[] direccion, String guionista, String musica,
            String[] reparto, String productora, String genero,
            String sinopsis) {
        return m.altasPeliculas(titulo, año, duracion, pais, direccion,
                guionista, musica, reparto, productora,
                genero, sinopsis);
    }

    public boolean bajasPeliculas(String titulo) {
        return m.bajasPeliculas(titulo);
    }

    public boolean modificarAñoPelicula(String titulo, int nuevoValor) {
        return m.modificarAñoPelicula(titulo, nuevoValor);
    }

    public boolean modificarDuracionPelicula(String titulo, int nuevoValor) {
        return m.modificarDuracionPelicula(titulo, nuevoValor);
    }

    public boolean modificarPaisPelicula(String titulo, String nuevoValor) {
        return m.modificarPaisPelicula(titulo, nuevoValor);
    }

    public boolean modificarGuionistaPelicula(String titulo, String nuevoValor) {
        return m.modificarGuionistaPelicula(titulo, nuevoValor);
    }

    public boolean modificarMusicaPelicula(String titulo, String nuevoValor) {
        return m.modificarMusicaPelicula(titulo, nuevoValor);
    }

    public boolean modificarProductoraPelicula(String titulo, String nuevoValor) {
        return m.modificarProductoraPelicula(titulo, nuevoValor);
    }

    public boolean modificarGeneroPelicula(String titulo, String nuevoValor) {
        return m.modificarGeneroPelicula(titulo, nuevoValor);
    }

    public boolean modificarSinopsisPelicula(String titulo, String nuevoValor) {
        return m.modificarSinopsisPelicula(titulo, nuevoValor);
    }

    /*
            ░█▀▄░▀█▀░█▀▄░█▀▀░█▀▀░▀█▀░█▀█░█▀▄░█▀▀░█▀▀░
            ░█░█░░█░░█▀▄░█▀▀░█░░░░█░░█░█░█▀▄░█▀▀░▀▀█░
            ░▀▀░░▀▀▀░▀░▀░▀▀▀░▀▀▀░░▀░░▀▀▀░▀░▀░▀▀▀░▀▀▀░
     */
    public boolean altasDirectores(String nombre, LocalDate fechaNacimiento,
            String nacionalidad, String ocupacion, String[] peliculas) {
        return m.altasDirectores(nombre, fechaNacimiento, nacionalidad,
                ocupacion, peliculas);
    }

    public ArrayList<String> bajasDirectores(String nombre) {
        return m.bajasDirectores(nombre);
    }

    public ArrayList<String> bajasDirectoresConFecha(String nombre, LocalDate fechaNacimiento) {
        return m.bajasDirectoresConFecha(nombre, fechaNacimiento);
    }

    public boolean modificarFechaDirector(String nombre, LocalDate nuevoValor) {
        return m.modificarFechaDirector(nombre, nuevoValor);
    }

    public boolean modificarNacinalidadDirector(String nombre, String nuevoValor) {
        return m.modificarNacinalidadDirector(nombre, nuevoValor);
    }

    public boolean modificarOcupacionDirector(String nombre, String nuevoValor) {
        return m.modificarOcupacionDirector(nombre, nuevoValor);
    }

    /*
            ░█▀█░█▀▀░▀█▀░█▀█░█▀▄░█▀▀░█▀▀░
            ░█▀█░█░░░░█░░█░█░█▀▄░█▀▀░▀▀█░
            ░▀░▀░▀▀▀░░▀░░▀▀▀░▀░▀░▀▀▀░▀▀▀░
     */
    public boolean altasActores(String nombre, LocalDate fechaNacimiento,
            String nacionalidad, int añoDebut, String[] peliculas) {
        return m.altasActores(nombre, fechaNacimiento, nacionalidad,
                añoDebut, peliculas);
    }

    public ArrayList<String> bajasActores(String nombre) {
        return m.bajasActores(nombre);
    }

    public ArrayList<String> bajasActoresConFecha(String nombre, LocalDate fechaNacimiento) {
        return m.bajasActoresConFecha(nombre, fechaNacimiento);
    }

    public boolean modificarFechaActor(String nombre, LocalDate nuevoValor) {
        return m.modificarFechaActor(nombre, nuevoValor);
    }

    public boolean modificarNacinalidadActor(String nombre, String nuevoValor) {
        return m.modificarNacionalidadActor(nombre, nuevoValor);
    }

    public boolean modificarAñoDebutActor(String nombre, int nuevoValor) {
        return m.modificarAñoDebutActor(nombre, nuevoValor);
    }

    public ArrayList<String> peliculasActor(String nombre) {
        return m.peliculasActor(nombre);
    }

    public ArrayList<String> peliculasActoresConFecha(String nombre, LocalDate fechaNacimiento) {
        return m.peliculasActorConFecha(nombre, fechaNacimiento);
    }

    /*
            ░█░░░▀█▀░█▀▀░▀█▀░█▀█░█▀▄░█▀█░█▀▀░
            ░█░░░░█░░▀▀█░░█░░█▀█░█░█░█░█░▀▀█░
            ░▀▀▀░▀▀▀░▀▀▀░░▀░░▀░▀░▀▀░░▀▀▀░▀▀▀░
     */
    public ArrayList<String> listadosPeliculas() {
        return m.listadosPeliculas();
    }

    public ArrayList<String> listadosDirectores(boolean completo) {
        return m.listadosDirectores(completo);
    }

    public ArrayList<String> listadosActores(boolean completo) {
        return m.listadosActores(completo);
    }

    /*
            ░█▀▀░█░█░█▀█░█▀▄░█▀▄░█▀█░░░░█▀█░░░░░█▀▄░▀█▀░█▀█░█▀█░█▀▄░▀█▀░█▀█░
            ░█░█░█░█░█▀█░█▀▄░█░█░█▀█░░░░█▀█░░░░░█▀▄░░█░░█░█░█▀█░█▀▄░░█░░█░█░
            ░▀▀▀░▀▀▀░▀░▀░▀░▀░▀▀░░▀░▀░░░░▀░▀░░░░░▀▀░░▀▀▀░▀░▀░▀░▀░▀░▀░▀▀▀░▀▀▀░ 
     */
    public boolean guardarActoresEnBinario() {
        return m.guardarActoresEnBinario();
    }

    public boolean guardarDirectoresEnBinario() {
        return m.guardarDirectoresEnBinario();
    }

    public boolean guardarPeliculasEnBinario() {
        return m.guardarPeliculasEnBinario();
    }

    
}
