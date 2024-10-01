package com.example.taxi;

import android.content.Intent;
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

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d("myLogs", "SecondActivity: onCreate");
        TextView nameSurname = findViewById(R.id.nameSurnameText);
        TextView phone = findViewById(R.id.phoneText);

        Intent intent = getIntent();
        nameSurname.setText(intent.getStringExtra("NameSurname"));
        phone.setText(intent.getStringExtra("Phone"));
    }

    public void clickBtnSetPath(View view) {
        Log.d("myLogs", "SecondActivity: clickBtnSetPath");
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivityForResult(intent, 1);
    }

    public void clickBtnCallTaxi(View view) {
        Log.d("myLogs", "SecondActivity: clickBtnCallTaxi");
        TextView addressText = findViewById(R.id.addressText);
        if (!addressText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Wait for Taxi. Good Luck!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.d("myLogs", "SecondActivity: onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            TextView addressText = findViewById(R.id.addressText);
            String address = data.getStringExtra("Address");
            addressText.setText(address);

            Button btnCall = findViewById(R.id.btnCallTaxi);
            btnCall.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
        }
    }
}