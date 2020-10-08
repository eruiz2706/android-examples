package eduardo.enfermedades.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import eduardo.enfermedades.db.DbHelper;
import eduardo.enfermedades.interfaces.AgendaDao;
import eduardo.enfermedades.interfaces.DiagnosticoDao;
import eduardo.enfermedades.modelos.Agenda;
import eduardo.enfermedades.modelos.Diagnostico;
import eduardo.enfermedades.modelos.Enfermedad;

/**
 * Created by Eduardo on 21/05/2017.
 */

public class DiagnosticoDaoImpl implements DiagnosticoDao {

    private Context ctx;
    String table="diagnostico";

    public DiagnosticoDaoImpl(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public boolean save(Diagnostico obj) {
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
    public boolean update(Diagnostico obj) {
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
    public ArrayList<Diagnostico> findAll() {
        ArrayList<Diagnostico> items = new ArrayList<>();

        try {
            DbHelper conexion = new DbHelper(ctx,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            Cursor cursor = db.rawQuery("select id,id_medico,id_enfermedad,observaciones,descripcion from "+table,null);

            if (cursor.moveToFirst()) {
                do {
                    int id              =Integer.parseInt(cursor.getString(0));
                    int id_medico       =Integer.parseInt(cursor.getString(1));
                    int id_enfermedad       =Integer.parseInt(cursor.getString(2));
                    String observaciones  =cursor.getString(3);
                    String descripcion        =cursor.getString(4);


                    Diagnostico diagnostico= new Diagnostico();
                    diagnostico.setId(id);
                    diagnostico.setId_medico(id_medico);
                    diagnostico.setId_enfermedad(id_enfermedad);
                    diagnostico.setObservaciones(observaciones);
                    diagnostico.setDescripcion(descripcion);

                    items.add(diagnostico);

                } while(cursor.moveToNext());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return items;
    }

    @Override
    public Diagnostico findbyId(int id) {
        Diagnostico diagnostico=null;

        try {
            DbHelper conexion = new DbHelper(ctx,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();


            Cursor cursor = db.rawQuery("select id, id_medico, id_enfermedad, observaciones, descripcion from "+table+" where id="+id,null);

            if (cursor.moveToFirst()) {
                do {
                    int idobj           =Integer.parseInt(cursor.getString(0));
                    int id_medico       =Integer.parseInt(cursor.getString(1));
                    int id_enfermedad   =Integer.parseInt(cursor.getString(2));
                    String observaciones = cursor.getString(3);
                    String descripcion  =cursor.getString(4);


                    diagnostico = new Diagnostico();
                    diagnostico.setId(idobj);
                    diagnostico.setId_medico(id_medico);
                    diagnostico.setId_enfermedad(id_enfermedad);
                    diagnostico.setObservaciones(observaciones);
                    diagnostico.setDescripcion(descripcion);


                } while(cursor.moveToNext());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return diagnostico;
    }
}
