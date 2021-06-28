/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demas;

/**
 *
 * @author Robert
 */
public class PriorityQueue {
    NodeQueue prevenidoTail = new NodeQueue();
    NodeStack pastTail = new NodeStack();
    public PriorityNode raiz = null;
    PriorityNode tail = null;
    PriorityNode ultimo = null;
    
    /**
     * ordena a los elementos del monticulo por orden de prioridad primero, tiempo segundo, y tamano tercero;
     * @param nodo 
     */
    public void heapify(PriorityNode nodo){
        Document aux = nodo.documento;
        
        if (nodo.padre == null){return;}
        
        if (nodo.documento.total < nodo.padre.documento.total){
            
            nodo.documento = nodo.padre.documento;
            nodo.padre.documento = aux;
            heapify(nodo.padre);
        }
    }
    
    /**
     * agrega un nuevo documento al monticulo
     * @param documento 
     */
    public void agregarACola(Document documento){
        PriorityNode nuevo = new PriorityNode(documento);
        if (raiz == null){
            raiz = ultimo = nuevo;
            prevenidoTail.push(nuevo);
        } else {
            tail = prevenidoTail.top();
            if (tail.hijoIzq == null){
                nuevo.padre = tail;
                tail.hijoIzq = nuevo;
                prevenidoTail.push(nuevo);
                nuevo.pasado = ultimo;
                ultimo = nuevo;
                heapify(ultimo);
            } else if (tail.hijoDer == null){
                nuevo.padre = tail;
                tail.hijoDer = nuevo;
                prevenidoTail.push(nuevo);
                nuevo.pasado = ultimo;
                ultimo = nuevo;
                heapify(ultimo);
            } else {
                prevenidoTail.pop();
                pastTail.push(tail);
                agregarACola(documento);
            }
        }
    }
    
    /**
     * ordena los documentos del monticulo por prioridad, tiempo y luego tamano, ordena desde la raiz para abajo.
     * @param nodo 
     */
    public void heapifyTopDown(PriorityNode nodo){
        if (nodo == null){return;}
        Document aux = nodo.documento;
        
        if (nodo.hijoIzq == null & nodo.hijoDer == null){} 
        else if (nodo.hijoDer == null){
            
            if (nodo.hijoIzq.documento.total < nodo.documento.total){
                nodo.documento = nodo.hijoIzq.documento;
                nodo.hijoIzq.documento = aux;
                heapifyTopDown(nodo.hijoIzq);  
            } 
            
        } else if (nodo.hijoIzq == null){
            if (nodo.hijoDer.documento.total < nodo.documento.total){
                nodo.documento = nodo.hijoDer.documento;
                nodo.hijoDer.documento = aux;
                heapifyTopDown(nodo.hijoDer);
            } 
        } else {
            if (nodo.hijoIzq.documento.total < nodo.hijoDer.documento.total && nodo.hijoIzq.documento.total < nodo.documento.total){
                nodo.documento = nodo.hijoIzq.documento;
                nodo.hijoIzq.documento = aux;
                heapifyTopDown(nodo.hijoIzq);
                
            } else if (nodo.hijoDer.documento.total < nodo.hijoIzq.documento.total && nodo.hijoDer.documento.total < nodo.documento.total){
                nodo.documento = nodo.hijoDer.documento;
                nodo.hijoDer.documento = aux;
                heapifyTopDown(nodo.hijoDer);
            } 
        }
    }
    
    /**
     * elimina un nodo del monticulo
     * @param nodo 
     */
    public void eliminarNodo(PriorityNode nodo){
        if (nodo.padre == null){return;}
        PriorityNode padre = nodo.padre;
        if (padre.hijoIzq == nodo){
            padre.hijoIzq = null;
        } else {
            padre.hijoDer = null;
        }
        this.prevenidoTail.fishOut();
        ultimo = nodo.pasado;
    }
    
    /**
     * retorna el proximo nodo
     * @return 
     */
    public Document popTop(){
        if (raiz == null){return null;}
        Document aux = raiz.documento;
        if (raiz != ultimo){
            raiz.documento = ultimo.documento;
            eliminarNodo(ultimo);
            this.heapifyTopDown(raiz);
            return aux;
        } else {
            raiz = ultimo = null;
            return aux;
        }
    }
    
    /**
     * elimina y retorna el documento en la raiz del monticulo
     * @return 
     */
    public String imprimir(){
        Document aImprimir = this.popTop();
        if (aImprimir.total < 0){
            return "Documento eliminado.";
        } else {
            return "El documento " + aImprimir.getName() + aImprimir.getType() + " ha sido impreso exitosamente.";
        }
    }
    
    /**
     * recorrido en preorden para imprimir en forma de arbol
     * @return 
     */
    public String recorridoPreorden(){
        if (raiz == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(raiz.documento.nombre);

        String pointerRight = "└──";
        String pointerLeft = (raiz.hijoDer != null) ? "├──" : "└──";

        recorridoPreorden(sb, "", pointerLeft, raiz.hijoIzq, raiz.hijoDer != null);
        recorridoPreorden(sb, "", pointerRight, raiz.hijoDer, false);

        return sb.toString();
    }
    
    /**
     * recorrido en preorden para imprimir en forma de arbol pt.2
     * @param sb
     * @param padding
     * @param pointer
     * @param nodo
     * @param hermanoDer
     */

    public void recorridoPreorden(StringBuilder sb, String padding, String pointer, PriorityNode nodo, boolean hermanoDer){
        
        if (nodo != null){
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(nodo.documento.nombre);
            
            StringBuilder paddingBuilder = new StringBuilder(padding);
            
            if (hermanoDer){
                paddingBuilder.append("│    ");
            } else {
                paddingBuilder.append("     ");
            }
            
            String paddingAmbos = paddingBuilder.toString();
            String pointerDerecho = "└──";
            String pointerIzquierdo = (nodo.hijoDer != null) ? "├──" : "└──";
            recorridoPreorden(sb, paddingAmbos, pointerIzquierdo, nodo.hijoIzq, nodo.hijoDer != null);
            recorridoPreorden(sb, paddingAmbos, pointerDerecho, nodo.hijoDer, false);
        }

    }
    
    /**
     * reinicializa el monticulo para arreglar el orden de insercion
     * @return 
     */
    public String reHeap(){
        this.prevenidoTail = new NodeQueue();
        this.pastTail = new NodeStack();
                
        
        NodeQueue colaAuxiliar = new NodeQueue();
        Document docAuxiliar;
        String impresionante = "En Cola: \n";
        int i = 1;
        while (this.raiz != null){
            docAuxiliar = this.popTop();
            //if (docAuxiliar == null){continue;}
            impresionante += i + ". " + docAuxiliar.nombre + "\n";
            colaAuxiliar.push(new PriorityNode(docAuxiliar));
            i++;
        }
        
        while (!colaAuxiliar.isEmpty()){
            this.agregarACola(colaAuxiliar.popTop().documento);
        }
        
        return impresionante;
    }
    
    /**
     * revisa si el monticulo esta vacio
     * @return 
     */
    public boolean isEmpty(){
        return raiz == null;
    }
    
    /**
     * elimina un documento de la cola
     * @param elimDoc
     * @return 
     */
    public String eliminarDocumento(String elimDoc){
        boolean eliminar = false;
        this.prevenidoTail = new NodeQueue();
        this.pastTail = new NodeStack();
        String outString = "Documento no encontrado";        
        
        NodeQueue colaAuxiliar = new NodeQueue();
        Document docAuxiliar;
        while (this.raiz != null){
            docAuxiliar = this.popTop();
            if (docAuxiliar.nombre.equals(elimDoc)){
                docAuxiliar.setPriority(-10);
                outString = "Documento eliminado";
                eliminar = true;
            }
            colaAuxiliar.push(new PriorityNode(docAuxiliar));

        }
        
        while (!colaAuxiliar.isEmpty()){
            this.agregarACola(colaAuxiliar.popTop().documento);
        }
        
        if (eliminar){this.imprimir();}
        return outString;
    }
}
