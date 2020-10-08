package eduardo.agendacontact;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eduardo on 09/04/2017.
 */

public class ConexionDB extends SQLiteOpenHelper{

    public static final String  SQL_TABLA        ="CREATE TABLE contactos(id INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT,apellido TEXT,direccion TEXT,telefono TEXT,correo TEXT,sexo TEXT)";
    public static final String  DATABASE_NAME    = "bdagenda.db";
    public static final int     DATABASE_VERSION = 2;

    public ConexionDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,DATABASE_NAME, null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        /*Se ejecuta si la base de datos no existe*/
        db.execSQL(SQL_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*se ejecuta si hay cambios en la version*/
        db.execSQL("DROP TABLE IF EXISTS contactos");
        db.execSQL(SQL_TABLA);
    }
}
