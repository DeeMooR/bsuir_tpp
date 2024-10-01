package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText oldText;
    String operation;
    private Map<Integer, String> buttonMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        oldText = findViewById(R.id.oldText);

        buttonMap = new HashMap<>();
        buttonMap.put(R.id.btnDot, ".");
        buttonMap.put(R.id.btnPlusMinus, "+/-");
        buttonMap.put(R.id.btnAC, "AC");
        buttonMap.put(R.id.btnEqual, "=");
    }

    public void clickNumber(View view) {
        oldText.setText("");
        String number = editText.getText().toString();
        String value = ((Button) view).getText().toString();
        number = number.equals("0") || number.equals("Error") ? value : number + value;
        if (number.equals("-0")) number = "-" + number;
        editText.setText(number);
    }

    public void clickOperation(View view) {
        String number = editText.getText().toString();
        String value = ((Button) view).getText().toString();
        if (number.equals("0") || number.equals("-0") || number.equals("Error")) return;
        oldText.setText("");

        if (operation != null) {
            getResult(number);
            number= editText.getText().toString();
        }
        operation = value;
        editText.setText(number + value);
    }

    public void clickButton(View view) {
        oldText.setText("");
        String number = editText.getText().toString();
        String value = buttonMap.get(view.getId());
        switch(value) {
            case ".": addDot(number); break;
            case "+/-": addPlusMinus(number); break;
            case "AC": clearText(); break;
            case "=": getResult(number); break;
        }
    }

    public void addDot(String number) {
        if (operation == null) {
            if (!number.contains(".")) {
                editText.setText(number + ".");
            }
        } else {
            int index = number.indexOf(operation);
            String part2 = number.substring(index + 1);
            if (!part2.contains(".")) {
                editText.setText(number + ".");
            }
        }
    }
    public void addPlusMinus(String number) {
        String str;
        if (number.startsWith("-")) {
            str = number.substring(1);
        } else {
            str = "-" + number;
        }
        editText.setText(str);
    }
    public void clearText() {
        editText.setText("0");
        operation = null;
    }
    public void getResult(String number) {
        if (number != null && operation != null) {
            int index = number.indexOf(operation);
            if (index != -1) {
                oldText.setText(number);
                Double part1 = Double.parseDouble(number.substring(0, index));
                Double part2 = Double.parseDouble(number.substring(index + 1));
                Double result;
                switch (operation) {
                    case "+": result = part1 + part2; break;
                    case "-": result = part1 - part2; break;
                    case "*": result = part1 * part2; break;
                    case "/":
                        if (part2 != 0) {
                            result = part1 / part2;
                        } else {
                            editText.setText("Error");
                            return;
                        } break;
                    case "%":
                        if (part2 != 0) {
                            result = part1 % part2;
                        } else {
                            editText.setText("Error");
                            return;
                        } break;
                    default:
                        throw new IllegalArgumentException("Unknown operation: " + operation);
                }
                if (result == result.longValue()) {
                    editText.setText(String.valueOf(result.longValue()));
                } else {
                    editText.setText(String.format("%.4f", result));
                }
                operation = null;
            }
        }
    }
}