package com.example.btl_nhom_7.Motor;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_nhom_7.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MotorActivity extends AppCompatActivity {

    private RecyclerView listView;
    private List<String> motorList;
    private ArrayAdapter<String> adapter;
    private MotorDatabaseHelper databaseHelper;
    private EditText nameEditText;
    private EditText priceEditText;
    private Button addButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor);

//        listView = findViewById(R.id.recyclerView);
        motorList = new ArrayList<>();
        nameEditText = findViewById(R.id.nameEditText);
        priceEditText = findViewById(R.id.priceEditText);
        addButton = findViewById(R.id.addButton);

        databaseHelper = new MotorDatabaseHelper(this);

        // Khởi tạo adapter
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, motorList);
//        listView.setAdapter(adapter);

        loadMotorList(); // Load danh sách xe máy tu csdl

        // khi chon 1 muc trong xe may
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItem = motorList.get(position);
//                // chuyen den trang chi tiet xe may
//                Intent intent = new Intent(MotorActivity.this, MotorDetailActivity.class);
//                intent.putExtra("selectedItem", selectedItem);
//                startActivity(intent);
//            }
//        });

        // Xu ly khi nut them duoc an
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                int price = Integer.parseInt(priceEditText.getText().toString());
                addMotor(name, price);
                nameEditText.setText("");
                priceEditText.setText("");
            }
        });
    }

    // Load danh sách xe máy từ cơ sở dữ liệu
    private void loadMotorList() {
        motorList.clear();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String[] projection = {"name"};
        String sortOrder = "name ASC";
        Cursor cursor = db.query("motor", projection, null, null, null, null, sortOrder);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            motorList.add(name);
        }
        cursor.close();
        db.close();
        adapter.notifyDataSetChanged();
        // Trong phương thức loadMotorList()
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            int price = cursor.getInt(cursor.getColumnIndexOrThrow("price"));
            String imageName = cursor.getString(cursor.getColumnIndexOrThrow("image"));

            motorList.add(name);

            @SuppressLint("DiscouragedApi") int imageResId = getResources().getIdentifier(imageName, "drawable", getPackageName());

            // Inflate layout tùy chỉnh cho mỗi mục
            View listItemView = getLayoutInflater().inflate(R.layout.list_item_motor, null);

            // Đặt thông tin xe máy và hình ảnh cho mỗi mục
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textViewName = listItemView.findViewById(R.id.textViewName);
            textViewName.setText(name);

            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textViewPrice = listItemView.findViewById(R.id.textViewPrice);
            textViewPrice.setText("Price: $" + price);

            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView imageView = listItemView.findViewById(R.id.imageView);
            imageView.setImageResource(imageResId);

            // Thêm mục vào danh sách
            listView.addView(listItemView);
        }

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

    // Sap xep xe may theo ten
    private void sortMotorList() {
        Collections.sort(motorList, new Comparator<String>() {
            @Override
            public int compare(String motor1, String motor2) {
                // So sánh theo tên xe máy
                return motor1.compareTo(motor2);
            }
        });
        adapter.notifyDataSetChanged(); // Cập nhật giao diện
    }

    // Xoa nhieu xe may theo ten xe
    private void deleteMultipleMotors(List<String> names) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        for (String name : names) {
            String selection = "name = ?";
            String[] selectionArgs = {name};
            db.delete("motor", selection, selectionArgs);
        }
        db.close();
        loadMotorList();
    }

}
