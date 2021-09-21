package com.sleepingpandaaa.basicbankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9111234561644,'reyna',15000.00,'reyna38@gmail.com','1234567890','ABC09876543')");
        db.execSQL("insert into user_table values(9111234563567,'pheonix',10582.67,'pheonix2000@gmail.com','1234567891','BCA98765432')");
        db.execSQL("insert into user_table values(9111234568524,'asthra',1359.56,'asthra@gmail.com','1234567892','CAB87654321')");
        db.execSQL("insert into user_table values(9111234562685,'kj',1500.01,'kj@gmail.com','1234567893','ABC76543210')");
        db.execSQL("insert into user_table values(9111234563473,'yoru',2603.48,'yoru2000@gmail.com','1234567894','BCA65432109')");
        db.execSQL("insert into user_table values(9111234562567,'breach',945.16,'breach99@gmail.com','1234567895','CAB54321098')");
        db.execSQL("insert into user_table values(9111234562463,'breamstone',5936.00,'breamstone@gmail.com','1234567896','ABC43210987')");
        db.execSQL("insert into user_table values(9111234563573,'jett',857.22,'jett99@gmail.com','1234567897','BCA32109876')");
        db.execSQL("insert into user_table values(9111234563672,'viper',4398.46,'viper@gmail.com','1234567898','CAB21098765')");
        db.execSQL("insert into user_table values(9111234563664,'omen',273.90,'omen99@gmail.com','1234567899','ABC10987654')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
