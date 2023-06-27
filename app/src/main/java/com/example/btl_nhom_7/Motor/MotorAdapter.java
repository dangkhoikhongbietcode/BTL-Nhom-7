package com.example.btl_nhom_7.Motor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_nhom_7.R;

import java.util.List;

public class MotorAdapter extends RecyclerView.Adapter<MotorAdapter.MotorViewHolder> {
    private List<Integer> imageList = null;
    private List<String> motorList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MotorAdapter(List<String> motorList) {
        this.motorList = motorList;
        this.imageList = imageList;
    }


    @NonNull
    @Override
    public MotorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_motor, parent, false);
        return new MotorViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MotorViewHolder holder, int position) {
        String motorName = motorList.get(position);
        int motorImage = imageList.get(position);
        holder.nameTextView.setText(motorName);
        holder.imageView.setImageResource(motorImage);
    }


    @Override
    public int getItemCount() {
        return motorList.size();
    }

    public class MotorViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        private TextView nameTextView;

        public MotorViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public void bind(String motor) {
            nameTextView.setText(motor);
        }
    }
}
