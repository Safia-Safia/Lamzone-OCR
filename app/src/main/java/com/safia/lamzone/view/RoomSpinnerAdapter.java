package com.safia.lamzone.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.safia.lamzone.R;
import com.safia.lamzone.model.Room;

import java.util.List;

public class RoomSpinnerAdapter extends ArrayAdapter<Room> {

    public RoomSpinnerAdapter(Context context, List<Room> roomList) {
        super(context, 0, roomList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.room_spinner, parent, false
            );
        }
        ImageView roomColor = convertView.findViewById(R.id.image_room_color);
        TextView textViewName = convertView.findViewById(R.id.text_view_name);
        Room mRoom = getItem(position);

        if (mRoom != null) {
            roomColor.setBackgroundColor(mRoom.getRoomColor());
            textViewName.setText(mRoom.getRoomName());
        }
        return convertView;
    }
}