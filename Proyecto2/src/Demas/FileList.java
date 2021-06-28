/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demas;

/**
 *
 * lista de documentos para cada usuario
 * @author Robert
 */
public class FileList {
    private class Nodo{
        Document info;
        Nodo sig;
        
        private Nodo(Document info){
            this.info = info;
            this.sig = null;
        }
    }
    
    Nodo head, tail = null;
    int size = 0;
    
    /**
     * agrega el documento a la lista de documentos
     * @param doc 
     */
    public void agregarDoc(Document doc){
        if (head == null){
            head = tail = new Nodo(doc);
            this.size += 1;
        } else {
            tail.sig = new Nodo(doc);
            tail = tail.sig;
            this.size += 1;
        }
    }
    
    /**
     * retorna un string con todos los archivos en la lista, con su nombre, tamano y si esta en cola de impresion;s
     * @return 
     */
    public String printDocs(){
        String outString = "";
        if (head == null){
            outString = "No tiene documentos";
        } else {
            Nodo track = head;
            int i = 1;
            while (track != null){
                outString += "     " + i + ". " + track.info.nombre + track.info.getType() + "\n        Tamano: " + track.info.tamano + ".\n        ";
                if (track.info.timeStamp > 0){
                    outString += "-- En Impresora --";
                } 
                outString += "\n";
                i++;
                track = track.sig;
            }
        }
        return outString + "\n";
    }
    
    /**
     * retorna el documento en una posicion dada.
     * @param posicion
     * @return Document
     */
    public Document getDocAt(int posicion){
        if (posicion >= this.size){return null;}
        Nodo track = head;
        int i = 0;
        while (i < posicion){
            track = track.sig;
            i++;
        }
        return track.info;
    }
    
    /**
     * Elimina el archivo del nombre recibido, si no lo encuentra retorna false.
     * @param name
     * @return boolean
     * 
     * es posible que no me sirva, chequear
     */
    public boolean eliminarArchivo(String name){
        if (head == null){
            return false;
        } else {
            Nodo track = head;
            if (head.info.nombre.equals(name)){
                head = head.sig;
                return true;
            } 
            Nodo previo = track;
            track = track.sig;
            
            while (track != null){
                if (track.info.nombre.equals(name)){
                    previo.sig = track.sig;
                    return true;
                }
                track = track.sig;
            }
        }
        return false;
    }
    
    /**
     * Retorna un documento de la lista buscado por nombre
     * @param nombre
     * @return 
     */
    public Document getDoc(String nombre){
        Nodo track = head;
        while (track != null){
            
            if (track.info.nombre.equals(nombre)){
                return track.info;
            }
            track = track.sig;
        }
        return null;
    }
}
