package eduardo.enfermedades.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import eduardo.enfermedades.db.DbHelper;
import eduardo.enfermedades.interfaces.MedicoDao;
import eduardo.enfermedades.modelos.Medico;

/**
 * Created by Eduardo on 21/05/2017.
 */

public class MedicoDaoImpl implements MedicoDao{

    private Context ctx;
    String table="medico";
    String[]    items;

    public MedicoDaoImpl(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public boolean save(Medico obj) {
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
    public boolean update(Medico obj) {
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
    public ArrayList<Medico> findAll() {
        ArrayList<Medico> items = new ArrayList<>();

        try {
            DbHelper conexion = new DbHelper(ctx,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            Cursor cursor = db.rawQuery("select id,nombre,especialidad from "+table,null);

            if (cursor.moveToFirst()) {
                do {
                    int id              =Integer.parseInt(cursor.getString(0));
                    String nombre       =cursor.getString(1);
                    String especialidad =cursor.getString(2);

                    Medico  medico= new Medico();
                    medico.setId(id);
                    medico.setNombre(nombre);
                    medico.setEspecialidad(especialidad);

                    items.add(medico);
                } while(cursor.moveToNext());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return items;
    }

    @Override
    public Medico findbyId(int id) {
        Medico  medico=null;

        try {
            DbHelper conexion = new DbHelper(ctx,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            Cursor cursor = db.rawQuery("select id,nombre,especialidad from "+table+" where id="+id,null);

            if (cursor.moveToFirst()) {
                do {
                    int idobj           =Integer.parseInt(cursor.getString(0));
                    String nombre       =cursor.getString(1);
                    String especialidad  =cursor.getString(2);

                    medico= new Medico();
                    medico.setId(id);
                    medico.setNombre(nombre);
                    medico.setEspecialidad(especialidad);

                } while(cursor.moveToNext());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return medico;
    }

    public String[] findAllArray(){
        items = new String[1];
        items[0]    ="-";
        try {
            DbHelper conexion = new DbHelper(ctx,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            Cursor cursor = db.rawQuery("select id,nombre from "+table,null);

            int count=cursor.getCount();
            items = new String[count+1];

            items[0]    ="-";
            int i=1;

            if (cursor.moveToFirst()) {
                do {
                    int id           = cursor.getInt(0);
                    String nombre    = cursor.getString(1);

                    items[i]    =id+" - "+nombre;
                    i++;

                } while(cursor.moveToNext());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return items;
    }
}
