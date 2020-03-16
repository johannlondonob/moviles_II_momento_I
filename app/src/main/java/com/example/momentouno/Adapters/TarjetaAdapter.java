package com.example.momentouno.Adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.momentouno.Models.TarjetaModel;
import com.example.momentouno.R;

import java.util.ArrayList;

public class TarjetaAdapter extends BaseAdapter {

    private ArrayList<TarjetaModel> tarjetaModels;
    private Context context;
    private TarjetaModel model;

    public TarjetaAdapter(ArrayList<TarjetaModel> tarjetaModels, Context context) {
        this.tarjetaModels = tarjetaModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return tarjetaModels.size();
    }

    @Override
    public Object getItem(int position) {
        return tarjetaModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.lista_tarjetas, parent, false);
        }

        ImageView imageViewFranquicia;
        TextView textViewNumeroTarjeta, textViewFechaVencimiento;
        textViewNumeroTarjeta = view.findViewById(R.id.textViewNumeroTarjeta);
        textViewFechaVencimiento = view.findViewById(R.id.textViewFechaVencimiento);
        imageViewFranquicia = view.findViewById(R.id.imageViewFranquicia);

        model = tarjetaModels.get(position);

        String numeroTarjeta = model.get_numeroTarjeta();

        String fechaVencimiento = model.get_mesVencimiento() + "/" + model.get_anioVencimiento().substring(2,4);

        switch (model.get_franquicia()){
            case "Visa":
                imageViewFranquicia.setImageResource(R.drawable.visa_50);
                break;
            case "Mastercard":
                imageViewFranquicia.setImageResource(R.drawable.mastercard_50);
                break;
            case "Diner":
                imageViewFranquicia.setImageResource(R.drawable.diner_club_50);
                break;
            default:
                imageViewFranquicia.setImageResource(R.drawable.visa_50);
        }

        textViewNumeroTarjeta.setText(numeroTarjeta);
        textViewFechaVencimiento.setText(fechaVencimiento);

        return view;
    }
}
