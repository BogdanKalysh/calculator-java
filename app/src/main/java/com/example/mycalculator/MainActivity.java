package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Calculator calculator;
    private TextView inputField, resultField;

    private List<Integer> numButtonsId = new ArrayList<Integer>(
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
    private List<Integer> actionButtonsId = new ArrayList<Integer>(
            Arrays.asList(R.id.main_activity__plus,
                    R.id.main_activity__minus,
                    R.id.main_activity__multiply,
                    R.id.main_activity__divide,
                    R.id.main_activity__divisionReminder,
                    R.id.main_activity__equals,
                    R.id.main_activity__resetbutton,
                    R.id.main_activity__erase)
    );
    private List<Calculator.Action> ActionSymbols = new ArrayList<Calculator.Action>(
            Arrays.asList(Calculator.Action.PLUS,
                    Calculator.Action.MINUS,
                    Calculator.Action.MULTIPLY,
                    Calculator.Action.DIVIDE,
                    Calculator.Action.DIVISION_REMINDER)
    );

    private String buildExpression(int firstArg, Calculator.Action action, int secondArg) {
        StringBuilder expression = new StringBuilder()
                .append(firstArg)
                .append(action.str);
        if (secondArg != 0)
            expression.append(secondArg);

        return expression.toString();
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

                calculator.addDigit(digit);

                inputField.setText(buildExpression(calculator.getFirstArg(), calculator.getAction(), calculator.getSecondArg()));
                resultField.setText(Integer.toString(calculator.getResult()));
            }
        };

        View.OnClickListener actionButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.main_activity__resetbutton) {
                    calculator.reset();
                } else if (v.getId() == R.id.main_activity__equals) {
                    calculator.finish();
                } else if(v.getId() == R.id.main_activity__erase) {
                    calculator.undoLastAction();
                } else {
                    Calculator.Action action = ActionSymbols.get(actionButtonsId.indexOf(v.getId()));
                    calculator.addAction(action);
                }

                inputField.setText(buildExpression(calculator.getFirstArg(), calculator.getAction(), calculator.getSecondArg()));
                resultField.setText(Integer.toString(calculator.getResult()));
            }
        };

        for(int id : numButtonsId)
            findViewById(id).setOnClickListener(numberButtonClickListener);

        for(int id : actionButtonsId)
            findViewById(id).setOnClickListener(actionButtonClickListener);
    }
}