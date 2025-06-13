package org.cepdoc.pixup.model;

public class Disquera extends Catalogo {
    
    private Integer id;
    private String nombre;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Disquera{id=" + id + ", nombre='" + nombre + "'}";
    }
}