package com.navjot.decemberapplication;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    static final String DBNAME = "decemberapplicationDB";


    static final String TABLE_1 = "cartData";
    static final String COL_1 = "itemName";
    static final String COL_2 = "itemPrice";
    static final String COL_3 = "itemTPrice";
    static final String COL_4 = "itemQty";
    static final String COL_5 = "kg";
    static final String COL_6 = "space";

    static final String TABLE_2 = "OrderDetails";
    static final String COLUMN_1 = "orderTotalPrice";
    static final String COLUMN_2 = "orderDate";
    static final String COLUMN_3 = "orderTime";
    static final String COLUMN_4 = "orderedItems";
    static final String COLUMN_5 = "orderID";




    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("    create table '" + TABLE_1 + "'  ('" + COL_1 + "' varchar(100) ," +
                " '" + COL_2 + "' varchar(100)   , '" + COL_3 + "' varchar(100)  ," +
                "  '" + COL_4 + "' varchar(100)   , '" + COL_5 + "' varchar(100)   , '" + COL_6 + "' varchar(100) )     ");


        db.execSQL("    create table '" + TABLE_2 + "'  ('" + COLUMN_1 + "' varchar(100) ," +
                " '" + COLUMN_2 + "' varchar(100)   , '" + COLUMN_3 + "' varchar(100)     ," +
                " '" + COLUMN_4 + "' varchar(200)      , '" + COLUMN_5 + "' varchar(100)   )  ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("   drop table '" + TABLE_1 + "'   ");
        db.execSQL("   drop table '" + TABLE_2 + "'   ");

        onCreate(db);
    }

    public boolean InsertData(String name, String price, String quantity,String tprice,String kg,String space) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1, name);
        cv.put(COL_2, price);
        cv.put(COL_3, tprice);
        cv.put(COL_4, quantity);
        cv.put(COL_5, kg);
        cv.put(COL_6, space);



        Log.e("inserted data", cv.toString());
        long l = db.insert(TABLE_1, null, cv);

        if (l > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cs = db.rawQuery("   select * from  '" + TABLE_1 + "'   ", null);
        return cs;
    }

    public void cleancart() {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE from  '" + TABLE_1 + "'    ");
        db.execSQL(query);
        db.close();
    }


    public void  transferCartData(String orderID)
    {
        SQLiteDatabase db = getReadableDatabase();

        // SELECT GROUP_CONCAT(vegname,vegquantity) as allitems FROM `cartdata`
        //======================================================================
        //String abc="SELECT   group_concat ( '" + COL_1 + "' , '" + COL_4 + "'   ) as allitems FROM '"+TABLE_1+"'  ";
       // String abc="SELECT   group_concat ( '" + COL_1 + "'   ) as allitems FROM '"+TABLE_1+"'  ";
        // String abc="(SELECT  '" + COL_1 + "' FROM '"+TABLE_1+"'  )";
        //String abc = "SELECT  group_concat(itemName, itemQty) from cartData";
        //db.execSQL( abc );
       // Cursor data = db.rawQuery(abc , null);

        //String str = data.getString(data.getColumnIndex(COL_1));

       // String q=" UPDATE '"+TABLE_2+"'    SET '"+COLUMN_4+"' =  '"+abc+"' WHERE   '"+COLUMN_4+"'  =  NULL ";

        String aa=" - ";
        String bb=" kg ";

        // OK Working ----- String q = "  UPDATE OrderDetails SET orderedItems = (SELECT  group_concat(itemName, itemQty) from cartData) where  orderID =  '"+orderID+"'  " ;
        String q = "  UPDATE OrderDetails SET orderedItems = (SELECT    group_concat( itemName||space||itemQty||kg)   from cartData) where  orderID =  '"+orderID+"'  " ;

        // OK Working -------String q = "UPDATE OrderDetails SET orderedItems = (SELECT  itemName FROM cartData where itemName='tomato') ";
        // OK Working ----- String q = "UPDATE OrderDetails SET orderedItems = 'SBJI' ";


        //String q=" UPDATE '"+TABLE_2+"'    SET '"+COLUMN_4+"' =  '"+abc+"' WHERE   '"+COLUMN_4+"'  =  NULL ";
       // Log.d("LOGGED DATA- I  ",    ""+abc +" -- "+ q  );
        db.execSQL( q );
        Log.d("LOGGED DATA- II ",    " "+ q  );
        db.close();

    }



    public boolean InsertOrderDetails(String orderTotalPrice, String orderDate,String orderTime,String orderID ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_1, orderTotalPrice);
        cv.put(COLUMN_2, orderDate);
        cv.put(COLUMN_3, orderTime);
        cv.put(COLUMN_5, orderID);

       // Log.e("inserted data", cv.toString());
        long l = db.insert(TABLE_2, null, cv);

        if (l > 0) {
            return true;
        } else {
            return false;
        }
    }
    public Cursor getOrderDetails() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cs = db.rawQuery("   select * from  '" + TABLE_2 + "'   ", null);
        return cs;
    }

    public Cursor getOrderedItemsForUploading(String orderID)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cs = db.rawQuery("   SELECT   orderedItems   from OrderDetails  where   orderID =  '"+orderID+"'   ", null);
        return cs;
    }



}