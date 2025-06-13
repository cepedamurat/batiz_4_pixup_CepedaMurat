package org.cepdoc.pixup.model;

import java.util.Date;

public class Orden extends Catalogo {
    private int idOrden;
    private float costoTotal;
    private Date fecha;
    private float cantidadTotal;
    private int idUsuario;

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public float getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(float costoTotal) {
        this.costoTotal = costoTotal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(float cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Orden{" +
                "idOrden=" + idOrden +
                ", costoTotal=" + costoTotal +
                ", fecha=" + fecha +
                ", cantidadTotal=" + cantidadTotal +
                ", idUsuario=" + idUsuario +
                '}';
    }
}