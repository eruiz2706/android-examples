package eduardo.academico;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eduardo on 09/04/2017.
 */

public class ConexionBD extends SQLiteOpenHelper{

    public static final String TABLAPROGRAMA    ="CREATE TABLE programas(id INTEGER PRIMARY KEY AUTOINCREMENT,codigo TEXT,nombre TEXT,duracion TEXT)";
    public static final String TABLAESTUDIANTE  ="CREATE TABLE estudiantes(id INTEGER PRIMARY KEY AUTOINCREMENT,codigo TEXT,nombre TEXT,programa integer)";

    public static final String  DATABASE_NAME    ="academico.db";
    public static final int     DATABASE_VERSION =4;

    public ConexionBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,DATABASE_NAME, null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        /*Se ejecuta si la base de datos no existe*/
        db.execSQL(TABLAPROGRAMA);
        db.execSQL(TABLAESTUDIANTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*se ejecuta si hay cambios en la version*/
        db.execSQL("DROP TABLE IF EXISTS programas");
        db.execSQL(TABLAPROGRAMA);

        db.execSQL("DROP TABLE IF EXISTS estudiantes");
        db.execSQL(TABLAESTUDIANTE);
    }
}
