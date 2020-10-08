package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by jhonyrenteria on 18/05/17.
 */

public  class GetConexion {

    private DBHelper DBHP = null;
    private SQLiteDatabase DB = null;

    public GetConexion(Context context) {
        DBHP = new DBHelper(context);
        DB = DBHP.getWritableDatabase();
    }

    public DBHelper getDBHP() {
        return DBHP;
    }

    public void setDBHP(DBHelper DBHP) {
        this.DBHP = DBHP;
    }

    public SQLiteDatabase getDB() {
        return DB;
    }

    public void setDB(SQLiteDatabase DB) {
        this.DB = DB;
    }
}
