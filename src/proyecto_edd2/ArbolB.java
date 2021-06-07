
package proyecto_edd2;

/*
Jamil García 11911053
Daniel Alvarado 12011159
Miguel Rojas 11941201
*/

public class ArbolB {
    
    Nodo raiz;
    int T;// Grado del arbol
    
    //Constructor que inicializa los componentes del arbol b
    public ArbolB(int T) {
        this.T = T;
        raiz = new Nodo(T);
    }
    
    
    public boolean buscarLlave(Nodo x, int llave){
        //Contador utilizado para evaluar indice de llaves y nodos hijo
        int contador = 0;
        //variable de nodo temporal
        Nodo temp = x;
        
        //Ciclo while que evalua si el indice es menor al numero de llaves
        //y si la llave es mayor que la llave del indice del contador
                
        while(contador < temp.getNumero_llaves() &&  llave > temp.getLlaves()[contador].getLlave()){
            //incremento al contador
            contador = contador + 1;
        }
        
        //si el contador es menor que el numero de llaves y la llave 
        //del indice es igual a la llave a buscar entonces devuelve true
        if(contador < temp.getNumero_llaves() && llave == temp.getLlaves()[contador].getLlave()){
            return true;
        }
        
        //si en caso el nodo es hoja significa que la llave no existe y devuelve false
        if(temp.isLeaf){
            return false;
        } else {
            //caso recursivo que evaluara las llaves del hijo del nodo actual
            buscarLlave(x.getHijos()[contador], llave);
        }
        
        return false; 
    }
    
    
    public void insert(Llave llave_env){
        //variable temporal que almacena la raiz
        Nodo r = raiz;
        
        //evalua si el nodo a realizar la insercion se encuentra lleno
        if(r.getNumero_llaves() == ((2*T) - 1) ){
            //nueva variable nodo donde la llave promovida se almacenara
            Nodo s = new Nodo(T);
            raiz = s;
            s.setIsLeaf(false);
            s.setNumero_llaves(0);
            s.hijos[0] = r;
            //llamado a la funcion Split
            split(s, 0 ,r);
            //luego de el split se llama a esta funcion para insertar el nodo
            insert_nonFull(s, llave_env);
        } else {
            //caso donde el nodo tiene espacio y la insercion se puede llevar a cabo
            insert_nonFull(r, llave_env);
        }
    }
    
    public void insert_nonFull(Nodo x, Llave llave_env){
        int i = x.getNumero_llaves();//Contador con el valor de numero de llaves
        
        //if donde se evalua si el nodo x es una llave
        if(x.isLeaf){
            
            //ciclo while que realiza corrimiento de llaves de derecha a izquierda
            //de acuerdo al valor de la llave a insertar
            while(i >= 1 && llave_env.getLlave() < x.getLlaves()[i - 1].getLlave()){
                x.getLlaves()[i] = x.getLlaves()[i - 1];
                i--;
            }
            //insercion de la llave y aumento de contador interno del nodo
            x.getLlaves()[i] = llave_env;
            x.numero_llaves++;
            
        } else {
            //Buscar posicion del hijo de un nodo interno
            int contador_hijo = 0;
            
            //ciclo while que posiciona el contador en el array para realizar la insercion
            while(contador_hijo < x.getNumero_llaves() && llave_env.getLlave() > x.getLlaves()[contador_hijo].getLlave()){
                contador_hijo++;
            }
            
            //Evalua si el nodo esta lleno para aplicar split
            if(x.getHijos()[contador_hijo].getNumero_llaves() ==  ( 2 * T - 1)){
                //split
                split(x,contador_hijo , x.hijos[0]);
                
                //aumenta el contador si la llave en posicion contador hijo
                //es menor a la llave a insertar
                if(llave_env.getLlave() > x.getLlaves()[contador_hijo].getLlave()){
                    contador_hijo++;
                }
            }
            //llamado a metodo recursivo para insertar la llave en un nodo hoja
            insert_nonFull(x.getHijos()[contador_hijo], llave_env);  
        }
    }
    
    
    public void split(Nodo x, int i, Nodo y){
        Nodo z = new Nodo(T);// Nodo temporal hijo i + 1 de x
        z.isLeaf = y.isLeaf;
        z.setNumero_llaves(T - 1);
        
        //asignacion de llaves a nodo z empezando desde T-1
        for (int j = 0; j < (T - 1); j++) {
            z.llaves[j] = y.llaves[j + T];
        }
        
        //evalua si el nodo no es hoja
        if(!y.isLeaf){
            //si no es hoja asigna las llaves a z desde la posicion k hasta k + T
            for (int k = 0; k < T; k++) {
                z.hijos[k] = y.hijos [ k + T];
            }
        }
        
        //seteo de numero de llaves en nodo y ya que algunas llaves
        //fueron asignadas al nodo z
        y.setNumero_llaves( T - 1);
        
        //ciclo for que realiza corrimientos de llaves izquierda a derecha
        //de acuerdo al numero de llaves en el nodo x
        for (int j = x.getNumero_llaves(); j > i; j--) {
            x.hijos[j + 1] = x.hijos[j];
        }
        
        //asigna al nodo z hijo de x en la posicion i + 1
        x.hijos[i + 1] = z;
        
        //ciclo for que realiza corrimiento de llaves de izquierda a derecha
        //de acuerdo al numero de llaves en x
        for (int j = x.getNumero_llaves(); j > i ; j--) {
            x.llaves[( j + 1)] = x.llaves[j];
        }
        
        //asigna la llave promovida a x que se encuentra en la posicion T-1 de y
        x.llaves[i] = y.llaves[(T - 1)];
        //aumento en el numero de llaves de x
        x.numero_llaves++;
    }
    
}
