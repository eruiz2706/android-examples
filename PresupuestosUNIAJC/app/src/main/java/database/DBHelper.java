package database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jhonyrenteria on 18/05/17.
 */

public class DBHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "presupuestos.sqlite";
    private static final int DB_SHEME_VERSION = 1;
    private Context context = null;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_SHEME_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataScheme.create_table_conceptos_cedula);
        db.execSQL(DataScheme.create_table_producto);
        db.execSQL(DataScheme.create_table_empresa);
        DataScheme.crearConfiguracion(db);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
