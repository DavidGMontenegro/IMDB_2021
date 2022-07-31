
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
public class Actor implements Serializable{
    
    /*
         ▄▄     ▄▄▄ ▄▄▄▄▄▄▄  ▄▄▄▄  ▄▄▄▄▄ 
         ██   ▄▀   ▀   █    ▄▀  ▀▄ █   ▀█
        █  █  █        █    █    █ █▄▄▄▄▀
        █▄▄█  █        █    █    █ █   ▀▄
       █    █  ▀▄▄▄▀   █     █▄▄█  █    █
    
    */
    
    private String nombre = new String();
    private LocalDate fechaNacimiento;
    private String nacionalidad = new String();
    private int añoDebut;
    private String[] peliculas;
    
    
    // Constructor
    Actor() {
    }
    
    
    // Constructor a partir del nombre, año de debut y nombre de la pelicula
    Actor(String nombre, int año, String pelicula) {
        this.nombre = nombre;
        this.fechaNacimiento = LocalDate.now();
        this.nacionalidad = "  ...";
        this.añoDebut = año;
        
        String[] pelis = new String[]{ pelicula };
        this.peliculas = pelis;
    }
    
    // Constructor con todos los datos
    public Actor(String nombre, LocalDate fechaNacimiento, String nacionalidad,
            int añoDebut, String[] peliculas)
    {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.añoDebut = añoDebut;
        this.peliculas = peliculas;
    }
    
    // Constructor a partir de los datos pasados como String[] por argumento
    static Actor crearActorAPartirDeCampos(String[] campos) {
        
        try {
            String nombre = campos[0];
            LocalDate fechaNacimiento = LocalDate.parse(campos[1]);
            String nacionalidad = campos[2];
            int añoDebut = Integer.parseInt(campos[3]);
            String[] peliculas = campos[4].split("\t");

            return new Actor(nombre, fechaNacimiento, nacionalidad, añoDebut, peliculas);
        }catch (Exception e){
            return null;
        }
    }


    // Convertir los datos de un actor a una String con fomato encolumnado (ancho delimitado por arg)
    public String asColumn(int[] numCols){
        
        String films;
        String theFormat = "| %-" +
                numCols[0] +
                "s | %-" +
                numCols[1] +
                "s | %-" +
                numCols[2] +
                "s | %-" +
                numCols[3] +
                "s | %-" +
                numCols[4] +
                "s |";
        
        StringBuffer pelis = new StringBuffer();
        
        for (String pelicula : this.peliculas) {
            pelis = pelis.append(pelicula).append(", ");
        }
        
        if (pelis.length() > numCols[4]){
           films = pelis.toString().substring(0, numCols[4] - 5) + " ...";
        }else{
            films = pelis.toString();
        }
        
        return String.format(theFormat, this.nombre, fechaNacimientoFormateada(),
                this.nacionalidad, String.valueOf(añoDebut), films);
        
    }
    
    // Convertir LocalDate a String
    public String fechaNacimientoFormateada() {

        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        return this.fechaNacimiento.format(formatter);
    }

    
    /*
        hashCode y equals
    */
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.nombre);
        hash = 67 * hash + Objects.hashCode(this.fechaNacimiento);
        hash = 67 * hash + Objects.hashCode(this.nacionalidad);
        hash = 67 * hash + this.añoDebut;
        hash = 67 * hash + Arrays.deepHashCode(this.peliculas);
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
        final Actor other = (Actor) obj;
        if (this.añoDebut != other.añoDebut) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.nacionalidad, other.nacionalidad)) {
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

    public int getAñoDebut() {
        return añoDebut;
    }

    public void setAñoDebut(int añoDebut) {
        this.añoDebut = añoDebut;
    }

    public String[] getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(String[] peliculas) {
        this.peliculas = peliculas;
    }
    
    
}
