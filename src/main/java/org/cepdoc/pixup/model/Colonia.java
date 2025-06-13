package org.cepdoc.pixup.model;

public class Colonia extends Catalogo {
    public String nombre;
    public String cp;
    public int idMunicipio;

    public Colonia() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    @Override
    public String toString() {
        return "Colonia{" +
                "nombre='" + nombre + '\'' +
                ", cp=" + cp +
                '}';
    }
}
