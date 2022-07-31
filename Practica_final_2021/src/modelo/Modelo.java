package modelo;

import com.coti.tools.Rutas;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author david
 */
public class Modelo {

    String carpetaArchivos = "IMDB21";
    Filmoteca filmoteca = new Filmoteca();

    /*
            ░█▀▀░█▀▀░█▀█░█▀▀░█▀▄░█▀█░█░░░█▀▀░█▀▀
            ░█░█░█▀▀░█░█░█▀▀░█▀▄░█▀█░█░░░█▀▀░▀▀█
            ░▀▀▀░▀▀▀░▀░▀░▀▀▀░▀░▀░▀░▀░▀▀▀░▀▀▀░▀▀▀
     */
    // Funcion auxiliar en caso de que el sistema esté en Español.
    private static Path pathToFileInFolderOnDesktopEs(String nameOfFolder, String nameOfFile) {
        Path p = FileSystems.getDefault().getPath(System.getProperty("user.home"),
                File.separator
                + "Escritorio"
                + File.separator
                + nameOfFolder
                + File.separator
                + nameOfFile);
        return p;
    }

    // Convertir String a LocalDate
    public LocalDate cadenaALocalDate(String fecha) {

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/M/yyyy");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("d/MM/yyyy");
        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate localDate = LocalDate.now().plusDays(1);

        try {
            localDate = LocalDate.parse(fecha, formatter1);
            return localDate;
        } catch (DateTimeParseException e) {
        }

        try {
            localDate = LocalDate.parse(fecha, formatter2);
            return localDate;
        } catch (DateTimeParseException e) {
        }

        try {
            localDate = LocalDate.parse(fecha, formatter3);
            return localDate;
        } catch (DateTimeParseException e) {
        }

        try {
            localDate = LocalDate.parse(fecha, formatter4);
            return localDate;
        } catch (DateTimeParseException e) {
        }

        return localDate;
    }

    // Comprobar si un fichero existe.
    public int comprobarFichero(String nombreFichero, String extension) {
        // Comprobamos si el fichero nombreFichero.html existe
        Path p;

        if ("es".equals(System.getProperty("user.language"))) {
            // En Español
            p = pathToFileInFolderOnDesktopEs(carpetaArchivos, nombreFichero + extension);
        } else {
            p = Rutas.pathToFileInFolderOnDesktop(carpetaArchivos, nombreFichero + extension);
        }

        if (p.toFile().exists()) {

            // EL fichero ya existe
            return 2;
        } else {

            if (extension.equals(".bin")) {
                return 0;
            }

            // EL fichero no existe, habrá que crearlo.
            try {
                p.toFile().createNewFile();
                return 0;
            } catch (IOException ex) {

                // Ha habido un error al crear el fichero.
                return 1;
            }
        }
    }

    // ELiminar un fichero, con nombre pasado por argumento
    public boolean eliminarFichero(String nombreFichero) {

        Path p;

        if ("es".equals(System.getProperty("user.language"))) {
            // En Español
            p = pathToFileInFolderOnDesktopEs(carpetaArchivos, nombreFichero);
        } else {
            p = Rutas.pathToFileInFolderOnDesktop(carpetaArchivos, nombreFichero);
        }

        File f = p.toFile();

        return (f.delete());

    }

    // Eliminar los espacios en blanco
    public void recortarArray(String[] array) {

        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].trim();
        }
    }

    /*
            ░█▀█░█▀▄░█▀█░█▀█░█▀▀░█▀█░█▀▄
            ░█▀█░█▀▄░█▀█░█░█░█░░░█▀█░█▀▄
            ░▀░▀░▀░▀░▀░▀░▀░▀░▀▀▀░▀░▀░▀░▀
     */
    // Comprobamos si hay datos almacenados del tipo pasado como argumento
    public boolean hayAlmacenado(String tipo) {
        
        switch (tipo){
            case "peliculas" -> {
                
                return !Filmoteca.peliculas.isEmpty();
            }
            
            case "actores" -> {
                
                return !Filmoteca.actores.isEmpty();
            }
            
            case "directores" -> {
                
                return !Filmoteca.directores.isEmpty();
            }
            
            default -> {
                
                return false;
            }
        }
    }
    
    // Vaciamos el ArrayList del objeto correspondiente.
    public void vaciarColeccionDeRegistros(String tipo) {
    
        switch (tipo){
            case "peliculas" -> {
                Filmoteca.peliculas.clear();
            }
            
            case "actores" -> {
                Filmoteca.actores.clear();
            }
            
            case "directores" -> {
                Filmoteca.directores.clear();
            }
        }
    }
    
    // Importar datos de un fichero bianario pasado por argumento
    public boolean importarDeBin(String tipo) {

        Path p;
        switch (tipo){
            case "peliculas" -> {
                p = pathToFileInFolderOnDesktopEs(carpetaArchivos, filmoteca.PELICULASSBIN);
                try {
                    FileInputStream fis = new FileInputStream(p.toFile());
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    ObjectInputStream ois = new ObjectInputStream(bis);
                    Filmoteca.peliculas = (List<Pelicula>) ois.readObject();
                    ois.close();
                } catch (IOException | ClassNotFoundException ex) {
                    return false;
                }
                
                return true;
            }
            
            case "actores" -> {
                
                p = pathToFileInFolderOnDesktopEs(carpetaArchivos, filmoteca.ACTORESBIN);
        
                try {
                    FileInputStream fis = new FileInputStream(p.toFile());
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    ObjectInputStream ois = new ObjectInputStream(bis);
                    Filmoteca.actores = (List<Actor>) ois.readObject();
                    ois.close();
                } catch (IOException | ClassNotFoundException ex) {
                    return false;
                }

                return true;
            }
            
            case "directores" -> {
                
                p = pathToFileInFolderOnDesktopEs(carpetaArchivos, filmoteca.DIRECTORESBIN);
                try {
                    FileInputStream fis = new FileInputStream(p.toFile());
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    ObjectInputStream ois = new ObjectInputStream(bis);
                    Filmoteca.directores = (List<Director>) ois.readObject();
                    ois.close();
                } catch (IOException | ClassNotFoundException ex) {
                    return false;
                }

                return true;
            }   
            
            default -> {
                return false;
            }
        }
    }

    // Importar los datos de un fichero de texto alternativo
    public int importarDeAlternativo(String fileName, String datos) {

        Path p;

        if ("es".equals(System.getProperty("user.language"))) {
            // En Español
            p = pathToFileInFolderOnDesktopEs(carpetaArchivos, fileName);
        } else {
            p = Rutas.pathToFileInFolderOnDesktop(carpetaArchivos, fileName);
        }

        switch (datos) {
            case "peliculas" -> {

                // Importamos los datos del fichero peliculas.txt
                if (p.toFile().exists()) {
                    try {
                        List<String> lineas = Files.readAllLines(p);
                        boolean leidoTodo = true;

                        for (String linea : lineas) {

                            String[] campos = linea.split("#");

                            recortarArray(campos);

                            Pelicula film = Pelicula.crearPeliculaAPartirDeCampos(campos);

                            if (film != null) {
                                if ( !Filmoteca.peliculas.contains(film) ){
                                    Filmoteca.peliculas.add(film);
                                }else{
                                    leidoTodo = false;
                                }
                            } else {
                                leidoTodo = false;
                            }
                        }

                        if (!leidoTodo) {
                            return 1;
                        }

                    } catch (IOException ex) {
                        return 3;
                    }
                } else {
                    return 2;
                }

                return Filmoteca.peliculas.size();
            }

            case "actores" -> {

                // Importamos los datos del fichero actores.txt
                if (p.toFile().exists()) {
                    try {
                        List<String> lineas = Files.readAllLines(p);
                        boolean leidoTodo = true;

                        for (String linea : lineas) {

                            String[] campos = linea.split("#");

                            recortarArray(campos);

                            Actor actor = Actor.crearActorAPartirDeCampos(campos);

                            if (actor != null) {
                                if ( !Filmoteca.actores.contains(actor) ){
                                    Filmoteca.actores.add(actor);
                                }else{
                                    leidoTodo = false;
                                }
                            } else {
                                leidoTodo = false;
                            }
                        }

                        if (!leidoTodo) {
                            return 1;
                        }

                    } catch (IOException ex) {
                        return 3;
                    }
                } else {
                    return 2;
                }

                return Filmoteca.actores.size();
            }

            case "directores" -> {

                // Importamos los datos del fichero directores.txt
                if (p.toFile().exists()) {
                    try {
                        List<String> lineas = Files.readAllLines(p);
                        boolean leidoTodo = true;

                        for (String linea : lineas) {

                            String[] campos = linea.split("#");

                            recortarArray(campos);

                            Director director = Director.crearDirectorAPartirDeCampos(campos);

                            if (director != null) {
                                if ( !Filmoteca.directores.contains(director) ){
                                    Filmoteca.directores.add(director);
                                }else{
                                    leidoTodo = false;
                                }
                            } else {
                                leidoTodo = false;
                            }
                        }

                        if (!leidoTodo) {
                            return 1;
                        }

                    } catch (IOException ex) {
                        return 3;
                    }
                } else {
                    return 2;
                }

                return Filmoteca.peliculas.size();
            }
        }

        return 4;

    }

    /*
            ░█▀█░█▀▄░█▀▀░█░█░▀█▀░█░█░█▀█░█▀▀░
            ░█▀█░█▀▄░█░░░█▀█░░█░░▀▄▀░█░█░▀▀█░
            ░▀░▀░▀░▀░▀▀▀░▀░▀░▀▀▀░░▀░░▀▀▀░▀▀▀░
     */
    // Exportar las peliculas en formato tabular a .HTML
    public int peliculasHtml(String nombreFichero) {

        Path p;

        if ("es".equals(System.getProperty("user.language"))) {
            // En Español
            p = pathToFileInFolderOnDesktopEs(carpetaArchivos, nombreFichero + ".html");
        } else {
            p = Rutas.pathToFileInFolderOnDesktop(carpetaArchivos, nombreFichero + ".html");
        }

        // Ya tenemos el fichero. Ahora hay que añadir la informacion en HTML.
        try {

            String html1 = """
                          <style>
                          	.demo {
                          		border:1px ninguna #000000;
                          		border-collapse:colapso;
                          		padding:8px;
                          	}
                          	.demo th {
                          		border:1px ninguna #000000;
                          		padding:8px;
                          		background:#F96262;
                          	}
                          	.demo td {
                          		border:1px ninguna #000000;
                          		padding:8px;
                          		background:#F39191;
                          	}
                          </style>
                          
                           
                           <table class="demo">
                                <pre align=\"center\">
                           
                                ░█▀█░█▀▀░█░░░▀█▀░█▀▀░█░█░█░░░█▀█░█▀▀░
                                ░█▀▀░█▀▀░█░░░░█░░█░░░█░█░█░░░█▀█░▀▀█░
                                ░▀░░░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░
                           
                                </pre>
                           	<thead>
                           	<tr>
                                        <th> TITULO </th>
                           		<th>   AÑO   </th>
                           		<th>  DURACIÓN  </th>
                           		<th>  PAIS  </th>
                           		<th>  DIRECCIÓN  </th>
                           		<th>  GUIONISTA  </th>
                                        <th>  MÚSICA  </th>
                                        <th>  REPARTO  </th>
                                        <th>  PRODUCTORA  </th>
                                        <th>  GÉNERO  </th>
                                        <th>  SINOPSIS  </th>
                           
                           	</tr>
                           	</thead>
                           	<tbody>
                           """;
            
            
            // Escribimos en el fichero en formato HTML
            PrintWriter pw = new PrintWriter(p.toFile());
            
            pw.printf(html1);

            // Ordenamos las peliculas antes de enviarlas al fichero
            ordenarPeliculas(Filmoteca.peliculas);

            // Escribimos las lineas como filas de una tabla en HTML
            for (Pelicula peli : Filmoteca.peliculas) {
                pw.printf("%s%n", peli.comoFilaDeTablaHTML());
            }

            pw.printf("</tbody>\n" +
                        "</table>");
            pw.close();

            return 0;
        } catch (FileNotFoundException ex) {
            return 1;
        }

    }

    // Exportar los directores en formato de columnas a .COL
    public int directoresCol(String nombreFichero, boolean completo) {

        Path p;
        if ("es".equals(System.getProperty("user.language"))) {
            // En Español
            p = pathToFileInFolderOnDesktopEs(carpetaArchivos, nombreFichero + ".col");
        } else {
            p = Rutas.pathToFileInFolderOnDesktop(carpetaArchivos, nombreFichero + ".col");
        }

        ArrayList<String> datosDirectores;

        if (completo) {
            datosDirectores = listadosDirectores(true);
        } else {
            datosDirectores = listadosDirectores(false);
        }

        try {

            // Imprimimos los datos en el archivo.
            Files.write(p, datosDirectores);
            return 0;
        } catch (IOException ex) {
            return 1;
        }

    }

    /*
            ░█▀█░█▀▀░█░░░▀█▀░█▀▀░█░█░█░░░█▀█░█▀▀░
            ░█▀▀░█▀▀░█░░░░█░░█░░░█░█░█░░░█▀█░▀▀█░
            ░▀░░░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░
     */
    // Buscar una pelicula (titulo pasado por arg) en la colección
    public String consultaPelicula(String titulo) {

        // Comprobamos si hay peliculas almacenadas
        if (Filmoteca.peliculas.isEmpty()) {
            // Si aún no hay peliculas, devolvemos vacio
            return "vacio";
        } else {
            for (Pelicula pelicula : Filmoteca.peliculas) {
                if (pelicula.getTitulo().equalsIgnoreCase(titulo)) {
                    
                    // La pelicula es esta. La devolvemos
                    return pelicula.toString();
                }
            }
            // Si la pelicula no se ha encontrado, se devuleve  null.
            return "error";
        }
    }

    public ArrayList<String> consultaPeliculasSimilares(String titulo) {
        String[] tituloSegmentado = titulo.split(" ");
        
        ArrayList<String> opciones = new ArrayList<>();
        
        for (String segmento : tituloSegmentado){
            
            if (segmento.trim().length() < 2 ){
                    
                continue;
            }
            
            for (Pelicula pelicula : Filmoteca.peliculas){
                
                if ( pelicula.getTitulo().toUpperCase().contains(segmento.trim().toUpperCase())){
                    
                    // La pelicula contiene parte del titulo original de la pelçicula buscada.
                    opciones.add(pelicula.getTitulo());
                }
            }
        }
        
        return opciones;
    }

    // Dar de alta una nueva película
    public boolean altasPeliculas(String titulo, int año, int duracion, String pais,
            String[] direccion, String guionista, String musica,
            String[] reparto, String productora, String genero,
            String sinopsis) {

        boolean encontrado = false;

        // Creamos la pelicula con los datos pasados como argumento
        Pelicula nueva = new Pelicula(titulo, año, duracion, pais, direccion,
                guionista, musica, reparto, productora,
                genero, sinopsis);

        // Comprobamos si la pelicula ya está introducida en la colección
        if ( Filmoteca.peliculas.contains(nueva)){
            return false;
        }

        if( direccion.length != 0 && !direccion[0].isBlank()){
            
            // AÑADIR LOS DIRECTORES DE LA NUEVA PELÍCULA
            for (String direccion1 : nueva.getDireccion()) {
                for (int j = 0; j < Filmoteca.directores.size(); j++) {
                    
                    /*
                    Comprobamos para cada director de la nueva pelicula si existe en la coleccin de directores.
                    Si lo hace, añadimos la pelicula, si no, añadimos el nuevo director.
                     */
                    if (direccion1.equalsIgnoreCase(Filmoteca.directores.get(j).getNombre())) {
                        // Añadimos el titulo de la pelicula.
                        List<String> listaPeliculas = new ArrayList<>(Arrays.asList(
                                Filmoteca.directores.get(j).getPeliculas()));

                        listaPeliculas.add(nueva.getTitulo());

                        Filmoteca.directores.get(j).setPeliculas(listaPeliculas.toArray(new String[0]));
                        encontrado = true;
                    }
                }
                
                // Comprobamos si se ha encontrado el director o habrá que crearlo
                if (!encontrado) {
                    // Lo creamos y añadimos
                    Filmoteca.directores.add(new Director(direccion1, nueva.getTitulo()));
                }
                encontrado = false;
            }
        }

        // AÑADIR LOS ACTORES DE LA NUEVA PELICULA
        if ( reparto.length != 0 && !reparto[0].isBlank()){

            // AÑADIR LOS ACTORES DE LA NUEVA PELÍCULA
            for (String reparto1 : nueva.getReparto()) {
                for (int j = 0; j < Filmoteca.actores.size(); j++) {
                   
                    /*
                    Comprobamos para cada actor de la nueva pelicula si existe en la coleccin de actores. 
                    Si lo hace, añadimos la pelicula, si no, añadimos el nuevo actor.
                     */
                    if (reparto1.equalsIgnoreCase(Filmoteca.actores.get(j).getNombre())) {
                        // Añadimos el titulo de la pelicula.
                        List<String> listaPeliculas = new ArrayList<>(Arrays.asList(
                                Filmoteca.actores.get(j).getPeliculas()));

                        listaPeliculas.add(nueva.getTitulo());

                        Filmoteca.actores.get(j).setPeliculas(listaPeliculas.toArray(new String[0]));
                        encontrado = true;
                    }
                }
                
                // Comprobamos si se ha encontrado el director o habrá que crearlo
                if (!encontrado) {
                    // Lo creamos y añadimos
                    Filmoteca.actores.add(new Actor(reparto1, nueva.getAño(), nueva.getTitulo()));
                }
                encontrado = false;
            }
        }

        return Filmoteca.peliculas.add(nueva);
    }

    public boolean bajasPeliculas(String titulo) {

        // Quitamos el titulo de la coleccin de directores y autores.
        // Recorremos colecciones directores y autores en busca de esa película
        for (Director director : Filmoteca.directores){
            for (String peliculaDirector : director.getPeliculas()){
                
                if (peliculaDirector.equalsIgnoreCase(titulo)){
                    // La pelicula está en la coleccion de peliculas del director
                    
                    peliculaDirector = "";
                }
            }
        }
        
        // Lo mismo con actores
        for (Actor actor : Filmoteca.actores){
            for (String peliculaActor : actor.getPeliculas()){
                
                if (peliculaActor.equalsIgnoreCase(titulo)){
                    // La pelicula está en la coleccion de peliculas del director
                    
                    peliculaActor = "";
                }
            }
        }

        boolean encontrada = false;
        
        for ( Pelicula pelicula : Filmoteca.peliculas ){
            if ( pelicula.getTitulo().equalsIgnoreCase(titulo)){
                encontrada = true;
            }
        }
        
        return encontrada;
    }

    /*
        *-*-* MODIFICAR CAMPOS DE PELÍCULAS *-*-*
    */
    public boolean modificarAñoPelicula(String titulo, int nuevoValor) {

        // Recorremos ~ PELICULAS ~ en busca del titulo pasado como argumento
        for (int i = 0; i < Filmoteca.peliculas.size(); i++) {
            if (Filmoteca.peliculas.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                // Se he encontrado la pelicula
                Filmoteca.peliculas.get(i).setAño(nuevoValor);
                return true;
            }
        }

        return false;
    }

    public boolean modificarDuracionPelicula(String titulo, int nuevoValor) {

        // Recorremos ~ PELICULAS ~ en busca del titulo pasado como argumento
        for (int i = 0; i < Filmoteca.peliculas.size(); i++) {
            if (Filmoteca.peliculas.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                // Se he encontrado la pelicula
                Filmoteca.peliculas.get(i).setDuracion(nuevoValor);
                return true;
            }
        }

        return false;
    }

    public boolean modificarPaisPelicula(String titulo, String nuevoValor) {

        // Recorremos ~ PELICULAS ~ en busca del titulo pasado como argumento
        for (int i = 0; i < Filmoteca.peliculas.size(); i++) {
            if (Filmoteca.peliculas.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                // Se he encontrado la pelicula
                Filmoteca.peliculas.get(i).setPais(nuevoValor);
                return true;
            }
        }

        return false;
    }

    public boolean modificarGuionistaPelicula(String titulo, String nuevoValor) {

        // Recorremos ~ PELICULAS ~ en busca del titulo pasado como argumento
        for (int i = 0; i < Filmoteca.peliculas.size(); i++) {
            if (Filmoteca.peliculas.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                // Se he encontrado la pelicula
                Filmoteca.peliculas.get(i).setGuionista(nuevoValor);
                return true;
            }
        }

        return false;
    }

    public boolean modificarMusicaPelicula(String titulo, String nuevoValor) {

        // Recorremos ~ PELICULAS ~ en busca del titulo pasado como argumento
        for (int i = 0; i < Filmoteca.peliculas.size(); i++) {
            if (Filmoteca.peliculas.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                // Se he encontrado la pelicula
                Filmoteca.peliculas.get(i).setMusica(nuevoValor);
                return true;
            }
        }

        return false;
    }

    public boolean modificarProductoraPelicula(String titulo, String nuevoValor) {

        // Recorremos ~ PELICULAS ~ en busca del titulo pasado como argumento
        for (int i = 0; i < Filmoteca.peliculas.size(); i++) {
            if (Filmoteca.peliculas.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                // Se he encontrado la pelicula
                Filmoteca.peliculas.get(i).setProductora(nuevoValor);
                return true;
            }
        }

        return false;
    }

    public boolean modificarGeneroPelicula(String titulo, String nuevoValor) {

        // Recorremos ~ PELICULAS ~ en busca del titulo pasado como argumento
        for (int i = 0; i < Filmoteca.peliculas.size(); i++) {
            if (Filmoteca.peliculas.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                // Se he encontrado la pelicula
                Filmoteca.peliculas.get(i).setGenero(nuevoValor);
                return true;
            }
        }

        return false;
    }

    public boolean modificarSinopsisPelicula(String titulo, String nuevoValor) {

        // Recorremos ~ PELICULAS ~ en busca del titulo pasado como argumento
        for (int i = 0; i < Filmoteca.peliculas.size(); i++) {
            if (Filmoteca.peliculas.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                // Se he encontrado la pelicula
                Filmoteca.peliculas.get(i).setSinopsis(nuevoValor);
                return true;
            }
        }

        return false;
    }

    /*
            ░█▀▄░▀█▀░█▀▄░█▀▀░█▀▀░▀█▀░█▀█░█▀▄░█▀▀░█▀▀░
            ░█░█░░█░░█▀▄░█▀▀░█░░░░█░░█░█░█▀▄░█▀▀░▀▀█░
            ░▀▀░░▀▀▀░▀░▀░▀▀▀░▀▀▀░░▀░░▀▀▀░▀░▀░▀▀▀░▀▀▀░
     */
    // Dar de alta un nuevo director
    public boolean altasDirectores(String nombre, LocalDate fechaNacimiento,
            String nacionalidad, String ocupacion, String[] peliculas) {

        // Creamos el nuevo director a partir de los datos pasados como argumento
        Director nuevo = new Director(nombre, fechaNacimiento, nacionalidad,
                ocupacion, peliculas);

        // Comprobamos si ya existe ese director en la colección
        if ( Filmoteca.directores.contains(nuevo)){
            return false;
        }
        
        return Filmoteca.directores.add(nuevo);
        
    }

    // Eliminar un director de la colección
    public ArrayList<String> bajasDirectores(String nombre) {

        ArrayList<String> dadasDeAlta = new ArrayList<>();
        ArrayList<String> datosDeDirector = new ArrayList<>();
        boolean eliminar = false;
        Director eliminado = new Director();
        datosDeDirector.add("repetido");

        int encontrados = 0;

        // Recorremos la colección en busca del director a eliminar
        for (Director director : Filmoteca.directores) {

            if (director.getNombre().equalsIgnoreCase(nombre)) {
                encontrados += 1;

                // Almacenamos los datos del director en un ArrayList.
                // Si se encuentran varios, se devolverán sus datos.
                datosDeDirector.add(director.getNombre() + "    "
                        + director.fechaNacimientoFormateada()
                        + "    " + director.getNacionalidad());

                // Es el director indicado
                if (director.getPeliculas().length == 0 && director.getPeliculas()[0].isBlank()) {

                    // Eliminar el director del array
                    eliminado = director;
                    eliminar = true;
                    dadasDeAlta.add("eliminado");
                } else {

                    // Comprobar si las peliculas están dadas de alta
                    for (String pelicula : director.getPeliculas()) {
                        
                        String compruebaPelicula = consultaPelicula(pelicula);
                        if ( !"error".equals(compruebaPelicula) && 
                                !"vacio".equals(compruebaPelicula)) {
                            
                            // Hay alguna pelicula dada de alta
                            if (dadasDeAlta.isEmpty()) {
                                dadasDeAlta.add("noEliminado");
                            }

                            dadasDeAlta.add(pelicula);
                        }
                    }
                    
                    if (dadasDeAlta.isEmpty()){
                        
                        System.out.println("***** entro en está vacio.");
                        // NO tiene peliculas dadas de alta
                        eliminado = director;
                        eliminar = true;
                        dadasDeAlta.add("eliminado");
                    }
                    
                }
            }

        }

        if (encontrados > 1) {
            return datosDeDirector;
        }

        if (eliminar == true) {
            Filmoteca.directores.remove(eliminado);
        }
        
        List<String> sinRepetir = dadasDeAlta.stream().distinct().collect(Collectors.toList());
        dadasDeAlta.clear();
        dadasDeAlta.addAll(sinRepetir);

        return dadasDeAlta;
    }

    public ArrayList<String> bajasDirectoresConFecha(String nombre, LocalDate fechaNacimiento) {

        ArrayList<String> dadasDeAlta = new ArrayList<>();
        ArrayList<String> datosDeDirector = new ArrayList<>();
        Director eliminado = new Director();

        for (Director director : Filmoteca.directores) {

            if (director.getNombre().equalsIgnoreCase(nombre)) {
                if (director.getFechaNacimiento().isEqual(fechaNacimiento)) {

                    // Es el director indicado
                   if (director.getPeliculas().length == 0 && director.getPeliculas()[0].isBlank()) {
                        // Eliminar el director del array
                        Filmoteca.directores.remove(director);
                        dadasDeAlta.add("eliminado");
                        return dadasDeAlta;
                    } else {

                        // Comprobar si las peliculas están dadas de alta
                        for (String pelicula : director.getPeliculas()) {

                            String compruebaPelicula = consultaPelicula(pelicula);
                            if ( !"error".equals(compruebaPelicula) && 
                                    !"vacio".equals(compruebaPelicula)) {

                                // Hay alguna pelicula dada de alta
                                if (dadasDeAlta.isEmpty()) {
                                    dadasDeAlta.add("noEliminado");
                                }

                                dadasDeAlta.add(pelicula);
                            }
                        }
                        
                        if (dadasDeAlta.isEmpty()){
                        
                            // NO tiene peliculas dadas de alta
                            Filmoteca.directores.remove(director);
                            dadasDeAlta.add("eliminado");
                            return dadasDeAlta;
                        }
                    }
                }
            }

        }

        return dadasDeAlta;
    }

    /*
        *-*-* MODIFICAR CAMPOS DE DIRECTORES *-*-*
     */
    public boolean modificarFechaDirector(String nombre, LocalDate nuevoValor) {

        // Recorremos ~ DIRECTORES ~ en busca del nombre pasado como argumento
        for (int i = 0; i < Filmoteca.directores.size(); i++) {
            if (Filmoteca.directores.get(i).getNombre().equalsIgnoreCase(nombre)) {
                // Se he encontrado el director
                Filmoteca.directores.get(i).setFechaNacimiento(nuevoValor);
                return true;
            }
        }

        return false;
    }

    public boolean modificarNacinalidadDirector(String nombre, String nuevoValor) {

        // Recorremos ~ DIRECTORES ~ en busca del nombre pasado como argumento
        for (int i = 0; i < Filmoteca.directores.size(); i++) {
            if (Filmoteca.directores.get(i).getNombre().equalsIgnoreCase(nombre)) {
                // Se he encontrado el director
                Filmoteca.directores.get(i).setNacionalidad(nuevoValor);
                return true;
            }
        }

        return false;
    }

    public boolean modificarOcupacionDirector(String nombre, String nuevoValor) {

        // Recorremos ~ DIRECTORES ~ en busca del nombre pasado como argumento
        for (int i = 0; i < Filmoteca.directores.size(); i++) {
            if (Filmoteca.directores.get(i).getNombre().equalsIgnoreCase(nombre)) {
                // Se he encontrado el director
                Filmoteca.directores.get(i).setOcupacion(nuevoValor);
                return true;
            }
        }

        return false;
    }

    /*
            ░█▀█░█▀▀░▀█▀░█▀█░█▀▄░█▀▀░█▀▀░
            ░█▀█░█░░░░█░░█░█░█▀▄░█▀▀░▀▀█░
            ░▀░▀░▀▀▀░░▀░░▀▀▀░▀░▀░▀▀▀░▀▀▀░
     */
    // Dar de alta un nuevo actor
    public boolean altasActores(String nombre, LocalDate fechaNacimiento,
            String nacionalidad, int añoDebut, String[] peliculas) {

        // Creamos el nuevo actor con los parametros pasados por argumento.
        Actor nuevo = new Actor(nombre, fechaNacimiento, nacionalidad,
                añoDebut, peliculas);

        // Comprobamos si el nuevo actor ya existe en la colección
        if ( Filmoteca.actores.contains(nuevo)){
            return false;
        }
        
        return Filmoteca.actores.add(nuevo);

    }

    // ELiminar el actor pasado como parametro
    public ArrayList<String> bajasActores(String nombre) {

        ArrayList<String> dadasDeAlta = new ArrayList<>();
        ArrayList<String> datosDeActor = new ArrayList<>();
        boolean eliminar = false;
        Actor eliminado = new Actor();
        datosDeActor.add("repetido");

        int encontrados = 0;

        // Recorremos la colección en busca del actor a eliminar
        for (Actor actor : Filmoteca.actores) {

            if (actor.getNombre().equalsIgnoreCase(nombre)) {
                encontrados += 1;

                // Almacenamos los datos del actor en un ArrayList.
                // Si se encuentran varios, se devolverán sus datos.
                datosDeActor.add(actor.getNombre() + "    "
                        + actor.fechaNacimientoFormateada()
                        + "    " + actor.getNacionalidad());

                // Es el actor indicado
                if (actor.getPeliculas().length == 0 && actor.getPeliculas()[0].isBlank()) {

                    // Eliminar el actor del array y devolver null
                    eliminado = actor;
                    eliminar = true;
                    dadasDeAlta.add("eliminado");
                } else {

                    // Comprobar si las peliculas están dadas de alta
                    for (String pelicula : actor.getPeliculas()) {

                        String compruebaPelicula = consultaPelicula(pelicula);
                        if ( !"error".equals(compruebaPelicula) && 
                                !"vacio".equals(compruebaPelicula)) {

                            // Hay alguna pelicula dada de alta
                            if (dadasDeAlta.isEmpty()) {
                                dadasDeAlta.add("noEliminado");
                            }

                            dadasDeAlta.add(pelicula);
                        }
                    }
                    
                    if (dadasDeAlta.isEmpty()){
                        
                        // NO tiene peliculas dadas de alta
                        eliminado = actor;
                        eliminar = true;
                        dadasDeAlta.add("eliminado");
                    }
                }
            }

        }

        if (encontrados > 1) {
            return datosDeActor;
        }

        if (eliminar == true) {
            Filmoteca.actores.remove(eliminado);
        }

        List<String> sinRepetir = dadasDeAlta.stream().distinct().collect(Collectors.toList());
        dadasDeAlta.clear();
        dadasDeAlta.addAll(sinRepetir);
        
        return dadasDeAlta;
    }

    // Eliminar el actor (en caso de que haya más de uno con el mismo nombre).
    public ArrayList<String> bajasActoresConFecha(String nombre, LocalDate fechaNacimiento) {

        ArrayList<String> dadasDeAlta = new ArrayList<>();
        ArrayList<String> datosDeActor = new ArrayList<>();
        Actor eliminado = new Actor();

        for (Actor actor : Filmoteca.actores) {

            if (actor.getNombre().equalsIgnoreCase(nombre)) {
                if (actor.getFechaNacimiento().isEqual(fechaNacimiento)) {

                    // Es el actor indicado
                    if (actor.getPeliculas().length == 0 && actor.getPeliculas()[0].isBlank()) {

                        // Eliminar el actor del array
                        Filmoteca.actores.remove(actor);
                        dadasDeAlta.add("eliminado");
                        return dadasDeAlta;
                    } else {

                        // Comprobar si las peliculas están dadas de alta
                        for (String pelicula : actor.getPeliculas()) {

                            String compruebaPelicula = consultaPelicula(pelicula);
                            if ( !"error".equals(compruebaPelicula) && 
                                    !"vacio".equals(compruebaPelicula)) {

                                // Hay alguna pelicula dada de alta
                                if (dadasDeAlta.isEmpty()) {
                                    dadasDeAlta.add("noEliminado");
                                }

                                dadasDeAlta.add(pelicula);
                            }
                        }
                        
                        if (dadasDeAlta.isEmpty()){
                        
                            // NO tiene peliculas dadas de alta
                            Filmoteca.actores.remove(actor);
                            dadasDeAlta.add("eliminado");
                            return dadasDeAlta;
                        }
                        
                    }
                }
            }

        }

        return dadasDeAlta;
    }

     // Consulta todas las peliculas de un actor pasado como parámetro
    public ArrayList<String> peliculasActor(String nombre) {

        List<Pelicula> peliculasDeActor = new ArrayList<>();
        ArrayList<String> datosDePeliculas = new ArrayList<>();
        ArrayList<String> datosDeActor = new ArrayList<>();
        boolean cargado = false;
        
        datosDeActor.add("repetido");
        int[] numCols = {8, 5, 10, 6, 8};

        int encontrados = 0;

        // Recorremos la colección en busca del actor
        for (Actor actor : Filmoteca.actores) {

            if (actor.getNombre().equalsIgnoreCase(nombre)) {
                encontrados += 1;

                // Almacenamos los datos del actor en un ArrayList.
                // Si se encuentran varios, se devolverán sus datos.
                datosDeActor.add(actor.getNombre() + "    "
                        + actor.getFechaNacimiento().toString()
                        + "    " + actor.getNacionalidad());

                // Es el actor indicado
                if (actor.getPeliculas().length == 0 && actor.getPeliculas()[0].isBlank()) {

                    // No tiene peliculas
                    return datosDePeliculas;
                } else {
                    
                    // Tiene películas dadas de alta
                    // Recorremos las peliculas y comprobamos si están dadas de alta en películas
                    for (String tituloPeliculaActor : actor.getPeliculas()){
                        for (Pelicula pelicula : Filmoteca.peliculas){
                            
                            // Comprobamos si los titulos coinciden
                            if (tituloPeliculaActor.equalsIgnoreCase(pelicula.getTitulo())){
                                
                                // La pélicula está dada de alta
                                peliculasDeActor.add(pelicula);
                                cargado = true;
                            }
                        }
                        
                        if (!cargado){
                            peliculasDeActor.add(new Pelicula(tituloPeliculaActor));
                        }
                        
                        cargado = false;
                    }
                }
            }

        }

        if (encontrados > 1) {
            return datosDeActor;
        }
        
        if (peliculasDeActor.isEmpty()){
            datosDePeliculas.add("sinPelis");
        }
        
        ordenarPeliculasPorAnio(peliculasDeActor);
        
        
        return formateaListadosPeliculas(peliculasDeActor);
    }

    // Consulta las peliculas de un actor (en caso de que dos se llamen igual)
    public ArrayList<String> peliculasActorConFecha(String nombre, LocalDate fechaNacimiento) {
        
        ArrayList<Pelicula> peliculasDeActor = new ArrayList<>();
        ArrayList<String> datosDePeliculas = new ArrayList<>();
        boolean cargado = false;
        
        // Recorremos la colección en busca del actor
        for (Actor actor : Filmoteca.actores) {

            if (actor.getNombre().equalsIgnoreCase(nombre)) {
                if (actor.getFechaNacimiento().isEqual(fechaNacimiento)) {

                    // Es el actor indicado
                    if (actor.getPeliculas().length == 0 && actor.getPeliculas()[0].isBlank()) {

                        // No tiene peliculas
                        return new ArrayList<>();
                    } else {// Tiene peliculas
                        // Tiene películas dadas de alta
                        // Recorremos las peliculas y comprobamos si están dadas de alta en películas
                        for (String tituloPeliculaActor : actor.getPeliculas()){
                            for (Pelicula pelicula : Filmoteca.peliculas){

                                // Comprobamos si los titulos coinciden
                                if (tituloPeliculaActor.equalsIgnoreCase(pelicula.getTitulo())){

                                    // La pélicula está dada de alta
                                    peliculasDeActor.add(pelicula);
                                    cargado = true;
                                }
                            }

                            if (!cargado){
                                peliculasDeActor.add(new Pelicula(tituloPeliculaActor));
                            }

                            cargado = false;
                        }
                    }
                }
            }
        }

        if (peliculasDeActor.isEmpty()){
            datosDePeliculas.add("sinPelis");
        }
        
        ordenarPeliculasPorAnio(peliculasDeActor);
        
        
        return formateaListadosPeliculas(peliculasDeActor);
    }
    
    
    /*
        *-*-* MODIFICAR CAMPOS DE ACTORES *-*-*
    */
    public boolean modificarFechaActor(String nombre, LocalDate nuevoValor) {

        // Recorremos ~ ACTORES ~ en busca del nombre pasado como argumento
        for (int i = 0; i < Filmoteca.actores.size(); i++) {
            if (Filmoteca.actores.get(i).getNombre().equalsIgnoreCase(nombre)) {
                // Se he encontrado el director
                Filmoteca.actores.get(i).setFechaNacimiento(nuevoValor);
                return true;
            }
        }

        return false;
    }

    public boolean modificarNacionalidadActor(String nombre, String nuevoValor) {

        // Recorremos ~ ACTORES ~ en busca del nombre pasado como argumento
        for (int i = 0; i < Filmoteca.actores.size(); i++) {
            if (Filmoteca.actores.get(i).getNombre().equalsIgnoreCase(nombre)) {
                // Se he encontrado el director
                Filmoteca.actores.get(i).setNacionalidad(nuevoValor);
                return true;
            }
        }

        return false;

    }

    public boolean modificarAñoDebutActor(String nombre, int nuevoValor) {

        // Recorremos ~ ACTORES ~ en busca del nombre pasado como argumento
        for (int i = 0; i < Filmoteca.actores.size(); i++) {
            if (Filmoteca.actores.get(i).getNombre().equalsIgnoreCase(nombre)) {
                // Se he encontrado el director
                Filmoteca.actores.get(i).setAñoDebut(nuevoValor);
                return true;
            }
        }

        return false;
    }

   

    /*
            ░█░░░▀█▀░█▀▀░▀█▀░█▀█░█▀▄░█▀█░█▀▀░
            ░█░░░░█░░▀▀█░░█░░█▀█░█░█░█░█░▀▀█░
            ░▀▀▀░▀▀▀░▀▀▀░░▀░░▀░▀░▀▀░░▀▀▀░▀▀▀░
     */
    public ArrayList<String> formateaListadosPeliculas(List<Pelicula> peliculas){
        
        int[] numCols = {8, 5, 10, 6, 8};

        ArrayList<String> datos = new ArrayList<>();

        // Comprobamos si hay datos almacenados
        if (peliculas.isEmpty()) {
            return null;
        } else {

            // Ordenamos la coleccion de peliculas
            ordenarPeliculas(peliculas);

            // Recorremos los datos necesarios en busca del de mayor longitud
            for (Pelicula temp : peliculas) {
                if (temp.getTitulo().length() > numCols[0]) {
                    numCols[0] = temp.getTitulo().length() + 2;
                }

                if (temp.getPais().length() > numCols[3]) {
                    numCols[3] = temp.getPais().length() + 2;
                }

                if (temp.getGenero().length() > numCols[4]) {
                    numCols[4] = temp.getGenero().length() + 2;
                }
            }

            StringBuilder separador = new StringBuilder();
            separador.append("|");

            // Creamos una barra separadora de la longitud de la tabla
            for (int i = 0; i < numCols.length; i++) {
                for (int j = 0; j < numCols[i] + 2; j++) {
                    separador.append("-");
                }
                separador.append("|");
            }

            datos.add(separador.toString());

            String theFormat = "| %-"
                    + numCols[0]
                    + "s | %-"
                    + numCols[1]
                    + "s | %-"
                    + numCols[2]
                    + "s | %-"
                    + numCols[3]
                    + "s | %-"
                    + numCols[4]
                    + "s |";

            // Añadimos una cadena con los atributos.
            String titulo = String.format(theFormat, "TITULO", "AÑO",
                    "DURACIÓN", "PAÍS", "GÉNERO");

            datos.add(titulo);

            datos.add(separador.toString());

            // Añadimos los datos de las peliculas como String Formateada
            for (Pelicula temp : peliculas) {

                datos.add(temp.asColumn(numCols));
            }

            // Añadimos una bara separadora para la parte baja de la tabla
            datos.add(separador.toString());
        }

        return datos;
    }
    
    // Muestra los listados de peliculas almacenados.
    public ArrayList<String> listadosPeliculas() {

        return formateaListadosPeliculas(Filmoteca.peliculas);

    }

    // Muestra los listados de directores almacenados.
    public ArrayList<String> listadosDirectores(Boolean completo) {

        int[] numCols = {8, 12, 14, 11, 11};

        ArrayList<String> datos = new ArrayList<>();

        // Comprobamos si hay datos almacenados
        if (Filmoteca.directores.isEmpty()) {
            return null;
        } else {

            // Ordenamos la coleccion de directores
            ordenarDirectores(Filmoteca.directores);

            // Recorremos los datos necesarios en busca del de mayor longitud
            for (Director temp : Filmoteca.directores) {

                if (temp.getNombre().length() > numCols[0]) {
                    numCols[0] = temp.getNombre().length() + 2;
                }

                if (temp.getNacionalidad().length() > numCols[2]) {
                    numCols[2] = temp.getNacionalidad().length() + 2;
                }

                if (temp.getOcupacion().length() > numCols[3]) {
                    numCols[3] = temp.getOcupacion().length() + 2;
                }

                if (Arrays.toString(temp.getPeliculas()).length() > numCols[4]) {
                     numCols[4] = Arrays.toString(temp.getPeliculas()).length() + 2;
                }
                
            }
            
            if ( !completo ){
                
                if (numCols[3] > 40 ){
                    numCols[3] = 40;
                }
                
                if (numCols[4] > 40 ){
                    numCols[4] = 40;
                }
            }

            StringBuilder separador = new StringBuilder();
            separador.append("|");

            // Creamos una barra separadora de la longitud necesaria
            for (int i = 0; i < numCols.length; i++) {
                for (int j = 0; j < numCols[i] + 2; j++) {
                    separador.append("-");
                }
                separador.append("|");
            }

            datos.add(separador.toString());

            String theFormat = "| %-"
                    + numCols[0]
                    + "s | %-"
                    + numCols[1]
                    + "s | %-"
                    + numCols[2]
                    + "s | %-"
                    + numCols[3]
                    + "s | %-"
                    + numCols[4]
                    + "s |";

            // Añadimos una cadena con los atributos.
            String titulo = String.format(theFormat, "NOMBRE", "FECHA NAC.",
                    "NACIONALIDAD", "OCUPACION", "PELICULAS");

            datos.add(titulo);

            datos.add(separador.toString());

            // Añadimos los datos de los directores como cadenas formateadas
            for (Director temp : Filmoteca.directores) {

                datos.add(temp.asColumn(numCols));
            }

            // Añadimos una cadena seaparadora al final de la tabla.
            datos.add(separador.toString());
        }

        return datos;
    }

    // Muestra los listados de actores almacenados.
    public ArrayList<String> listadosActores(boolean completo) {

        int[] numCols = {8, 12, 14, 7, 11};

        ArrayList<String> datos = new ArrayList<>();

        // Comprobamos si hay datos almacenados
        if (Filmoteca.actores.isEmpty()) {
            return null;
        } else {

            // Ordenamos la coleccion de actores
            ordenarActores(Filmoteca.actores);

            // Recorremos los datos necesarios en busca del de mayor longitud
            for (Actor temp : Filmoteca.actores) {

                if (temp.getNombre().length() > numCols[0]) {
                    numCols[0] = temp.getNombre().length() + 2;
                }

                if (temp.getNacionalidad().length() > numCols[2]) {
                    numCols[2] = temp.getNacionalidad().length() + 2;
                }

                if (Arrays.toString(temp.getPeliculas()).length() > numCols[4]) {
                    numCols[4] = Arrays.toString(temp.getPeliculas()).length() + 2;
                }
            }
            
            if ( !completo ){
                
                if (numCols[4] > 55 ){
                    numCols[4] = 55;
                }
            }

            StringBuilder separador = new StringBuilder();
            separador.append("|");

            // Creamos una barra separadora de la longitud de la tabla
            for (int i = 0; i < numCols.length; i++) {
                for (int j = 0; j < numCols[i] + 2; j++) {
                    separador.append("-");
                }
                separador.append("|");
            }

            datos.add(separador.toString());

            String theFormat = "| %-"
                    + numCols[0]
                    + "s | %-"
                    + numCols[1]
                    + "s | %-"
                    + numCols[2]
                    + "s | %-"
                    + numCols[3]
                    + "s | %-"
                    + numCols[4]
                    + "s |";

            // Añadimos una cadena con los atributos.
            String titulo = String.format(theFormat, "NOMBRE", "FECHA NACI.",
                    "NACIONALIDAD", "DEBUT", "PELICULAS");

            datos.add(titulo);

            datos.add(separador.toString());

            // Añadimos los datos de los actores como String Format
            for (Actor temp : Filmoteca.actores) {

                datos.add(temp.asColumn(numCols));
            }

            // Añadimos una barra separadora al final de los datos.
            datos.add(separador.toString());
        }

        return datos;
    }

    /*
            ░█▀▄░▀█▀░█▀█░█▀█░█▀▄░▀█▀░█▀█░
            ░█▀▄░░█░░█░█░█▀█░█▀▄░░█░░█░█░
            ░▀▀░░▀▀▀░▀░▀░▀░▀░▀░▀░▀▀▀░▀▀▀░
     */
    // Guarda los datos de los actores en un fichero binario actores.bin
    public boolean guardarActoresEnBinario() {

        Path actoresBin = pathToFileInFolderOnDesktopEs(carpetaArchivos, filmoteca.ACTORESBIN);

        try {
            FileOutputStream fos = new FileOutputStream(actoresBin.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(Filmoteca.actores);
            oos.close();
        } catch (IOException ex) {
            System.err.println("No fue posible guardar el archivo");
            System.err.println(ex.toString());
        }
        return true;
    }

    // Guarda los datos de las peliculas en un fichero binario peliculas.bin
    public boolean guardarDirectoresEnBinario() {

        Path directoresBin = pathToFileInFolderOnDesktopEs(carpetaArchivos, "directores.bin");

        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(directoresBin.toFile()));
        } catch (IOException ex) {
            return false;
        }

        try {
            oos.writeObject(Filmoteca.directores);
        } catch (IOException ex) {
            return false;
        }
        try {
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    // Guarda los datos de los directores en un fichero binario directores.bin
    public boolean guardarPeliculasEnBinario() {

        Path peliculasBin = pathToFileInFolderOnDesktopEs(carpetaArchivos, "peliculas.bin");

        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(peliculasBin.toFile()));
        } catch (IOException ex) {
            return false;
        }

        try {
            oos.writeObject(Filmoteca.peliculas);
        } catch (IOException ex) {
            return false;
        }
        try {
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    /*
            ░█▀█░█▀▄░█▀▄░█▀▀░█▀█░█▀█░█▀▀░▀█▀░█▀█░█▀█
            ░█░█░█▀▄░█░█░█▀▀░█░█░█▀█░█░░░░█░░█░█░█░█
            ░▀▀▀░▀░▀░▀▀░░▀▀▀░▀░▀░▀░▀░▀▀▀░▀▀▀░▀▀▀░▀░▀

     */
    public void ordenarPeliculas(List<Pelicula> peliculas) {

        Comparator<Pelicula> comp = Comparator.comparing(Pelicula::getTitulo);

        peliculas.sort(comp);
    }
    
    public void ordenarPeliculasPorAnio(List<Pelicula> peliculas) {

        Comparator<Pelicula> comp = Comparator.comparing(Pelicula::getAño);

        peliculas.sort(comp);
    }

    public void ordenarDirectores(List<Director> directores) {

        Comparator<Director> comp = Comparator.comparing(Director::getNacionalidad)
                .thenComparing(Director::getFechaNacimiento);

        directores.sort(comp);
    }

    public void ordenarActores(List<Actor> actores) {

        Comparator<Actor> comp = Comparator.comparing(Actor::getAñoDebut)
                .thenComparing(Actor::getNombre);

        actores.sort(comp);
    }
    
}
