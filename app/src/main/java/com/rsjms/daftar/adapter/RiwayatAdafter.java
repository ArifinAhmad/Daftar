package com.rsjms.daftar.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rsjms.daftar.Model.RiwayatModel;
import com.rsjms.daftar.R;
import com.rsjms.daftar.detail_riwayat;

import java.util.ArrayList;

public class RiwayatAdafter extends RecyclerView.Adapter<RiwayatAdafter.ViewHolder> {
    private final ArrayList<RiwayatModel> riwayatModelArrayList;
    private final Context context;
    private String noRawat;

    public RiwayatAdafter(ArrayList<RiwayatModel> riwayatModelArrayList, Context context) {
        this.riwayatModelArrayList = riwayatModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_riwayat,parent, false) ;
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RiwayatModel riwayatModel = riwayatModelArrayList.get(position);

        holder.tvTglregister.setText(riwayatModel.getMtgl_registrasi());
        holder.tvNorawat.setText(riwayatModel.getMnorawat());
        holder.tvNmpoli.setText(riwayatModel.getMnmPoli());
        holder.tvnmDokter.setText(riwayatModel.getMnmDokter());
        holder.tvStatus.setText(riwayatModel.getMnstatus());
        holder.tvPng.setText(riwayatModel.getMcarabayar());
        holder.tvNoantrian.setText(riwayatModel.getMnoantrian());
        holder.card_riwayat.setOnClickListener((View view) ->{
            noRawat = riwayatModel.getMnorawat();
            Intent intent = new Intent(context,detail_riwayat.class);
            intent.putExtra("no_rawat", noRawat);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return riwayatModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTglregister, tvNorawat, tvNmpoli,tvnmDokter, tvStatus, tvPng, tvNoantrian;
        private final CardView card_riwayat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTglregister = itemView.findViewById(R.id.VtglKunjungan);
            tvNorawat = itemView.findViewById(R.id.Vnorawat);
            tvNmpoli = itemView.findViewById(R.id.VnmaPoli);
            tvnmDokter = itemView.findViewById(R.id.VnmDokter);
            tvStatus = itemView.findViewById(R.id.Vstatus);
            tvPng = itemView.findViewById(R.id.Vpngjawab);
            tvNoantrian = itemView.findViewById(R.id.Vnoantri);
            card_riwayat = itemView.findViewById(R.id.card_riwayat);

        }
    }

//    private void detailRawat(String norawat){
//        Intent intent = new Intent(context,detail_riwayat.class);
//        intent.putExtra("no_rawat", norawat);
//        context.startActivity(intent);
//    }
}
