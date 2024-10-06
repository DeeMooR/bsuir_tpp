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

public class FragmentUpdate extends Fragment implements DBUser {
    private View view;
    private DB db;

    public void setDatabase(DB db) {
        this.db = db;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_update, container, false);
        Button btnUpdate = view.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(v -> clickUpdate());
        return view;
    }

    public void clickUpdate() {
        EditText idElement = view.findViewById(R.id.inputIdFragUpdate);
        String id = idElement.getText().toString();
        EditText textElement = view.findViewById(R.id.inputTextFragUpdate);
        String text = textElement.getText().toString();

        if (id.isEmpty() || text.isEmpty()) {
            Toast.makeText(getContext(), "ID or text is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (db == null) {
            Toast.makeText(getContext(), "Error updating note", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int intID = Integer.parseInt(id);
            db.update(intID, text);
            idElement.setText("");
            textElement.setText("");
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Invalid ID format", Toast.LENGTH_SHORT).show();
        } catch (IllegalArgumentException e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
