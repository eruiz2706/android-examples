package eduardo.enfermedades.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import eduardo.enfermedades.db.DbHelper;
import eduardo.enfermedades.interfaces.EnfermedadDao;
import eduardo.enfermedades.modelos.Enfermedad;
import eduardo.enfermedades.modelos.Medico;

/**
 * Created by Eduardo on 21/05/2017.
 */

public class EnfermedadDaoImpl implements EnfermedadDao {
    private Context ctx;
    String table="enfermedad";

    public EnfermedadDaoImpl(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public boolean save(Enfermedad obj) {
        try {
            DbHelper conexion   =new DbHelper(ctx,null,null,1);
            SQLiteDatabase bd   =conexion.getWritableDatabase();;

            ContentValues values = obj.getValues();
            bd.insert(table, null, values);
            bd.close();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Enfermedad obj) {
        try {
            DbHelper conexion   =new DbHelper(ctx,null,null,1);
            SQLiteDatabase bd   =conexion.getWritableDatabase();

            ContentValues values = obj.getValues();
            bd.update(table, values,"id="+obj.getId(), null);
            bd.close();

            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean destroy(int id) {
        try {
            DbHelper conexion   =new DbHelper(ctx,null,null,1);
            SQLiteDatabase bd   =conexion.getWritableDatabase();

            bd.delete(table, "id="+id, null);
            bd.close();

            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Enfermedad> findAll() {
        ArrayList<Enfermedad> items = new ArrayList<>();

        try {
            DbHelper conexion = new DbHelper(ctx,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            Cursor cursor = db.rawQuery("select id,nombre from "+table,null);

            if (cursor.moveToFirst()) {
                do {
                    int id              =Integer.parseInt(cursor.getString(0));
                    String nombre       =cursor.getString(1);

                    Enfermedad enfermedad= new Enfermedad();
                    enfermedad.setId(id);
                    enfermedad.setNombre(nombre);
                    items.add(enfermedad);

                } while(cursor.moveToNext());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return items;
    }

    @Override
    public Enfermedad findbyId(int id) {
        Enfermedad  enfermedad=null;

        try {
            DbHelper conexion = new DbHelper(ctx,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            Cursor cursor = db.rawQuery("select id,nombre from "+table+" where id="+id,null);

            if (cursor.moveToFirst()) {
                do {
                    int idobj           =Integer.parseInt(cursor.getString(0));
                    String nombre       =cursor.getString(1);

                    enfermedad= new Enfermedad();
                    enfermedad.setId(id);
                    enfermedad.setNombre(nombre);
                } while(cursor.moveToNext());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return enfermedad;
    }
}
