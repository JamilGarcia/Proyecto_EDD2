
package proyecto_edd2;

public class ArbolB {
    
    Nodo raiz;
    int T;// Grado del arbol

    public ArbolB(int T) {
        this.T = T;
        raiz = new Nodo(T);
    }
    
    
    public boolean buscarLlave(Nodo x, int llave){
        int contador = 0;
        Nodo temp = x;
        
        while(contador < temp.getNumero_llaves() &&  llave > temp.getLlaves()[contador].getLlave()){
            contador = contador + 1;
        }
        
        if(contador < temp.getNumero_llaves() && llave == temp.getLlaves()[contador].getLlave()){
            return true;
        }
        
        if(temp.isLeaf){
            return false;
        } else {
            buscarLlave(x.getHijos()[contador], llave);
        }
        
        return false;
    }
    
    
    public void insert(Llave llave_env){
        Nodo r = raiz;
        if(r.getNumero_llaves() == ((2*T) - 1) ){
            Nodo s = new Nodo(T);
            raiz = s;
            s.setIsLeaf(false);
            s.setNumero_llaves(0);
            s.hijos[0] = r;
            //Split
            split(s, 0 ,r);
            insert_nonFull(s, llave_env);
        } else {
            insert_nonFull(r, llave_env);
        }
    }
    
    public void insert_nonFull(Nodo x, Llave llave_env){
        int i = x.getNumero_llaves();//Contador con el valor de numero de llaves
        
        if(x.isLeaf){
            while(i >= 1 && llave_env.getLlave() < x.getLlaves()[i - 1].getLlave()){
                x.getLlaves()[i] = x.getLlaves()[i - 1];
                i--;
            }
            x.getLlaves()[i] = llave_env;
            x.numero_llaves++;
            
        } else {
            //Buscar posicion del hijo de un nodo interno
            int contador_hijo = 0;
            
            while(contador_hijo < x.getNumero_llaves() && llave_env.getLlave() > x.getLlaves()[contador_hijo].getLlave()){
                contador_hijo ++;
            }
            
            if(x.getHijos()[contador_hijo].getNumero_llaves() ==  ( 2 * T - 1)){
                //split
                split(x,contador_hijo , x.hijos[0]);
                if(llave_env.getLlave() > x.getLlaves()[contador_hijo].getLlave()){
                    contador_hijo++;
                }
            }     
            insert_nonFull(x.getHijos()[contador_hijo], llave_env);  
        }
    }
    
    
    public void split(Nodo x, int i, Nodo y){
        Nodo z = new Nodo(T);// Nodo temporal hijo i + 1 de x
        z.isLeaf = y.isLeaf;
        z.setNumero_llaves(T - 1);
        
        for (int j = 0; j < (T - 1); j++) {
            z.llaves[j] = y.llaves[j + T];
        }
        
        if(!y.isLeaf){
            for (int k = 0; k < T; k++) {
                z.hijos[k] = y.hijos [ k + T];
            }
        }
        y.setNumero_llaves( T - 1);
        
        
        for (int j = x.getNumero_llaves(); j > i; j--) {
            x.hijos[j + 1] = x.hijos[j];
        }
        
        x.hijos[i + 1] = z;
        
        
        for (int j = x.getNumero_llaves(); j > i ; j--) {
            x.llaves[( j + 1)] = x.llaves[j];
        }
        
        x.llaves[i] = y.llaves[(T - 1)];
        x.numero_llaves++;
    }
    
}
