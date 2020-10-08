package eduardo.academico;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by Eduardo on 17/04/2017.
 */

public class ControlEstudiante {

    Estudiante  estudiante;
    ConexionBD  conexion;
    Context contexto;
    String[]    items;
    String codigo;
    String nombre;
    int id;
    boolean existe=false;

    public ControlEstudiante(Context context) {
        contexto    =context;
    }

    public void agregar(String codigo,String nombre,int programa){
        estudiante =new Estudiante(codigo,nombre,programa);
    }

    public void save(){
        try {
            ConexionBD conexion = new ConexionBD(contexto,null,null,1);
            SQLiteDatabase bd       =conexion.getWritableDatabase();
            ContentValues values    = new ContentValues();

            values.put("codigo",estudiante.getCodigo());
            values.put("nombre",estudiante.getNombre());
            values.put("programa",estudiante.getPrograma());
            Long id = bd.insert("estudiantes", null,values);
            bd.close();

            Toast toast = Toast.makeText(contexto,"Estudiante Guardado", Toast.LENGTH_SHORT);
            toast.show();
        }catch (Exception e){
            Toast toast = Toast.makeText(contexto,e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public String[] listProgram2(int item){
        items = null;

        try {
            ConexionBD conexion = new ConexionBD(contexto,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            Cursor cursor = db.rawQuery("select id,nombre,codigo from estudiantes where programa="+item,null);

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

    public boolean getexist(String codigo){
        try {
            ConexionBD conexion = new ConexionBD(contexto,null,null,1);
            SQLiteDatabase db   = conexion.getReadableDatabase();

            Cursor cursor = db.rawQuery("select id from estudiantes where codigo='"+codigo+"'",null);
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
