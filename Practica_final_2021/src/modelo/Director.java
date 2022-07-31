
package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author david
 */
public class Director implements Serializable{

    /*
        ▄▄▄▄   ▄▄▄▄▄  ▄▄▄▄▄  ▄▄▄▄▄▄   ▄▄▄ ▄▄▄▄▄▄▄  ▄▄▄▄  ▄▄▄▄▄ 
        █   ▀▄   █    █   ▀█ █      ▄▀   ▀   █    ▄▀  ▀▄ █   ▀█
        █    █   █    █▄▄▄▄▀ █▄▄▄▄▄ █        █    █    █ █▄▄▄▄▀
        █    █   █    █   ▀▄ █      █        █    █    █ █   ▀▄
        █▄▄▄▀  ▄▄█▄▄  █    █ █▄▄▄▄▄  ▀▄▄▄▀   █     █▄▄█  █    █

     */
    private String nombre = new String();
    private LocalDate fechaNacimiento;
    private String nacionalidad = new String();
    private String ocupacion = new String();
    private String[] peliculas;

    // Constructores
    public Director(){
    }
    
    
    // Creamos un director conocido el nombre y la pelicula en que participa
    public Director(String nombre, String pelicula) {
        this.nombre = nombre;
        this.fechaNacimiento = LocalDate.now();
        this.nacionalidad = " ...";
        this.ocupacion = "director";
        
        String[] pelis = new String[]{ pelicula };
        this.peliculas = pelis;
    }

    
    // Creamos un director conocidos todos los datos
    public Director(String nombre, LocalDate fechaNacimiento, String nacionalidad,
            String ocupacion, String[] peliculas) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.ocupacion = ocupacion;
        this.peliculas = peliculas;
    }

    // Creamos un director conocidos los datos y pasados en formato String[]
    static Director crearDirectorAPartirDeCampos(String[] campos) {

        try {
            String nombre = campos[0];
            LocalDate fechaNacimiento = LocalDate.parse(campos[1]);
            String nacionalidad = campos[2];
            String ocupacion = campos[3];
            String[] peliculas = campos[4].split("\t");

            return new Director(nombre, fechaNacimiento, nacionalidad, ocupacion, peliculas);
        } catch (Exception e) {
            return null;
        }
    }

    
    // Convertimos los datos de un director a una cadena formateada como columna
    public String asColumn(int[] numCols) {

        String films;
        String subOcupacion;
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

        StringBuffer pelis = new StringBuffer();

        // Añadimos todas las peliculas a una nueva cadena, separandolos por comas
        for (String pelicula : this.peliculas) {
            // Las peliculas no caben en el espacio máximo asigando para ellas
            pelis = pelis.append(pelicula).append(", ");
        }
        
        // Si la longitud de peliculas es mayor a la especificada, se corta la cadena
        if (pelis.length() > numCols[4]) {
            films = pelis.toString().substring(0, numCols[4] - 5) + " ...";
        } else {
            films = pelis.toString();
        }
        
        // Si la longitud de ocupacioens es mayor a la especificada, se corta la cadena
        if ( ocupacion.length() > numCols[3]){
            
            // Las ocupaciones no caben en el espacio maximo reservado para las mismas
            subOcupacion = this.ocupacion.substring(0, numCols[3] - 5) + " ...";
        }else{
            subOcupacion = this.ocupacion;
        }
        
        
        return String.format(theFormat, this.nombre, fechaNacimientoFormateada(),
                this.nacionalidad, subOcupacion, films);

    }

    // Devuelve la fecha de nacimiento del director con una disposición formateada
    public String fechaNacimientoFormateada() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return this.fechaNacimiento.format(formatter);
    }

    
    /*
        hashcode y equals
    */

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.nombre);
        hash = 59 * hash + Objects.hashCode(this.fechaNacimiento);
        hash = 59 * hash + Objects.hashCode(this.nacionalidad);
        hash = 59 * hash + Objects.hashCode(this.ocupacion);
        hash = 59 * hash + Arrays.deepHashCode(this.peliculas);
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
        final Director other = (Director) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.nacionalidad, other.nacionalidad)) {
            return false;
        }
        if (!Objects.equals(this.ocupacion, other.ocupacion)) {
            return false;
        }
        if (!Objects.equals(this.fechaNacimiento, other.fechaNacimiento)) {
            return false;
        }
        if (!Arrays.deepEquals(this.peliculas, other.peliculas)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    /*
        GETTERS Y SETTERS
    */
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String[] getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(String[] peliculas) {
        this.peliculas = peliculas;
    }

}
