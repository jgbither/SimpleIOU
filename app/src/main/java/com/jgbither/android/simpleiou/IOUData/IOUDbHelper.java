package com.jgbither.android.simpleiou.IOUData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.jgbither.android.simpleiou.IOUData.IOUDbSchema.*;

/**
 * Created by Josh on 10/21/2016.
 */

public class IOUDbHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "iou.database";

    public IOUDbHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + IOUTable.NAME + "(" + " _id integer primary key autoincrement, "
                + IOUTable.Cols.UUID        + ", "
                + IOUTable.Cols.TITLE       + ", "
                + IOUTable.Cols.DATE        + ", "
                + IOUTable.Cols.MONEY       + ", "
                + IOUTable.Cols.DESCRIPTION + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
