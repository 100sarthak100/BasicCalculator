package com.example.basiccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextView _screen;
    private String display = "";
    private String val = "";
    private String currentOperator = "";
    private String result = "";
    private boolean sinclick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _screen = (TextView)findViewById(R.id.text);
        _screen.setText(display);
    }
    public void onClicksin(View v){

    }

    protected void updatescreen(){
        _screen.setText(display);
    }

    public void onClickNumber(View v){
        if(result != ""){
            Clear();
            updatescreen();
        }
        Button b = (Button)v;
        display += b.getText();
        updatescreen();
    }
    private boolean isOperator(char op) {
        switch (op) {
            case '+':
            case '-':
            case '*':
            //case 'sin' :
            case '/':
                return true;
            default:
                return false;
        }
    }


    public void onClickOperator(View v){
        Button b = (Button)v;
        sinclick = true;
        if(result != "") {
            display = result;
            result = "";
        }
        if(currentOperator != ""){
            if(isOperator(display.charAt(display.length()-1))){
                display.replace(display.charAt(display.length()-1),b.getText().charAt(0));
            }else {
                getResult();
                display = result;
                result = "";
            }
            currentOperator = b.getText().toString();
        }

        //Button b = (Button)v;
        display+=(b).getText();
        currentOperator = b.getText().toString();
        updatescreen();
    }
    private void Clear(){
        display = "";
        currentOperator = "";
        result = "";
    }



    public void onClickClear(View v){
        Clear();
        updatescreen();
    }
    private double operate(String a, String b, String op){
        switch (op){
            case "+": return Double.valueOf(a) + Double.valueOf(b);
            case "-": return Double.valueOf(a) - Double.valueOf(b);
            case "*": return Double.valueOf(a) * Double.valueOf(b);
            //case "sin" : return  Math.sin(Double.valueOf(a));
            case "/": try{
                return Double.valueOf(a) / Double.valueOf(b);
            } catch (Exception e){
                //hasResult = false;
                Log.d("Calc", e.getMessage());
            }
            default: return -1;
        }
    }
    private boolean getResult(){
            String[] operation = display.split(Pattern.quote(currentOperator));
            if(operation.length <2) return false;
            result = String.valueOf(operate(operation[0],operation[1],currentOperator));

            return true;
    }


    protected void onClickEqual(View v){
            if(!getResult()) {
                return ;}
            _screen.setText(display + "\n" + String.valueOf(result));

    }

}


