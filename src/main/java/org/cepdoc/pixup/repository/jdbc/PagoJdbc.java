package org.cepdoc.pixup.repository.jdbc;

import org.cepdoc.pixup.model.Pago;
import java.util.List;

public interface PagoJdbc {
    List<Pago> findAll();
    Pago findById(int id);
    List<Pago> findByNumeroTarjeta(String numeroTarjeta);
    int insert(Pago pago);

}