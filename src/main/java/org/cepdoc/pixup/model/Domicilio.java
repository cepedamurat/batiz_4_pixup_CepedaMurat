package org.cepdoc.pixup.model;

public class Domicilio extends Catalogo{

    public String calle;
    public int num_interior;
    public int num_exterior;
    public int idUsuario;
    public int idColonia;


    public Domicilio() {
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNum_exterior() {
        return num_exterior;
    }

    public void setNum_exterior(int num_exterior) {
        this.num_exterior = num_exterior;
    }

    public int getNum_interior() {
        return num_interior;
    }

    public void setNum_interior(int num_interior) {
        this.num_interior = num_interior;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(int idColonia) {
        this.idColonia = idColonia;
    }

    @Override
    public String toString() {
        return "Domicilio{" +
                "calle='" + calle + '\'' +
                ", num_interior=" + num_interior +
                ", num_exterior=" + num_exterior +
                ", idUsuario=" + idUsuario +
                ", idColonia=" + idColonia +
                '}';
    }
}
