package vn.edu.poly.quanlyoto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static vn.edu.poly.quanlyoto.CarRender.COL_ID;
import static vn.edu.poly.quanlyoto.CarRender.COL_NAME;
import static vn.edu.poly.quanlyoto.CarRender.COL_PRICE;
import static vn.edu.poly.quanlyoto.CarRender.COL_YEAR;
import static vn.edu.poly.quanlyoto.CarRender.TABLE_NAME;

public class CarDao {
    public CarRender carRender;

    public CarDao(Context context) {
        carRender = new CarRender(context);

    }

    public long indertStudent(Car car) {
        SQLiteDatabase sqLiteDatabase = carRender.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, car.id);
        contentValues.put(COL_NAME, car.name);
        contentValues.put(COL_YEAR, car.year);
        contentValues.put(COL_PRICE, car.price);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        return result;
    }

    public List<Car> getAllStudents() {
        SQLiteDatabase sqLiteDatabase = carRender.getReadableDatabase();

        List<Car> carList = new ArrayList<>();
        String truyVan = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(truyVan, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Car car = new Car();

                car.id = cursor.getString(cursor.getColumnIndex(COL_ID));
                car.name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                car.year=cursor.getInt(cursor.getColumnIndex(COL_YEAR));
                car.price=cursor.getFloat(cursor.getColumnIndex(COL_PRICE));

                carList.add(car);


                cursor.moveToNext();
            }
            cursor.close();

        }

        return carList;
    }

    public void deleteSudent(String id) {
        SQLiteDatabase sqLiteDatabase = carRender.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, COL_ID + "=?", new String[]{id});


    }

    public long updateStudent(Car car) {
        SQLiteDatabase sqLiteDatabase = carRender.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, car.id);
        contentValues.put(COL_NAME, car.name);
        contentValues.put(COL_YEAR, car.year);
        contentValues.put(COL_PRICE, car.price);

        //cau lenh them vao cs du lieu
        long result = sqLiteDatabase.update(TABLE_NAME, contentValues, COL_ID + "=?", new String[]{String.valueOf(car.id)});

        return result;
    }

    public List<Car> SearchStudents(String id) {
        SQLiteDatabase sqLiteDatabase = carRender.getReadableDatabase();

        List<Car> carList = new ArrayList<>();
        String truyVan = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + " = " + id;
        Cursor cursor = sqLiteDatabase.rawQuery(truyVan, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Car car = new Car();

                car.id = cursor.getString(cursor.getColumnIndex(COL_ID));
                car.name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                car.year=cursor.getInt(cursor.getColumnIndex(COL_YEAR));
                car.price=cursor.getFloat(cursor.getColumnIndex(COL_PRICE));



                carList.add(car);


                cursor.moveToNext();
            }
            cursor.close();

        }

        return carList;
    }
}
