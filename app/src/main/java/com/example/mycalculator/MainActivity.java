package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Calculator calculator;
    private TextView inputField;

    private int[] numButtonsId = {
            R.id.main_activity__zero,
            R.id.main_activity__one,
            R.id.main_activity__two,
            R.id.main_activity__three,
            R.id.main_activity__four,
            R.id.main_activity__five,
            R.id.main_activity__six,
            R.id.main_activity__seven,
            R.id.main_activity__eight,
            R.id.main_activity__nine
    };

    private int[] actionButtonsId = {
            R.id.main_activity__plus,
            R.id.main_activity__minus,
            R.id.main_activity__multiply,
            R.id.main_activity__divide,
            R.id.main_activity__equals,
            R.id.main_activity__resetbutton
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();
        inputField = findViewById(R.id.main_activity__textfield);

        View.OnClickListener numberButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onNumPressed(v.getId());
                inputField.setText(calculator.getText());
            }
        };

        View.OnClickListener actionButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onActionPressed(v.getId());
                inputField.setText(calculator.getText());
            }
        };

        for(int id : numButtonsId)
            findViewById(id).setOnClickListener(numberButtonClickListener);

        for(int id : actionButtonsId)
            findViewById(id).setOnClickListener(actionButtonClickListener);
    }
}