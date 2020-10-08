package database;

import android.content.Context;
import android.database.Cursor;

import modelo.Empresa;
import modelo.Producto;

/**
 * Created by jhonyrenteria on 25/05/17.
 */

public class DataProducto {
    private Producto producto = null;
    private Context context = null;

    public DataProducto(Context context) {
        this.context = context;
    }

    private Producto[] getProducto(){
        GetConexion conn = new GetConexion(context);
        Producto[] data_producto = null;
        String sql = "SELECT codigo,nombre,empresa,foto from producto";
        Cursor cursor = conn.getDB().rawQuery(sql,null);
        if(cursor.moveToFirst()){
            data_producto = new Producto[cursor.getCount()];
            do{
                producto = new Producto(cursor.getInt(0),cursor.getString(1),
                        cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getFloat(5));
                data_producto[cursor.getPosition()] = producto;
            }while (cursor.moveToFirst());
        }
        conn.getDB().close();
        return data_producto;
    }

    private Producto[] getProducto(int codigo){
        GetConexion conn = new GetConexion(context);
        Producto[] data_producto = null;
        String sql = "SELECT codigo,nombre,empresa,foto,precio from producto where codigo ="+codigo;
        Cursor cursor = conn.getDB().rawQuery(sql,null);
        if(cursor.moveToFirst()){
            data_producto = new Producto[cursor.getCount()];
            do{
                producto = new Producto(cursor.getInt(0),cursor.getString(1),
                        cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getFloat(5));
                data_producto[cursor.getPosition()] = producto;
            }while (cursor.moveToFirst());
        }
        conn.getDB().close();
        return data_producto;
    }

    public boolean crearProducto(Producto p){
        boolean bandera = false;
        GetConexion conn = new GetConexion(context);
        String sql = "INSERT INTO producto(nombre,empresa,foto) VALUES('"+p.getNombre()+
                "',"+p.getEmpresa()+",'"+p.getFoto()+"')";
        conn.getDB().execSQL(sql);
        if(conn.getDB().isDatabaseIntegrityOk()){
            conn.getDB().close();
            bandera = true;
        }

        return bandera;
    }

    public String[][] getListaProducto(){
        GetConexion con = new GetConexion(context);
        Cursor c = con.getDB().rawQuery("select p.nombre,p.descripcion,e.nombre,p.foto from producto p" +
                " left join empresa e on(p.empresa = e.codigo)", null);
        String[][] datos = new String[c.getCount()][3];
        int i = 0;
        if (c.getCount() >= 1) {
            if (c.moveToFirst()) {
                do {
                    datos[i][0] = c.getString(0);
                    datos[i][1] = c.getString(1);
                    datos[i][2] = c.getString(2);
                    i++;
                } while (c.moveToNext());
            }
        }
        c.close();
        con.getDB().close();
        return datos;
    }
}
