package eduardo.enfermedades.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import eduardo.enfermedades.db.DbHelper;
import eduardo.enfermedades.interfaces.MedicamentoDao;
import eduardo.enfermedades.modelos.Medicamento;
import eduardo.enfermedades.modelos.Medico;

/**
 * Created by Eduardo on 21/05/2017.
 */

public class MedicamentoDaoImpl implements MedicamentoDao{

    private Context ctx;
    String table="medicamento";

    public MedicamentoDaoImpl(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public boolean save(Medicamento obj) {
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
    public boolean update(Medicamento obj) {
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
    public ArrayList<Medicamento> findAll() {
        ArrayList<Medicamento> items = new ArrayList<>();

        try {
            DbHelper conexion = new DbHelper(ctx,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            Cursor cursor = db.rawQuery("select id,nombre from "+table,null);

            if (cursor.moveToFirst()) {
                do {
                    int id              =Integer.parseInt(cursor.getString(0));
                    String nombre       =cursor.getString(1);

                    Medicamento  medicamento= new Medicamento();
                    medicamento.setId(id);
                    medicamento.setNombre(nombre);

                    items.add(medicamento);
                } while(cursor.moveToNext());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return items;
    }

    @Override
    public Medicamento findbyId(int id) {
        Medicamento  medicamento=null;

        try {
            DbHelper conexion = new DbHelper(ctx,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            Cursor cursor = db.rawQuery("select id,nombre from "+table+" where id="+id,null);

            if (cursor.moveToFirst()) {
                do {
                    int idobj           =Integer.parseInt(cursor.getString(0));
                    String nombre       =cursor.getString(1);

                    medicamento= new Medicamento();
                    medicamento.setId(id);
                    medicamento.setNombre(nombre);

                } while(cursor.moveToNext());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return medicamento;
    }
}
