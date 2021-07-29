package com.example.mycalculator;

import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {

    private enum CalcState{
        FIRST_ARG_INPUT,
        SECOND_ARG_INPUT,
        SHOW_RESULT
    }
    private final ArrayList<Character> actionSymbols = new ArrayList<Character>(
            Arrays.asList('+','-','*','/')
    );

    private int firstArg, secondArg, result;
    private char action;
    CalcState state;

    public Calculator() {
        state = CalcState.FIRST_ARG_INPUT;
        action = ' ';
    }

    public String getResult() {

        System.out.println("\n\n" + Integer.toString(firstArg) + " ----- " + Integer.toString(secondArg) + "\n\n\n");

        if (state == CalcState.FIRST_ARG_INPUT) {
            return "";
        } else if (state == CalcState.SECOND_ARG_INPUT) {
            switch (action) {
                case '+':
                    return Integer.toString(firstArg + secondArg);
                case '-':
                    return Integer.toString(firstArg - secondArg);
                case '*':
                    return Integer.toString(firstArg * secondArg);
                case '/':
                    if (secondArg == 0)
                        return "";
                    return Integer.toString(firstArg / secondArg);
            }
        } else {
            return Integer.toString(result);
        }

        return "Error";
    }

    public boolean addNumber(int num) {
        int lim = 999999999;

        if (state == CalcState.FIRST_ARG_INPUT && firstArg < lim && (firstArg != 0 || num != 0)) {
            firstArg = (firstArg * 10) + num;
            return true;
        } else if (state == CalcState.SECOND_ARG_INPUT && secondArg < lim && (secondArg != 0 || num != 0)) {
            secondArg = (secondArg * 10) + num;
            return true;
        } else if (state == CalcState.SHOW_RESULT) {
            firstArg = num;
            return true;
        }

        return false;
    }

    public boolean addAction(char act) {

        if(act == 'C') { //reset calculator
            firstArg = secondArg = 0;
            action = ' ';
            state = CalcState.FIRST_ARG_INPUT;
            return true;
        } else if (state == CalcState.FIRST_ARG_INPUT && actionSymbols.contains(act)) { //entering first arg and adding action
            action = act;
            state = CalcState.SECOND_ARG_INPUT;
            return true;
        }  else if (state == CalcState.SECOND_ARG_INPUT && act == '=') { //entering second arg and want result
            state = CalcState.SHOW_RESULT;
            switch (action) {
                case '+':
                    result = firstArg + secondArg;
                    break;
                case '-':
                    result = firstArg - secondArg;
                    break;
                case '*':
                    result = firstArg * secondArg;
                    break;
                case '/':
                    if (secondArg == 0)
                        return false;
                    result = firstArg / secondArg;
                    break;
            }
            firstArg = secondArg = 0;
            action = ' ';
            return true;
        } else if (state == CalcState.SHOW_RESULT && actionSymbols.contains(act)) { // got result and want to work with it
            firstArg = result;
            action = act;
            state = CalcState.SECOND_ARG_INPUT;
            return true;
        }

        return false;

    }
}
