package com.example.taxi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FirstActivity extends AppCompatActivity {
    SharedPreferences sPref;
    TextView name;
    TextView surname;
    TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Log.d("myLogs", "FirstActivity: onCreate");

        name = findViewById(R.id.nameInput);
        surname = findViewById(R.id.surnameInput);
        phone = findViewById(R.id.phoneInput);
        loadText();
    }

    public void clickBtnAuth(View view) {
        Log.d("myLogs", "FirstActivity: clickBtnAuth");
        EditText name = findViewById(R.id.nameInput);
        EditText surname = findViewById(R.id.surnameInput);
        EditText phone = findViewById(R.id.phoneInput);
        if (name.getText().toString().isEmpty() || surname.getText().toString().isEmpty() || phone.getText().toString().isEmpty()) {
            Toast.makeText(this, "All fields must be filled in", Toast.LENGTH_LONG).show();
            return;
        }

        saveText();
        Intent intent = new Intent(this, SecondActivity.class);
        String nameSurname = name.getText().toString() + " " + surname.getText().toString();
        intent.putExtra("NameSurname", nameSurname);
        intent.putExtra("Phone", phone.getText().toString());
        startActivity(intent);
    }

    private void saveText() {
        Log.d("myLogs", "FirstActivity: saveText");
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString("Name", name.getText().toString());
        editor.putString("Surname", surname.getText().toString());
        editor.putString("Phone", phone.getText().toString());
        editor.commit();
        Toast.makeText(this, "User data saved", Toast.LENGTH_SHORT).show();
    }
    private void loadText() {
        Log.d("myLogs", "FirstActivity: loadText");
        sPref = getPreferences(MODE_PRIVATE);
        String nameText = sPref.getString("Name", "");
        String surnameText = sPref.getString("Surname", "");
        String phoneText = sPref.getString("Phone", "");
        name.setText(nameText);
        surname.setText(surnameText);
        phone.setText(phoneText);
        if (!nameText.isEmpty() && !surnameText.isEmpty() && !phoneText.isEmpty()) {
            Toast.makeText(this, "User data loaded", Toast.LENGTH_SHORT).show();
            Button btnAuth = findViewById(R.id.btnAuth);
            btnAuth.setText("LOG IN");
        }
    }
}