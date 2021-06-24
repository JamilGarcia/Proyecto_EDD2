package proyecto_edd2;

import java.io.Serializable;
import java.util.ArrayList;

public class ArbolB implements Serializable {

    Nodo raiz;
    int T;// Grado del arbol

    //Constructor que inicializa los componentes del arbol b
    public ArbolB(int T) {
        this.T = T;
        raiz = new Nodo(T);
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public int getT() {
        return T;
    }

    public void setT(int T) {
        this.T = T;
    }

    public Llave buscarLlave(Nodo x, int llave) {
        //Contador utilizado para evaluar indice de llaves y nodos hijo
        int contador = 0;
        Nodo temp = x;
        //Ciclo while que evalua si el indice es menor al numero de llaves
        //y si la llave es mayor que la llave del indice del contador

        while (contador < temp.getNumero_llaves() && llave > temp.getLlaves()[contador].getLlave()) {
            //incremento al contador
            contador = contador + 1;
        }

        //si el contador es menor que el numero de llaves y la llave 
        //del indice es igual a la llave a buscar entonces devuelve true
        if (contador < temp.getNumero_llaves() && llave == temp.getLlaves()[contador].getLlave()) {
            return temp.getLlaves()[contador];
        }

        //si en caso el nodo es hoja significa que la llave no existe y devuelve false
        if (temp.isLeaf) {
            return null;
        } else {
            //caso recursivo que evaluara las llaves del hijo del nodo actual
            return buscarLlave(x.getHijos()[contador], llave);
        }

    }

    public void insert(Llave llave_env) {
        Nodo r = raiz;

        //evalua si el nodo a realizar la insercion se encuentra lleno
        if (r.getNumero_llaves() == ((2 * T) - 1)) {
            //nueva variable nodo donde la llave promovida se almacenara
            Nodo s = new Nodo(T);
            raiz = s;
            s.setIsLeaf(false);
            s.setNumero_llaves(0);
            s.hijos[0] = r;
            split(s, 0, r);
            //luego de el split se llama a esta funcion para insertar el nodo
            insert_nonFull(s, llave_env);
        } else {
            //caso donde el nodo tiene espacio y la insercion se puede llevar a cabo
            insert_nonFull(r, llave_env);
        }
    }

    public void insert_nonFull(Nodo x, Llave llave_env) {
        //int i = x.getNumero_llaves();//Contador con el valor de numero de llaves
        int i = 0;

        if (x.isLeaf) {

            i = x.getNumero_llaves();
            //ciclo while que realiza corrimiento de llaves de derecha a izquierda
            //de acuerdo al valor de la llave a insertar
            while (i >= 1 && llave_env.getLlave() < x.getLlaves()[i - 1].getLlave()) {
                x.getLlaves()[i] = x.getLlaves()[i - 1];
                i--;
            }
            x.getLlaves()[i] = llave_env;
            x.numero_llaves++;

        } else {
            //Buscar posicion del hijo de un nodo interno
            //int contador_hijo = 0;

            //ciclo while que posiciona el contador en el array para realizar la insercion
//            while (contador_hijo < x.getNumero_llaves() && llave_env.getLlave() > x.getLlaves()[contador_hijo].getLlave()) {
//                System.out.println(x.getLlaves()[contador_hijo].getLlave());
//                System.out.println(contador_hijo);
//                System.out.println(x.getNumero_llaves());
//                System.out.println(x.getHijos().length);
//                contador_hijo++;
//            }
            for (i = x.getNumero_llaves() - 1; i >= 0 && llave_env.getLlave() < x.getLlaves()[i].getLlave(); i--) {
                //ciclo que mueve a i
            }

            i++;

            //Evalua si el nodo esta lleno para aplicar split
            if (x.getHijos()[i].getNumero_llaves() == (2 * T - 1)) {
                split(x, i, x.hijos[i]);

                //aumenta el contador si la llave en posicion contador hijo
                //es menor a la llave a insertar
                if (llave_env.getLlave() > x.getLlaves()[i].getLlave()) {
                    i++;
                }
            }
            insert_nonFull(x.getHijos()[i], llave_env);
        }
    }

    public void split(Nodo x, int i, Nodo y) {
        Nodo z = new Nodo(T);// Nodo temporal hijo i + 1 de x
        z.isLeaf = y.isLeaf;
        z.setNumero_llaves(T - 1);

        //asignacion de llaves a nodo z empezando desde T-1
        for (int j = 0; j < (T - 1); j++) {
            z.llaves[j] = y.llaves[j + T];
        }

        //evalua si el nodo no es hoja
        if (!y.isLeaf) {
            //si no es hoja asigna las llaves a z desde la posicion k hasta k + T
            for (int k = 0; k < T; k++) {
                z.hijos[k] = y.hijos[k + T];
            }
        }

        //seteo de numero de llaves en nodo y ya que algunas llaves
        //fueron asignadas al nodo z
        y.setNumero_llaves(T - 1);

        //ciclo for que realiza corrimientos de llaves izquierda a derecha
        //de acuerdo al numero de llaves en el nodo x
        for (int j = x.getNumero_llaves(); j >= (i + 1); j--) {
            x.hijos[j + 1] = x.hijos[j];
        }

        //asigna al nodo z hijo de x en la posicion i + 1
        x.hijos[i + 1] = z;

        //ciclo for que realiza corrimiento de llaves de izquierda a derecha
        //de acuerdo al numero de llaves en x
        for (int j = x.getNumero_llaves() - 1; j >= i; j--) {
            x.llaves[(j + 1)] = x.llaves[j];
        }

        //asigna la llave promovida a x que se encuentra en la posicion T-1 de y
        x.llaves[i] = y.llaves[(T - 1)];
        //aumento en el numero de llaves de x
        x.numero_llaves++;
    }

    public boolean eliminar(Nodo x, Llave llave) {

        //encuentra el indice de la llave a eliminar adentro del nodo
        int indice = x.indice(llave);
        //verifica si el nodo es una hoja
        if (indice != -1) {
            if (x.isLeaf) {
                //si el indice de la llave se ubica al final al reemplaza con un valor nulo
                if (indice == x.getLlaves().length - 1) {
                    x.getLlaves()[indice] = null;
                    x.setNumero_llaves(x.getNumero_llaves() - 1);
                    return true;
                } else {

                    //si el valor esta dentro del arreglo hace un corrimiento
                    //eliminando la llave en el proceso
                    for (int j = indice; j < x.getLlaves().length - 1; j++) {
                        x.getLlaves()[j] = x.getLlaves()[j + 1];
                    }
                    x.setNumero_llaves(x.getNumero_llaves() - 1);
                    return true;
                }
            }

            //caso # 2
            if (!x.isLeaf) {
                //Caso donde hijo predecesor tiene T llaves
                //declaracion de un nuevo nodo
                Nodo predecesor = x.hijos[indice];
                //declaracion de llave predecesora
                Llave llavePredecesora;
                //valida que el nodo tenga arriba del minimo de llaves
                if (predecesor.getNumero_llaves() >= T) {

                    //busca en un for infinito la llave predecesora
                    for (;;) {

                        if (predecesor.isLeaf) {
                            llavePredecesora = predecesor.llaves[predecesor.getNumero_llaves() - 1];
                            break;

                        } else {
                            predecesor = predecesor.hijos[predecesor.numero_llaves];
                        }
                    }

                    //llamado recursivo al metodo de eliminar hasta dar al caso base
                    eliminar(predecesor, llavePredecesora);
                    x.llaves[indice] = llavePredecesora;
                    return true;
                }

                //caso donde hijo sucesor tiene T llaves
                //declaracion de un nodo sucesor
                Nodo sucesor = x.hijos[indice + 1];

                //verifica que el nodo sucesor tenga mas del minimo de llaves
                if (sucesor.numero_llaves >= T) {

                    Llave llaveSucesora = sucesor.llaves[0];

                    //verifica si el nodo sucesor es hoja
                    if (!sucesor.isLeaf) {

                        sucesor = sucesor.hijos[0];

                        //for busca la llave sucesora dentro de los nodos sucesor
                        for (;;) {

                            //valida si el nodo sucesor es hoja
                            if (sucesor.isLeaf) {
                                llaveSucesora = sucesor.llaves[sucesor.numero_llaves - 1];
                                break;

                            } else {
                                sucesor = sucesor.hijos[sucesor.numero_llaves];
                            }
                        }
                    }

                    //llamada recursiva a la funcion eliminar
                    eliminar(sucesor, llaveSucesora);
                    x.llaves[indice] = llaveSucesora;
                }

                //Caso #3 Merges y mas
                int varTemporal = predecesor.numero_llaves + 1;
                predecesor.llaves[predecesor.numero_llaves++] = x.llaves[indice];

                for (int i = 0, j = predecesor.numero_llaves; i < sucesor.numero_llaves; i++) {
                    predecesor.llaves[j + 1] = sucesor.llaves[i];
                    predecesor.numero_llaves++;
                }

                for (int i = 0; i < sucesor.numero_llaves + 1; i++) {
                    predecesor.hijos[varTemporal+ 1] = sucesor.hijos[i];
                }

                x.hijos[indice] = predecesor;

                //corrimiento de llaves
                for (int i = indice; i < x.numero_llaves; i++) {

                    //si i es diferente que el numero que el grado menos 2
                    if (i != (2 * T - 2)) {
                        x.llaves[i] = x.llaves[i + 1];
                    }
                }

                //corrimiento en el arreglo de hijos
                for (int i = indice + 1; i < x.numero_llaves + 1; i++) {

                    if (i != (2 * T - 1)) {
                        x.hijos[i] = x.hijos[i + 1];
                    }
                }

                x.numero_llaves--;

                if (x.numero_llaves == 0) {

                    if (x == raiz) {
                        raiz = x.hijos[0];
                    }

                    x = x.hijos[0];
                }

                eliminar(predecesor, llave);
                return true;

            }
        } else {
            //caso donde no encuentra la posicion de la llave a insertar
            for (indice = 0; indice < x.numero_llaves; indice++) {

                if (x.llaves[indice].llave > llave.llave) {
                    break;
                }
            }

            Nodo temporal = x.hijos[indice];

            if (temporal.numero_llaves >= T) {
                eliminar(temporal, llave);
                return true;
            }

            if (true) {
                Nodo newNode = null;
                Llave divisor;

                if (indice != x.numero_llaves && x.hijos[indice + 1].numero_llaves >= T) {

                    divisor = x.llaves[indice];
                    newNode = x.hijos[indice + 1];

                    x.llaves[indice] = newNode.llaves[0];

                    temporal.llaves[temporal.numero_llaves++] = divisor;
                    temporal.hijos[temporal.numero_llaves] = newNode.hijos[0];

                    for (int i = 1; i < newNode.numero_llaves; i++) {
                        newNode.llaves[i - 1] = newNode.llaves[i];
                    }

                    for (int i = 1; i <= newNode.numero_llaves; i++) {
                        newNode.hijos[i - 1] = newNode.hijos[i];
                    }

                    newNode.numero_llaves--;
                    eliminar(temporal, llave);
                    return true;

                } else if (indice != 0 && x.hijos[indice - 1].numero_llaves >= T) {

                    divisor = x.llaves[indice - 1];

                    newNode = x.hijos[indice - 1];

                    x.llaves[indice - 1] = newNode.llaves[newNode.numero_llaves - 1];

                    Nodo hijo = newNode.hijos[newNode.numero_llaves];
                    newNode.numero_llaves--;

                    for (int i = temporal.numero_llaves; i > 0; i--) {
                        temporal.llaves[i] = temporal.llaves[i - 1];
                    }

                    temporal.llaves[0] = divisor;

                    for (int i = temporal.numero_llaves + 1; i > 0; i--) {
                        temporal.hijos[i] = temporal.hijos[i - 1];
                    }

                    temporal.hijos[0] = hijo;

                    temporal.numero_llaves++;
                    eliminar(temporal, llave);

                } else {
                    Nodo nodoIzquierdo = null;
                    Nodo nodoDerecho = null;
                    boolean ultimo = false;

                    if (indice != x.numero_llaves) {
                        divisor = x.llaves[indice];
                        nodoIzquierdo = x.hijos[indice];
                        nodoDerecho = x.hijos[indice + 1];

                    } else {
                        divisor = x.llaves[indice - 1];
                        nodoDerecho = x.hijos[indice];
                        nodoIzquierdo = x.hijos[indice - 1];
                        ultimo = true;
                        indice--;
                    }

                    for (int i = indice; i < x.numero_llaves - 1; i++) {
                        x.llaves[i] = x.llaves[i + 1];
                    }

                    for (int i = indice + 1; i < x.numero_llaves; i++) {
                        x.hijos[i] = x.hijos[i + 1];
                    }

                    x.numero_llaves--;

                    nodoIzquierdo.llaves[nodoIzquierdo.numero_llaves++] = divisor;

                    for (int i = 0, j = nodoIzquierdo.numero_llaves; i < nodoDerecho.numero_llaves + 1; i++, j++) {

                        if (i < nodoDerecho.numero_llaves) {
                            nodoIzquierdo.llaves[j] = nodoDerecho.llaves[i];
                        }
                        nodoIzquierdo.hijos[j] = nodoDerecho.hijos[i];
                    }

                    nodoIzquierdo.numero_llaves += nodoDerecho.numero_llaves;

                    if (x.numero_llaves == 0) {
                        if (x == raiz) {
                            raiz = x.hijos[0];
                        }
                        x = x.hijos[0];
                    }

                    eliminar(nodoIzquierdo, llave);
                    return true;

                }
            }
        }
        return false;
    }

    public Nodo searchDeleteNode(Nodo x, int llave) {
        //Contador utilizado para evaluar indice de llaves y nodos hijo
        int contador = 0;
        Nodo temp = x;
        //Ciclo while que evalua si el indice es menor al numero de llaves
        //y si la llave es mayor que la llave del indice del contador
        while (contador < temp.getNumero_llaves() && llave > temp.getLlaves()[contador].getLlave()) {
            //incremento al contador
            contador = contador + 1;
        }
        //si el contador es menor que el numero de llaves y la llave 
        //del indice es igual a la llave a buscar entonces devuelve true
        if (contador < temp.getNumero_llaves() && llave == temp.getLlaves()[contador].getLlave()) {
            return temp;
        }
        //si en caso el nodo es hoja significa que la llave no existe y devuelve false
        if (temp.isLeaf) {
            return null;
        } else {
            //caso recursivo que evaluara las llaves del hijo del nodo actual
            return searchDeleteNode(x.getHijos()[contador], llave);
        }
    }

    public void getRegistersOffsets(ArrayList<Object> offsets, Nodo x, int contador) {

        int i = 0;

        for (i = 0; i < x.numero_llaves; i++) {

            if (!x.isLeaf) {
                getRegistersOffsets(offsets, x.getHijos()[i], contador);
            }

            offsets.add(x.getLlaves()[i].getOffset());
        }

        if (!x.isLeaf) {
            getRegistersOffsets(offsets, x.getHijos()[i], contador);
        }
    }

    public int retornarUnaLlave() {

        if (raiz.getNumero_llaves() >= 1) {

            return 0;

        } else {
            return raiz.getLlaves()[0].getLlave();
        }
    }

}
