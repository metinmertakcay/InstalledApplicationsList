package com.example.user.getinstalledapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<AppInfo> {

    public CustomAdapter(Context context, List<AppInfo> list)
    {
        super(context,R.layout.satir,list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.satir,parent,false);

        TextView textView = (TextView)customView.findViewById(R.id.appName);
        ImageView imageView = (ImageView)customView.findViewById(R.id.appIcon);

        textView.setText(getItem(position).getAppName());
        imageView.setImageDrawable(getItem(position).getDrawable());

        return customView;
    }
}
