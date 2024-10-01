package com.example.taxi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Log.d("myLogs", "ThirdActivity: onCreate");
    }

    public void clickBtnOk(View view) {
        Log.d("myLogs", "ThirdActivity: clickBtnOk");
        TextView fromStreet = findViewById(R.id.fromStreetInput);
        TextView fromHouse = findViewById(R.id.fromHouseInput);
        TextView fromFlat = findViewById(R.id.fromFlatInput);
        TextView toStreet = findViewById(R.id.toStreetInput);
        TextView toHouse = findViewById(R.id.toHouseInput);
        TextView toFlat = findViewById(R.id.toFlatInput);
        if (fromStreet.getText().toString().isEmpty() || fromHouse.getText().toString().isEmpty() || fromFlat.getText().toString().isEmpty() ||
            toStreet.getText().toString().isEmpty() || toHouse.getText().toString().isEmpty() || toFlat.getText().toString().isEmpty()) {
            Toast.makeText(this, "All fields must be filled in", Toast.LENGTH_LONG).show();
            return;
        }
        String address = String.format(
            "From: %s, %s, %s.\nTo: %s, %s, %s.\nTaxi will arrive in 5 minutes.\nIf you agree, click Call Taxi.",
            fromStreet.getText().toString(), fromHouse.getText().toString(), fromFlat.getText().toString(), toStreet.getText().toString(), toHouse.getText().toString(), toFlat.getText().toString()
        );
        Intent intent = new Intent();
        intent.putExtra("Address", address);
        setResult(RESULT_OK, intent);
        finish();
    }
}