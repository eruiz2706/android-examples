package eduardovue.vuelo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eduardo on 05/06/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    /*Nombre por default*/
    public static final String DATABASE_NAME ="vuelos.db";

    /*Version por default*/
    public static final int DATABASE_VERSION =6;

    /*tablas*/
    public static final String  TABLA_VUELO      ="CREATE TABLE vuelos(id INTEGER PRIMARY KEY AUTOINCREMENT,origen text,destino text,fechaida text,fechadestino text,cantidad integer,totalapagar real,estado text)";

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*Se ejecuta si la base de datos no existe*/
        db.execSQL(TABLA_VUELO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*se ejecuta si hay cambios en la version*/
        db.execSQL("DROP TABLE IF EXISTS vuelos");

        db.execSQL(TABLA_VUELO);

    }
}
