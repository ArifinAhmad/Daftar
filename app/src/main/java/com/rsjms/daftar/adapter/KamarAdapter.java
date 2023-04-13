package com.rsjms.daftar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rsjms.daftar.Model.KamarModel;
import com.rsjms.daftar.R;

import java.util.ArrayList;

public class KamarAdapter extends RecyclerView.Adapter<KamarAdapter.ViewHolder> {
    private final ArrayList<KamarModel> kamarModelArrayList;
    private final Context context;

    public KamarAdapter(ArrayList<KamarModel> kamarModelArrayList, Context context) {
        this.kamarModelArrayList = kamarModelArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_info_kamar,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        KamarModel kamarModel = kamarModelArrayList.get(position);

        holder.tvNamaKamar.setText(kamarModel.getNmKamar());
        holder.tvJumlah.setText(kamarModel.getnJumlah());
        holder.tvIsi.setText(kamarModel.getnIsi());
        holder.tvKosong.setText(kamarModel.getnKosong());
    }

    @Override
    public int getItemCount() {
        return kamarModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvNamaKamar, tvJumlah, tvIsi, tvKosong;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNamaKamar = itemView.findViewById(R.id.tv_NamaKamar);
            tvJumlah = itemView.findViewById(R.id.tv_jml_bed);
            tvIsi = itemView.findViewById(R.id.tv_jml_bed_terisi);
            tvKosong = itemView.findViewById(R.id.tv_jml_bed_kosong);
        }
    }
}
