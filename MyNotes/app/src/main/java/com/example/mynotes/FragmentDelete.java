package com.example.mynotes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.mynotes.database.DB;
import com.example.mynotes.interfaces.DBUser;

public class FragmentDelete extends Fragment implements DBUser {
    private View view;
    private DB db;

    public void setDatabase(DB db) {
        this.db = db;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_delete, container, false);
        Button btnDeleteOne = view.findViewById(R.id.btnDeleteOne);
        btnDeleteOne.setOnClickListener(v -> clickDeleteOne());
        Button btnDeleteAll = view.findViewById(R.id.btnDeleteAll);
        btnDeleteAll.setOnClickListener(v -> clickDeleteAll());
        return view;
    }
    public void clickDeleteOne() {
        EditText idElement = view.findViewById(R.id.inputIdFragDelete);
        String id = idElement.getText().toString();
        if (id.isEmpty()) {
            Toast.makeText(getContext(), "ID is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (db == null) {
            Toast.makeText(getContext(), "Error deleting note", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int intID = Integer.parseInt(id);
            db.delete(intID);
            idElement.setText("");
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Invalid ID format", Toast.LENGTH_SHORT).show();
        } catch (IllegalArgumentException e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void clickDeleteAll() {
        EditText idElement = view.findViewById(R.id.inputIdFragDelete);
        if (db == null) {
            Toast.makeText(getContext(), "Error deleting all notes", Toast.LENGTH_SHORT).show();
            return;
        }
        db.deleteAll();
        idElement.setText("");
    }
}
