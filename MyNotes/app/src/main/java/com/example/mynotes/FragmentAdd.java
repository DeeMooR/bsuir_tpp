package com.example.mynotes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.mynotes.database.DB;
import com.example.mynotes.interfaces.DBUser;

public class FragmentAdd extends Fragment implements DBUser {
    private View view;
    private DB db;

    public void setDatabase(DB db) {
        this.db = db;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add, container, false);
        Button btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> clickAdd());
        return view;
    }

    public void clickAdd() {
        EditText textElement = view.findViewById(R.id.inputTextFragShow);
        String text = textElement.getText().toString();
        if (text.isEmpty()) {
            Toast.makeText(getContext(), "Note is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (db == null) {
            Toast.makeText(getContext(), "Error adding note", Toast.LENGTH_SHORT).show();
            return;
        }
        db.add(text);
        textElement.setText("");
    }
}
