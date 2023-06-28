package com.example.btl_nhom_7.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.btl_nhom_7.Home;
import com.example.btl_nhom_7.R;
import com.example.btl_nhom_7.database.DatabaseHelper;
import com.example.btl_nhom_7.Warranty.DisplayActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class WarrantyFragment extends Fragment {
    public DatabaseHelper dbHelper;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextInputEditText edtNameWarranty;
    EditText edtDay;
    DatePickerDialog datePickerDialog;
    String[] items = {"Kiểm tra định kì", "Sửa chữa"};
    String[] items2 = {"Air Blade", "Wave RS", "Wave RSX"};
    String[] items3 = {"Long Biên", "Đống Đa", "Hai Bà Trưng"};
    AutoCompleteTextView autoCompleteOption, autoCompleteMotor, autoCompleteStore;
    Button btnSubmitWarranty;
    ArrayAdapter<String> adapterItems;
    ArrayAdapter<String> adapterItems2;
    ArrayAdapter<String> adapterItems3;
    private String mParam1;
    private String mParam2;
    DatabaseHelper userDatabaseHelper;
    public WarrantyFragment() {
        // Required empty public constructor
    }

    public static WarrantyFragment newInstance(String param1, String param2) {
        WarrantyFragment fragment = new WarrantyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_warranty, container, false);
        autoCompleteOption = view.findViewById(R.id.autoCompleteOption);
        autoCompleteMotor = view.findViewById(R.id.autoCompleteMotor);
        autoCompleteStore = view.findViewById(R.id.autoCompleteStore);
        adapterItems = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, items);
        autoCompleteOption.setAdapter(adapterItems);

        adapterItems2 = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, items2);
        autoCompleteMotor.setAdapter(adapterItems2);

        adapterItems3 = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, items3);
        autoCompleteStore.setAdapter(adapterItems3);

        edtNameWarranty = view.findViewById(R.id.edtNameWarranty);
        edtDay = view.findViewById(R.id.edtDate);
        btnSubmitWarranty = view.findViewById(R.id.btnSubmitWarranty);
        userDatabaseHelper = new DatabaseHelper(requireContext());
        btnSubmitWarranty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRegistrationForm();
                Toast.makeText(requireContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            }
        });
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            // Người dùng đã đăng nhập
            String phoneNumber = sharedPreferences.getString("phoneNumber", "");
            int id = sharedPreferences.getInt("id", 0);
        }
        dbHelper = new DatabaseHelper(requireContext());

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        edtDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                        edtDay.setText(selectedDayOfMonth + "/" + (selectedMonth + 1) + "/" + selectedYear);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        return view;
    }

    private void saveRegistrationForm() {
        String nameWarranty = edtNameWarranty.getText().toString();
        String dateWarranty = edtDay.getText().toString();
        String service = autoCompleteOption.getText().toString();
        String selectMotor = autoCompleteMotor.getText().toString();
        String selectStore = autoCompleteStore.getText().toString();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NameWarranty", nameWarranty);
        values.put("WarrantyDate", dateWarranty);
        values.put("Option", service);
        values.put("WarrantyMotor", selectMotor);
        values.put("WarrantyStore", selectStore);

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("Warranty", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NameWarranty", nameWarranty);
        editor.putString("WarrantyDate", dateWarranty);
        editor.putString("Option", service);
        editor.putString("WarrantyMotor", selectMotor);
        editor.putString("WarrantyStore", selectStore);
        editor.apply();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_warranty, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idItem = item.getItemId();
        if (idItem == R.id.mnu_warranty) {

            Intent intent = new Intent(requireActivity(), DisplayActivity.class);
            startActivity(intent);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}