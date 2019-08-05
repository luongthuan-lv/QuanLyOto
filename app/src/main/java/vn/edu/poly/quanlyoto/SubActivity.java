package vn.edu.poly.quanlyoto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.util.List;

public class SubActivity extends AppCompatActivity {
    private EditText edtMa, edtTen, edtNam, edtGia;
    private CarDao carDao;
    private CarAdapter carAdapter;
    private List<Car> carList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        edtMa = findViewById(R.id.edtMa);
        edtTen = findViewById(R.id.edtTen);
        edtNam = findViewById(R.id.edtNam);
        edtGia = findViewById(R.id.edtGia);

        Intent intent = getIntent();
        edtMa.setText(intent.getStringExtra("idx"));
        edtTen.setText(intent.getStringExtra("namex"));
        edtNam.setText(intent.getStringExtra("yearx"));
        edtGia.setText(intent.getStringExtra("pricex"));
    }
}
