package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Calculator calculator;
    private TextView inputField;

    private int[] numButtonsId = {
            R.id.zero,
            R.id.one,
            R.id.two,
            R.id.three,
            R.id.four,
            R.id.five,
            R.id.six,
            R.id.seven,
            R.id.eight,
            R.id.nine
    };

    private int[] actionButtonsId = {
            R.id.plus,
            R.id.minus,
            R.id.multiply,
            R.id.divide,
            R.id.equals,
            R.id.clear
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();
        inputField = findViewById(R.id.numbersField);

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