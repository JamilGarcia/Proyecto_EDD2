
package proyecto_edd2;

public class Campo {

    private String nombre_Campo;
    private String tipo_dato;
    private int longitud = 0;
    private boolean esLlavePrimaria = false;

    public Campo() {
    }

    public Campo(String nombre_Campo, String tipo_dato, int longitud) {
        this.nombre_Campo = nombre_Campo;
        this.tipo_dato = tipo_dato;
        this.longitud = longitud;
    }
    
    //Nombre de Campo
    public String getNombre_Campo() {
        return nombre_Campo;
    }
    public void setNombre_Campo(String nombre_Campo) {
        this.nombre_Campo = nombre_Campo;
    }

    //Tipo de Dato
    public String getTipo_dato() {
        return tipo_dato;
    }
    public void setTipo_dato(String tipo_dato) {
        this.tipo_dato = tipo_dato;
    }

    //Longitud
    public int getLongitud() {
        return longitud;
    }
    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    //Es Llave Primaria
    public boolean isEsLlavePrimaria() {
        return esLlavePrimaria;
    }
    public void setEsLlavePrimaria(boolean esLlavePrimaria) {
        this.esLlavePrimaria = esLlavePrimaria;
    }

    @Override
    public String toString() {
        return nombre_Campo;
    }
    
    
    
}
