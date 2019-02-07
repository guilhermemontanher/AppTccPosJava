package com.example.guilhermemontanher.apptccposjava.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.guilhermemontanher.apptccposjava.R;
import com.example.guilhermemontanher.apptccposjava.listener.RecyclerViewButtonClick;
import com.example.guilhermemontanher.apptccposjava.model.Param;

import java.util.List;

public class RecyclerAdapterParams extends RecyclerView.Adapter<RecyclerAdapterParams.MyViewHolder> {

    private Context context;
    private List<Param> listParam;

    private RecyclerViewButtonClick recyclerViewButtonClick;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvName;
        public TextView tvValue;
        ImageButton btRemove;

        public MyViewHolder(View view) {
            super(view);
            tvName =  view.findViewById(R.id.text_view_name);
            tvValue =  view.findViewById(R.id.text_view_value);
            btRemove =  view.findViewById(R.id.image_button_remove);

            btRemove.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerViewButtonClick.onClick(getAdapterPosition());
        }
    }


    public RecyclerAdapterParams(Context context, List<Param> listParam) {
        this.context = context;
        this.listParam = listParam;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_param, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Param param = listParam.get(position);
        holder.tvName.setText(param.getName());
        holder.tvValue.setText(param.getValue());
    }

    @Override
    public int getItemCount() {
        return listParam.size();
    }


    public void setOnClickRecyclerViewButtonClick(RecyclerViewButtonClick recyclerViewButtonClick) {
        this.recyclerViewButtonClick = recyclerViewButtonClick;
    }
}
