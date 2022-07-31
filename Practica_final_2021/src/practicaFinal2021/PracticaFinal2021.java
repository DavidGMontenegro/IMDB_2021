/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practicaFinal2021;

import vista.Vista;

/**
 *
 * @author david
 */
public class PracticaFinal2021 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        /*
            ▄    ▄   ▄▄   ▄▄▄▄▄  ▄▄   ▄
            ██  ██   ██     █    █▀▄  █
            █ ██ █  █  █    █    █ █▄ █
            █ ▀▀ █  █▄▄█    █    █  █ █
            █    █ █    █ ▄▄█▄▄  █   ██

        */
        
        Vista v = new Vista();
        int temp = -1;
        
        v.arrancar("peliculas");
        v.arrancar("actores");
        v.arrancar("directores");
        
        while ((temp == -1 || temp != 1)) {  
            v.mostrarTitulo();
            temp = v.mostrarMenu();
        }
        
    }
    
}
