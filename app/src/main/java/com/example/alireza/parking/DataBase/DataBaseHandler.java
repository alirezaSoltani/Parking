package com.example.alireza.parking.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.alireza.parking.Model.DaramadRuzane;
import com.example.alireza.parking.Model.Gharardad;
import com.example.alireza.parking.Model.VorudKhoruj;

import java.util.ArrayList;

/**
 * Created by AliReza on 4/5/2017.
 */

public class DataBaseHandler extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "dbParking";
    private static final int DATABASE_VERSION = 1;





    private static final String TABLE_GHARARDAD = "gharardad_table";
    private static final String KEY_GHARARDADID = "gharardadid";
    private static final String KEY_GHARARDADNAME = "gharardadname";
    private static final String KEY_GHARARDADTEL = "gharardadtel";
    private static final String KEY_GHARARDADCARTYPE = "gharardadcartype";
    private static final String KEY_GHARARDADCARPELAK = "gharardadcarpelak";
    private static final String KET_GHARARDADMABLAGH = "gharardadmablagh";
    private static final String KEY_GHARARDADDES = "gharardaddes";
    private static final String KEY_GHARARDADISBAYGANI = "gharardadisbaygani";
    private static final String KEY_GHARARDADSTATUS = "gharardadstatus";
    private static final String[] ALL_GHARARDAD_KEYS = {KEY_GHARARDADID, KEY_GHARARDADNAME, KEY_GHARARDADTEL, KEY_GHARARDADCARTYPE, KEY_GHARARDADCARPELAK, KET_GHARARDADMABLAGH, KEY_GHARARDADDES ,KEY_GHARARDADISBAYGANI,KEY_GHARARDADSTATUS};
    private static final String CREATE_TABLE_GHARARDAD = String.format("CREATE TABLE %S (%S INTEGER PRIMARY KEY AUTOINCREMENT , %S TEXT , %S TEXT , %S TEXT , %S TEXT , %S TEXT , %S TEXT , %S TEXT , %S TEXT )", TABLE_GHARARDAD,KEY_GHARARDADID, KEY_GHARARDADNAME, KEY_GHARARDADTEL, KEY_GHARARDADCARTYPE, KEY_GHARARDADCARPELAK, KET_GHARARDADMABLAGH, KEY_GHARARDADDES , KEY_GHARARDADISBAYGANI , KEY_GHARARDADSTATUS);



    private static final String TABLE_VORUDKHORUJ = "vorudkhoruj_table";
    private static final String KEY_VORUDKHORUJID = "vorudkhorujid";
    private static final String KEY_VORUDKHORUJSHOMAREKHODRO = "vorudkhorujshomarekhodro";
    private static final String KEY_VORUDKHORUJSAATVORUD = "vorudkhorujsaatvorud";
    private static final String KEY_VORUDKHORUJSAATKHORUJ = "vorudkhorujsaatkhoruj";
    private static final String KEY_VORUDKHORUJTARIKH = "vorudkhorujtarikh";
    private static final String KEY_VORUDKHORUJMABLAGH = "vorudkhorujmablagh";
    private static final String KET_VORUDKHORUJDES = "vorudkhorujdes";
    private static final String KEY_VORUDKHORUJSHIFT = "vorudkhorujshift";
    private static final String KEY_VORUDKHORUJISBAYGANI = "vorudkhorujisbaygani";
    private static final String KEY_VORUDKHORUJSTATUS = "vorudkhorujstatus";
    private static final String[] ALL_VORUDKHORUJ_KEYS = {KEY_VORUDKHORUJID, KEY_VORUDKHORUJSHOMAREKHODRO, KEY_VORUDKHORUJSAATVORUD, KEY_VORUDKHORUJSAATKHORUJ,KEY_VORUDKHORUJTARIKH, KEY_VORUDKHORUJMABLAGH, KET_VORUDKHORUJDES, KEY_VORUDKHORUJSHIFT , KEY_VORUDKHORUJISBAYGANI , KEY_VORUDKHORUJSTATUS};
    private static final String CREATE_TABLE_VORUDKHORUJ = String.format("CREATE TABLE %S (%S INTEGER PRIMARY KEY AUTOINCREMENT , %S TEXT , %S TEXT, %S TEXT , %S TEXT , %S TEXT , %S TEXT , %S TEXT, %S TEXT, %S TEXT )", TABLE_VORUDKHORUJ, KEY_VORUDKHORUJID, KEY_VORUDKHORUJSHOMAREKHODRO, KEY_VORUDKHORUJSAATVORUD, KEY_VORUDKHORUJSAATKHORUJ,KEY_VORUDKHORUJTARIKH, KEY_VORUDKHORUJMABLAGH, KET_VORUDKHORUJDES, KEY_VORUDKHORUJSHIFT , KEY_VORUDKHORUJISBAYGANI , KEY_VORUDKHORUJSTATUS);




    private static final String TABLE_DARAMADRUZANE = "daramadruzane_table";
    private static final String KEY_DARAMADRUZANEID = "daramadruzaneid";
    private static final String KEY_DARAMADRUZANEDAY = "daramadruzaneday";
    private static final String KEY_DARAMADRUZANEDATE = "daramadruzanedate";
    private static final String KEY_DARAMADRUZANEMABLAGH = "daramadruzanemablagh";
    private static final String KEY_DARAMADRUZANEDES = "daramadruzanedes";
    private static final String KEY_DARAMADRUZANESHIFT = "daramadruzaneshift";
    private static final String KEY_DARAMADRUZANEISBAYGANI = "daramadruzaneisbaygani";
    private static final String KEY_DARAMADRUZANESTATUS = "daramadruzanestatus";
    private static final String[] ALL_DARAMADRUZANE_KEYS = {KEY_DARAMADRUZANEID,KEY_DARAMADRUZANEDAY,KEY_DARAMADRUZANEDATE,KEY_DARAMADRUZANEMABLAGH,KEY_DARAMADRUZANEDES,KEY_DARAMADRUZANESHIFT , KEY_DARAMADRUZANEISBAYGANI , KEY_DARAMADRUZANESTATUS};
    private static final String CREATE_TABLE_DARAMADRUZANE = String.format("CREATE TABLE %S (%S INTEGER PRIMARY KEY AUTOINCREMENT , %S TEXT , %S TEXT, %S TEXT , %S TEXT , %S TEXT , %S TEXT, %S TEXT )", TABLE_DARAMADRUZANE,KEY_DARAMADRUZANEID,KEY_DARAMADRUZANEDAY,KEY_DARAMADRUZANEDATE,KEY_DARAMADRUZANEMABLAGH,KEY_DARAMADRUZANEDES,KEY_DARAMADRUZANESHIFT , KEY_DARAMADRUZANEISBAYGANI , KEY_DARAMADRUZANESTATUS);

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_GHARARDAD);
        db.execSQL(CREATE_TABLE_VORUDKHORUJ);
        db.execSQL(CREATE_TABLE_DARAMADRUZANE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GHARARDAD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VORUDKHORUJ);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DARAMADRUZANE);
    }






    //......................................................Gharardad methods......................................................
    public void insertGharardad(Gharardad gharardad) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_GHARARDADNAME, gharardad.getName());
        values.put(KEY_GHARARDADTEL, gharardad.getTel());
        values.put(KEY_GHARARDADCARTYPE, gharardad.getCar_Type());
        values.put(KEY_GHARARDADCARPELAK, gharardad.getCar_pelak());
        values.put(KET_GHARARDADMABLAGH, gharardad.getMablagh());
        values.put(KEY_GHARARDADDES,gharardad.getDes());
        values.put(KEY_GHARARDADISBAYGANI,gharardad.getIsBaygani());
        values.put(KEY_GHARARDADSTATUS,gharardad.getStatus());
        db.insert(TABLE_GHARARDAD, null, values);
        db.close();
    }
    public ArrayList<Gharardad> getAllGharardadNBaygani() {
        ArrayList<Gharardad> gharardadArrayList = new ArrayList<Gharardad>();

        String selectQuery = "SELECT * FROM " + TABLE_GHARARDAD + " WHERE "+KEY_GHARARDADISBAYGANI+" ='false'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Gharardad gharardad = new Gharardad(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
                gharardadArrayList.add(gharardad);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return gharardadArrayList;
    }
    public ArrayList<String> getAllGharardadStatusBaygani() {
        ArrayList<String> status= new ArrayList<String>();
        String selectQuery = "SELECT DISTINCT "+KEY_GHARARDADSTATUS+" FROM " + TABLE_GHARARDAD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                status.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return status;
    }
    public ArrayList<Gharardad> getAllGharardadByStatus(String status) {
        ArrayList<Gharardad> gharardads = new ArrayList<Gharardad>();

        String selectQuery = "SELECT * FROM " + TABLE_GHARARDAD + " WHERE "+KEY_GHARARDADISBAYGANI+"='true' AND "+KEY_GHARARDADSTATUS + "='"+status+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Gharardad gharardad = new Gharardad(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
                gharardads.add(gharardad);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return gharardads;
    }
    public void deleteGaharardadByID(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GHARARDAD, KEY_GHARARDADID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public Gharardad getGharardadBySTId(int id) {
        Gharardad gharardad= null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_GHARARDAD, ALL_GHARARDAD_KEYS, KEY_GHARARDADID+ "=?", new String[]{String.valueOf(id)}, null, null, null);
            cursor.moveToLast();
            if (cursor != null)
                cursor.moveToFirst();
            gharardad = new Gharardad(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6) , cursor.getString(7) , cursor.getString(8));
            cursor.close();
            db.close();
        } catch (Exception e) {
        }
        return gharardad;
    }
    public void BayganiGharardads (ArrayList<Gharardad> gharardads , String status) {
        for (int i=0 ; i<gharardads.size() ; i++){
            Gharardad gharardad = gharardads.get(i);
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_GHARARDADNAME, gharardad.getName());
            values.put(KEY_GHARARDADTEL, gharardad.getTel());
            values.put(KEY_GHARARDADCARTYPE, gharardad.getCar_Type());
            values.put(KEY_GHARARDADCARPELAK, gharardad.getCar_pelak());
            values.put(KET_GHARARDADMABLAGH, gharardad.getMablagh());
            values.put(KEY_GHARARDADDES,gharardad.getDes());
            values.put(KEY_GHARARDADISBAYGANI,"true");
            values.put(KEY_GHARARDADSTATUS,status);
            int result = db.update(TABLE_GHARARDAD, values, KEY_GHARARDADID + "=?", new String[]{String.valueOf(gharardad.getId())});
            db.close();
        }


    }




    //......................................................Vorud Khoruj methods......................................................
    public void insertVorudKhoruj(VorudKhoruj vorudKhoruj) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_VORUDKHORUJSHOMAREKHODRO, vorudKhoruj.getShomareKhodro());
        values.put(KEY_VORUDKHORUJSAATVORUD, vorudKhoruj.getSaatVorud());
        values.put(KEY_VORUDKHORUJSAATKHORUJ, vorudKhoruj.getSaatKhoruj());
        values.put(KEY_VORUDKHORUJTARIKH,vorudKhoruj.getTarikh());
        values.put(KEY_VORUDKHORUJMABLAGH, vorudKhoruj.getMablagh());
        values.put(KET_VORUDKHORUJDES, vorudKhoruj.getDes());
        values.put(KEY_VORUDKHORUJSHIFT,vorudKhoruj.getShift());
        values.put(KEY_VORUDKHORUJISBAYGANI,vorudKhoruj.getIsBaygani());
        values.put(KEY_VORUDKHORUJSTATUS,vorudKhoruj.getStatus());
        db.insert(TABLE_VORUDKHORUJ, null, values);
        db.close();
    }
    public ArrayList<VorudKhoruj> getAllVorudKhorujNBaygani() {
        ArrayList<VorudKhoruj> vorudKhorujArrayList = new ArrayList<VorudKhoruj>();
        String selectQuery = "SELECT * FROM " + TABLE_VORUDKHORUJ+ " WHERE "+KEY_VORUDKHORUJISBAYGANI+" ='false'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                VorudKhoruj vorudKhoruj= new VorudKhoruj(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6) , cursor.getString(7), cursor.getString(8), cursor.getString(9));
                vorudKhorujArrayList.add(vorudKhoruj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return vorudKhorujArrayList;
    }
    public void deleteVorudKhorujByID(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VORUDKHORUJ, KEY_VORUDKHORUJID+ "=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public VorudKhoruj getVorudKhorujById(int id) {
        VorudKhoruj vorudKhoruj = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_VORUDKHORUJ, ALL_VORUDKHORUJ_KEYS, KEY_VORUDKHORUJID + "=?", new String[]{String.valueOf(id)}, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();
            vorudKhoruj = new VorudKhoruj(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6) , cursor.getString(7) , cursor.getString(8), cursor.getString(9));
            cursor.close();
            db.close();
        } catch (Exception e) {
        }
        return vorudKhoruj;
    }
    public void BayganiVorudKhoruj (ArrayList<VorudKhoruj> vorudKhorujs , String status) {
        for (int i=0 ; i<vorudKhorujs.size() ; i++){
            VorudKhoruj vorudKhoruj = vorudKhorujs.get(i);
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_VORUDKHORUJSHOMAREKHODRO, vorudKhoruj.getShomareKhodro());
            values.put(KEY_VORUDKHORUJSAATVORUD, vorudKhoruj.getSaatVorud());
            values.put(KEY_VORUDKHORUJSAATKHORUJ, vorudKhoruj.getSaatKhoruj());
            values.put(KEY_VORUDKHORUJTARIKH,vorudKhoruj.getTarikh());
            values.put(KEY_VORUDKHORUJMABLAGH, vorudKhoruj.getMablagh());
            values.put(KET_VORUDKHORUJDES, vorudKhoruj.getDes());
            values.put(KEY_VORUDKHORUJSHIFT,vorudKhoruj.getShift());
            values.put(KEY_VORUDKHORUJISBAYGANI,"true");
            values.put(KEY_VORUDKHORUJSTATUS,status);
            int result = db.update(TABLE_VORUDKHORUJ, values, KEY_VORUDKHORUJID + "=?", new String[]{String.valueOf(vorudKhoruj.getId())});
            db.close();
        }


    }
    public ArrayList<String> getAllVorudKhorujStatusBaygani() {
        ArrayList<String> status= new ArrayList<String>();
        String selectQuery = "SELECT DISTINCT "+KEY_VORUDKHORUJSTATUS+" FROM " + TABLE_VORUDKHORUJ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                status.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return status;
    }
    public ArrayList<VorudKhoruj> getAllVorudKhorujByStatus(String status) {
        ArrayList<VorudKhoruj> vorudKhorujs = new ArrayList<VorudKhoruj>();

        String selectQuery = "SELECT * FROM " + TABLE_VORUDKHORUJ + " WHERE "+KEY_VORUDKHORUJISBAYGANI+"='true' AND "+KEY_VORUDKHORUJSTATUS + "='"+status+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                VorudKhoruj vorudKhoruj = new VorudKhoruj(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8) , cursor.getString(9));
                vorudKhorujs.add(vorudKhoruj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return vorudKhorujs;
    }


    //......................................................daramad ruzane methods......................................................
    public void insertDaramadRuzane(DaramadRuzane daramadRuzane) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DARAMADRUZANEDAY, daramadRuzane.getDay());
        values.put(KEY_DARAMADRUZANEDATE, daramadRuzane.getDate());
        values.put(KEY_DARAMADRUZANEMABLAGH, daramadRuzane.getMablagh());
        values.put(KEY_DARAMADRUZANEDES,daramadRuzane.getDes());
        values.put(KEY_DARAMADRUZANESHIFT, daramadRuzane.getShift());
        values.put(KEY_DARAMADRUZANEISBAYGANI, daramadRuzane.getIsBaygani());
        values.put(KEY_DARAMADRUZANESTATUS, daramadRuzane.getStatus());
        db.insert(TABLE_DARAMADRUZANE, null, values);
        db.close();
    }
    public ArrayList<DaramadRuzane> getAllDaramadRuzaneNBaygani() {
        ArrayList<DaramadRuzane> daramadRuzaneArrayList = new ArrayList<DaramadRuzane>();
        String selectQuery = "SELECT * FROM " + TABLE_DARAMADRUZANE+ " WHERE "+KEY_DARAMADRUZANEISBAYGANI+" ='false'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                DaramadRuzane daramadRuzane = new DaramadRuzane(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
                daramadRuzaneArrayList.add(daramadRuzane);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return daramadRuzaneArrayList;
    }
    public void deleteDaramadRuzaneByID(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DARAMADRUZANE, KEY_DARAMADRUZANEID+ "=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public DaramadRuzane getDaramadRuzaneById(int id) {
        DaramadRuzane daramadRuzane = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_DARAMADRUZANE, ALL_DARAMADRUZANE_KEYS , KEY_DARAMADRUZANEID + "=?", new String[]{String.valueOf(id)}, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();
            daramadRuzane = new DaramadRuzane(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5) , cursor.getString(6), cursor.getString(7));
            cursor.close();
            db.close();
        } catch (Exception e) {
        }
        return daramadRuzane;
    }
    public void BayganiDaramad (ArrayList<DaramadRuzane> daramadRuzanes , String status) {
        for (int i=0 ; i<daramadRuzanes.size() ; i++){
            DaramadRuzane daramadRuzane = daramadRuzanes.get(i);
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_DARAMADRUZANEDAY, daramadRuzane.getDay());
            values.put(KEY_DARAMADRUZANEDATE, daramadRuzane.getDate());
            values.put(KEY_DARAMADRUZANEMABLAGH, daramadRuzane.getMablagh());
            values.put(KEY_DARAMADRUZANEDES,daramadRuzane.getDes());
            values.put(KEY_DARAMADRUZANESHIFT, daramadRuzane.getShift());
            values.put(KEY_DARAMADRUZANEISBAYGANI, "true");
            values.put(KEY_DARAMADRUZANESTATUS, status);
            int result = db.update(TABLE_DARAMADRUZANE, values, KEY_DARAMADRUZANEID+ "=?", new String[]{String.valueOf(daramadRuzane.getId())});
            db.close();
        }
    }
    public ArrayList<String> getAllDaramaddStatusBaygani() {
        ArrayList<String> status= new ArrayList<String>();
        String selectQuery = "SELECT DISTINCT "+KEY_DARAMADRUZANESTATUS+" FROM " + TABLE_DARAMADRUZANE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                status.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return status;
    }
    public ArrayList<DaramadRuzane> getAllDaramadByStatus(String status) {
        ArrayList<DaramadRuzane> daramadRuzanes = new ArrayList<DaramadRuzane>();

        String selectQuery = "SELECT * FROM " + TABLE_DARAMADRUZANE + " WHERE "+KEY_DARAMADRUZANEISBAYGANI+"='true' AND "+KEY_DARAMADRUZANESTATUS + "='"+status+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                DaramadRuzane daramadRuzane= new DaramadRuzane(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
                daramadRuzanes.add(daramadRuzane);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return daramadRuzanes;
    }
}
