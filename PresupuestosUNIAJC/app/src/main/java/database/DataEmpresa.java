package database;

import android.content.Context;
import android.database.Cursor;

import modelo.Empresa;

/**
 * Created by jhonyrenteria on 22/05/17.
 */

public class DataEmpresa {
    private Empresa empresa = null;
    private Context context = null;

    public DataEmpresa(Context context) {
        this.context = context;
    }
    
    public Empresa[] getEmpresa(){
        GetConexion conn = new GetConexion(context);
        Empresa[] data_empresa = null;
        String sql = "SELECT codigo,nit,nombre,razons,sector,numempleados,descripcion from empresa";
        Cursor cursor = conn.getDB().rawQuery(sql,null);
        if(cursor.moveToFirst()){
            data_empresa = new Empresa[cursor.getCount()];
            do{
                empresa = new Empresa(cursor.getInt(0),cursor.getString(1),
                                      cursor.getString(2),cursor.getString(3),
                                      cursor.getString(4),cursor.getInt(5),cursor.getString(6));
                data_empresa[cursor.getPosition()] = empresa;
            }while (cursor.moveToFirst());
        }
        conn.getDB().close();
        return data_empresa;
    }

    private Empresa getEmpresa(int codigo){
        GetConexion conn = new GetConexion(context);
        Empresa data_empresa = null;
        String sql = "SELECT codigo,nit,nombre,razons,sector,numempleados,descripcion from empresa where codigo = '"+codigo+"'";
        Cursor cursor = conn.getDB().rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                data_empresa = new Empresa(cursor.getInt(0),cursor.getString(1),
                        cursor.getString(2),cursor.getString(3),
                        cursor.getString(4),cursor.getInt(5),cursor.getString(6));

            }while (cursor.moveToFirst());
        }
        conn.getDB().close();
        return data_empresa;
    }

    public boolean crearEmpresa(Empresa e){
        boolean bandera = false;
        GetConexion conn = new GetConexion(context);
        Empresa data_empresa = null;
        String sql = "INSERT INTO empresa(nit,nombre,razons,sector,numempleados,descripcion) VALUES('"+e.getNit()+
                "','"+e.getNombre()+"','"+e.getRazonSocial()+"','"+e.getSector()+"',"+e.getNumEmpleados()+",'"+e.getDescripcion()+"')";
        conn.getDB().execSQL(sql);
        if(conn.getDB().isDatabaseIntegrityOk()){
            conn.getDB().close();
            bandera = true;
        }

        return bandera;
    }

    public String[][] getEmpresas(){
        GetConexion con = new GetConexion(context);
        Cursor c = con.getDB().rawQuery("select nit,nombre,sector from empresa", null);
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
