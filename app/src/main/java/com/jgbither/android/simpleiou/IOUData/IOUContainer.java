package com.jgbither.android.simpleiou.IOUData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.jgbither.android.simpleiou.IOUData.IOUDbSchema.*;

/**
 * Created by Josh on 10/20/2016.
 */

public class IOUContainer {

    private static IOUContainer mIOUContainer;
    private List<IOU> mIous;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static IOUContainer getIOUContainer() {
        if (mIOUContainer == null) {
            mIOUContainer = new IOUContainer();
        }
        return mIOUContainer;
    }

    public List<IOU> getIOUs(){
        List<IOU> IOUs = new ArrayList<>();

        IOUCursorWrapper cursor = queryIOUs(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                IOUs.add(cursor.getIOU());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return IOUs;
    }

    public IOU getIOU(UUID id){
        IOUCursorWrapper cursor = queryIOUs(
                IOUTable.Cols.UUID + "=?",
                new String[] {id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null
            }

            cursor.moveToFirst();
            return cursor.getIOU();
        }
        finally {
            cursor.close();
        }
    }

    public void addIOU(IOU iou){
        ContentValues values = getContentValues(iou);
        mDatabase.insert(IOUTable.NAME, null, values);
    }

    public void deleteIOU(UUID id){
        mDatabase.delete(IOUTable.NAME, IOUTable.Cols.UUID + "=?",
                new String[] {id.toString()}
        );
    }

    public void updateIOU(IOU iou){
        UUID id = iou.getmId();
        ContentValues values = getContentValues(iou);

        mDatabase.update(IOUTable.NAME, values, IOUTable.Cols.UUID + "=?", new String[] {id.toString()});
    }

    private static ContentValues getContentValues(IOU iou){
        ContentValues values = new ContentValues();
        values.put(IOUTable.Cols.UUID, iou.getmId().toString());
        values.put(IOUTable.Cols.TITLE, iou.getmTitle());
        values.put(IOUTable.Cols.DATE, iou.getmDate().getTime());
        values.put(IOUTable.Cols.MONEY, iou.getmMoney().toString());
        values.put(IOUTable.Cols.DESCRIPTION, iou.getmMoney().toString());
        return values;
    }

    private IOUCursorWrapper queryIOUs(String whereClause, String[] whereArg){
        Cursor cursor = mDatabase.query(
                IOUTable.NAME,
                null,
                whereClause,
                whereArg,
                null,
                null,
                null
        );

        return new IOUCursorWrapper(cursor);
    }
}
