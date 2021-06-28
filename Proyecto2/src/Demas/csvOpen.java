/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Robert
 */
public class csvOpen {
    
    /**
     * lee la informacion de un csv
     * @return 
     */
    public static String read() {
        String text = "";
        Scanner entry = null;
        //Se crea el JFileChooser. Se le indica que la ventana se abra en el directorio actual                    
        JFileChooser fileChooser = new JFileChooser(".");      
        //Se crea el filtro. El primer parámetro es el mensaje que se muestra,
        //El segundo es la extensión de los ficheros que se van a mostrar      
        FileFilter filter = new FileNameExtensionFilter("Archivos de Texto (.csv)", "csv"); 
        //Se le asigna al JFileChooser el filtro
        fileChooser.setFileFilter(filter);
        //Se muestra la ventana
        int value = fileChooser.showOpenDialog(fileChooser);
        if (value == JFileChooser.APPROVE_OPTION) {
            String route = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                File f = new File(route);
                if (!f.exists()){return null;}
                entry = new Scanner(f);
                while (entry.hasNext()) {
                    text += entry.nextLine()+ "\n";
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());

            } finally {
                if (entry != null) {
                    entry.close();
                }
            }
        } else {
            System.out.println("No se ha seleccionado ningún archivo");
            return null;
        }
        return text;
    }
    
    /**
     * copia la informacion a un csv
     * @param escribir 
     */
    public static boolean write(String escribir){
        //Se crea el JFileChooser. Se le indica que la ventana se abra en el directorio actual                    
        JFileChooser fileChooser = new JFileChooser(".");      
        //Se crea el filtro. El primer parámetro es el mensaje que se muestra,
        //El segundo es la extensión de los ficheros que se van a mostrar      
        FileFilter filter = new FileNameExtensionFilter("Archivos de Texto (.csv)", "csv"); 
        //Se le asigna al JFileChooser el filtro
        fileChooser.setFileFilter(filter);
        //Se muestra la ventana
        int value = fileChooser.showOpenDialog(fileChooser);
        if (value == JFileChooser.APPROVE_OPTION) {
            String route = fileChooser.getSelectedFile().getAbsolutePath();
            try {

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(route))) {
                    writer.write(escribir);
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());

            } 
        } else {
            System.out.println("No se ha seleccionado ningún archivo");
            return false;
        }
        return true;
    }
    
}
