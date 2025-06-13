package org.cepdoc.pixup.gui.consola;

import org.cepdoc.pixup.gui.LecturaAccion;
import org.cepdoc.pixup.negocio.Ejecutable;

public class ListaCatalogos extends LecturaAccion
{
    public static ListaCatalogos listaCatalogos;
    private ListaCatalogos()
    {
    }
    public static ListaCatalogos getInstance( )
    {
        if(listaCatalogos==null)
        {
            listaCatalogos = new ListaCatalogos();
        }
        return listaCatalogos;
    }

    @Override
    public void despliegaMenu()
    {
        System.out.println( "Seleccione una opcion:" );
        System.out.println( "1.-Usuarios");
        System.out.println( "2.-Ordenes");
        System.out.println( "3.-Discos");
        System.out.println( "4.-Salir");
    }
    @Override
    public int valorMinMenu()
    {
        return 1;
    }

    @Override
    public int valorMaxMenu()
    {
        return 4;
    }

    @Override
    public void procesaOpcion()
    {
        Ejecutable ejecutable = null;
        switch (opcion)
        {
            case 1:
                ejecutable = UsuarioCatalogo.getInstance( );
                break;
            case 2:
                ejecutable = OrdenCatalogo.getInstance( );
                break;
            case 3:
                ejecutable = DiscoCatalogo.getInstance( );
                break;
        }
        ejecutable.setFlag( true );
        ejecutable.run( );

    }
}
