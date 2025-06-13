package org.cepdoc.pixup.model;

import java.util.Date;

public class Disco extends Catalogo {

    private Integer id;
    private String titulo;
    private float precio;
    private int existencia;
    private float descuento;
    private Date fechaLanzamiento;
    private String imagen;
    private int idArtista;
    private int idDisquera;
    private int idGeneroMusical;

    public Disco() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public int getIdDisquera() {
        return idDisquera;
    }

    public void setIdDisquera(int idDisquera) {
        this.idDisquera = idDisquera;
    }

    public int getIdGeneroMusical() {
        return idGeneroMusical;
    }

    public void setIdGeneroMusical(int idGeneroMusical) {
        this.idGeneroMusical = idGeneroMusical;
    }

    @Override
    public String toString() {
        return "Disco{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", precio=" + precio +
                ", existencia=" + existencia +
                ", descuento=" + descuento +
                ", fechaLanzamiento=" + fechaLanzamiento +
                ", imagen='" + imagen + '\'' +
                '}';
    }
    
}
