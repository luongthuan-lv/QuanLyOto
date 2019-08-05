package vn.edu.poly.quanlyoto;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edtId, edtName, edtYear, edtPrice, edtSearch;
    ListView lvList;
    private CarDao carDao;
    private CarAdapter carAdapter;
    private List<Car> carList;
    Button btnThem, btnSxyear, btnSxPrice, btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        edtYear = findViewById(R.id.edtYear);
        edtSearch = findViewById(R.id.edtSearch);
        btnThem = findViewById(R.id.btnThem);
        btnSearch = findViewById(R.id.btnSearch);
        btnSxPrice = findViewById(R.id.btnSxPrice);
        btnSxyear = findViewById(R.id.btnSxyear);

        carList = new ArrayList<>();
        lvList = findViewById(R.id.lvList);

        carDao = new CarDao(MainActivity.this);
        carList = carDao.getAllStudents();
        carAdapter = new CarAdapter(MainActivity.this, carList, carDao);
        lvList.setAdapter(carAdapter);


        lvList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Bạn có muốn xóa dữ liệu?");
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Đã hủy thao tác!", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            carDao = new CarDao(MainActivity.this);
                            carList = carDao.getAllStudents();
                            carAdapter = new CarAdapter(MainActivity.this, carList, carDao);
                            lvList.setAdapter(carAdapter);

                            Car car = carList.get(position);
                            carDao.deleteSudent(car.id);
                            carList.remove(position);
                            Toast.makeText(MainActivity.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {

                        }
                    }
                });

                builder.create();
                builder.show();

                return false;
            }
        });

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);

                Car car = carList.get(position);
                intent.putExtra("idx", car.id);
                intent.putExtra("namex", car.name);
                intent.putExtra("yearx", car.year + "");
                intent.putExtra("pricex", car.price + "");
                startActivity(intent);
            }
        });
    }


    public void ThemNhanVien(View view) {
        try {

            carDao = new CarDao(MainActivity.this);
            String id = edtId.getText().toString().trim();
            String name = edtName.getText().toString().trim();
            String year = edtYear.getText().toString().trim();
            String price = edtPrice.getText().toString().trim();

            if (id.equals("")) {
                Toast.makeText(this, "Vui lòng nhập id !", Toast.LENGTH_SHORT).show();
            } else if (name.equals("")) {
                Toast.makeText(this, "Vui lòng name !", Toast.LENGTH_SHORT).show();
            } else if (year.equals("")) {
                Toast.makeText(this, "Vui lòng year !", Toast.LENGTH_SHORT).show();
            } else if (Integer.valueOf(year) <= 0) {
                Toast.makeText(this, "Vui lòng year là số lớn hơn 0!", Toast.LENGTH_SHORT).show();
            } else if (price.equals("")) {
                Toast.makeText(this, "Vui lòng price !", Toast.LENGTH_SHORT).show();
            } else if (Float.valueOf(price) <= 0) {
                Toast.makeText(this, "Vui lòng price là số lớn hơn 0!", Toast.LENGTH_SHORT).show();
            } else {

                Car car = new Car();

                car.id = id;
                car.name = name;
                car.year = Integer.valueOf(year);
                car.price = Float.valueOf(price);
                long result = carDao.indertStudent(car);

                carList = carDao.getAllStudents();
                carAdapter = new CarAdapter(MainActivity.this, carList, carDao);
                lvList.setAdapter(carAdapter);

                edtId.setText("");
                edtName.setText("");
                edtYear.setText("");
                edtPrice.setText("");


                if (result > 0) {

                    Toast.makeText(this, "Thêm Thành Công!", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(this, "Thêm Không Thành Công!", Toast.LENGTH_SHORT).show();

                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Vui lòng nhập là số!", Toast.LENGTH_SHORT).show();
        }


    }


    public void sxtheonam(View view) {
        if (carList.size() > 0) {
            Comparator<Car> comparatorz = new Comparator<Car>() {
                @Override
                public int compare(Car o1, Car o2) {
                    Integer d1 = o2.getYear();
                    Integer d2 = o1.getYear();
                    return d1.compareTo(d2);
                }
            };

            Collections.sort(carList, comparatorz);
            carAdapter = new CarAdapter(MainActivity.this, carList, carDao);
            lvList.setAdapter(carAdapter);
            Toast.makeText(MainActivity.this, "Sắp xếp hoàn tất", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();

        }
    }


    public void sxtheogia(View view) {
        if (carList.size() > 0) {
            Comparator<Car> comparatorz = new Comparator<Car>() {
                @Override
                public int compare(Car o1, Car o2) {
                    Float d1 = o1.getPrice();
                    Float d2 = o2.getPrice();
                    return d1.compareTo(d2);
                }
            };

            Collections.sort(carList, comparatorz);
            carAdapter = new CarAdapter(MainActivity.this, carList, carDao);
            lvList.setAdapter(carAdapter);
            Toast.makeText(MainActivity.this, "Sắp xếp hoàn tất", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();

        }
    }

    public void Timkiem(View view) {
        String maid = edtSearch.getText().toString().trim();
        boolean kq = false;

        for (int i = 0; i < carList.size(); i++) {
            Car car = carList.get(i);
            if (car.getId().equalsIgnoreCase(maid)) {

                edtId.setText(car.id);
                edtName.setText(car.name);
                edtYear.setText(car.year + "");
                edtPrice.setText(car.price + "");

                kq = true;
                break;

            }
        }
        if (!kq) {
            Toast.makeText(MainActivity.this, "Mã nhân viên không có!", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(MainActivity.this, "Tìm kiếm thành công!", Toast.LENGTH_SHORT).show();
        }
    }
}
