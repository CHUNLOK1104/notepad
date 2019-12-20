package com.example.notepad;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AddNoteAdapter extends ArrayAdapter<AddNote> {
    private Context mContext;
    private ArrayList<AddNote> mData;

    public AddNoteAdapter(Context context, ArrayList<AddNote> data){
        super(context, R.layout.list_note);
        mContext = context;
        mData = data;
    }

    @Nullable
    @Override
    public AddNote getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AddNote data = getItem(position);
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_note, null);

            holder.tvNote = convertView.findViewById(R.id.tvNote);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvNote.setText(data.getNote());

        return convertView;
    }

    private class ViewHolder {
        TextView tvNote;
    }

    public void updateData(ArrayList<AddNote> newData){
        mData.clear();
        mData.addAll(newData);
        notifyDataSetChanged();
    }
}
