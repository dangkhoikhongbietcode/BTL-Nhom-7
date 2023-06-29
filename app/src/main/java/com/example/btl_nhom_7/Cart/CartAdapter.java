package com.example.btl_nhom_7.Cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_nhom_7.Motor.Motor;
import com.example.btl_nhom_7.R;
import com.example.btl_nhom_7.until.Utils;

import java.util.Map;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    public interface OnDataChangeListener {
        void onDataChanged();
    }
        private Map<Motor, Integer> cartList;
        private Context mContext;

        private OnDataChangeListener mListener;

    public CartAdapter( Context mContext ,Map<Motor, Integer> cartList) {
        this.cartList = cartList;
        this.mContext = mContext;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(itemView);
    }
    //key dai dien cho 1 san pham trong gio hang, con gia tri la value la so luong san pham
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Motor key = (Motor) cartList.keySet().toArray()[position];
        int value = cartList.get(key);

        holder.tvName.setText(key.getName());
        holder.tvPrice.setText(key.getPrice() + "VND");
        holder.tvQuantity.setText(value + "");

        holder.tvPlus.setOnClickListener(v-> {
            Cart.increaseQuantityItemInCart(key);
           notifyDataSetChanged();
           mListener.onDataChanged();
        });

        holder.tvSub.setOnClickListener(v-> {
            Cart.decreaseQuantityItemInCart(key);
            notifyDataSetChanged();
            mListener.onDataChanged();
        });

        holder.tvTotal.setText(Utils.unitPrase(key.getPrice() * value) + "VND");


    }


    @Override
    public int getItemCount() {
        return cartList.size();
    }


    // Phương thức để thiết lập lắng nghe sự thay đổi dữ liệu
    public void setOnDataChangeListener(OnDataChangeListener listener) {
        mListener = listener;
    }



    public class CartViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        private TextView tvName , tvPrice, tvPlus, tvSub, tvQuantity, tvTotal, tvTotalprice;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
           image = itemView.findViewById(R.id.imv_cart_item_image);
           tvName = itemView.findViewById(R.id.tv_cart_item_name);
           tvPrice = itemView.findViewById(R.id.tv_cart_item_price);
           tvPlus = itemView.findViewById(R.id.tv_cart_item_plus);
           tvSub = itemView.findViewById(R.id.tv_cart_item_subtract);
           tvQuantity = itemView.findViewById(R.id.tv_cart_item_quantity);
           tvTotal = itemView.findViewById(R.id.tv_cart_item_total);
           tvTotalprice=itemView.findViewById(R.id.tvTotalPrice);
        }

    }
}
