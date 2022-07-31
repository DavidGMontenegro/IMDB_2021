
package modelo;

import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author david
 */
public class Filmoteca {
    
    // Creamos listas de todas las clases a almacenar en nuestra base de datos
    public static List<Pelicula> peliculas = new ArrayList();
    public static List<Actor> actores = new ArrayList();
    public static List<Director> directores = new ArrayList();

    final String ACTORESBIN= "actores.bin";
    final String PELICULASSBIN= "peliculas.bin";
    final String DIRECTORESBIN= "directores.bin";
    
}
