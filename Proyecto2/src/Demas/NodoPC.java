/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demas;

/**
 * Nodos para el NodeStack y NodeQueue
 * @author Robert
 */
class NodoPC{
    PriorityNode info;
    NodoPC sig;
     
    public NodoPC(PriorityNode info){
        this.info = info;
        this.sig = null;
    }
}
