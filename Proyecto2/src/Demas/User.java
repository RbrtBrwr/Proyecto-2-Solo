/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demas;

/**
 * usuarios del sistema, poseen nombre, prioridad y una lista de documentos
 * @author Robert
 */
public class User {
    String nombre;
    String prioridad;
    public FileList documentos;
    
    public User(String nombre, String prioridad){
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.documentos = new FileList();
    }
    
    /**
     * agrega un documento a la lista de documentos del usuario
     * @param nuevo 
     */
    public void agregarDocs(Document nuevo){
        documentos.agregarDoc(nuevo);
    }
    
    /**
     * retorna la prioridad del usuario
     * 
     * @return String
     */
    public String getPriority(){
        return this.prioridad;
    }
}
