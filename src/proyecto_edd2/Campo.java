/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_edd2;

/**
 *
 * @author dalva
 */
public class Campo {

    private String nombreCampo;
    private String restriccion;
    private boolean esNumero;
    private boolean esLlavePrimaria;

    public Campo() {
    }

    public Campo(String nombreCampo, String restriccion, boolean esNumero, boolean esLlavePrimaria) {
        this.nombreCampo = nombreCampo;
        this.restriccion = restriccion;
        this.esNumero = esNumero;
        this.esLlavePrimaria = esLlavePrimaria;
    }


    public String getNombreCampo() {
        return nombreCampo;
    }

    public void setNombreCampo(String nombreCampo) {
        this.nombreCampo = nombreCampo;
    }

    public String getRestriccion() {
        return restriccion;
    }

    public void setRestriccion(String restriccion) {
        this.restriccion = restriccion;
    }

    public boolean isEsNumero() {
        return esNumero;
    }

    public void setEsNumero(boolean esNumero) {
        this.esNumero = esNumero;
    }

    @Override
    public String toString() {
        return "Campo: " + "nombreCampo: " + nombreCampo + " restriccion: " + restriccion + " esNumero=" + esNumero;
    }

    
}
