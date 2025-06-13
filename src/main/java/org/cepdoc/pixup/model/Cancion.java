package org.cepdoc.pixup.model;

public class Cancion extends Catalogo{
    private int idCancion;
    private String titulo;
    private int duracion; // en segundos
    private int idDisco;

    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "idCancion=" + idCancion +
                ", titulo='" + titulo + '\'' +
                ", duracion=" + duracion +
                ", idDisco=" + idDisco +
                '}';
    }

}