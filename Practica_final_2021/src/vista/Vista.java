package vista;

import controlador.Controlador;

import static com.coti.tools.DiaUtil.clear;
import static com.coti.tools.Esdia.readInt;
import static com.coti.tools.Esdia.readString;
import static com.coti.tools.Esdia.readString_ne;
import static com.coti.tools.Esdia.siOno;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Vista {

    Controlador c = new Controlador();

    // Mostrar titulo principal del programa
    public void mostrarTitulo() {

        System.out.printf(
                """
                          
                          
                                ▄▄▄▄▄▄   ▄▄▄  ▄▄▄  ▄▄▄▄▄     ▄▄▄▄▄▄         ▄▄▄▄▄      ▄▄▄▄     ▄▄▄▄▄      ▄▄▄    
                                ▀▀██▀▀   ███  ███  ██▀▀▀██   ██▀▀▀▀██      █▀▀▀▀██▄   ██▀▀██   █▀▀▀▀██▄   █▀██    
                                  ██     ████████  ██    ██  ██    ██            ██  ██    ██        ██     ██    
                                  ██     ██ ██ ██  ██    ██  ███████           ▄█▀   ██ ██ ██      ▄█▀      ██    
                                  ██     ██ ▀▀ ██  ██    ██  ██    ██        ▄█▀     ██    ██    ▄█▀        ██    
                                ▄▄██▄▄   ██    ██  ██▄▄▄██   ██▄▄▄▄██      ▄██▄▄▄▄▄   ██▄▄██   ▄██▄▄▄▄▄  ▄▄▄██▄▄▄ 
                                ▀▀▀▀▀▀   ▀▀    ▀▀  ▀▀▀▀▀     ▀▀▀▀▀▀▀       ▀▀▀▀▀▀▀▀    ▀▀▀▀    ▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀ 
                """);
    }

    // Monstrar menu principal del programa
    public int mostrarMenu() {

        String opcion;

        System.out.printf("\n");
        System.out.println(
                """
                           
                        L  ->  Arrancar
                        F  ->  Archivos
                        P  ->  Películas
                        D  ->  Directores
                        A  ->  Actores
                        LI ->  Listados
                        S  ->  SALIR
                """);
        

        // Leemos la opción de teclado
        opcion = readString_ne("\n\tSelección de menú -> ");

        switch (opcion.toUpperCase()) {
            case "ARRANCAR", "LAUNCH", "L" -> {
                
                clear();
                System.out.println("\n"
                        + "\t\t\t\t░█▀█░█▀▄░█▀▄░█▀█░█▀█░█▀▀░█▀█░█▀▄░\n"
                        + "\t\t\t\t░█▀█░█▀▄░█▀▄░█▀█░█░█░█░░░█▀█░█▀▄░\n"
                        + "\t\t\t\t░▀░▀░▀░▀░▀░▀░▀░▀░▀░▀░▀▀▀░▀░▀░▀░▀░\n\n");
                
                arrancar("peliculas");
                arrancar("actores");
                arrancar("directores");
                
                // Todo ha ido bien
                return 0;
            }
            case "ARCHIVOS", "FILES", "F" -> {
                
                int temp = subMenuArchivos();

                // Ejecutamos hasta que se pulse salir
                while (temp == -1 || temp != 1) {

                    clear();
                    temp = subMenuArchivos();
                }
                // Todo ha ido bien
                return 0;
            }
            case "PELICULAS", "FILMS", "P" -> {
                
                int temp = subMenuPeliculas();

                // Ejecutamos hasta que se pulse salir
                while (temp == -1 || temp != 1) {

                    clear();
                    temp = subMenuPeliculas();
                }
                return 0;
            }

            case "DIRECTORES", "DIRECTORS", "D" -> {
                
                int temp = subMenuDirectores();

                // Ejecutamos hasta que se pulse salir
                while (temp == -1 || temp != 1) {

                    clear();
                    temp = subMenuDirectores();
                }
                return 0;
            }
            case "ACTORES", "ACTORS", "A" -> {
                
                int temp = subMenuActores();

                // Ejecutamos hasta que se pulse salir
                while (temp == -1 || temp != 1) {

                    clear();
                    temp = subMenuActores();
                }
                return 0;
            }
            case "LISTADOS", "LISTINGS", "LISTS", "LI" -> {
                
                int temp = subMenuListados();

                // Ejecutamos hasta que se pulse salir
                while (temp == -1 || temp != 1) {

                    clear();
                    temp = subMenuListados();
                }
                return 0;
            }

            case "SALIR", "EXIT", "S" -> {
                
                // Todo ha ido bien. Se sale del programa
                if (siOno("¿Desea realmente salir?")) {

                    // Si se desea salir del programa, se guardan los cambios en
                    // archivos binarios.
                    if (c.guardarActoresEnBinario() && c.guardarDirectoresEnBinario() && c.guardarPeliculasEnBinario()) {
                        System.out.println("Los cambios se han guardado correctamente.");
                    } else {
                        System.out.println("\n\tERROR: No se han podido guardar todos los cambios.");
                    }
                    
                    clear();
                    System.out.println("\n\n"
                            + "\t\t░█░█░█▀█░█▀▀░▀█▀░█▀█░░░█░░░█▀█░░░█▀█░█▀▄░█▀█░█░█░▀█▀░█▄█░█▀█░\n"
                            + "\t\t░█▀█░█▀█░▀▀█░░█░░█▀█░░░█░░░█▀█░░░█▀▀░█▀▄░█░█░▄▀▄░░█░░█░█░█▀█░\n"
                            + "\t\t░▀░▀░▀░▀░▀▀▀░░▀░░▀░▀░░░▀▀▀░▀░▀░░░▀░░░▀░▀░▀▀▀░▀░▀░▀▀▀░▀░▀░▀░▀░\n");

                    return 1;
                } else {
                    return 0;
                }
            }
            default -> {
                
                clear();
                System.out.println("Opción introducida no válida");

                // La opción introducida no es válida.
                return -1;
            }
        }
    }

    /*
            ░█▀▀░█░█░█▀▄░█▄█░█▀▀░█▀█░█░█░█▀▀░
            ░▀▀█░█░█░█▀▄░█░█░█▀▀░█░█░█░█░▀▀█░
            ░▀▀▀░▀▀▀░▀▀░░▀░▀░▀▀▀░▀░▀░▀▀▀░▀▀▀░
     */
    // Submenus para los apartados
    private int subMenuArchivos() {
        
        clear();

        System.out.println("\n"
                + "\t\t\t\t░█▀█░█▀▄░█▀▀░█░█░▀█▀░█░█░█▀█░█▀▀░\n"
                + "\t\t\t\t░█▀█░█▀▄░█░░░█▀█░░█░░▀▄▀░█░█░▀▀█░\n"
                + "\t\t\t\t░▀░▀░▀░▀░▀▀▀░▀░▀░▀▀▀░░▀░░▀▀▀░▀▀▀░\n");

        // Mostramos el subMenu de ARCHIVOS. Para exportar en .col o .html
        System.out.println(
                """
                           
                        C  ->  Exportar directores a columnas
                        T  ->  Exportar peliculas a tabla
                        S  ->  SALIR\n
                """);


        switch (readString_ne("\n\tSelección de menú -> ").toUpperCase()) {
            case "C", "COL", "COLUMNA" -> {
                directoresCol();
                return 0;
            }
            case "T", "TABLA", "HTML" -> {
                peliculasHtml();
                return 0;
            }
            case "SALIR", "EXIT", "S" -> {
                
                // Todo ha ido bien. Se sale del programa
                if (siOno("¿Desea realmente salir?")) {
                    return 1;
                } else {
                    return 0;
                }
            }

            default -> {
                clear();
                System.err.println("Opción introducida no válida");

                // La opción introducida no es válida. Devolvemos -1 para que
                // se vuelva a llamar a la función
                return -1;
            }
        }
    }

    private int subMenuPeliculas() {
        clear();

        System.out.println("\n"
                + "\t\t\t\t░█▀█░█▀▀░█░░░▀█▀░█▀▀░█░█░█░░░█▀█░█▀▀░\n"
                + "\t\t\t\t░█▀▀░█▀▀░█░░░░█░░█░░░█░█░█░░░█▀█░▀▀█░\n"
                + "\t\t\t\t░▀░░░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░\n\n");

        // Mostramos el subMenu de PELICULAS.
        System.out.printf(
                """
                
                        A  ->  Altas
                        B  ->  Bajas
                        M  ->  Modificaciones
                        C  ->  Consultas
                        S  ->  SALIR
                """);

        switch (readString_ne("\n\tSelección de menú -> ").toUpperCase()) {
            case "A", "ALTAS" -> {
                altasPeliculas();
                return 0;
            }
            case "B", "BAJAS" -> {
                bajasPeliculas();
                return 0;
            }

            case "M", "MODIFICACIONES" -> {
                modificacionesPeliculas();
                return 0;
            }
            case "C", "CONSULTAS" -> {
                consultaPeliculas(null);
                return 0;
            }

            case "SALIR", "EXIT", "S" -> {
                
                // Todo ha ido bien. Se sale del programa
                boolean exit = siOno("¿Desea realmente salir?");
                if (exit) {
                    return 1;
                } else {
                    return 0;
                }
            }
            default -> {
                clear();
                System.out.println("Opción introducida no válida");

                // La opción introducida no es válida. Devolvemos -1 para que
                // se vuelva a llamar a la función
                return -1;
            }
        }
    }

    private int subMenuDirectores() {

        System.out.println("\n"
                + "\t\t\t\t░█▀▄░▀█▀░█▀▄░█▀▀░█▀▀░▀█▀░█▀█░█▀▄░█▀▀░█▀▀░\n"
                + "\t\t\t\t░█░█░░█░░█▀▄░█▀▀░█░░░░█░░█░█░█▀▄░█▀▀░▀▀█░\n"
                + "\t\t\t\t░▀▀░░▀▀▀░▀░▀░▀▀▀░▀▀▀░░▀░░▀▀▀░▀░▀░▀▀▀░▀▀▀░\n\n");

        // Mostramos el subMenu de DIRECTORES.
        System.out.printf(
                """
                
                        A  ->  Altas
                        B  ->  Bajas
                        M  ->  Modificaciones
                        S  ->  SALIR\n
                """);
        
        switch (readString_ne("\n\tSelección de menú -> ").toUpperCase()) {
            case "A", "ALTAS" -> {
                altasDirectores();
                return 0;
            }
            case "B", "BAJAS" -> {
                bajasDirectores();
                return 0;
            }

            case "M", "MODIFICACIONES" -> {
                modificacionesDirectores();
                return 0;
            }
            case "SALIR", "EXIT", "S" -> {
                
                // Todo ha ido bien. Se sale del programa
                boolean exit = siOno("¿Desea realmente salir?");
                if (exit) {
                    return 1;
                } else {
                    return 0;
                }
            }

            default -> {
                clear();
                System.out.println("Opción introducida no válida");

                // La opción introducida no es válida. Devolvemos -1 para que
                // se vuelva a llamar a la función
                return -1;
            }
        }
    }

    private int subMenuActores() {

        System.out.println("\n"
                + "\t\t\t\t░█▀█░█▀▀░▀█▀░█▀█░█▀▄░█▀▀░█▀▀░\n"
                + "\t\t\t\t░█▀█░█░░░░█░░█░█░█▀▄░█▀▀░▀▀█░\n"
                + "\t\t\t\t░▀░▀░▀▀▀░░▀░░▀▀▀░▀░▀░▀▀▀░▀▀▀░\n");

        // Mostramos el subMenu de ACTORES.
        System.out.printf(
                """
                      
                        A  ->  Altas
                        B  ->  Bajas
                        M  ->  Modificaciones
                        P  ->  Peliculas de un actor
                        S  ->  SALIR
                """);
        
        switch (readString_ne("\n\tSelección de menú -> ").toUpperCase()) {
            case "A", "ALTAS" -> {
                altasActores();
                return 0;
            }
            case "B", "BAJAS" -> {
                bajasActores();
                return 0;
            }

            case "M", "MODIFICACIONES" -> {
                modificacionesActores();
                return 0;
            }
            case "P", "PELICULAS", "FILMS" -> {
                peliculasActores();
                return 0;
            }

            case "SALIR", "EXIT", "S" -> {
                
                // Todo ha ido bien. Se sale del programa
                boolean exit = siOno("¿Desea realmente salir?");
                if (exit) {
                    return 1;
                } else {
                    return 0;
                }
            }
            default -> {
                clear();
                System.out.println("Opción introducida no válida");

                // La opción introducida no es válida. Devolvemos -1 para que
                // se vuelva a llamar a la función
                return -1;
            }
        }
    }

    private int subMenuListados() {
        
        clear();

        System.out.println("\n"
                + "\t\t\t\t░█░░░▀█▀░█▀▀░▀█▀░█▀█░█▀▄░█▀█░█▀▀░\n"
                + "\t\t\t\t░█░░░░█░░▀▀█░░█░░█▀█░█░█░█░█░▀▀█░\n"
                + "\t\t\t\t░▀▀▀░▀▀▀░▀▀▀░░▀░░▀░▀░▀▀░░▀▀▀░▀▀▀░\n\n");

        // Mostramos el subMenu de LISTAS.
        System.out.printf("\nImprime la relación de: ");

        System.out.printf("\n\tP  ->  Películas");
        System.out.printf("\n\tD  ->  Directores");
        System.out.printf("\n\tA  ->  Actores");
        System.out.printf("\n\tS  ->  SALIR\n");
        
        switch (readString_ne("\n\tSelección de menú -> ").toUpperCase()) {
            case "P", "PELICULAS" -> {
                listadosPeliculas();
                return 0;
            }
            case "D", "DIRECTORES" -> {
                listadosDirectores();
                return 0;
            }

            case "A", "ACTORES" -> {
                listadosActores();
                return 0;
            }
            case "SALIR", "EXIT", "S" -> {
                
                // Todo ha ido bien. Se sale del programa
                boolean exit = siOno("¿Desea realmente salir?");
                if (exit) {
                    return 1;
                } else {
                    return 0;
                }
            }

            default -> {
                clear();
                System.out.println("Opción introducida no válida");

                // La opción introducida no es válida. Devolvemos -1 para que
                // se vuelva a llamar a la función
                return -1;
            }
        }
    }
    
    /*
            ░█▀█░█▀▄░█▀▄░█▀█░█▀█░█▀▀░█▀█░█▀▄░
            ░█▀█░█▀▄░█▀▄░█▀█░█░█░█░░░█▀█░█▀▄░
            ░▀░▀░▀░▀░▀░▀░▀░▀░▀░▀░▀▀▀░▀░▀░▀░▀░
     */
    // Leer los datos necesarios para el funcionamiento del programa.
    // Se leerá de un .bin o de otro tipo de archivo
    public void arrancar(String tipo) {

        boolean cargar = true;
        
        System.out.println("\n\t\t+-+-+--+-+- " + tipo.toUpperCase() + " -+-+--+-+-+\n");
        
        // Comprobamos si tiene datos almacenados
        if (c.hayAlmacenado(tipo)){
            
            System.out.println("Ya hay registros de " + tipo +" almacenados. Esto podría crear duplicidad.");
            if (siOno("¿Desea eliminarlos antes de cargar los nuevos datos?")){
                
                c.vaciarColeccionDeRegistros(tipo);    
                System.out.println("Los registros se han borrado correctamente.");
            }else{
                cargar = siOno("¿Desea cargar los datos igualmente?");
            }
        }
        
        // Comprobamos la existencia del fichero.
        if (c.comprobarFichero(tipo, ".bin") == 0 && cargar) {

            // El fichero no existe, leemos los datos de otro fichero.
            System.out.println("No se ha encontrado el fichero \"" + tipo + ".bin\".\n");
            
            // Preguntamos si se desea leer del fichero por defecto .txt o de 
            // otro fichero introducido por el usuario.
            String fileName = siOno("¿Desea tomar el fichero por defecto para " + tipo + "?") ?
                    tipo + ".txt" : readString_ne("Nombre y extensión del fichero de peliculas: ");
            
            int lineas = c.importarDeAlternativo(fileName, tipo);

            switch (lineas) {
                case 1 ->  {
                    System.out.println("Ha habido error en alguna linea del fichero.\n");
                }

                case 2 ->  {
                    System.out.println("\n\tERROR: No se ha encontrado el fichero.\n");
                }

                case 3 ->  {
                    System.out.println("Se ha encontradoe el fichero,"
                            + " pero ha habido un error al leerlo.\n");
                }

                case 4 ->  {
                    System.out.println("\n\tERROR: Ha habido un error.\n");
                }

                default ->  {
                    System.out.printf("El fichero se ha leido por completo."
                            + " %d lineas leidas\n\n", lineas);
                }
            }
        } else {
            if (cargar){
                // El fichero si que existe.
                if (c.importarDeBin(tipo)) {
                    System.out.println("Datos de " + tipo + " importados correctamente\n");
                } else {
                    System.out.println("\n\tERROR: No se han podido importar los datos.\n");
                    if(siOno("¿Desea eliminar el fichero directores.bin?")){
                        c.eliminarFichero(tipo + ".bin");
                    }
                }
            }
        }
        
        readString("\nPulse intro para continuar: ");
    }

    /*
            ░█▀█░█▀▄░█▀▀░█░█░▀█▀░█░█░█▀█░█▀▀░
            ░█▀█░█▀▄░█░░░█▀█░░█░░▀▄▀░█░█░▀▀█░
            ░▀░▀░▀░▀░▀▀▀░▀░▀░▀▀▀░░▀░░▀▀▀░▀▀▀░
     */
    // Función para exportar los datos de directores a .COL
    private void directoresCol() {

        clear();
        System.out.println("\n"
                + "\t\t\t\t░░░░█▀▀░█▀█░█░░░\n"
                + "\t\t\t\t░░░░█░░░█░█░█░░░\n"
                + "\t\t\t\t░▀░░▀▀▀░▀▀▀░▀▀▀░\n\n");

        String extension = ".col";

        // Leemos el nombre del fichero que desea el usuario.
        
        String nombreFichero = siOno("¿Desea guardar los datos en el fichero por defecto? (directores.col)") ?
                    "directores" : readString_ne("Introduce el nombre del nuevo archivo .col:");
        
        // Comprobamos si el fichero existe.
        switch (c.comprobarFichero(nombreFichero, extension)) {
            case 1 ->  {

                // No se ha podido crear el fichero
                System.out.println("\n\tERROR: Ha habido un error al crear el fichero.");
            }

            case 2 -> {

                // Ya existe un fichero con ese nombre.
                // Preguntamos si desean sobreescribirlo.
                if (siOno("Ya existe un fichero con ese nombre.\n¿Desea sobreescribirlo?")) {

                    boolean completo = siOno("Puede que el formato salga distorsionado. "
                                + "¿Desea mostrar los datos completos?");
                    
                    // Llamamos a la funcion directoresCol().
                    if (c.directoresCol(nombreFichero, completo) == 1) {

                        // Se ha podido crear el fichero, pero no se ha rellenado correctamente.
                        System.out.println("\n\tERROR: Ha habido un error al rellenar el fichero.");

                        if (siOno("¿Desea eliminar el fichero?")) {
                            if (c.eliminarFichero(nombreFichero)) {
                                System.out.println("El fichero ha sido eliminado.");
                            } else {
                                System.out.println("\n\tERROR: No se ha podido eliminar el fichero.");
                            }
                        }

                    } else {

                        // No se ha podido escribir correctamente el fichero.
                        System.out.println("El fichero se ha sobreescrito correctamente.");
                    }
                }
            }

            default -> {

                boolean completo = siOno("Puede que el formato salga distorsionado. "
                                + "¿Desea mostrar los datos completos?");
                
                // Llamamos a la funcion directoresCOL().
                if (c.directoresCol(nombreFichero, completo) == 1) {

                    // Se ha podido crear el fichero, pero no se ha rellenado correctamente.
                    System.out.println("\n\tERROR: Ha habido un error al rellenar el fichero.");

                    if (siOno("¿Desea eliminar el fichero?")) {
                        if (c.eliminarFichero(nombreFichero)) {
                            System.out.println("El fichero ha sido eliminado.");
                        } else {
                            System.out.println("\n\tERROR: No se ha podido eliminar el fichero.");
                        }
                    }
                } else {

                    // NO se ha podido escribir correctamente el fichero.
                    System.out.println("El fichero se ha escrito correctamente.");
                }
            }
        }
        
        readString("\nPulse intro para continuar: ");
    }

    // Funcion para exportar los datos de las peliculas a .HTML
    private void peliculasHtml() {

        clear();
        System.out.println("\n"
                + "\t\t\t\t░░░░█░█░▀█▀░█▄█░█░░░\n"
                + "\t\t\t\t░░░░█▀█░░█░░█░█░█░░░\n"
                + "\t\t\t\t░▀░░▀░▀░░▀░░▀░▀░▀▀▀░\n\n");

        String extension = ".html";

        String nombreFichero = siOno("¿Desea guardar los datos en el fichero por defecto? (directores.col)") ?
                    "peliculas" : readString_ne("Introduce el nombre del nuevo archivo .html:");

        switch (c.comprobarFichero(nombreFichero, extension)) {
            case 1 ->  {

                // No se ha podido crear el fichero
                System.out.println("\n\tERROR: Ha habido un error al crear el fichero.");
            }

            case 2 -> {

                // Ya existe un fichero con ese nombre.
                if (siOno("Ya existe un fichero con ese nombre.\n¿Desea sobreescribirlo?")) {

                    // Llamamos a la funcion peliculasHTML().
                    // Preguntamos si desean sobreescribirlo.
                    if (c.peliculasHtml(nombreFichero) == 1) {

                        // Se ha podido crear el fichero, pero no se ha rellenado correctamente.
                        System.out.println("\n\tERROR: Ha habido un error al rellenar el fichero.");

                        if (siOno("¿Desea eliminar el fichero?")) {
                            if (c.eliminarFichero(nombreFichero)) {
                                System.out.println("El fichero ha sido eliminado.");
                            } else {
                                System.out.println("\n\tERROR: No se ha podido eliminar el fichero.");
                            }
                        }
                        
                    } else {

                        // NO se ha podido escribir correctamente el fichero.
                        System.out.println("El fichero se ha sobreescrito correctamente.");
                    }
                }
            }

            default -> {

                // Llamamos a la funcion peliculasHTML().
                if (c.peliculasHtml(nombreFichero) == 1) {

                    // Se ha podido crear el fichero, pero no se ha rellenado correctamente.
                    System.out.println("\n\tERROR: Ha habido un error al rellenar el fichero.");
                    
                    if (siOno("¿Desea eliminar el fichero?")) {
                        if (c.eliminarFichero(nombreFichero)) {
                            System.out.println("El fichero ha sido eliminado.");
                        } else {
                            System.out.println("\n\tERROR: No se ha podido eliminar el fichero.");
                        }
                    }

                } else {

                    // NO se ha podido escribir correctamente el fichero.
                    System.out.println("El fichero se ha escrito correctamente.");
                }
            }
        }
        
        readString("\nPulse intro para continuar: ");
    }

    /*
            ░█▀█░█▀▀░█░░░▀█▀░█▀▀░█░█░█░░░█▀█░█▀▀░
            ░█▀▀░█▀▀░█░░░░█░░█░░░█░█░█░░░█▀█░▀▀█░
            ░▀░░░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░
     */
    // Dar de alta una nueva pelicula
    private void altasPeliculas() {

        clear();
        System.out.println("\n"
                + "\t\t\t\t░█▀█░█░█░█▀▀░█░█░█▀█░░░█▀█░█▀▀░█░░░▀█▀░█▀▀░█░█░█░░░█▀█░\n"
                + "\t\t\t\t░█░█░█░█░█▀▀░▀▄▀░█▀█░░░█▀▀░█▀▀░█░░░░█░░█░░░█░█░█░░░█▀█░\n"
                + "\t\t\t\t░▀░▀░▀▀▀░▀▀▀░░▀░░▀░▀░░░▀░░░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀░▀░\n\n");
        
        // Leemos los datos necesarios
        String titulo = readString_ne("\n\tTítulo: ");
        int año = readInt("\n\tAño: ", 0, LocalDate.now().getYear());
        int duracion = readInt("\n\tDuración: ");
        String pais = readString("\n\tPais: ");
        
        String[] direccion = readString("\n\tDireccion (separado por comas): ").split(",");
        
        c.recortarArray(direccion);

        String guionista = readString("\n\tGuionista: ");
        String musica = readString("\n\tMusica: ");

        String[] reparto = readString("\n\tReparto (separado por comas): ").split(",");
        
        c.recortarArray(reparto);

        String productora = readString("\n\tProductora: ");
        String genero = readString("\n\tGenero: ");
        String sinopsis = readString("\n\tSinopsis: ");

        if (c.altasPeliculas(titulo, año, duracion, pais, direccion,
                            guionista, musica, reparto, productora,
                            genero, sinopsis) == false) {
            
            System.out.println("\n\tERROR: No se ha podido añadir la pelicula");
        } else {
            
            System.out.printf("\nLa película ~ %s ~ ha sido añadida correctamente.\n", titulo);
        }

        readString("\n\nPulse intro para continuar: ");
    }

    // Dar de baja una nueva pelicula
    private void bajasPeliculas() {
        
        clear();
        System.out.println("\n"
                + "\t\t\t\t░█▀▀░█░░░▀█▀░█▄█░▀█▀░█▀█░█▀█░█▀▄░░░█▀█░█▀▀░█░░░▀█▀░█▀▀░█░█░█░░░█▀█░\n"
                + "\t\t\t\t░█▀▀░█░░░░█░░█░█░░█░░█░█░█▀█░█▀▄░░░█▀▀░█▀▀░█░░░░█░░█░░░█░█░█░░░█▀█░\n"
                + "\t\t\t\t░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░▀░▀░▀░▀░▀░▀░░░▀░░░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀░▀░\n\n");
        
        // Leemos el titulo de la película que se desea borrar
        String titulo = readString("\n\tTítulo: ");

        if (c.bajasPeliculas(titulo)) {
            System.out.printf("\nLa película ~ %s ~ ha sido borrada correctamente.\n", titulo);
        } else {
            System.out.println("\n\tERROR: No se ha podido borrar la pelicula o no existe.");
        }

        readString("\n\nPulse intro para continuar: ");
    }

    // Modificar los datos de una pelicula
    private void modificacionesPeliculas() {
        
        boolean salir = false;
        
        while( !salir ){
            
            // Mostramos las opciones de modificacion que hay para las peliculas.
            clear();
            System.out.println("\n"
                    + "\t\t\t\t░█▄█░█▀█░█▀▄░▀█▀░█▀▀░▀█▀░█▀▀░█▀█░█▀▄░░░█▀█░█▀▀░█░░░▀█▀░█▀▀░█░█░█░░░█▀█░\n"
                    + "\t\t\t\t░█░█░█░█░█░█░░█░░█▀▀░░█░░█░░░█▀█░█▀▄░░░█▀▀░█▀▀░█░░░░█░░█░░░█░█░█░░░█▀█░\n"
                    + "\t\t\t\t░▀░▀░▀▀▀░▀▀░░▀▀▀░▀░░░▀▀▀░▀▀▀░▀░▀░▀░▀░░░▀░░░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀░▀░\n\n");
            String titulo = readString("\n\tTítulo: ");

            String atributos = 
                    """
                            T -> Título
                            A -> Año
                            D -> Duración
                            C -> País
                            S -> Guionista
                            M -> Música
                            P -> Productora
                            G -> Género
                            R -> Resumen/Sinopsis
                            Q -> SALIR
                    
                    """;

            System.out.println(atributos);

            // Según la selección modificamos los campos necesarios.
            switch (readString("\n\nSelección de menú -> ").toUpperCase()) {
                case "A":
                case "AÑO":
                case "YEAR": {

                    int nuevoValor = readInt("Nuevo valor: ");
                    
                    if (c.modificarAñoPelicula(titulo, nuevoValor)) {
                        System.out.println("El valor ha sido añadido correctamente.");
                    } else {
                        System.out.println("\n\tERROR: NO se ha podido modificar.");
                    }
                    break;
                }

                case "D":
                case "DURACION":
                case "DURATION": {

                    int nuevoValor = readInt("Nuevo valor: ");
                    
                    if (c.modificarDuracionPelicula(titulo, nuevoValor)) {
                        System.out.println("El valor ha sido añadido correctamente.");
                    } else {
                        System.out.println("\n\tERROR: NO se ha podido modificar.");
                    }
                    break;
                }

                case "C":
                case "PAIS":
                case "COUNTRY": {

                    String nuevoValor = readString("Nuevo valor: ");
                    
                    if (c.modificarPaisPelicula(titulo, nuevoValor)) {
                        System.out.println("El valor ha sido añadido correctamente.");
                    } else {
                        System.out.println("\n\tERROR: NO se ha podido modificar.");
                    }
                    break;
                }

                case "S":
                case "GUIONISTA":
                case "SCREENWRITER": {

                    String nuevoValor = readString("Nuevo valor: ");
                    
                    if (c.modificarGuionistaPelicula(titulo, nuevoValor)) {
                        System.out.println("El valor ha sido añadido correctamente.");
                    } else {
                        System.out.println("\n\tERROR: NO se ha podido modificar.");
                    }
                    break;
                }

                case "M":
                case "MUSICA":
                case "MUSIC": {

                    String nuevoValor = readString("Nuevo valor: ");
                    
                    if (c.modificarMusicaPelicula(titulo, nuevoValor)) {
                        System.out.println("El valor ha sido añadido correctamente.");
                    } else {
                        System.out.println("\n\tERROR: NO se ha podido modificar.");
                    }
                    break;
                }

                case "P":
                case "PRODUCTORA":
                case "PRODUCER": {

                    String nuevoValor = readString("Nuevo valor: ");
                    
                    if (c.modificarProductoraPelicula(titulo, nuevoValor)) {
                        System.out.println("El valor ha sido añadido correctamente.");
                    } else {
                        System.out.println("\n\tERROR: NO se ha podido modificar.");
                    }
                    break;
                }

                case "G":
                case "GENERO":
                case "GENRE": {

                    String nuevoValor = readString("Nuevo valor: ");
                    
                    if (c.modificarGeneroPelicula(titulo, nuevoValor)) {
                        System.out.println("El valor ha sido añadido correctamente.");
                    } else {
                        System.out.println("\n\tERROR: NO se ha podido modificar.");
                    }
                    break;
                }

                case "R":
                case "SINOPSIS":
                case "SYNOPSIS":
                case "RESUMEN":
                case "SUMMARY": {

                    String nuevoValor = readString("Nuevo valor: ");
                    
                    if (c.modificarSinopsisPelicula(titulo, nuevoValor)) {
                        System.out.println("El valor ha sido añadido correctamente.");
                    } else {
                        System.out.println("\n\tERROR: NO se ha podido modificar.");
                    }
                    break;
                }

                case "SALIR":
                case "EXIT":
                case "Q": {
                    System.out.println("SALIR");

                    // Todo ha ido bien. Se sale del programa
                    salir = siOno("¿Desea realmente salir?");
                    break;
                }

                default: {
                }
            }
        }

        readString("\n\nPulse intro para continuar: ");
    }

    // Realizar una busqueda entre las peliculas
    private void consultaPeliculas(String cadenaABuscar) {

        // Leemos el titulo de la película a buscar.
        String datosDePeliculaBuscada;
        String titulo = cadenaABuscar;
        
        if (cadenaABuscar == null){
            clear();
            System.out.println("\n"
                        + "\t\t\t\t░█▀▄░█░█░█▀▀░█▀▀░█▀█░█▀▄░░░█▀█░█▀▀░█░░░▀█▀░█▀▀░█░█░█░░░█▀█░\n"
                        + "\t\t\t\t░█▀▄░█░█░▀▀█░█░░░█▀█░█▀▄░░░█▀▀░█▀▀░█░░░░█░░█░░░█░█░█░░░█▀█░\n"
                        + "\t\t\t\t░▀▀░░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀░▀░░░▀░░░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀░▀░\n\n");

            System.out.printf("\tBUSCAR PELICULA:\n\n");
            titulo = readString("Introduce el titulo de la pelicula: ");
        }
        
        datosDePeliculaBuscada = c.consultaPelicula(titulo);

        switch (datosDePeliculaBuscada) {
            case "vacio" ->  {

                System.out.println("\n\tERROR: No hay peliculas almacenadas.");
                readString("\n\nPulse intro para continuar: ");
            }

            case "error" ->  {
                
                ArrayList<String> similares = new ArrayList<>();
                System.out.printf("\n\tERROR: No se ha encontrado la película ~ %s ~\n", titulo);
                
                // Mostramos las peliculas similares a la busqueds (en caso de haberlas)
                if ((similares = c.consultaPeliculasSimilares(titulo)).isEmpty()){
                    
                    System.out.println("Tampoco se han encontrado peliculas similares en nuestra base de datos.");
                }else{
                    
                    System.out.println("\nSe han encontrado las siguientes similitudes: ");
                    System.out.println("\n" + similares.toString());
                    
                    if (siOno("\n¿Desea buscar alguna de las sugeridas?")){
                        titulo = readString(" -> ");
                        
                        // realizar la busqueda con ese título
                        consultaPeliculas(titulo);
                    }else{
                        readString("\n\nPulse intro para continuar: ");
                    }
                }
            }

            default ->  {

                System.out.println(datosDePeliculaBuscada);
                readString("\n\nPulse intro para continuar: ");
            }
        }

        
    }

    /*
            ░█▀▄░▀█▀░█▀▄░█▀▀░█▀▀░▀█▀░█▀█░█▀▄░█▀▀░█▀▀░
            ░█░█░░█░░█▀▄░█▀▀░█░░░░█░░█░█░█▀▄░█▀▀░▀▀█░
            ░▀▀░░▀▀▀░▀░▀░▀▀▀░▀▀▀░░▀░░▀▀▀░▀░▀░▀▀▀░▀▀▀░
     */
    // Dar de alta un nuevo director
    private void altasDirectores() {
        
        clear();
        System.out.println("\n"
                    + "\t\t\t\t░█▀█░█░█░█▀▀░█░█░█▀█░░░█▀▄░▀█▀░█▀▄░█▀▀░█▀▀░▀█▀░█▀█░█▀▄░\n"
                    + "\t\t\t\t░█░█░█░█░█▀▀░▀▄▀░█░█░░░█░█░░█░░█▀▄░█▀▀░█░░░░█░░█░█░█▀▄░\n"
                    + "\t\t\t\t░▀░▀░▀▀▀░▀▀▀░░▀░░▀▀▀░░░▀▀░░▀▀▀░▀░▀░▀▀▀░▀▀▀░░▀░░▀▀▀░▀░▀░\n\n");
        
        // Leemos el nombre del director que se desea modificar.
        String nombre = readString_ne("\n\tNombre: ");

        String cadenaNacimiento;
        LocalDate fechaNacimiento = LocalDate.now().plusDays(1);

        // Pedimos una fecha de nacimiento que sea válida para el director
        while (fechaNacimiento.isAfter(LocalDate.now())) {
            cadenaNacimiento = readString_ne("\n\tFecha de nacimiento (dd/MM/yyyy): ");
            fechaNacimiento = c.cadenaALocalDate(cadenaNacimiento);
        }

        String nacionalidad = readString("\n\tNacionalidad: ");

        String ocupacion = readString("\n\tOcupacion: ");

        String[] peliculas = readString("\n\tPeliculas (separado por comas): ").split(",");
        
        c.recortarArray(peliculas);

        if (c.altasDirectores(nombre, fechaNacimiento, nacionalidad,
                              ocupacion, peliculas) == false) {
            System.out.println("\n\tERROR: No se ha podido añadir el director");
        } else {
            System.out.printf("\nEl director ~ %s ~ ha sido añadida correctamente.\n", nombre);
        }

        readString("\n\nPulse intro para continuar: ");
    }

    // Dar de baja un director
    private void bajasDirectores() {

        clear();
        System.out.println("\n"
                    + "\t\t\t\t░█▀▀░█░░░▀█▀░█▄█░▀█▀░█▀█░█▀█░█▀▄░░░█▀▄░▀█▀░█▀▄░█▀▀░█▀▀░▀█▀░█▀█░█▀▄░\n"
                    + "\t\t\t\t░█▀▀░█░░░░█░░█░█░░█░░█░█░█▀█░█▀▄░░░█░█░░█░░█▀▄░█▀▀░█░░░░█░░█░█░█▀▄░\n"
                    + "\t\t\t\t░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░▀░▀░▀░▀░▀░▀░░░▀▀░░▀▀▀░▀░▀░▀▀▀░▀▀▀░░▀░░▀▀▀░▀░▀░\n\n");
        
        // Leemos el nombre del director a eliminar
        String nombre = readString_ne("\n\tNombre: ");

        // La funciones bajasDirectores devolverá NULL si no hay peliculas dadas de alta
        // Si las hay, este ArrayList contendrá sus nombres.
        ArrayList<String> dadasDeAlta = c.bajasDirectores(nombre);

        if (dadasDeAlta.isEmpty()) {

            System.out.printf("\n\tERROR: No se ha encontrado el director %s\n", nombre);
        } else {

            switch (dadasDeAlta.get(0)) {
                case "eliminado" ->  {

                    System.out.printf("El director ~ %s ~ ha sido eliminado correctamente\n",
                            nombre);
                }

                case "noEliminado" ->  {

                    // El director tiene peliculas dadas de alta
                    System.out.println("\n\tERROR: El director no ha podido eliminarse.");
                    System.out.println("Aun tiene las siguientes peliculas dadas de alta: ");
                    
                    for (int i = 1; i < dadasDeAlta.size(); i++) {
                        System.out.println(dadasDeAlta.get(i));
                    }

                }

                case "repetido" ->  {

                    // Hay más de un director dado de alta con ese nombre
                    System.out.printf("\n\tERROR: Hay varios directores con el mismo nombre.\n\n");

                    // Imprimimos los datos de los directores
                    for (int i = 1; i < dadasDeAlta.size(); i++) {
                        System.out.println(dadasDeAlta.get(i));
                    }
                    
                    bajaDirectorConFecha(nombre);
                }

                default -> {

                    System.out.println("\n\tERROR: Ha habido un error");
                }
            }
        }

        readString("\n\nPulse intro para continuar: ");
    }

    // Dar de baja un director con nombre repetido
    private void bajaDirectorConFecha(String nombre) {

        // Leemos la fecha para distinguir los directores.
        String cadenaNacimiento;
        LocalDate fechaNacimiento = LocalDate.now().plusDays(1);

        while (fechaNacimiento.isAfter(LocalDate.now())) {
            cadenaNacimiento = readString_ne("\n\tFecha de nacimiento (dd/MM/yyyy): ");
            fechaNacimiento = c.cadenaALocalDate(cadenaNacimiento);
        }

        // La funcion m.bajaDirectorConFecha devolverá NULL si no hay peliculas dadas de alta
        // Si las hay, este ArrayList contendrá sus nombres.
        ArrayList<String> dadasDeAlta = c.bajasDirectoresConFecha(nombre, fechaNacimiento);

        if (dadasDeAlta.isEmpty()) {

            System.out.printf("\nERROR: No se ha encontrado el director %s\n"
                    + "con fecha de nacimiento %s.\n",
                    nombre, fechaNacimiento);
        } else {

            switch (dadasDeAlta.get(0)) {
                case "eliminado" ->  {

                    System.out.printf("El director ~ %s ~ ha sido eliminado correctamente\n",
                                        nombre);
                }

                case "noEliminado" ->  {

                    // El director tiene peliculas dadas de alta
                    System.out.println("\n\tERROR: El director no ha podido eliminarse.");
                    System.out.println("Aun tiene las siguientes peliculas dadas de alta: ");

                    for (int i = 1; i < dadasDeAlta.size(); i++) {
                        System.out.println(dadasDeAlta.get(i));
                    }

                }

                default -> {

                    System.out.println("EROOR: Ha habido un error");
                }
            }
        }
    }

    // Modificar algun dato de un director
    private void modificacionesDirectores() {
        
        boolean salir = false;
        
        while (!salir){
            clear();
            System.out.println("\n"
                        + "\t\t\t\t░█▄█░█▀█░█▀▄░▀█▀░█▀▀░▀█▀░█▀▀░█▀█░█▀▄░░░█▀▄░▀█▀░█▀▄░█▀▀░█▀▀░▀█▀░█▀█░█▀▄░\n"
                        + "\t\t\t\t░█░█░█░█░█░█░░█░░█▀▀░░█░░█░░░█▀█░█▀▄░░░█░█░░█░░█▀▄░█▀▀░█░░░░█░░█░█░█▀▄░\n"
                        + "\t\t\t\t░▀░▀░▀▀▀░▀▀░░▀▀▀░▀░░░▀▀▀░▀▀▀░▀░▀░▀░▀░░░▀▀░░▀▀▀░▀░▀░▀▀▀░▀▀▀░░▀░░▀▀▀░▀░▀░\n\n");

            // Pedimos el nombre del director a modificar
            String nombre = readString_ne("\n\tNombre: ");

            String atributos = 
                    """
                            T -> Título
                            F -> Fecha de nacimiento
                            N -> Nacionialidad
                            O -> Ocupacion
                            Q -> SALIR

                    """;

            System.out.println(atributos);

            // Tras mostrar los atributos modificables, se pide el nuevo valor
            switch (readString_ne("\n\nSelección de menú -> ").toUpperCase()) {
                case "F", "FECHA", "FECHA DE NACIMIENTO" -> {
                    String cadenaNacimiento;
                    LocalDate fechaNacimiento = LocalDate.now().plusDays(1);

                    while (fechaNacimiento.isAfter(LocalDate.now())) {
                        cadenaNacimiento = readString_ne("\n\tFecha de nacimiento (dd/MM/yyyy): ");
                        fechaNacimiento = c.cadenaALocalDate(cadenaNacimiento);
                    }

                    if (c.modificarFechaDirector(nombre, fechaNacimiento)) {
                        System.out.println("El valor ha sido añadido correctamente.");
                    } else {
                        System.out.println("\n\tERROR: NO se ha podido modificar.");
                    }

                    readString("\n\nPulse intro para continuar: ");
                }

                case "N", "NACIONALIDAD", "NATIONALITY" -> {

                    String nuevoValor = readString_ne("Nuevo valor: ");

                    if (c.modificarNacinalidadDirector(nombre, nuevoValor)) {
                        System.out.println("El valor ha sido añadido correctamente.");
                    } else {
                        System.out.println("\n\tERROR: NO se ha podido modificar.");
                    }

                    readString("\n\nPulse intro para continuar: ");
                }

                case "O", "OCUPACION", "OCCUPATION" ->  {

                    String nuevoValor = readString_ne("Nuevo valor: ");

                    if (c.modificarOcupacionDirector(nombre, nuevoValor)) {
                        System.out.println("El valor ha sido añadido correctamente.");
                    } else {
                        System.out.println("\n\tERROR: NO se ha podido modificar.");
                    }

                    readString("\n\nPulse intro para continuar: ");
                }

                case "SALIR", "EXIT", "Q" -> {
                    // Todo ha ido bien. Se sale del programa
                    
                    salir = siOno("¿Desea realmente salir?");
                }
                default -> {
                    clear();
                    System.out.println("\n\tERROR: Opción introducida no válida");

                    // La opción introducida no es válida.
                }
            }
        }
    }

    /*
            ░█▀█░█▀▀░▀█▀░█▀█░█▀▄░█▀▀░█▀▀░
            ░█▀█░█░░░░█░░█░█░█▀▄░█▀▀░▀▀█░
            ░▀░▀░▀▀▀░░▀░░▀▀▀░▀░▀░▀▀▀░▀▀▀░
     */
    // Dar de alta un nuevo actor
    private void altasActores() {

        clear();
        System.out.println("\n"
                    + "\t\t\t\t░█▀█░█░█░█▀▀░█░█░█▀█░░░█▀█░█▀▀░▀█▀░█▀█░█▀▄░\n"
                    + "\t\t\t\t░█░█░█░█░█▀▀░▀▄▀░█░█░░░█▀█░█░░░░█░░█░█░█▀▄░\n"
                    + "\t\t\t\t░▀░▀░▀▀▀░▀▀▀░░▀░░▀▀▀░░░▀░▀░▀▀▀░░▀░░▀▀▀░▀░▀░\n\n");
        
        // Pedimos los datos del nuevo actor por teclado.
        String nombre = readString_ne("\n\tNombre: ");

        String cadenaNacimiento;
        LocalDate fechaNacimiento = LocalDate.now().plusDays(1);

        while (fechaNacimiento.isAfter(LocalDate.now())) {
            cadenaNacimiento = readString_ne("\n\tFecha de nacimiento (dd/MM/yyyy): ");
            fechaNacimiento = c.cadenaALocalDate(cadenaNacimiento);
        }

        String nacionalidad = readString("\n\tNacionalidad: ");

        // Nos aseguramos de que el dato introducido no es posterior al año actual.
        int añoDebut = readInt("\n\tAño de debut ", 0 , LocalDate.now().getYear() + 1);
        String[] peliculas = readString("\n\tPeliculas (separado por comas): ").split(",");
        
        c.recortarArray(peliculas);

        // Comprobamos si se ha podido añadir correctamente.
        if (c.altasActores(nombre, fechaNacimiento, nacionalidad,
                añoDebut, peliculas) == false) {
            System.out.println("\n\tERROR: No se ha podido añadir el actor");
        } else {
            System.out.printf("\nEl actor ~ %s ~ ha sido añadida correctamente.\n", nombre);
        }

        readString("\n\nPulse intro para continuar: ");
    }

    // Dar de baja un actor
    private void bajasActores() {
        
        clear();
        System.out.println("\n"
                    + "\t\t\t\t░█▀▀░█░░░▀█▀░█▄█░▀█▀░█▀█░█▀█░█▀▄░░░█▀█░█▀▀░▀█▀░█▀█░█▀▄░\n"
                    + "\t\t\t\t░█▀▀░█░░░░█░░█░█░░█░░█░█░█▀█░█▀▄░░░█▀█░█░░░░█░░█░█░█▀▄░\n"
                    + "\t\t\t\t░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░▀░▀░▀░▀░▀░▀░░░▀░▀░▀▀▀░░▀░░▀▀▀░▀░▀░\n\n");
        
        // Leemos el nombre del actor a eliminar
        String nombre = readString_ne("\n\tNombre: ");

        // La funciones bajasDirectores devolverá NULL si no hay peliculas dadas de alta
        // Si las hay, este ArrayList contendrá sus nombres.
        ArrayList<String> dadasDeAlta = c.bajasActores(nombre);

        if (dadasDeAlta.isEmpty()) {

            System.out.printf("\n\tERROR: No se ha encontrado el actor %s\n", nombre);
        } else {

            switch (dadasDeAlta.get(0)) {
                case "eliminado" ->  {

                    System.out.printf("El actor ~ %s ~ ha sido eliminado correctamente\n",
                            nombre);
                }

                case "noEliminado" ->  {

                    // El actor tiene peliculas dadas de alta
                    System.out.println("\n\tERROR: El actor no ha podido eliminarse.");
                    System.out.println("Aun tiene las siguientes peliculas dadas de alta: ");

                    for (int i = 1; i < dadasDeAlta.size(); i++) {
                        System.out.println(dadasDeAlta.get(i));
                    }

                }

                case "repetido" ->  {

                    // Hay más de un director dado de alta con ese nombre
                    System.out.printf("Hay varios actores con el mismo nombre.\n\n");

                    // Imprimimos los datos de los actores
                    for (int i = 1; i < dadasDeAlta.size(); i++) {
                        System.out.println(dadasDeAlta.get(i));
                    }
                    bajaActorConFecha(nombre);

                }

                default -> {

                    System.out.println("\n\tERROR: Ha habido un error");
                }
            }
        }

        readString("\n\nPulse intro para continuar: ");
    }

    // Dar de baja un actor con nombre repetido
    private void bajaActorConFecha(String nombre) {

        // Leemos la fecha para distinguir los actores.
        String cadenaNacimiento;
        LocalDate fechaNacimiento = LocalDate.now().plusDays(1);

        while (fechaNacimiento.isAfter(LocalDate.now())) {
            cadenaNacimiento = readString_ne("\n\tFecha de nacimiento (dd/MM/yyyy): ");
            fechaNacimiento = c.cadenaALocalDate(cadenaNacimiento);
        }

        // La funciones bajaActorConFecha devolverá NULL si no hay peliculas dadas de alta
        // Si las hay, este ArrayList contendrá sus nombres.
        ArrayList<String> dadasDeAlta = c.bajasActoresConFecha(nombre, fechaNacimiento);

        if (dadasDeAlta.isEmpty()) {

            System.out.printf("\n\tERROR: No se ha encontrado el actor %s\n"
                    + "con fecha de nacimiento %s.",
                    nombre, fechaNacimiento);
        } else {

            switch (dadasDeAlta.get(0)) {
                case "eliminado" ->  {

                    System.out.printf("El actor ~ %s ~ ha sido eliminado correctamente\n",
                            nombre);
                }

                case "noEliminado" ->  {

                    // El actor tiene peliculas dadas de alta
                    System.out.println("\n\tERROR: El actor no ha podido eliminarse.");
                    System.out.println("Aun tiene las siguientes peliculas dadas de alta: ");

                    for (int i = 1; i < dadasDeAlta.size(); i++) {
                        System.out.println(dadasDeAlta.get(i));
                    }

                }

                default -> {

                    System.out.println("EROOR: Ha habido un error");
                }
            }
        }
    }

    // Modificar atributos de un actor
    private void modificacionesActores() {
        
        boolean salir = false;
        
        while (!salir){
            clear();
            System.out.println("\n"
                        + "\t\t\t\t░█▄█░█▀█░█▀▄░▀█▀░█▀▀░▀█▀░█▀▀░█▀█░█▀▄░░░█▀█░█▀▀░▀█▀░█▀█░█▀▄░\n"
                        + "\t\t\t\t░█░█░█░█░█░█░░█░░█▀▀░░█░░█░░░█▀█░█▀▄░░░█▀█░█░░░░█░░█░█░█▀▄░\n"
                        + "\t\t\t\t░▀░▀░▀▀▀░▀▀░░▀▀▀░▀░░░▀▀▀░▀▀▀░▀░▀░▀░▀░░░▀░▀░▀▀▀░░▀░░▀▀▀░▀░▀░\n\n");

            // Pedimos los datos del actor a modificar
            String nombre = readString_ne("\n\tNombre: ");

            String atributos = 
                    """
                            T -> Título\n"
                            F -> Fecha de nacimiento
                            N -> Nacionialidad
                            A -> Año de debut
                            Q -> SALIR
                    
                    """;

            System.out.println(atributos);

            // Se pide el nuevo valor del atributo para modificarlo
            switch (readString_ne("\n\nSelección de menú -> ").toUpperCase()) {

                case "F":
                case "FECHA":
                case "FECHA DE NACIMIENTO" : {

                    String cadenaNacimiento;
                    LocalDate fechaNacimiento = LocalDate.now().plusDays(1);

                    while (fechaNacimiento.isAfter(LocalDate.now())) {
                        cadenaNacimiento = readString_ne("\n\tFecha de nacimiento (dd/MM/yyyy): ");
                        fechaNacimiento = c.cadenaALocalDate(cadenaNacimiento);
                    }

                    if (c.modificarFechaActor(nombre, fechaNacimiento)) {
                        System.out.println("El valor ha sido añadido correctamente.");
                    } else {
                        System.out.println("\n\tERROR: NO se ha podido modificar.");
                    }

                    readString("\n\nPulse intro para continuar: ");
                    break;
                }

                case "N":
                case "NACIONALIDAD":
                case "NATIONALITY": {

                    String nuevoValor = readString_ne("Nuevo valor: ");
                    if (c.modificarNacinalidadActor(nombre, nuevoValor)) {
                        System.out.println("El valor ha sido añadido correctamente.");
                    } else {
                        System.out.println("\n\tERROR: NO se ha podido modificar.");
                    }

                    readString("\n\nPulse intro para continuar: ");
                    break;
                }

                case "A":
                case "AÑO DE DEBUT":
                case "DEBUT YEAR": {

                    int nuevoValor = readInt("Nuevo valor: ", 0 , LocalDate.now().getYear() + 1);
                    if (c.modificarAñoDebutActor(nombre, nuevoValor)) {
                        System.out.println("El valor ha sido añadido correctamente.");
                    } else {
                        System.out.println("\n\tERROR: NO se ha podido modificar.");
                    }

                    readString("\n\nPulse intro para continuar: ");
                    break;
                }

                case "SALIR":
                case "EXIT":
                case "Q": {

                    // Todo ha ido bien. Se sale del programa
                    salir = siOno("¿Desea realmente salir?");
                }

                default: {
                    clear();
                    System.out.println("\n\tERROR: Opción introducida no válida");

                    // La opción introducida no es válida.
                }
            }
        }
    }

    private void peliculasActores() {
        
        clear();
        System.out.println("\n"
                    + "\t\t\t\t░█▀█░█▀▀░█░░░▀█▀░█▀▀░█░█░█░░░█▀█░█▀▀░░░█▀▄░█▀▀░░░█▀█░█▀▀░▀█▀░█▀█░█▀▄░\n"
                    + "\t\t\t\t░█▀▀░█▀▀░█░░░░█░░█░░░█░█░█░░░█▀█░▀▀█░░░█░█░█▀▀░░░█▀█░█░░░░█░░█░█░█▀▄░\n"
                    + "\t\t\t\t░▀░░░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░░░▀▀░░▀▀▀░░░▀░▀░▀▀▀░░▀░░▀▀▀░▀░▀░\n\n");
        
        // Leemos el nombre del actor a eliminar
        String nombre = readString_ne("\n\tNombre: ");

        ArrayList<String> peliculasActor = c.peliculasActor(nombre);

        if (peliculasActor.isEmpty()) {

            System.out.printf("\n\tERROR: No se ha encontrado el actor %s\n", nombre);
        } else {

            switch (peliculasActor.get(0)) {

                case "sinPelis" ->  {

                    // El actor no tiene peliculas guardadas.
                    System.out.println("\n\tERROR: El actor no tiene peliculas almacenadas.");
                }

                case "repetido" ->  {

                    // Hay más de un director dado de alta con ese nombre
                    System.out.println("Hay varios actores con el mismo nombre.");

                    // Imprimimos los datos de los directores
                    for (int i = 1; i < peliculasActor.size(); i++) {
                        System.out.println(peliculasActor.get(i));
                    }
                    
                    peliculasActoresConFecha(nombre);

                }

                default -> {

                    System.out.printf("%s tiene las siguientes peliculas dadas de alta: \n\n", nombre);

                    for (String datos : peliculasActor){
                        System.out.println(datos);
                    }
                }
            }
        }

        readString("\n\nPulse intro para continuar: ");

    }

    private void peliculasActoresConFecha(String nombre) {

        // Leemos la fecha para distinguir los actores.
        String cadenaNacimiento;
        LocalDate fechaNacimiento = LocalDate.now().plusDays(1);

        while (fechaNacimiento.isAfter(LocalDate.now())) {
            cadenaNacimiento = readString_ne("\n\tFecha de nacimiento (dd/MM/yyyy): ");
            fechaNacimiento = c.cadenaALocalDate(cadenaNacimiento);
        }

        ArrayList<String> peliculasActor = c.peliculasActoresConFecha(nombre, fechaNacimiento);

        if (peliculasActor == null) {

            System.out.printf("\n\tERROR: No se ha encontrado el actor %s\n"
                    + "con fecha de nacimiento %s.\n",
                    nombre, fechaNacimiento);
        } else {
            
            if (peliculasActor.isEmpty()) {
                System.out.println("\n\tERROR: Aún no hay peliculas almacenadas para ese actor");
            } else {
                
                for (String pelicula : peliculasActor) {
                    System.out.println(pelicula);
                }
            }
        }
    }

    /*
            ░█░░░▀█▀░█▀▀░▀█▀░█▀█░█▀▄░█▀█░█▀▀░
            ░█░░░░█░░▀▀█░░█░░█▀█░█░█░█░█░▀▀█░
            ░▀▀▀░▀▀▀░▀▀▀░░▀░░▀░▀░▀▀░░▀▀▀░▀▀▀░
     */
    // Mostrar el listdo de peliculas
    private void listadosPeliculas() {
        
        clear();
        System.out.println("\n"
                    + "\t\t\t\t░█░░░▀█▀░█▀▀░▀█▀░█▀█░█▀▄░█▀█░░░█▀▄░█▀▀░░░█▀█░█▀▀░█░░░▀█▀░█▀▀░█░█░█░░░█▀█░█▀▀░\n"
                    + "\t\t\t\t░█░░░░█░░▀▀█░░█░░█▀█░█░█░█░█░░░█░█░█▀▀░░░█▀▀░█▀▀░█░░░░█░░█░░░█░█░█░░░█▀█░▀▀█░\n"
                    + "\t\t\t\t░▀▀▀░▀▀▀░▀▀▀░░▀░░▀░▀░▀▀░░▀▀▀░░░▀▀░░▀▀▀░░░▀░░░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░\n\n");
        
        ArrayList<String> listadoPeliculas = c.listadosPeliculas();

        if (listadoPeliculas == null) {
            System.out.println("\n\tERROR: No hay datos cargados que mostrar.");
        } else {

            // Mostramos los datos recibidos en formato tabular
            for (String linea : listadoPeliculas){
                System.out.println(linea);
            }
        }

        readString("\n\nPulse intro para continuar: ");
    }

    // Mostrar el listdo de directores
    private void listadosDirectores() {
        
        clear();
        System.out.println("\n"
                    + "\t\t\t\t░█░░░▀█▀░█▀▀░▀█▀░█▀█░█▀▄░█▀█░░░█▀▄░█▀▀░░░█▀▄░▀█▀░█▀▄░█▀▀░█▀▀░▀█▀░█▀█░█▀▄░█▀▀░█▀▀░\n"
                    + "\t\t\t\t░█░░░░█░░▀▀█░░█░░█▀█░█░█░█░█░░░█░█░█▀▀░░░█░█░░█░░█▀▄░█▀▀░█░░░░█░░█░█░█▀▄░█▀▀░▀▀█░\n"
                    + "\t\t\t\t░▀▀▀░▀▀▀░▀▀▀░░▀░░▀░▀░▀▀░░▀▀▀░░░▀▀░░▀▀▀░░░▀▀░░▀▀▀░▀░▀░▀▀▀░▀▀▀░░▀░░▀▀▀░▀░▀░▀▀▀░▀▀▀░\n\n");
        
        Boolean completo = siOno("Puede que el formato salga distorsionado. "
                    + "¿Desea mostrar los datos completos?");
        
        ArrayList<String> listadoDirectores = c.listadosDirectores(completo);

        if (listadoDirectores == null) {
            System.out.println("\n\tERROR: No hay datos cargados que mostrar.");
        } else {

            // Mostramos los datos recibidos en formato tabular
            for (String linea : listadoDirectores){
                System.out.println(linea);
            }
        }

        readString("\n\nPulse intro para continuar: ");

    }

    // Mostrar el listado de actores
    private void listadosActores() {
        
        clear();
        System.out.println("\n"
                    + "\t\t\t\t░█░░░▀█▀░█▀▀░▀█▀░█▀█░█▀▄░█▀█░░░█▀▄░█▀▀░░░█▀█░█▀▀░▀█▀░█▀█░█▀▄░█▀▀░█▀▀\n"
                    + "\t\t\t\t░█░░░░█░░▀▀█░░█░░█▀█░█░█░█░█░░░█░█░█▀▀░░░█▀█░█░░░░█░░█░█░█▀▄░█▀▀░▀▀█\n"
                    + "\t\t\t\t░▀▀▀░▀▀▀░▀▀▀░░▀░░▀░▀░▀▀░░▀▀▀░░░▀▀░░▀▀▀░░░▀░▀░▀▀▀░░▀░░▀▀▀░▀░▀░▀▀▀░▀▀▀\n\n");
        
        Boolean completo = siOno("Puede que el formato salga distorsionado. "
                    + "¿Desea mostrar los datos completos?");
        
        ArrayList<String> listadoActores = c.listadosActores(completo);

        if (listadoActores == null) {
            System.out.println("\n\tERROR: No hay datos cargados que mostrar.");
        } else {

            // Mostramos los datos recibidos en formato tabular
            for (String linea : listadoActores){
                System.out.println(linea);
            }
        }

        readString("\n\nPulse intro para continuar: ");
    }

}
