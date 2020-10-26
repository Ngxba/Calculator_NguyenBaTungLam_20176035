package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private Button zero;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button mul;
    private Button div;
    private Button add;
    private Button sub;
    private Button equal;
    private Button dot;
    private Button lunisolar;
    private Button ce;
    private Button c;
    private Button bs;
    private String input, answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = findViewById(R.id.screen);
        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        mul = findViewById(R.id.mul);
        div = findViewById(R.id.div);
        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        equal = findViewById(R.id.equal);
        dot = findViewById(R.id.dot);
        lunisolar = findViewById(R.id.lunisolar);
        ce = findViewById(R.id.ce);
        c = findViewById(R.id.c);
        bs = findViewById(R.id.bs);
    }

    public void ButtonClick(View view) {
        Button button = (Button) view;
        String data = button.getText().toString();
        switch (data) {
            case "CE":
                try {
                    if (input.length() == 1 || input == null) {
                        input = "0";
                    } else if (isNumeric(input.charAt(input.length() - 1))) {
                        input = input.substring(0, input.length() - 1);
                    } else {
                        input = input + "";
                    }
                } catch (Exception e) {
                    input = "0";
                }
                break;
            case "C":
                try {
                    input = "0";
                } catch (Exception e) {
                    input = "0";
                }
                break;
            case "BS":
                try {
                    if(input.length() == 1) {
                        input = "0";
                    } else {
                        input = input.substring(0, input.length() - 1);
                    }
                } catch (Exception e) {
                    input = "0";
                }
                break;
            case "x":
                Solve();
                input += "*";
                break;
            case "=":
                Solve();
                answer = input;
                break;
            case "+/-":
                if (input.startsWith("-")) {
                    input = input.replace(input.substring(0, 1), "");
                } else {
                    input = "-" + input;
                }
                break;
            default:
                if (input == null) {
                    input = "0";
                }
                if (data.equals("+") || data.equals("-") || data.equals("/")) {
                    Solve();
                }
                if (input.equals("0")) {
                    input = data;
                } else {
                    input += data;
                }

        }

        screen.setText(input);
    }

    public void Solve() {
        if (input.split("\\*").length == 2) {
            String[] number = input.split("\\*");
            try {
                double mul = Double.parseDouble(number[0]) * Double.parseDouble(number[1]);
                input = mul + "";
            } catch (Exception e) {
                //Toggle Error
            }
        } else if (input.split("/").length == 2) {
            String[] number = input.split("/");
            try {
                double div = Double.parseDouble(number[0]) / Double.parseDouble(number[1]);
                input = div + "";
            } catch (Exception e) {
                //Toggle Error
            }
        } else if (input.split("\\+").length == 2) {
            String[] number = input.split("\\+");
            try {
                double add = Double.parseDouble(number[0]) + Double.parseDouble(number[1]);
                input = add + "";
            } catch (Exception e) {
                //Toggle Error
            }
        } else if (input.split("-").length > 1) {
            String[] number = input.split("-");
            if (number[0].equals("") && number.length == 2) {
                number[0] = 0 + "";
            }
            try {
                double sub = 0;
                if (number.length == 2) {
                    sub = Double.parseDouble(number[0]) - Double.parseDouble(number[1]);
                } else if (number.length == 3) {
                    sub = -Double.parseDouble(number[1]) - Double.parseDouble(number[2]);
                }
                input = sub + "";
            } catch (Exception e) {
                //Toggle Error
            }
        }

        String[] n = input.split("\\.");
        if (n.length > 1) {
            if (n[1].equals("0")) {
                input = n[0];
            }
        }
        screen.setText(input);
    }

    public static boolean isNumeric(Character character) {
        try {
            Double.parseDouble(String.valueOf(character));
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}