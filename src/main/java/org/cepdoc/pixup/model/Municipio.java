package org.cepdoc.pixup.model;

public class Municipio extends Catalogo{
    public String nombre;
    public int idEstado;

    public Municipio() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public String toString() {
        return "Municipio{" +
                "nombre='" + nombre + '\'' +
                ", idEstado=" + idEstado +
                ", id=" + id +
                '}';
    }
}
