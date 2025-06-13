package org.cepdoc.pixup.gui.consola;

import org.cepdoc.pixup.model.Colonia;
import org.cepdoc.pixup.model.Domicilio;
import org.cepdoc.pixup.model.Estado;
import org.cepdoc.pixup.model.Municipio;
import org.cepdoc.pixup.model.Usuario;
import org.cepdoc.pixup.negocio.Ejecutable;
import org.cepdoc.pixup.repository.jdbc.UsuarioJdbc;
import org.cepdoc.pixup.repository.jdbc.impl.ColoniaJdbcImpl;
import org.cepdoc.pixup.repository.jdbc.impl.DomicilioJdbcImpl;
import org.cepdoc.pixup.repository.jdbc.impl.EstadoJdbcImpl;
import org.cepdoc.pixup.repository.jdbc.impl.MunicipioJdbcImpl;
import org.cepdoc.pixup.repository.jdbc.impl.UsuarioJdbcImpl;
import org.cepdoc.pixup.util.ReadUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class AgregarUsuario implements Ejecutable {
    private static AgregarUsuario instance;
    private boolean flag;

    private AgregarUsuario() {}

    public static AgregarUsuario getInstance() {
        if (instance == null) {
            instance = new AgregarUsuario();
        }
        return instance;
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        try  {
            System.out.println("Ingrese el nombre:");
            String nombre = ReadUtil.read();

            System.out.println("Ingrese el apellido paterno:");
            String apellidoPaterno = ReadUtil.read();
            System.out.println("Ingrese el apellido materno:");
            String apellidoMaterno = ReadUtil.read();

            System.out.println("Ingrese un correo:");
            String correo = ReadUtil.read();
            System.out.println("Ingrese una contraseña:");
            String contrasena = ReadUtil.read();

            System.out.println("Ingrese el RFC:");
            String rfc = ReadUtil.read();

            Usuario usuario = new Usuario();
            usuario.setNombreUsuario(nombre);
            usuario.setApellidoPaterno(apellidoPaterno);
            usuario.setApellidoMaterno(apellidoMaterno);
            usuario.setEmail(correo);
            usuario.setPassword(contrasena);
            usuario.setRfc(rfc);

            int id_usuario = UsuarioJdbcImpl.getInstance().insert(usuario);

            // Solicitar estado
            System.out.println("Ingrese el estado:");
            String nombre_estado = ReadUtil.read();
            int id_estado = 0;

            // Valida si existe en la base de datos y si no existe, agregalo
            Estado estado = EstadoJdbcImpl.getInstance().findByName(nombre_estado);
            if (estado != null) id_estado = estado.getId();
            else {
                estado = new Estado();
                estado.setNombre(nombre_estado);
                id_estado = EstadoJdbcImpl.getInstance().insert(estado);
            }

            // Solicita el municipio
            System.out.println("Ingrese el municipio:");
            String nombre_municipio = ReadUtil.read();
            int id_municipio = 0;
            // Valida si existe en la base de datos y si no existe, agregalo
            Municipio municipio = MunicipioJdbcImpl.getInstance().findByName(nombre_municipio);
            if (municipio != null) {
                id_municipio = municipio.getId();
            } else {
                municipio = new Municipio();
                municipio.setNombre(nombre_municipio);
                municipio.setIdEstado(id_estado);
                id_municipio = MunicipioJdbcImpl.getInstance().insert(municipio);
            }

            // Solicita la colonia
            System.out.println("Ingrese la colonia:");
            String nombre_colonia = ReadUtil.read();
            int id_colonia = 0;
            String cp = "";
            // Valida si existe en la base de datos y si no existe, agregala
            Colonia colonia = ColoniaJdbcImpl.getInstance().findByName(nombre_colonia);
            if (colonia != null) {
                id_colonia = colonia.getId();
                cp = colonia.getCp();
            } else {
                System.out.println("Ingrese el código postal:");
                cp = ReadUtil.read();
                colonia = new Colonia();
                colonia.setNombre(nombre_colonia);
                colonia.setCp(cp);
                colonia.setIdMunicipio(id_municipio);
                id_colonia = ColoniaJdbcImpl.getInstance().insert(colonia);
            }

            // Pide los datos del domicilio
            System.out.println("Ingrese la calle:");
            String calle = ReadUtil.read();
            System.out.println("Ingrese el número exterior:");
            int numero_exterior = ReadUtil.readInt();
            System.out.println("Ingrese el número interior (opcional, presione Enter para omitir):");
            String numero_interior_input = ReadUtil.read();
            Integer numero_interior = null;
            if (!numero_interior_input.isEmpty()) {
                numero_interior = Integer.parseInt(numero_interior_input);
            }
            // Crea el domicilio
            Domicilio domicilio = new Domicilio();
            domicilio.setCalle(calle);
            domicilio.setNum_exterior(numero_exterior);
            domicilio.setNum_interior(numero_interior);
            domicilio.setIdColonia(id_colonia);
            domicilio.setIdUsuario(id_usuario);
            
            // Inserta el domicilio
            int id_domicilio = DomicilioJdbcImpl.getInstance().insert(domicilio);


    
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        
    }
}