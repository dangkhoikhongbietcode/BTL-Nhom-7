package com.example.btl_nhom_7.fragment;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.btl_nhom_7.Motor.Motor;
import com.example.btl_nhom_7.Motor.MotorAdapter;
import com.example.btl_nhom_7.Motor.MotorDatabaseHelper;
import com.example.btl_nhom_7.Photo.adapter.PhotoViewPagerAdapter;
import com.example.btl_nhom_7.Photo.model.Photo;
import com.example.btl_nhom_7.R;
import com.example.btl_nhom_7.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ViewPager mViewPager;
    private CircleIndicator mCircleIndicator;
    private List<Photo> mListPhoto = new ArrayList<>();

    private RecyclerView rcv;

    ActivityHomeBinding binding;

    private MotorAdapter motorAdapter;

    private List<Motor> motorList = new ArrayList<>();
    private MotorDatabaseHelper databaseHelper;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        databaseHelper = new MotorDatabaseHelper(getContext());

//        List<String> namesToDelete = new ArrayList<>();
//        namesToDelete.add("Honda");
//        namesToDelete.add("Yamaha");
//        namesToDelete.add("Suzuki");
//        namesToDelete.add("Kawasaki");
//        deleteMultipleMotors(namesToDelete);
//        addMotor("Honda", 1000);
//        addMotor("Yamaha", 1500);
//        addMotor("Suzuki", 1200);
//        addMotor("Kawasaki", 1800);

        databaseHelper.createMotor(new Motor ("Honda" , 1000 ,  "https://cdn.honda.com.vn/motorbike-versions/April2023/2pSqu65qdei9HLkHfOtJ.png"));


        motorList = databaseHelper.getAllMotor();

//        motorList.add(new Motor ("Honda" , 1000 ,  "https://cdn.honda.com.vn/motorbike-versions/April2023/2pSqu65qdei9HLkHfOtJ.png")) ;
//        motorList.add(new Motor ("Yamaha" , 1500 ,  "https://cdn.honda.com.vn/motorbike-versions/April2023/2pSqu65qdei9HLkHfOtJ.png")) ;
//        motorList.add(new Motor ("Suzuki" , 1200 ,  "https://cdn.honda.com.vn/motorbike-versions/April2023/2pSqu65qdei9HLkHfOtJ.png")) ;
//        motorList.add(new Motor ("Kawasaki" , 1800 ,  "https://cdn.honda.com.vn/motorbike-versions/April2023/2pSqu65qdei9HLkHfOtJ.png")) ;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mViewPager = view.findViewById(R.id.view_page);
        mCircleIndicator = view.findViewById(R.id.circle_indicator);
        rcv = view.findViewById(R.id.list_motor);
        mListPhoto = getListPhoto();

        PhotoViewPagerAdapter adapter = new PhotoViewPagerAdapter(mListPhoto);
        mViewPager.setAdapter(adapter);
        mCircleIndicator.setViewPager(mViewPager);

        // RCV Adapter

        motorAdapter = new MotorAdapter(getContext(),motorList);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false );
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(motorAdapter);

        return view;
    }

    private List<Photo> getListPhoto() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.bgimg_1));
        list.add(new Photo(R.drawable.bgimg_2));
        list.add(new Photo(R.drawable.bgimg_3));
        list.add(new Photo(R.drawable.bgimg_4));

        return list;
    }



}