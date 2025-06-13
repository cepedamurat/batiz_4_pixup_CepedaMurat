package org.cepdoc.pixup.gui.consola;

import org.cepdoc.pixup.model.Orden;
import org.cepdoc.pixup.model.Usuario;
import org.cepdoc.pixup.negocio.Ejecutable;
import org.cepdoc.pixup.repository.jdbc.impl.OrdenJdbcImpl;
import org.cepdoc.pixup.repository.jdbc.impl.UsuarioJdbcImpl;
import org.cepdoc.pixup.util.ReadUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class AgregarOrdenes implements Ejecutable {
    private static AgregarOrdenes instance;
    private boolean flag;

    private AgregarOrdenes() {}

    public static AgregarOrdenes getInstance() {
        if (instance == null) {
            instance = new AgregarOrdenes();
        }
        return instance;
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        
        System.out.println("Agrega el costo total de la orden:");
        float costoTotal = Float.parseFloat(ReadUtil.read());

        System.out.println("Agrega la fecha de la orden en formato (YYYY-MM-DD):");
        String fecha = ReadUtil.read();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha_formateada;
        try {
            fecha_formateada = dateFormat.parse(fecha);
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido. Por favor, usa el formato yyyy-MM-dd.");
            return;
        }

        System.out.println("Agrega la cantidad total de la orden:");
        int cantidadTotal = Integer.parseInt(ReadUtil.read());

        System.out.println("Ingresa el nombre del usuario:");
        String nombre = ReadUtil.read();
        Usuario usuario = UsuarioJdbcImpl.getInstance().findByName(nombre);
        if (usuario == null) {
            System.out.println("Usuario no encontrado. Por favor, crea el usuario primero.");
            return;
        }
        int idUsuario = usuario.getIdUsuario(); 

        // Crear una nueva orden
        Orden orden = new Orden();  
        orden.setCostoTotal(costoTotal);
        orden.setFecha(fecha_formateada);
        orden.setCantidadTotal(cantidadTotal);
        orden.setIdUsuario(idUsuario);
        int idOrden = OrdenJdbcImpl.getInstance().insert(orden);

    }
}