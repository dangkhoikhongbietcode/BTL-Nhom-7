package com.example.btl_nhom_7.fragment;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
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
import com.example.btl_nhom_7.R;
import com.example.btl_nhom_7.Warranty.NotificationReceiver;
import com.example.btl_nhom_7.database.DatabaseHelper;
import com.example.btl_nhom_7.Warranty.DisplayActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
    private SharedPreferences sharedPreferences;

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
        sharedPreferences = requireActivity().getSharedPreferences("Warranty", Context.MODE_PRIVATE);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NameWarranty", nameWarranty);
        editor.putString("WarrantyDate", dateWarranty);
        editor.putString("Option", service);
        editor.putString("WarrantyMotor", selectMotor);
        editor.putString("WarrantyStore", selectStore);
        editor.apply();

        Calendar currentCalendar = Calendar.getInstance();
        int currentYear = currentCalendar.get(Calendar.YEAR);
        int currentMonth = currentCalendar.get(Calendar.MONTH);
        int currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH);

        try {
            Calendar warrantyCalendar = Calendar.getInstance();
            warrantyCalendar.setTime(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(dateWarranty));
            int warrantyYear = warrantyCalendar.get(Calendar.YEAR);
            int warrantyMonth = warrantyCalendar.get(Calendar.MONTH);
            int warrantyDay = warrantyCalendar.get(Calendar.DAY_OF_MONTH);

            if (warrantyYear == currentYear && warrantyMonth == currentMonth && warrantyDay == currentDay) {
                showNotification(); // Hiển thị thông báo nếu ngày trùng khớp
            } else if (warrantyCalendar.after(currentCalendar)) {
                // Tính toán khoảng thời gian giữa hiện tại và ngày hết hạn bảo hành
                long timeDiff = warrantyCalendar.getTimeInMillis() - currentCalendar.getTimeInMillis();
                long daysDiff = timeDiff / (24 * 60 * 60 * 1000); // Đổi ra số ngày

                // Đặt thông báo tự động trước ngày hết hạn bảo hành
                scheduleNotification(daysDiff);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private void scheduleNotification(long daysDiff) {
        int notificationId = 1;

        // Tạo công việc thông báo
        Intent notificationIntent = new Intent(requireContext(), NotificationReceiver.class);
        notificationIntent.putExtra("notificationId", notificationId);
        notificationIntent.putExtra("message", "Hôm nay là ngày hết hạn bảo hành.");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(requireContext(), notificationId, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Tính toán thời gian thông báo
        Calendar notificationCalendar = Calendar.getInstance();
        notificationCalendar.add(Calendar.DAY_OF_MONTH, (int) -daysDiff);
        notificationCalendar.set(Calendar.HOUR_OF_DAY, 0); // Đặt giờ thông báo vào 0 giờ sáng
        notificationCalendar.set(Calendar.MINUTE, 0);
        notificationCalendar.set(Calendar.SECOND, 0);

        // Đặt thông báo tự động
        AlarmManager alarmManager = (AlarmManager) requireContext().getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, notificationCalendar.getTimeInMillis(), pendingIntent);
            } else {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, notificationCalendar.getTimeInMillis(), pendingIntent);
            }
        }
    }

    private void showNotification() {
        // Tạo kênh thông báo (chỉ cần thực hiện trên Android 8.0+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("warranty_channel", "Warranty Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = requireContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        // Intent khi nhấn vào thông báo
        Intent intent = new Intent(requireContext(), DisplayActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Xây dựng thông báo
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), "warranty_channel")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Thông báo bảo hành")
                .setContentText("Ngày đăng kí bảo hành đã đến. Đến cửa hàng của chúng tôi thôi nào. Géc Gô!!!!!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        // Hiển thị thông báo
        NotificationManager notificationManager = requireContext().getSystemService(NotificationManager.class);
        notificationManager.notify(1, builder.build());
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
