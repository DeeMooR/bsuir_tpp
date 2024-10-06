package com.example.mynotes;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.mynotes.adapters.NotesAdapter;
import com.example.mynotes.database.DB;
import com.example.mynotes.interfaces.DBUser;
import com.example.mynotes.models.Note;

import java.util.ArrayList;

public class FragmentShow extends Fragment implements DBUser {
    private ListView listShow;
    private TextView isEmpty;
    private ArrayList<Note> arr_notes = new ArrayList<Note>();
    private DB db;

    public void setDatabase(DB db) {
        this.db = db;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show, container, false);
        listShow = view.findViewById(R.id.listFragShow);
        isEmpty = view.findViewById(R.id.emptyFragShow);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        isEmpty.setVisibility(View.GONE);
        fillList();
    }
    private void fillList() {
        arr_notes.clear();
        getAllNotes();
        NotesAdapter notesAdapter = new NotesAdapter(requireContext(), arr_notes);
        listShow.setAdapter(notesAdapter);
    }
    private void getAllNotes() {
        if (db == null) return;
        Cursor cursor = db.getAll();
        if (cursor != null) {
            try {
                if (!cursor.moveToFirst()) isEmpty.setVisibility(View.VISIBLE);
                else {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                        String text = cursor.getString(cursor.getColumnIndexOrThrow("text"));
                        arr_notes.add(new Note(id, text));
                    } while (cursor.moveToNext());
                }
            } finally {
                cursor.close();
            }
        }
    }
}
