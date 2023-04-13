package com.rsjms.daftar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rsjms.daftar.Model.jadwalModel;
import com.rsjms.daftar.R;


import java.util.ArrayList;


public class JadwalAdafter extends RecyclerView.Adapter<JadwalAdafter.ViewHolder> {
     private final ArrayList<jadwalModel> jadwalModelArrayList;
     private final Context context;

     public JadwalAdafter(ArrayList<jadwalModel> jadwalModelArrayList, Context context){
         this.jadwalModelArrayList = jadwalModelArrayList;
         this.context = context;
     }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_jadwal,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         jadwalModel jadwalModels = jadwalModelArrayList.get(position);

        holder.tvNamaDokter.setText(jadwalModels.getNmDokter());
        holder.tvNamaPoli.setText(jadwalModels.getNmPoli());
        holder.tvJamMulai.setText(jadwalModels.getJamMulai());
        holder.tvJamSelesai.setText(jadwalModels.getJamSelesai());
    }

    @Override
    public int getItemCount() {
        return jadwalModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
       private final TextView tvNamaDokter,tvNamaPoli,tvJamMulai,tvJamSelesai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaDokter = itemView.findViewById(R.id.txNamaDokter);
            tvNamaPoli = itemView.findViewById(R.id.txNamaPoli);
            tvJamMulai = itemView.findViewById(R.id.jammulai);
            tvJamSelesai = itemView.findViewById(R.id.jamSelesai);

        }
    }


}
