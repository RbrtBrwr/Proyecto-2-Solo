/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demas;

/**
 * HashTable para los usuarios
 * @author Robert
 */
public class HashMesa {
    public class Nodo {
        User user;
        Nodo sig;
        
        public Nodo(User user){
            this.user = user;
            this.sig = null;
        }
    }
    
    final int Size = 73;
    int userNumb = 0;
    Nodo[] table = new Nodo[Size];
    
    /**
     * agrega un usuario al hashTable, ,retorna un string informando si fue agregado
     * @param user
     * @return 
     */
    public String agregar(User user){
        int key = this.hashear(user.nombre);
        
        return this.add(user, key);
    }
    
    /**
     * inserta el usuario en la posicion indicada dentro de la tabla
     * @param user
     * @param key
     * @return 
     */
    public String add(User user, int key){
        Nodo usuario = new Nodo(user);
        if (table[key] == null){
            table[key] = usuario;
            userNumb++;
            return "Usuario " + usuario.user.nombre + " registrado.";
        } else {
            Nodo track = table[key];
            Nodo previo = table[key];
            while (track != null){
                if (track.user.nombre.equals(usuario.user.nombre)){
                    track.user.prioridad = user.prioridad;
                    return "Prioridad del usuario " + user.nombre +" modificada.";
                }
                previo = track;
                if (track.sig != null){track = track.sig;}
                else {break;}
            }
            
            previo.sig = usuario;
            userNumb++;
            return "Usuario " + usuario.user.nombre + " registrado.";
        }
    }
    
    /**
     * hashea el nombre de un usuario para generar una llave que indica su posicion en la tabla
     * @param nombre
     * @return int 
     */
    public int hashear(String nombre){
        int key = nombre.hashCode() % Size;
        if (key < 0){
            return -1 * key;
        } else {
            return key;
        }
    }
    
    /**
     * Busca un usuario por nombre dentro de la tabla, retorna un string informando si fue agregado exitosamente
     * @param nombre
     * @return 
     */
    public String buscarUsuario(String nombre){
        int key = hashear(nombre);
        if (table[key] == null){
            return "Usuario " + nombre + " no existe.";
        } else {
            Nodo track = table[key];
            while (track.sig != null){
                if (track.user.nombre.equals(nombre)){
                    return "Usuario ya existe";
                }
                track = track.sig;
            }
            return "Usuario " + nombre + " no existe.";
        }
    }
    
    /**
     * retorna el usuario de la tabla con el nombre indicado
     * @param nombre
     * @return 
     */
    public User getUser(String nombre){
        int key = hashear(nombre);
        if (table[key] == null){
            return null;
        } else {
            Nodo track = table[key];
            if (track.user.nombre.equals(nombre)){
                return track.user;
            } else {
                while (track.sig != null){
                    if (track.user.nombre.equals(nombre)){
                        return track.user;
                    }
                    track = track.sig;
                }
                return null;
            }
        }
    }

    /**
     * elimina al usuario con el nombre indicado de la tabla
     * @param nombre
     * @return String
     */
    public String eliminarUsuario(String nombre){
        int key = hashear(nombre);
        if (table[key] == null){
            return "Usuario " + nombre + " no existe.";
        } else {
            Nodo track = table[key];
            Nodo previo = null;
            if (table[key].user.nombre.equals(nombre)){
                if (table[key].sig == null){
                    table[key] = null;
                }
            }
            while (track != null){
                if (track.user.nombre.equals(nombre)){
                    if (previo == null){
                        table[key] = track.sig;
                    } else {
                        previo.sig = track.sig;
                    }
                    userNumb--;
                    return nombre + " eliminado.";
                }
                previo = track;
                track = track.sig;
            }

            return "Usuario " + nombre + " no existe.";
        }
    }
    
    /**
     * retorna un string con los usuarios de la tabla y los documentos de cada uno
     * 
     * @return 
     */
    public String imprimir(){
        Nodo aux;
        String outString = "";
        for (int i = 0; i < Size; i++){
            if (table[i] == null){continue;}
            aux = table[i];
            while (aux != null){
                outString += aux.user.nombre + ":\n" + aux.user.documentos.printDocs() + "\n";
                aux = aux.sig;
            }
        }
        return outString;
    }
    
    /**
     * retorna un string con los usuarios y su prioridad para guardar en el CSV
     * @return 
     */
    public String paraGuardar(){
        Nodo aux;
        String outString = "usuario,tipo\n";
        for (int i = 0; i < Size; i++){
            if (table[i] == null){continue;}
            aux = table[i];
            while (aux != null){
                outString += aux.user.nombre + "," + aux.user.prioridad + "\n";
                aux = aux.sig;
            }
        }
        return outString;
    }

}
