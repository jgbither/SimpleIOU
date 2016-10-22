package com.jgbither.android.simpleiou.IOUData;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import static com.jgbither.android.simpleiou.IOUData.IOUDbSchema.*;

/**
 * Created by Josh on 10/21/2016.
 */

public class IOUCursorWrapper extends CursorWrapper {
    public IOUCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public IOU getIOU(){
        String uuidString     = getString(getColumnIndex(IOUTable.Cols.UUID));
        String title          = getString(getColumnIndex(IOUTable.Cols.TITLE));
        long date             = getLong(getColumnIndex(IOUTable.Cols.DATE));
        String money          = getString(getColumnIndex(IOUTable.Cols.MONEY));
        String description    = getString(getColumnIndex(IOUTable.Cols.DESCRIPTION));

        IOU iou = new IOU();
        iou.setmId(UUID.fromString(uuidString));
        iou.setmDate(new Date(date));
        iou.setmDescription(description);
        iou.setmTitle(title);
        iou.setmMoney(new BigDecimal(money));

        return iou;
    }
}
