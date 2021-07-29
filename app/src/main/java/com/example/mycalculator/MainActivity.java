package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Calculator calculator;
    private TextView inputField, resultField;

    private ArrayList<Integer> numButtonsId = new ArrayList<Integer>(
            Arrays.asList( R.id.main_activity__zero,
                    R.id.main_activity__one,
                    R.id.main_activity__two,
                    R.id.main_activity__three,
                    R.id.main_activity__four,
                    R.id.main_activity__five,
                    R.id.main_activity__six,
                    R.id.main_activity__seven,
                    R.id.main_activity__eight,
                    R.id.main_activity__nine)
    );
    private ArrayList<Integer> actionButtonsId = new ArrayList<Integer>(
            Arrays.asList(R.id.main_activity__plus,
                    R.id.main_activity__minus,
                    R.id.main_activity__multiply,
                    R.id.main_activity__divide,
                    R.id.main_activity__equals,
                    R.id.main_activity__resetbutton)
    );
    private ArrayList<Character> actionSymbols = new ArrayList<Character>(
            Arrays.asList('+','-','*','/','=','C')
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();
        inputField = findViewById(R.id.main_activity__textfield);
        resultField = findViewById(R.id.main_activity__result);

        View.OnClickListener numberButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = numButtonsId.indexOf(v.getId());

                if (calculator.addNumber(number)) {
                    inputField.append(Integer.toString(number));
                    resultField.setText(calculator.getResult());
                }
            }
        };

        View.OnClickListener actionButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char action = actionSymbols.get(actionButtonsId.indexOf(v.getId()));

                if (calculator.addAction(action)) {
                    inputField.append(Character.toString(action));
                    resultField.setText(calculator.getResult());
                    if(action == '=') {
                        inputField.setText(calculator.getResult());
                        resultField.setText("");
                    } else if(action == 'C') {
                        inputField.setText("");
                        resultField.setText("");
                    }
                }
            }
        };

        for(int id : numButtonsId)
            findViewById(id).setOnClickListener(numberButtonClickListener);

        for(int id : actionButtonsId)
            findViewById(id).setOnClickListener(actionButtonClickListener);
    }
}