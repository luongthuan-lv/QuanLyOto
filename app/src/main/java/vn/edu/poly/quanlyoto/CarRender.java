package vn.edu.poly.quanlyoto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CarRender extends SQLiteOpenHelper {


    public static final String TABLE_NAME="car";
    public static final String COL_ID="id";
    public static final String COL_NAME="name";
    public static final String COL_YEAR="year";
    public static final String COL_PRICE="price";

    public CarRender (Context context){
        super(context,"student.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("+COL_ID+" INT PRIMARY KEY ,"+COL_NAME+" TEXT, "+COL_YEAR+" INT, "+COL_PRICE+" FLOAT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
