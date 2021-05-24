package proyecto_edd2;

import java.io.File;

public class Archivo {
    
    File archivo = null;
    String nombre_archivo;
    boolean guardado = false;
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

    public boolean isGuardado() {
        return guardado;
    }
    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }

    @Override
    public String toString() {
        return nombre_archivo;
    } 
}
