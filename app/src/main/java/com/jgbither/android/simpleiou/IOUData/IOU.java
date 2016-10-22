package com.jgbither.android.simpleiou.IOUData;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Josh on 10/20/2016.
 */

/*
    IOU CLASS
    Data class for individual IOU's
*/

public class IOU {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private BigDecimal mMoney;
    private String mDescription;

    public IOU (){
        mId          = UUID.randomUUID();
        mTitle       = "";
        mDate        = new Date();
        mMoney       = new BigDecimal(0.00);
        mDescription = "";
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public UUID getmId() {
        return mId;
    }

    public void setmId(UUID mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public BigDecimal getmMoney() {
        return mMoney;
    }

    public void setmMoney(BigDecimal mMoney) {
        this.mMoney = mMoney;
    }

    public String getmDescription() {
        return mDescription;
    }
    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
