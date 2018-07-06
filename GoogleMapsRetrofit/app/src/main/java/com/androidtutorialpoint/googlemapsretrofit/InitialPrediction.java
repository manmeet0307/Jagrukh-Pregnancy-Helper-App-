package com.androidtutorialpoint.googlemapsretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InitialPrediction extends AppCompatActivity {

    EditText heightE, ageE, parityE, smokerE, alcoholE;
    int sga = 1, height, age, parity;
    int smoker, alcohol;
    double pred;
    Button predict, next;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_prediction);
        predict = (Button) findViewById(R.id.predict);
        heightE = (EditText) findViewById(R.id.height);
        ageE = (EditText) findViewById(R.id.age);
        parityE = (EditText) findViewById(R.id.parity);
        smokerE = (EditText) findViewById(R.id.smoke);
        alcoholE = (EditText) findViewById(R.id.alcohol);
        result = (TextView) findViewById(R.id.result);
        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                height = Integer.parseInt(heightE.getText().toString());
                age = Integer.parseInt(ageE.getText().toString());
                parity = Integer.parseInt(heightE.getText().toString());
                if(smokerE.getText().toString()=="Y"){
                    smoker = 1;
                }else smoker = 0;

                if(alcoholE.getText().toString()=="Y"){
                    alcohol = 1;
                }else alcohol = 0;

                pred = (double )height*(-8.355455425669245859e-03)+(double)age*(-6.924575061266355358e-03)
                        +(double )sga*(-1.528999369474286940e+00)+(double)parity*(-3.018319793416523664e-02)+(double)smoker*(1.848600037062445023e-01) ;
                result.setText("Your predicted gestation period would last for : "+ (int)pred + " weeks");
            }
        });
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), UserMedicineSettingsFragmentActivity.class);
                startActivity(i);
            }
        });
    }
}
