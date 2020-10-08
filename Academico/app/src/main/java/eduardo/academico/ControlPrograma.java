package eduardo.academico;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Eduardo on 17/04/2017.
 */

public class ControlPrograma {

    Programa    programa;
    String codigo;
    ConexionBD  conexion;
    Context     contexto;
    String[]    items;
    int id;
    String nombre;
    boolean existe=false;

    public ControlPrograma(Context context) {
        contexto    =context;
    }

    public void agregar(String codigo,String nombre,String duracion){
        programa =new Programa(codigo,nombre,duracion);
    }

    public void save(){
        try {
            ConexionBD conexion = new ConexionBD(contexto,null,null,1);
            SQLiteDatabase bd       =conexion.getWritableDatabase();
            ContentValues values    = new ContentValues();

            values.put("codigo",programa.getCodigo());
            values.put("nombre",programa.getNombre());
            values.put("duracion",programa.getDuracion());
            Long id = bd.insert("programas", null,values);
            bd.close();

            Toast toast = Toast.makeText(contexto,"Programa Guardado", Toast.LENGTH_SHORT);
            toast.show();
        }catch (Exception e){
            Toast toast = Toast.makeText(contexto,e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public String[] listProgram(){
        items = new String[1];
        items[0]    ="-";
        try {
            ConexionBD conexion = new ConexionBD(contexto,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            Cursor cursor = db.rawQuery("select id,nombre,codigo from programas",null);

            int count=cursor.getCount();
            items = new String[count+1];

            items[0]    ="-";
            int i=1;

            if (cursor.moveToFirst()) {
                do {
                    id           = cursor.getInt(0);
                    nombre       = cursor.getString(1);
                    codigo       = cursor.getString(2);

                    items[i]    =codigo+" - "+nombre;
                    i++;

                } while(cursor.moveToNext());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return items;
    }

    public String[] listProgram2(){
        items = null;

        try {
            ConexionBD conexion = new ConexionBD(contexto,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            Cursor cursor = db.rawQuery("select id,nombre,codigo from programas",null);

            int count=cursor.getCount();
            if(count==0){
                items = null;
            }else{
                items = new String[count];
                int i=0;
                if (cursor.moveToFirst()) {
                    do {
                        id           = cursor.getInt(0);
                        nombre       = cursor.getString(1);
                        codigo       = cursor.getString(2);

                        items[i]    =codigo+" - "+nombre;
                        i++;

                    } while(cursor.moveToNext());
                }
            }


        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return items;
    }

    public int getid(int item){
        id  =-1;
        try {
            ConexionBD conexion = new ConexionBD(contexto,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            Cursor cursor = db.rawQuery("select id,nombre from programas",null);
            int i=1;
            if (cursor.moveToFirst()) {
                do {
                    id              = cursor.getInt(0);
                    nombre       = cursor.getString(1);

                   if(i==item){
                       return  id;
                   }
                   i++;
                } while(cursor.moveToNext());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return id;
    }

    public boolean getexist(String codigo){
       try {
            ConexionBD conexion = new ConexionBD(contexto,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            Cursor cursor = db.rawQuery("select id from programas where codigo='"+codigo+"'",null);
           int count=cursor.getCount();
            System.out.println(count);
           if(count>0){
              return true;
           }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return existe;
    }
}
