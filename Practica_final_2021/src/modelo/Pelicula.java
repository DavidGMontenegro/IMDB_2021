
package modelo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;


/**
 *
 * @author david
 */
public class Pelicula implements Serializable{

    /*
        ▄▄▄▄▄  ▄▄▄▄▄▄ ▄      ▄▄▄▄▄    ▄▄▄  ▄    ▄ ▄        ▄▄  
        █   ▀█ █      █        █    ▄▀   ▀ █    █ █        ██  
        █▄▄▄█▀ █▄▄▄▄▄ █        █    █      █    █ █       █  █ 
        █      █      █        █    █      █    █ █       █▄▄█ 
        █      █▄▄▄▄▄ █▄▄▄▄▄ ▄▄█▄▄   ▀▄▄▄▀ ▀▄▄▄▄▀ █▄▄▄▄▄ █    █

    */
    
    private String titulo = new String();
    private int año;
    private int duracion;
    private String pais = new String();
    private String[] direccion;
    private String guionista = new String();
    private String musica = new String();
    private String[] reparto;
    private String productora = new String();
    private String genero = new String();
    private String sinopsis = new String();
    
    
    // Constructores
    public Pelicula(String titulo) {
        this.titulo = titulo;
    }
    
    // Constructor de una nueva pelicula conocidos todos sus argumentos
    public Pelicula(String titulo, int año, int duracion, String pais, 
            String[] direccion, String guionista, String musica,
            String[] reparto, String productora,String genero, String sinopsis)
    {
        this.titulo = titulo;
        this.año = año;
        this.duracion = duracion;
        this.pais = pais;
        this.direccion = direccion;
        this.guionista = guionista;
        this.musica = musica;
        this.reparto = reparto;
        this.productora = productora;
        this.genero = genero;
        this.sinopsis = sinopsis;        
    }
    
    // Constructor de una pelicula con TODOS sus datos pasados por argumento (String [])
    static Pelicula crearPeliculaAPartirDeCampos(String[] campos) {
        if(campos.length != 11){
            return null;
        }else{
            try {

                String titulo = campos[0];
                int año = Integer.parseInt(campos[1]);
                int duracion = Integer.parseInt(campos[2]);
                String pais = campos[3];

                String[] direccion = campos[4].split("\t");

                String guionista = campos[5];
                String musica = campos[6];

                String[] reparto = campos[7].split("\t");

                String productora = campos[8];
                String genero = campos[9];
                String sinopsis = campos[10];


                return new Pelicula(titulo, año, duracion, pais, direccion, guionista,
                                musica, reparto, productora, genero, sinopsis);
            }catch (NumberFormatException e){
                return null;
            }
        }
    }
    
    
    // Convertir los datos de una pelicula a String
    @Override
    public String toString(){
        
        StringBuffer cadenaDireccion = new StringBuffer();
        StringBuffer cadenaReparto = new StringBuffer();
        
        // Añadimos los nombres de los directores a una String
        for (String direccion1 : this.direccion) {
            cadenaDireccion = cadenaDireccion.append(direccion1).append(" | ");
        }
        
        // Añadimos los nombres de los actores a una String
        for (String reparto1 : this.reparto) {
            cadenaReparto = cadenaReparto.append(reparto1).append(" | ");
        }
        
        return "\nTitulo: " + this.titulo +
                "\nAño: " + this.año +
                "\nDuracion: " + this.duracion +
                "\nPais: " + this.pais +
                "\nDireccion: | " + cadenaDireccion +
                "\nGuionista: " + this.guionista +
                "\nMusica: " + this.musica +
                "\nReparto: | " + cadenaReparto +
                "\nProductora: " + this.productora +
                "\nGenero: " + this.genero +
                "\nSinopsis: " + this.sinopsis;
        
    }
    
    
    // Convertir los datos a un formato encolumando, con ancho de columna delimitado por arg
    public String asColumn(int[] numCols){
        
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
        
        return String.format(theFormat, this.titulo, String.valueOf(this.año), 
                String.valueOf(this.duracion), this.pais, this.genero);
    }
    
    // Devuelve los datos formateados como la fila de una tabla de HTML
    public String comoFilaDeTablaHTML() {
        
        // Añadimos <TD>/</TD> al comienzo/final de cada dato
        String resultado;
        resultado = String.format("<TR>"
                + "<TD align=\"center\">%s</TD>" // titulo
                + "<TD align=\"center\">%d</TD>" // año
                + "<TD align=\"center\">%d</TD>" // duracion
                + "<TD align=\"center\">%s</TD>" // pais
                + "<TD align=\"center\">%s</TD>" // direccion
                + "<TD align=\"center\">%s</TD>" // guinoista
                + "<TD align=\"center\">%s</TD>" // musica
                + "<TD align=\"center\">%s</TD>" // reparto
                + "<TD align=\"center\">%s</TD>" // productora
                + "<TD align=\"center\">%s</TD>" // genero
                + "<TD align=\"center\">%s</TD>" // sinopsis
                + "</TR>",
                this.titulo.toUpperCase(),
                this.año,
                this.duracion,
                this.pais.toUpperCase(),
                Arrays.toString(this.direccion),
                this.guionista,
                this.musica,
                Arrays.toString(this.reparto),
                this.productora,
                this.genero.toUpperCase(),
                this.sinopsis
                );
        
        return resultado;
    }
    
    /*
        hashcode y equals
    */

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.titulo);
        hash = 41 * hash + this.año;
        hash = 41 * hash + this.duracion;
        hash = 41 * hash + Objects.hashCode(this.pais);
        hash = 41 * hash + Arrays.deepHashCode(this.direccion);
        hash = 41 * hash + Objects.hashCode(this.guionista);
        hash = 41 * hash + Objects.hashCode(this.musica);
        hash = 41 * hash + Arrays.deepHashCode(this.reparto);
        hash = 41 * hash + Objects.hashCode(this.productora);
        hash = 41 * hash + Objects.hashCode(this.genero);
        hash = 41 * hash + Objects.hashCode(this.sinopsis);
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
        final Pelicula other = (Pelicula) obj;
        if (this.año != other.año) {
            return false;
        }
        if (this.duracion != other.duracion) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.pais, other.pais)) {
            return false;
        }
        if (!Objects.equals(this.guionista, other.guionista)) {
            return false;
        }
        if (!Objects.equals(this.musica, other.musica)) {
            return false;
        }
        if (!Objects.equals(this.productora, other.productora)) {
            return false;
        }
        if (!Objects.equals(this.genero, other.genero)) {
            return false;
        }
        if (!Objects.equals(this.sinopsis, other.sinopsis)) {
            return false;
        }
        if (!Arrays.deepEquals(this.direccion, other.direccion)) {
            return false;
        }
        if (!Arrays.deepEquals(this.reparto, other.reparto)) {
            return false;
        }
        return true;
    }
    
    
    
    
    /*
        GETTER Y SETTERS
    */

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String[] getDireccion() {
        return direccion;
    }

    public void setDireccion(String[] direccion) {
        this.direccion = direccion;
    }

    public String getGuionista() {
        return guionista;
    }

    public void setGuionista(String guionista) {
        this.guionista = guionista;
    }

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

    public String[] getReparto() {
        return reparto;
    }

    public void setReparto(String[] reparto) {
        this.reparto = reparto;
    }

    public String getProductora() {
        return productora;
    }

    public void setProductora(String productora) {
        this.productora = productora;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
}
