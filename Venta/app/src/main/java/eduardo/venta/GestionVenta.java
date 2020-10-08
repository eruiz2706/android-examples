package eduardo.venta;

import android.provider.Settings;

import java.util.ArrayList;

/**
 * Created by Eduardo on 06/03/2017.
 */

public class GestionVenta {
    Venta venta;
    static ArrayList<Venta>gestion  =new ArrayList<Venta>();
    String[] lista;
    String  cedula;
    String  nombres;
    String  direccion;
    String  telefono;
    double  subtotal;
    double  iva=0.19;
    double  valoriva;
    double  bono;
    double  descuento;
    double  total;

    public void agregarVta(String  cccliente,String  nombreclient,String  telefono,String  direccion,double  valorbruto,double  bono){
        venta      =new Venta(cccliente,nombreclient,telefono,direccion,valorbruto,bono);
        gestion.add(venta);
    }

    public String[] getventas(){
        int sizelist    =gestion.size();
        lista           =new String[sizelist];

        for(int i=0;i<sizelist;i++){
            cedula      =gestion.get(i).getCccliente();
            nombres     =gestion.get(i).getNombreclient();
            direccion   =gestion.get(i).getDireccion();
            telefono    =gestion.get(i).getTelefono();
            subtotal    =gestion.get(i).getValorbruto();
            bono        =gestion.get(i).getBono()/100;
            valoriva    =subtotal*iva;
            descuento   =subtotal*bono;
            total       =subtotal-descuento;

            lista[i]    ="Cedula :"+cedula+"\nNombres:"+nombres+"\nDireccion:"+direccion+"\nTelefono:"+telefono+"\nSubtotal:"+subtotal+"\nIva(19%):"+valoriva+"\nDescuento:"+descuento+"\nTotal a Pagar:"+total;
            System.out.println(lista[i]);
        }

        return lista;
    }
}
