package eduardo.enfermedades.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eduardo on 20/05/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    /*Nombre por default*/
    public static final String DATABASE_NAME ="enfermedades.db";

    /*Version por default*/
    public static final int DATABASE_VERSION =17;

    /*tablas*/
    public static final String  TABLA_MEDICO       ="CREATE TABLE medico(id INTEGER PRIMARY KEY AUTOINCREMENT,nombre text,especialidad text)";
    public static final String  TABLA_ENFERMEDAD  ="CREATE TABLE enfermedad(id INTEGER PRIMARY KEY AUTOINCREMENT,nombre text)";
    public static final String  TABLA_MEDICAMENTO  ="CREATE TABLE medicamento(id INTEGER PRIMARY KEY AUTOINCREMENT,nombre text)";
    public static final String  TABLA_AGENDA      ="CREATE TABLE agenda(id INTEGER PRIMARY KEY AUTOINCREMENT,id_medico INTEGER,descripcion text,fecha date,hora time,recordatorio integer,fechar date,horar time)";
    public static final String  TABLA_DIAGNOSTICO      ="CREATE TABLE diagnostico(id INTEGER PRIMARY KEY AUTOINCREMENT,id_medico INTEGER,id_enfermedad INTEGER,observaciones text,descripcion text)";

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*Se ejecuta si la base de datos no existe*/
        db.execSQL(TABLA_MEDICO);
        db.execSQL(TABLA_ENFERMEDAD);
        db.execSQL(TABLA_MEDICAMENTO);
        db.execSQL(TABLA_AGENDA);
        db.execSQL(TABLA_DIAGNOSTICO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*se ejecuta si hay cambios en la version*/
        db.execSQL("DROP TABLE IF EXISTS medico");
        db.execSQL("DROP TABLE IF EXISTS enfermedad");
        db.execSQL("DROP TABLE IF EXISTS medicamento");
        db.execSQL("DROP TABLE IF EXISTS agenda");
        db.execSQL("DROP TABLE IF EXISTS diagnostico");

        db.execSQL(TABLA_MEDICO);
        db.execSQL(TABLA_ENFERMEDAD);
        db.execSQL(TABLA_MEDICAMENTO);
        db.execSQL(TABLA_AGENDA);
        db.execSQL(TABLA_DIAGNOSTICO);

    }
}
