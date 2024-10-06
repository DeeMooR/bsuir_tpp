package com.example.mynotes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mynotes.R;
import com.example.mynotes.models.Note;

import java.util.ArrayList;

public class NotesAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Note> arr_notes;
    private LayoutInflater layoutInflater;

    public NotesAdapter(Context context, ArrayList<Note> arr_notes) {
        this.context = context;
        this.arr_notes = arr_notes;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arr_notes.size();
    }
    @Override
    public Object getItem(int position) {
        return arr_notes.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_item, null, false);
        }
        Note good = arr_notes.get(position);
        TextView id = view.findViewById(R.id.itemId);
        id.setText(good.getId() + "");
        TextView text = view.findViewById(R.id.itemText);
        text.setText(good.getText());
        return view;
    }
}
