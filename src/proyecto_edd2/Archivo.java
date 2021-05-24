package proyecto_edd2;

import java.io.File;
import java.util.ArrayList;

public class Archivo {
    
    File archivo = null;
    String nombre_archivo;
    boolean guardado = false;
    ArrayList <Campo> lista_campos = new ArrayList();
    //Lista doblemente enlazada
    
    public Archivo(String path) {
        archivo = new File(path);
        nombre_archivo = "nuevo archivo";
    }
    //Archivo
    public File getArchivo() {
        return archivo;
    }
    public void setArchivo(String path) {
        archivo = null;
        this.archivo = new File(path);
    }
    
    //Nombre
    public String getNombre_archivo() {
        return nombre_archivo;
    }
    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }
    
    //Guardado
    public boolean isGuardado() {
        return guardado;
    }
    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }
    
    
    //Lista de Campos

    public ArrayList<Campo> getLista_campos() {
        return lista_campos;
    }

    public void setLista_campos(ArrayList<Campo> lista_campos) {
        this.lista_campos = lista_campos;
    }
    
    

    @Override
    public String toString() {
        return nombre_archivo;
    } 
}
