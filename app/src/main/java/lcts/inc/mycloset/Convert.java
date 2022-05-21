package lcts.inc.mycloset;


import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.view.View;


public class Convert {

    private int size;
    private int count;
    private Clothes[] temp;
    static private Clothes[] filtered;
    static private int sizefilter;

    public Convert() {
        size = 0;
        count = 0;
    }

    public void add(Clothes c) {
        if (temp.length == size) {
            Clothes[] x = new Clothes[temp.length + 1];

            for (int i = 0; i < temp.length; i++) {
                x[i] = temp[i];
            }

            temp = x;
            x = null;

            temp[size] = c;
            size++;

        } else {
            temp[size] = c;
            size++;
        }
    }

    void convertDB(View v) {
        ClosetSQLiteOpenHelper closetSQLiteOpenHelper = new ClosetSQLiteOpenHelper(v.getContext());
        SQLiteDatabase db = closetSQLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("CLOSET", new String[]{"TYPE", "NAME", "IMAGE_RESOURCE_ID"},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            temp = new Clothes[1];
            String name = cursor.getString(1);
            byte[] image = cursor.getBlob(2);
            String type = cursor.getString(0);
            add(new Clothes(type, name, image));
            while (cursor.moveToNext()) {
                name = cursor.getString(1);
                image = cursor.getBlob(2);
                type = cursor.getString(0);
                add(new Clothes(type, name, image));

            }
            cursor.close();
            Clothes.clothes = temp;

        }
        db.close();

    }

    public static Clothes[] filterTop(Clothes[] input) {
        sizefilter = 0;
        filtered = new Clothes[1];

        for (int i = 0; i < input.length; i++) {
            String type = input[i].getType();
            if (type.equalsIgnoreCase("top")) {
                String name = input[i].getName();
                byte[] image = input[i].getImageResourceId();
                addTemp(new Clothes(type, name, image));
            }
        }

        return filtered;
    }

    public static Clothes[] filterBottom(Clothes[] input) {

        sizefilter = 0;
        filtered = new Clothes[1];

        for (int i = 0; i < input.length; i++) {
            String type = input[i].getType();
            if (type.equalsIgnoreCase("Bottom")) {
                String name = input[i].getName();
                byte[] image = input[i].getImageResourceId();
                addTemp(new Clothes(type, name, image));
            }
        }

        return filtered;

    }


    private static void addTemp(Clothes c) {
        if (filtered.length == sizefilter) {
            Clothes[] x = new Clothes[filtered.length + 1];

            for (int i = 0; i < filtered.length; i++) {
                x[i] = filtered[i];
            }

            filtered = x;
            x = null;

            filtered[sizefilter] = c;
            sizefilter++;

        } else {
            filtered[sizefilter] = c;
            sizefilter++;
        }
    }
}
