package com.onurcansever.vacationplan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlaceAdapter extends BaseAdapter {

    ArrayList<Place> places;
    LayoutInflater inflater;

    public PlaceAdapter(Context context, ArrayList<Place> places) {
        this.places = places;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return places.size();
    }

    @Override
    public Object getItem(int i) {
        return places.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.place_row, null);
            holder = new ViewHolder();
            holder.placeName = view.findViewById(R.id.placeName);
            holder.placeCharge = view.findViewById(R.id.placeCharge);
            holder.placeSmallImage = view.findViewById(R.id.placeSmallImage);
            view.setTag(holder);
        }
        else
            holder = (ViewHolder) view.getTag();
            holder.placeName.setText(places.get(i).getPlaceName());
            holder.placeCharge.setText(String.valueOf(places.get(i).getChargeAmount()));
            holder.placeSmallImage.setImageResource(places.get(i).getImages()[0]);

        return view;
    }

    private static class ViewHolder {
        private TextView placeName, placeCharge;
        private ImageView placeSmallImage;
    }
}
