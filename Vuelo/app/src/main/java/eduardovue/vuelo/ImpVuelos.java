package eduardovue.vuelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import eduardovue.vuelo.db.DbHelper;

/**
 * Created by Eduardo on 05/06/2017.
 */

public class ImpVuelos {

    private Context context;
    private String table="vuelos";

    public ImpVuelos(Context context) {
        this.context = context;
    }


    public boolean register(ContentValues campos){
        try {
            DbHelper conexion   =new DbHelper(context,null,null,1);
            SQLiteDatabase bd   =conexion.getWritableDatabase();;

            bd.insert(table, null, campos);
            bd.close();
            System.out.println("vuelo creado");
            return true;
        }catch (Exception e){
            System.out.println("error vuelo=>"+e.getMessage());
            return false;
        }

    }

    public ArrayList<Vuelo> findAll() {
        ArrayList<Vuelo> items = new ArrayList<>();

        try {
            DbHelper conexion = new DbHelper(context,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            Cursor cursor = db.rawQuery("select id,origen,destino,fechaida,fechadestino,estado from "+table,null);

            if (cursor.moveToFirst()) {
                do {
                    int id              =Integer.parseInt(cursor.getString(0));
                    String origen       =cursor.getString(1);
                    String destino      =cursor.getString(2);
                    String fechaida     =cursor.getString(3);
                    String fechadestino =cursor.getString(4);
                    String estado       =cursor.getString(5);

                    Vuelo  vuelo= new Vuelo();
                    vuelo.setId(id);
                    vuelo.setOrigen(origen);
                    vuelo.setDestino(destino);
                    vuelo.setFechaorigen(fechaida);
                    vuelo.setFechadestino(fechadestino);
                    vuelo.setEstado(estado);
                    items.add(vuelo);

                } while(cursor.moveToNext());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return items;
    }

    public ArrayList<Vuelo> findAllPendientes() {
        ArrayList<Vuelo> items = new ArrayList<>();

        try {
            DbHelper conexion = new DbHelper(context,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            Cursor cursor = db.rawQuery("select id,origen,destino,fechaida,fechadestino,estado from "+table+" where estado='pendiente'",null);

            if (cursor.moveToFirst()) {
                do {
                    int id              =Integer.parseInt(cursor.getString(0));
                    String origen       =cursor.getString(1);
                    String destino      =cursor.getString(2);
                    String fechaida     =cursor.getString(3);
                    String fechadestino =cursor.getString(4);
                    String estado       =cursor.getString(5);

                    Vuelo  vuelo= new Vuelo();
                    vuelo.setId(id);
                    vuelo.setOrigen(origen);
                    vuelo.setDestino(destino);
                    vuelo.setFechaorigen(fechaida);
                    vuelo.setFechadestino(fechadestino);
                    vuelo.setEstado(estado);
                    items.add(vuelo);

                } while(cursor.moveToNext());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return items;
    }

    public boolean update(int id,ContentValues campos) {
        try {
            DbHelper conexion   =new DbHelper(context,null,null,1);
            SQLiteDatabase bd   =conexion.getWritableDatabase();

            bd.update(table, campos,"id="+id, null);
            bd.close();

            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Vuelo findbyId(int id) {
        Vuelo  vuelo=null;

        try {
            DbHelper conexion = new DbHelper(context,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            Cursor cursor = db.rawQuery("select id,origen,destino,fechaida,fechadestino,estado from "+table+" where id="+id,null);

            if (cursor.moveToFirst()) {
                do {
                    int objid              =Integer.parseInt(cursor.getString(0));
                    String origen       =cursor.getString(1);
                    String destino      =cursor.getString(2);
                    String fechaida     =cursor.getString(3);
                    String fechadestino =cursor.getString(4);
                    String estado       =cursor.getString(5);

                    vuelo= new Vuelo();
                    vuelo.setId(objid);
                    vuelo.setOrigen(origen);
                    vuelo.setDestino(destino);
                    vuelo.setFechaorigen(fechaida);
                    vuelo.setFechadestino(fechadestino);
                    vuelo.setEstado(estado);

                } while(cursor.moveToNext());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return vuelo;
    }
}
