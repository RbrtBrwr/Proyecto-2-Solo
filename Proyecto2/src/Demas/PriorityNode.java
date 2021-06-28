/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demas;

/**
 * Nodos para la cola de prioridad
 * @author Robert
 */
public class PriorityNode{
    Document documento;
    PriorityNode padre;
    PriorityNode hijoIzq;
    PriorityNode hijoDer;
    PriorityNode pasado;
    
    
    public PriorityNode(Document documento){
        this.documento = documento;
        this.padre = null;
        this.hijoDer = null;
        this.hijoIzq = null;
        this.pasado = null;
    }
}
