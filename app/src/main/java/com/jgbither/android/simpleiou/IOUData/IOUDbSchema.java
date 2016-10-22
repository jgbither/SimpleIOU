package com.jgbither.android.simpleiou.IOUData;

/**
 * Created by Josh on 10/21/2016.
 */

public class IOUDbSchema {
    public static final class IOUTable{
        public static final String NAME = "IOUs";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String MONEY = "money";
            public static final String DESCRIPTION = "description";
        }
    }
}
