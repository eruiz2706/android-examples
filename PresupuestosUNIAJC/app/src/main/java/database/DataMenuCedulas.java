package database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by jhonyrenteria on 20/05/17.
 */

public class DataMenuCedulas {
    Context context = null;
    public DataMenuCedulas(Context context) {
        this.context = context;
    }

    public String[][] getMenuCedulas(){
        GetConexion con = new GetConexion(context);
        Cursor c = con.getDB().rawQuery("select nombre,imagen from concepcedula", null);
        String[][] datos = new String[c.getCount()][2];
        int i = 0;
        if (c.getCount() >= 1) {
            if (c.moveToFirst()) {
                do {
                    datos[i][0] = c.getString(0);
                    datos[i][1] = c.getString(1);
                    i++;
                } while (c.moveToNext());
            }
        }
        c.close();
        return datos;
    }
}
