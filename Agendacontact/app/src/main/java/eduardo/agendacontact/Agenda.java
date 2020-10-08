package eduardo.agendacontact;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by Eduardo on 09/04/2017.
 */

public class Agenda {
    ConexionDB  conexion;
    Context     contexto;

    public Agenda(Context context){
        contexto    =context;
    }

    public void agregar(String nombre,String apellido,String direccion,String telefono,String correo,String sexo){


        try {
            ConexionDB conexion = new ConexionDB(contexto,null,null,1);
            SQLiteDatabase bd       =conexion.getWritableDatabase();
            ContentValues values    = new ContentValues();

            values.put("nombre",nombre);
            values.put("apellido",apellido);
            values.put("direccion",direccion);
            values.put("telefono",telefono);
            values.put("correo",correo);
            values.put("sexo",sexo);
            Long id = bd.insert("contactos", null, values);
            bd.close();

            Toast toast = Toast.makeText(contexto,"Contacto Guardada", Toast.LENGTH_SHORT);
            toast.show();
        }catch (Exception e){

            System.out.println(e.getMessage());
            Toast toast = Toast.makeText(contexto,e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }


    }

    public void update(int position,int id,String nombre,String apellido,String direccion,String telefono,String correo,String sexo){

        try {
            ConexionDB conexion = new ConexionDB(contexto,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            db.execSQL("UPDATE contactos SET nombre='"+nombre+"',apellido='"+apellido+"' WHERE id="+position);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(int position){

        try {
            ConexionDB conexion = new ConexionDB(contexto,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            db.execSQL("delete from contactos WHERE id="+position);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Contacto> lista(){
        ArrayList<Contacto> items = new ArrayList<>();
        try {
            ConexionDB conexion = new ConexionDB(contexto,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            Cursor cursor = db.rawQuery("select nombre,apellido,id,direccion,telefono,correo,sexo from contactos",null);

            if (cursor.moveToFirst()) {
                do {
                    String nombre       = cursor.getString(0);
                    String apellido     = cursor.getString(1);
                    int id              = Integer.parseInt(cursor.getString(2));
                    String direccion    = cursor.getString(3);
                    String telefono     = cursor.getString(4);
                    String correo       = cursor.getString(5);
                    String sexo         = cursor.getString(6);

                    items.add(new Contacto(id,nombre,apellido,direccion,telefono,correo,sexo));
                } while(cursor.moveToNext());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        return items;
    }

    public int getid(int position){
        try {
            ConexionDB conexion = new ConexionDB(contexto,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            Cursor cursor = db.rawQuery("select id from contactos",null);

            int i=0;
            if (cursor.moveToFirst()) {
                do {
                    int id       =Integer.parseInt(cursor.getString(0));

                    if(i==position){
                        return id;
                    }
                    i++;
                } while(cursor.moveToNext());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public Contacto getContacto(int position){
        Contacto contacto;

        try {
            ConexionDB conexion = new ConexionDB(contexto,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            Cursor cursor = db.rawQuery("select nombre,apellido,direccion,telefono,correo,sexo,id from contactos where id="+position,null);
            contacto    = new Contacto(0,"","","","","","");
            if (cursor.moveToFirst()) {
                String nombre   = cursor.getString(0);
                String apellido = cursor.getString(1);
                String direccion= cursor.getString(2);
                String telefono = cursor.getString(3);
                String correo   = cursor.getString(4);
                String sexo     = cursor.getString(5);
                int id          = Integer.parseInt(cursor.getString(6));

                contacto    =new Contacto(id,nombre,apellido,direccion,telefono,correo,sexo);
                return contacto;
            }

        }catch (Exception e){

        }
        return new Contacto(0,"","","","","","");
    }
}
