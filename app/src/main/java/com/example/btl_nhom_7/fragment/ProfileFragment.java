package com.example.btl_nhom_7.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_nhom_7.MainActivity;
import com.example.btl_nhom_7.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView tvGetIdOnLogo,tvGetIdOnUserProfile,tvGetPhoneNumberOnUserProfile,
        textView18,textView20;
    Button btnUserProfile,btnLogOut,btnChangePassword;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        tvGetIdOnLogo = view.findViewById(R.id.tvGetIdOnLogo);
        tvGetIdOnUserProfile = view.findViewById(R.id.tvGetIdOnUserProfile);
        tvGetPhoneNumberOnUserProfile = view.findViewById(R.id.tvGetPhoneNumberOnUserProfile);
        textView18 = view.findViewById(R.id.textView18);
        textView20 = view.findViewById(R.id.textView20);
        btnUserProfile = view.findViewById(R.id.btnUserProfile);
        btnLogOut = view.findViewById(R.id.btnLogOut);
        btnChangePassword = view.findViewById(R.id.btnChangePassword);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            // Người dùng đã đăng nhập
            String phoneNumber = sharedPreferences.getString("phoneNumber", "");
            int id = sharedPreferences.getInt("id", 0);
            tvGetPhoneNumberOnUserProfile.setText(phoneNumber);
            tvGetIdOnLogo.setText(String.valueOf(id));
            tvGetIdOnUserProfile.setText(String.valueOf(id));
        }
        btnUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView18.getVisibility()==View.VISIBLE){
                    textView18.setVisibility(View.INVISIBLE);
                    textView20.setVisibility(View.INVISIBLE);
                    tvGetIdOnUserProfile.setVisibility(View.INVISIBLE);
                    tvGetPhoneNumberOnUserProfile.setVisibility(View.INVISIBLE);
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)
                            btnChangePassword.getLayoutParams();
                    params.setMargins(0, 0, 0, 0); // Thay đổi giá trị của khoảng cách tại đây
                    btnChangePassword.setLayoutParams(params);
                    btnChangePassword.requestLayout();
                }
                else if (textView18.getVisibility()==View.INVISIBLE){
                    textView18.setVisibility(View.VISIBLE);
                    textView20.setVisibility(View.VISIBLE);
                    tvGetIdOnUserProfile.setVisibility(View.VISIBLE);
                    tvGetPhoneNumberOnUserProfile.setVisibility(View.VISIBLE);
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)
                            btnChangePassword.getLayoutParams();
                    params.setMargins(0, 350, 0, 0); // Thay đổi giá trị của khoảng cách tại đây
                    btnChangePassword.setLayoutParams(params);
                    btnChangePassword.requestLayout();
                }
            }
        });
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xóa thông tin đăng nhập
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("isLoggedIn");
                editor.remove("phoneNumber");
                editor.remove("id");
                editor.remove("password");
                editor.apply();
                // Điều hướng đến màn hình đăng nhập
                Toast.makeText(getContext(),"Bạn đã đăng xuất",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish(); // Kết thúc Fragment hiện tại (tuỳ chọn)
            }
        });
        return view;
    }
}