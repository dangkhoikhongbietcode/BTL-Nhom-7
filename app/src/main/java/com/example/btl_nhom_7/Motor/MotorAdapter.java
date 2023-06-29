package com.example.btl_nhom_7.Motor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btl_nhom_7.Cart.Cart;
import com.example.btl_nhom_7.R;
import com.example.btl_nhom_7.fragment.MotorDetailActivity;
import com.example.btl_nhom_7.until.Utils;
import com.google.gson.Gson;

import java.util.List;

public class MotorAdapter extends RecyclerView.Adapter<MotorAdapter.MotorViewHolder> {
    private List<Motor> motorList;
    private OnItemClickListener listener;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MotorAdapter( Context context , List<Motor> motorList) {
        this.context = context;
        this.motorList = motorList;

    }


    @NonNull
    @Override
    public MotorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_motor, parent, false);
        return new MotorViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MotorViewHolder holder, int position) {
        Motor motor= motorList.get(position);

        holder.tvName.setText( "" + motor.getName());
        holder.tvPrice.setText(Utils.unitPrase(motor.getPrice()) + " VND");
//        holder.image.setImageResource(R.drawable.xemay1);
        holder.addToCartbtn.setOnClickListener(v-> {
            Cart.addItemToCart(motor, 1);
            Toast.makeText(context,  "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
        });

        // load ảnh url vào imageView
        Glide.with(context).load(motor.getImage()).into(holder.image);
//        holder.imageView.setImageResource(motorImage);
    }


    @Override
    public int getItemCount() {
        return motorList.size();
    }

    public class MotorViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        private TextView tvName;
        private TextView tvPrice;

        private ImageView addToCartbtn;

        private void gotoDetail () {
            Intent intent = new Intent(context, MotorDetailActivity.class);
            Motor motor = motorList.get(getLayoutPosition());
            Gson gson = new Gson();
            intent.putExtra("Motor", gson.toJson(motor));
            context.startActivity(intent);

        }

        public MotorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_motor_name);
            tvPrice = itemView.findViewById(R.id.tv_motor_price);
            image = itemView.findViewById(R.id.imv_motor_image);
            addToCartbtn = itemView.findViewById(R.id.iv_add_to_cart);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoDetail();
                }
            });
        }

    }
}
