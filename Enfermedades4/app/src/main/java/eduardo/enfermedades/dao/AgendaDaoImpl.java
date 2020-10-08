package eduardo.enfermedades.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import eduardo.enfermedades.db.DbHelper;
import eduardo.enfermedades.interfaces.AgendaDao;
import eduardo.enfermedades.modelos.Agenda;
import eduardo.enfermedades.modelos.Enfermedad;

/**
 * Created by Eduardo on 21/05/2017.
 */

public class AgendaDaoImpl implements AgendaDao{

    private Context ctx;
    String table="agenda";

    public AgendaDaoImpl(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public boolean save(Agenda obj) {
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
    public boolean update(Agenda obj) {
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
    public ArrayList<Agenda> findAll() {
        ArrayList<Agenda> items = new ArrayList<>();

        try {
            DbHelper conexion = new DbHelper(ctx,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            Cursor cursor = db.rawQuery("select id,descripcion,fecha,recordatorio,fechar,id_medico from "+table+" order by fecha desc",null);

            if (cursor.moveToFirst()) {
                do {
                    int id              =Integer.parseInt(cursor.getString(0));
                    String descripcion  =cursor.getString(1);
                    String fecha        =cursor.getString(2);
                    int recordatorio    =Integer.parseInt(cursor.getString(3));
                    String fechar       =cursor.getString(4);
                    int id_medico    =Integer.parseInt(cursor.getString(5));

                    Agenda agenda= new Agenda();
                    agenda.setId(id);
                    agenda.setDescripcion(descripcion);
                    agenda.setFecha(fecha);
                    agenda.setRecordatorio(recordatorio);
                    agenda.setFechar(fechar);
                    agenda.setId_medico(id_medico);
                    items.add(agenda);

                } while(cursor.moveToNext());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return items;
    }

    @Override
    public Agenda findbyId(int id) {
        Agenda  agenda=null;

        try {
            DbHelper conexion = new DbHelper(ctx,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            Cursor cursor = db.rawQuery("select id,descripcion,fecha,recordatorio,fechar,id_medico from "+table+" where id="+id,null);

            if (cursor.moveToFirst()) {
                do {
                    int idobj           =Integer.parseInt(cursor.getString(0));
                    String descripcion  =cursor.getString(1);
                    String fecha        =cursor.getString(2);
                    int recordatorio    =Integer.parseInt(cursor.getString(3));
                    String fechar       =cursor.getString(4);
                    int id_medico       =Integer.parseInt(cursor.getString(4));

                    agenda= new Agenda();
                    agenda.setId(idobj);
                    agenda.setDescripcion(descripcion);
                    agenda.setFecha(fecha);
                    agenda.setRecordatorio(recordatorio);
                    agenda.setFechar(fechar);
                    agenda.setId_medico(id_medico);
                } while(cursor.moveToNext());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return agenda;
    }
}
