package com.example.mycalculator;

import android.view.View;

public class Calculator {
    public enum calcState{
        FIRST_ARG_INPUT,
        SECOND_ARG_INPUT,
        SHOW_RESULT
    }

    private int firstArg, secondArg;
    private int actionId;
    StringBuilder input = new StringBuilder();
    calcState state;

    public Calculator() {
        state = calcState.FIRST_ARG_INPUT;
    }

    public String getText() {
        return input.toString();
    }

    public void onNumPressed(int id) {
        if(input.length() < 9) {

            if(state == calcState.SHOW_RESULT){
                input.setLength(0);
                state = calcState.FIRST_ARG_INPUT;
            }

            switch(id){
                case R.id.main_activity__zero:
                    if(input.length() > 0)
                        input.append("0");
                    break;
                case R.id.main_activity__one:
                    input.append("1");
                    break;
                case R.id.main_activity__two:
                    input.append("2");
                    break;
                case R.id.main_activity__three:
                    input.append("3");
                    break;
                case R.id.main_activity__four:
                    input.append("4");
                    break;
                case R.id.main_activity__five:
                    input.append("5");
                    break;
                case R.id.main_activity__six:
                    input.append("6");
                    break;
                case R.id.main_activity__seven:
                    input.append("7");
                    break;
                case R.id.main_activity__eight:
                    input.append("8");
                    break;
                case R.id.main_activity__nine:
                    input.append("9");
                    break;
            }
        }
    }

    public void onActionPressed(int id) {
        if (id == R.id.main_activity__equals && input.length() > 0) {
            secondArg = Integer.parseInt(input.toString());
            input.setLength(0);
            state = calcState.SHOW_RESULT;

            switch (actionId) {
                case R.id.main_activity__plus:
                    input.append(firstArg + secondArg);
                    break;
                case R.id.main_activity__minus:
                    input.append(firstArg - secondArg);
                    break;
                case R.id.main_activity__multiply:
                    input.append(firstArg * secondArg);
                    break;
                case R.id.main_activity__divide:
                    input.append(firstArg / secondArg);
                    break;
            }
        } else if (id == R.id.main_activity__resetbutton) {
                input.setLength(0);
                state = calcState.FIRST_ARG_INPUT;
                actionId = 0;
        } else if (input.length() > 0 && state == calcState.FIRST_ARG_INPUT) {
                actionId = id;
                firstArg = Integer.parseInt(input.toString());
                input.setLength(0);
                state = calcState.SECOND_ARG_INPUT;
        }
    }
}
