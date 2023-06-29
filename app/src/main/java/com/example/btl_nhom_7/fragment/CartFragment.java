package com.example.btl_nhom_7.fragment;

import static com.example.btl_nhom_7.Cart.Cart.totalPrice;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.btl_nhom_7.Cart.Cart;
import com.example.btl_nhom_7.Cart.CartAdapter;
import com.example.btl_nhom_7.Motor.MotorAdapter;
import com.example.btl_nhom_7.R;
import com.example.btl_nhom_7.until.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /// --------

    private RecyclerView recyclerView ;
    private CartAdapter cartAdapter;

    private TextView tvTotalPrice;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // gan layout cho fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        tvTotalPrice = view.findViewById(R.id.tvTotalPrice);
        recyclerView = view.findViewById(R.id.list_cart);
        cartAdapter = new CartAdapter(getContext(), Cart.getCartItem());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false );
        recyclerView.setLayoutManager(layoutManager);
        cartAdapter.setOnDataChangeListener(new CartAdapter.OnDataChangeListener() {
            @Override
            public void onDataChanged() {
                tvTotalPrice.setText("Tổng giá tiền hàng: " +(Utils.unitPrase(Cart.totalPrice()))+ "VND");
            }
        });
        recyclerView.setAdapter(cartAdapter);

        tvTotalPrice.setText("Tổng giá tiền hàng: " + (Utils.unitPrase(Cart.totalPrice()))+ "VND");



        return view;
    }
}