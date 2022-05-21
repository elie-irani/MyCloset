package lcts.inc.mycloset;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ClosetSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DB_name = "MyCloset";
    private static final int DB_version = 1;

    public ClosetSQLiteOpenHelper(@Nullable Context context) {
        super(context, DB_name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE CLOSET(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TYPE TEXT," +
                "NAME TEXT," +
                "IMAGE_RESOURCE_ID BLOB);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void insertC(SQLiteDatabase db, String type, String name, byte[] image) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TYPE", type);
        contentValues.put("NAME", name);
        contentValues.put("IMAGE_RESOURCE_ID", image);
        db.insert("CLOSET", null, contentValues);
    }
}
