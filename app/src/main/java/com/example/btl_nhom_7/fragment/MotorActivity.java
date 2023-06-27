package com.example.btl_nhom_7.fragment;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_nhom_7.Motor.MotorAdapter;
import com.example.btl_nhom_7.Motor.MotorDatabaseHelper;
import com.example.btl_nhom_7.R;

import java.util.ArrayList;
import java.util.List;

public class MotorActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> motorList;
    private MotorAdapter adapter;
    private MotorDatabaseHelper databaseHelper;
    private EditText nameEditText;
    private EditText priceEditText;
    private Button addButton;
    private ImageView idImage;

    private List<Integer> imageList;

    public MotorActivity(MotorAdapter adapter, ImageView idImage, List<Integer> imageList) {
        this.adapter = adapter;
        this.idImage = idImage;
        this.imageList = imageList;
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor);

        recyclerView = findViewById(R.id.recyclerView);
        motorList = new ArrayList<>();
        nameEditText = findViewById(R.id.nameEditText);
        priceEditText = findViewById(R.id.priceEditText);
        addButton = findViewById(R.id.addButton);


        databaseHelper = new MotorDatabaseHelper(this);

        // Khởi tạo adapter
//        adapter = new MotorAdapter(motorList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        loadMotorList(); // Load danh sách xe máy từ CSDL

        // Khi chọn một mục trong danh sách xe máy
        adapter.setOnItemClickListener(new MotorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String selectedItem = motorList.get(position);
                // Chuyển đến trang chi tiết xe máy
                Intent intent = new Intent(com.example.btl_nhom_7.fragment.MotorActivity.this, MotorDetailActivity.class);
                intent.putExtra("selectedItem", selectedItem);
                startActivity(intent);
            }
        });

        // Xử lý khi nút "Thêm" được nhấn
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String priceString = priceEditText.getText().toString();
                if (!name.isEmpty() && !priceString.isEmpty()) {
                    int price = Integer.parseInt(priceString);
                    addMotor(name, price);
                    nameEditText.setText("");
                    priceEditText.setText("");
                } else {
                    Toast.makeText(com.example.btl_nhom_7.fragment.MotorActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Load danh sách xe máy từ cơ sở dữ liệu
    private void loadMotorList() {
        motorList.clear();
        imageList.clear();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String[] projection = {"name", "image"};
        String sortOrder = "name ASC";
        Cursor cursor = db.query("motor", projection, null, null, null, null, sortOrder);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            int image = cursor.getInt(cursor.getColumnIndexOrThrow("image"));
            motorList.add(name);
            imageList.add(image);
        }
        cursor.close();
        db.close();
        adapter.notifyDataSetChanged();

        View listItemView = getLayoutInflater().inflate(R.layout.list_item_motor, null);

    }

    // Thêm sản phẩm xe máy vào cơ sở dữ liệu
    private void addMotor(String name, int price) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("price", price);
        db.insert("motor", null, values);
        db.close();
        loadMotorList();
    }
}
