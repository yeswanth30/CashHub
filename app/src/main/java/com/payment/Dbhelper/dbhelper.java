package com.payment.Dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database";
    private static final int DATABASE_VERSION = 4;
    private static final String User_table = "user";
    private static final String Bank_table = "bank";
    private static final String Transaction_table = "transactio";
    private static final String Histroy_table = "histroy";
    private static final String User_Id = "user_id";
    private static final String User_Name = "name";
    private static final String Email = "email";

    private static final String Number = "number";
    private static final String Password = "password";
    private static final String Bank_id = "bank_id";
    private static final String Bank_User_id = "user_id";
    private static final String Account_number = "account_number";
    private static final String Bank_name = "bank_name";
    private static final String Expiry_date = "expiry_date";
    private static final String Upi_id = "upi_id";
    private static final String Account_type = "account_type";
    private static final String Transaction_Id = "id";
    private static final String Tran_User_id = "user_id";
    private static final String Transaction_Id1 = "transaction_id";
    private static final String Receiver_id = "receiver_id";
    private static final String Amount = "amount";
    private static final String Status = "status";
    private static final String Balance = "balance";
    private static final String Amount_type = "amount_type";
    private static final String Time = "time";






    private static final String CREATE_User_table = "CREATE TABLE " + User_table + "(" + User_Id + " INTEGER PRIMARY KEY AUTOINCREMENT," + User_Name + " TEXT," + Number + " TEXT," + Email + " TEXT," + Password + " TEXT)";
    private static final String CREATE_Bank_table = "CREATE TABLE " + Bank_table + "("
            + Bank_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Bank_User_id + " INTEGER, "
            + Account_number + " TEXT, "
            + Bank_name + " TEXT, "
            + Upi_id + " TEXT, "
            + Account_type + " TEXT) ";
    private static final String CREATE_Transaction_table = "CREATE TABLE " + Transaction_table + "("
            + Transaction_Id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Tran_User_id + " TEXT, "
            + Receiver_id + " TEXT, "
            + Transaction_Id1 + " TEXT, "
            + Expiry_date + " TEXT, "
            + Balance + " TEXT, "
            + Status + " TEXT, "
            + Amount_type + " TEXT, "
            + Time + " TEXT, "
            + Amount + " TEXT) ";



    public dbhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_User_table);
        db.execSQL(CREATE_Bank_table);
        db.execSQL(CREATE_Transaction_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + User_table);
        db.execSQL("DROP TABLE IF EXISTS " + Bank_table);
        db.execSQL("DROP TABLE IF EXISTS " + Transaction_table);
        db.execSQL("DROP TABLE IF EXISTS " + Histroy_table);
        onCreate(db);
    }
    public Integer register(String Name, String number, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Integer id = 0;
        ContentValues values = new ContentValues();
        values.put(User_Name, Name);
        values.put(Number, number);
        values.put(Email, email);
        values.put(Password, password);
        id = Math.toIntExact(db.insert(User_table, null, values));
        db.close();
        return id;

    }
    public String login(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String id=null;
        String sql = " SELECT * FROM user WHERE email = '" + email + "'  AND password = '" + password + "' ;";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()) {
//             id = cursor.getInt(cursor.getColumnIndexOrThrow("userid"));
            id = cursor.getString(0);
        }
        return id;
    }
    public long bank(String userid, String account_number, String bank_name, String account_type){
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(Bank_User_id, userid);
        values.put(Account_number, account_number);
        values.put(Bank_name, bank_name);
        values.put(Account_type, account_type);

        long result = db.insert(Bank_table, null, values);
        db.close();
        return result;



    }
}