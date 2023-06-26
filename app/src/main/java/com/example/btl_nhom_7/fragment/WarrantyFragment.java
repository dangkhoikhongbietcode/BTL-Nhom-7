package com.example.btl_nhom_7.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
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
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.btl_nhom_7.Home;
import com.example.btl_nhom_7.R;

import java.util.Calendar;

public class WarrantyFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText editPhoneNumberWarranty, edtNameWarranty, edtDay;
    DatePickerDialog datePickerDialog;
    String[] items = {"Kiểm tra định kì", "Sửa chữa"};
    AutoCompleteTextView autoCompleteOption;
    ArrayAdapter<String> adapterItems;
    private String mParam1;
    private String mParam2;

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
        adapterItems = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, items);
        autoCompleteOption.setAdapter(adapterItems);

        edtNameWarranty = view.findViewById(R.id.edtNameWarranty);
        editPhoneNumberWarranty = view.findViewById(R.id.editPhoneNumberWarranty);

        edtDay = view.findViewById(R.id.edtDate);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_warranty, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mnu_warranty) {
            Log.d("this", "cart show here");
            Intent intent = new Intent(getActivity(), Home.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
