package database;


import android.database.sqlite.SQLiteDatabase;

/**
 * Created by jhonyrenteria on 18/05/17.
 */

public class DataScheme {

    public static String create_table_conceptos_cedula = "CREATE TABLE concepcedula (CODIGO INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                         "NOMBRE TEXT,IMAGEN VARCHAR(30));";

    public static String create_table_producto = "CREATE TABLE producto (CODIGO INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                 "NOMBRE VARCHAR(50),DESCRIPCION TEXT, EMPRESA INTEGER(5),FOTO TEXT,precio NUMERIC(20));";

    public static String create_table_empresa = "CREATE TABLE empresa (CODIGO INTEGER PRIMARY KEY AUTOINCREMENT," +
            "NIT VARCHAR(200),NOMBRE VARCHAR(200),RAZONS VARCHAR(200),SECTOR VARCHAR(200),NUMEMPLEADOS INTERGER, DESCRIPCION TEXT,FOTO TEXT);";

    public static void crearConfiguracion(SQLiteDatabase db) {

        db.execSQL("insert into concepcedula (codigo,nombre,imagen) values(null,'PRESUPUESTO DE VENTAS','pv.png');");
        db.execSQL("insert into concepcedula (codigo,nombre,imagen) values(null,'PRESUPUESTO DE PRODUCCION','pp.png');");
        db.execSQL("insert into concepcedula (codigo,nombre,imagen) values(null,'PRESUPUESTO DE CONSUMOS Y COSTEOS','pc.png');");
        db.execSQL("insert into concepcedula (codigo,nombre,imagen) values(null,'PRESUPUESTO DE COMPRAS','pc.png');");
        db.execSQL("insert into concepcedula (codigo,nombre,imagen) values(null,'PRESUPUESTO DE MANO DE OBRA','pm.png');");

    }
}
