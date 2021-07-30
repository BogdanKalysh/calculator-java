package com.example.mycalculator;

public class Calculator {

    private enum CalcState {
        FIRST_ARG_INPUT,
        ACTION_INPUT,
        SECOND_ARG_INPUT,
        SHOW_RESULT
    }
    public enum Action {
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE,
        DIVISION_REMINDER,
        NONE
    }

    private int firstArg, secondArg, result;
    private Action action;
    CalcState state;

    public Calculator() {
        state = CalcState.FIRST_ARG_INPUT;
        action = Action.NONE;
    }

    public int getResult() {
        return result;
    }
    
    public int getFirstArg() {
        return firstArg;
    }
    
    public int getSecondArg() {
        return secondArg;
    }
    
    public Action getAction() {
        return action;
    }

    public void reset() {
        state = CalcState.FIRST_ARG_INPUT;
        action = Action.NONE;
        firstArg = secondArg = result = 0;
    }

    public void endComputition() {
        if(state == CalcState.SECOND_ARG_INPUT){
            firstArg = result;
            result = secondArg = 0;
            action = Action.NONE;
            state = CalcState.SHOW_RESULT;
        }
    }

    public void undoLastAction() {
        if (state == CalcState.FIRST_ARG_INPUT && firstArg != 0) {
            firstArg /= 10;
            result = firstArg;
        } else if (state == CalcState.ACTION_INPUT) {
            action = Action.NONE;
            state = CalcState.FIRST_ARG_INPUT;
        } else if (state == CalcState.SECOND_ARG_INPUT && secondArg != 0) {
            secondArg /= 10;
            if(secondArg == 0)
                state = CalcState.ACTION_INPUT;
            compute();
        } else {
            firstArg = result = 0;
            state = CalcState.FIRST_ARG_INPUT;
        }
    }

    public boolean compute() {
        if (state == CalcState.SECOND_ARG_INPUT && secondArg != 0) {
            switch (action) {
                case PLUS:
                    result = firstArg + secondArg;
                    break;
                case MINUS:
                    result = firstArg - secondArg;
                    break;
                case MULTIPLY:
                    result = firstArg * secondArg;
                    break;
                case DIVIDE:
                    result = firstArg / secondArg;
                    break;
                case DIVISION_REMINDER:
                    result = firstArg % secondArg;
                    break;
                case NONE:
                    result = firstArg;
                    break;
            }
            return true;
        }
        return false;
    }

    public boolean addDigit(int num) {
        int lim = 9999999;

        if (state == CalcState.FIRST_ARG_INPUT && firstArg < lim && (firstArg != 0 || num != 0)) {
            firstArg = (firstArg * 10) + num;
            result = firstArg;
            return true;
        } else if (state == CalcState.SECOND_ARG_INPUT && secondArg < lim && (secondArg != 0 || num != 0)) {
            secondArg = (secondArg * 10) + num;
            compute();
            return true;
        } else if (state == CalcState.ACTION_INPUT && (secondArg != 0 || num != 0)) {
            state = CalcState.SECOND_ARG_INPUT;
            secondArg = num;
            compute();
            return true;
        } else if (state == CalcState.SHOW_RESULT) {
            reset();
            result = firstArg = num;
            return true;
        }
        return false;
    }

    public boolean addAction(Action act) {
        if(state == CalcState.FIRST_ARG_INPUT || state == CalcState.ACTION_INPUT || state == CalcState.SHOW_RESULT) {
            state = CalcState.ACTION_INPUT;
            action = act;
            return true;
        }
        return false;
    }
}
