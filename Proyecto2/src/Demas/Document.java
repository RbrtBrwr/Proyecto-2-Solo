/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demas;

/**
 * Documentos de los usuarios que seran enviados a la cola de impresion, tienen nombre, tamano, prioridad y un timestamp;
 * @author Robert
 */
public class Document {
    String nombre;
    int tamano;
    int prioridad;
    int timeStamp;
    int total;
    String type;
    
    /**
     * Al inicializar el documento no esta en la cola entonces su timeStamp es negativo.
     * @param nombre 
     * @param tamano 
     * @param tipo 
     */
    public Document(String nombre, int tamano, int tipo){
        this.nombre = nombre;
        this.tamano = tamano;
        this.prioridad = tipo;
        this.timeStamp = -1;
    }
    
    /**
     * Asigna el tiempo al archivo, y calcula su total para la posicion de la cola
     * @param timeStamp 
     */
    public void setTime(int timeStamp){
        this.timeStamp = timeStamp;
        this.total = (((2 * tamano)) * prioridad) + timeStamp;
    }
    
    /**
     * Retorna el timeStamp del documento
     * creo que no lo uso
     * @return 
     */
    public int getTime(){
        return this.timeStamp;
    }
    
    /**
     * retorna la prioridad del documento
     * @return 
     */
    public int getPriority(){
        return this.prioridad;
    }
    
    /**
     * Asigna prioridad al documento y recalcula el total
     * @param a 
     */
    public void setPriority(int a){
        this.prioridad = a;
        this.total = (((2 * tamano)) * prioridad) + timeStamp;
    }
    
    /**
     * retorna el nombre del documento
     * @return 
     */
    public String getName(){
        return this.nombre;
    }
    
    /**
     * asigna el tipo de documento
     * @param docType 
     */
    public void setType(String docType){
        this.type = docType;
    }
    
    /**
     * retorna el tipo de docuento
     * @return 
     */
    public String getType(){
        return this.type;
    }
    
    
}
