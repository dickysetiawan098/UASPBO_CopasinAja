package com.unsia.pemrogramanberbasisobjek.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.unsia.pemrogramanberbasisobjek.CopasModel;
import com.unsia.pemrogramanberbasisobjek.R;

import java.util.ArrayList;

public class CopasAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CopasModel> CopasList = new ArrayList<>();

    public void setCopasList(ArrayList<CopasModel> CopasList) {
        this.CopasList = CopasList;
        notifyDataSetChanged(); // Tambahkan ini untuk memperbarui ListView saat data berubah
    }

    public CopasAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return CopasList.size();
    }

    @Override
    public Object getItem(int i) {
        return CopasList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;

        if (itemView == null) {
            itemView = LayoutInflater.from(context)
                    .inflate(R.layout.item_copas, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(itemView);

        CopasModel copas = (CopasModel) getItem(i);

        // Tambahkan log untuk memeriksa data
        Log.d("CopasAdapter", "ID: " + copas.getId() + ", Isi: " + copas.getIsiText());

        viewHolder.bind(copas);
        return itemView;
    }

    private class ViewHolder {
        private TextView TextID, TextIsi;

        ViewHolder(View view) {
            TextID = view.findViewById(R.id.TextID);
            TextIsi = view.findViewById(R.id.TextIsi);
        }

        void bind(CopasModel copas) {
            TextID.setText(copas.getId());
            TextIsi.setText(copas.getIsiText());
        }
    }
}
