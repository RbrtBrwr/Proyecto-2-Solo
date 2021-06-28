/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demas;

/**
 * Cola de nodos
 * @author Robert
 */
public class NodeQueue {   
    NodoPC cabeza, cola = null;
    int tamano = 0; // creo que no necesito esto

    /**
     * revisa si la cola esta vacia
     * @return 
     */
    public boolean isEmpty(){
        return this.tamano < 1;
    }
    
    /**
     * Agrega un nuevo nodo a la cola
     * @param info 
     */
    public void push(PriorityNode info){
        NodoPC nuevo = new NodoPC(info);
        
        if (this.isEmpty()){
            this.tamano = 1;
            cabeza = cola = nuevo;   
        } else {
            this.tamano ++;
            cola.sig = nuevo;
            cola = nuevo;
            
        }
    }
    
    /**
     * retorna tamano de la cola
     * creo que no lo use
     * @return 
     */
    public int getSize(){
        return this.tamano;
    }
    
    /**
     * elimina el elemento siguiente de la cola
     */
    public void pop(){
        if (this.isEmpty()){
        } else {
            try {
                cabeza = cabeza.sig;
            } catch(Exception e){
                return;//Fin
            }
            
            this.tamano --;
        }
    }
    
    /**
     * retorna el elemento siguiente de l cola sin eliminarlo
     * @return 
     */
    public PriorityNode top(){
        return this.cabeza.info;
    }
    
    
    /**
     * elimina y retorna el elemento siguiente de la cola
     * @return 
     */
    public PriorityNode popTop(){
        PriorityNode aux = null;
        if (this.isEmpty()){} 
        else {
            try {
                aux = top();
                cabeza = cabeza.sig;
            } catch(Exception e){}
     
            this.tamano --;
        }
        return aux;
    }
    
    /**
     * agrega un nodo el principio de la cola
     * @param info 
     */
    public void sumergir(PriorityNode info){
        this.push(info);
        while (this.cabeza.info != info){
            this.push(this.popTop());
        }
    }
    
    /**
     * elimina y retorna el ultimo elemento de la cola
     * @return 
     */
    public PriorityNode fishOut(){
        NodoPC aux = this.cola;
        while (this.cabeza != aux){
            push(this.popTop());
        }
        return this.popTop();
    }

}
