/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demas;

/**
 * Pila de nodos
 * @author Robert
 */
class NodeStack {
    NodoPC fondo, top = null;
    
    /**
     * chequea si la pila esta vacia
     * @return 
     */
    public boolean isEmpty(){
        return this.fondo == null;
    }
    
    /**
     * Agrega un nuevo nodo a la pila
     * @param info 
     */
    public void push(PriorityNode info){
        NodoPC nuevo = new NodoPC(info);
        
        if (this.isEmpty()){
            this.fondo = this.top = nuevo;
        } else {
            this.top.sig = nuevo;
            this.top = nuevo;
        }
    }
    
    /**
     * saca el nodo tope de la pila y retorna su informacion
     * @return 
     */
    public PriorityNode pop(){
        
        if (this.isEmpty()){return null;}
        
        PriorityNode aux = this.top.info;
        
        if (this.fondo == this.top){
            this.fondo = this.top = null;
            return aux;
        }
        
        NodoPC track = this.fondo;
        NodoPC previo = this.fondo;
        
        while (track != this.top){
            previo = track;
            track = track.sig;
        }
        previo.sig = null;
        this.top = previo;
        
        return aux;
    }
}
