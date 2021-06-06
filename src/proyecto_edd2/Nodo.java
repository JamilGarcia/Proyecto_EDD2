
package proyecto_edd2;

public class Nodo {
    
    int numero_llaves;//Numero de llaves almacenadas en el nodo
    Llave llaves [];//Llaves almacenadas en el nodo (orden nodescendente)
    boolean isLeaf; //Verificador si el nodo es hoja o no {nodo interno = false; nodo hoja = true}
    Nodo hijos[]; //Hijos del nodo

    public Nodo(int T) {
       
        this.numero_llaves = 0;
        this.llaves = new Llave[(2*T) - 1];
        this.isLeaf = true;
        this.hijos = new Nodo[2 * T];
    }

    public int getNumero_llaves() {
        return numero_llaves;
    }

    public void setNumero_llaves(int numero_llaves) {
        this.numero_llaves = numero_llaves;
    }

    public Llave[] getLlaves() {
        return llaves;
    }

    public void setLlaves(Llave[] llaves) {
        this.llaves = llaves;
    }

    public boolean isIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Nodo[] getHijos() {
        return hijos;
    }

    public void setHijos(Nodo[] hijos) {
        this.hijos = hijos;
    }
    
    
    
    
    
    
}
