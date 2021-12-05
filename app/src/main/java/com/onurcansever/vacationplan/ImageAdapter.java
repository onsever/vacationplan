package com.onurcansever.vacationplan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    int[] images;
    LayoutInflater inflater;

    public ImageAdapter(Context context, int[] images) {
        this.images = images;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int i) {
        return images[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.image_row, null);
            holder = new ViewHolder();
            holder.imageView = view.findViewById(R.id.imageView);
        }
        else
            holder = (ViewHolder) view.getTag();
            holder.imageView.setImageResource(images[i]);

        return view;
    }

    private static class ViewHolder {
        private ImageView imageView;
    }
}
