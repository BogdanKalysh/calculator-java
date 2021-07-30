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
                    R.id.main_activity__divisionReminder,
                    R.id.main_activity__equals,
                    R.id.main_activity__resetbutton)
    );
    private ArrayList<Calculator.Action> ActionSymbols = new ArrayList<Calculator.Action>(
            Arrays.asList(Calculator.Action.PLUS,
                    Calculator.Action.MINUS,
                    Calculator.Action.MULTIPLY,
                    Calculator.Action.DIVIDE,
                    Calculator.Action.DIVISION_REMINDER)
    );
    
    private String buildExpression(int firstArg, Calculator.Action action, int secondArg) {
        if (secondArg != 0) {
            switch (action) {
                case PLUS:
                    return Integer.toString(firstArg) + "+" + Integer.toString(secondArg);
                case MINUS:
                    return Integer.toString(firstArg) + "-" + Integer.toString(secondArg);
                case MULTIPLY:
                    return Integer.toString(firstArg) + "*" + Integer.toString(secondArg);
                case DIVIDE:
                    return Integer.toString(firstArg) + "/" + Integer.toString(secondArg);
                case DIVISION_REMINDER:
                    return Integer.toString(firstArg) + "%" + Integer.toString(secondArg);
            }
        } else {
            switch (action) {
                case PLUS:
                    return Integer.toString(firstArg) + "+";
                case MINUS:
                    return Integer.toString(firstArg) + "-";
                case MULTIPLY:
                    return Integer.toString(firstArg) + "*";
                case DIVIDE:
                    return Integer.toString(firstArg) + "/";
                case DIVISION_REMINDER:
                    return Integer.toString(firstArg) + "%";
                case NONE:
                    return Integer.toString(firstArg);
            }
        }
        return "";
    }

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
                int digit = numButtonsId.indexOf(v.getId());

                if (calculator.addDigit(digit)) {
                    inputField.setText(buildExpression(calculator.getFirstArg(), calculator.getAction(), calculator.getSecondArg()));
                    resultField.setText(Integer.toString(calculator.getResult()));
                }
            }
        };

        View.OnClickListener actionButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.main_activity__resetbutton) {
                    calculator.reset();
                    inputField.setText(buildExpression(calculator.getFirstArg(), calculator.getAction(), calculator.getSecondArg()));
                    resultField.setText("");
                } else if (v.getId() == R.id.main_activity__equals) {
                    if (calculator.compute()) {
                        inputField.setText(Integer.toString(calculator.getResult()));
                        resultField.setText("");
                        calculator.endComputition();
                    }
                } else {
                    Calculator.Action action = ActionSymbols.get(actionButtonsId.indexOf(v.getId()));
                    
                    if (calculator.addAction(action)) {
                        inputField.setText(buildExpression(calculator.getFirstArg(), calculator.getAction(), calculator.getSecondArg()));
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