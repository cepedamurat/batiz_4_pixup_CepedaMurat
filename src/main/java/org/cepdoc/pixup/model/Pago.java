package org.cepdoc.pixup.model;

import java.util.Date;

public class Pago extends Catalogo {

    private Integer idPago;
    private String numeroTarjeta;
    private Date fechaPago;
    private float cantidadTotal;
    private Integer idOrden;

    public Pago() {
    }

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public float getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(float cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "idPago=" + idPago +
                ", numeroTarjeta='" + numeroTarjeta + '\'' +
                ", fechaPago=" + fechaPago +
                ", cantidadTotal=" + cantidadTotal +
                ", idOrden=" + idOrden +
                '}';
    }
    
}
