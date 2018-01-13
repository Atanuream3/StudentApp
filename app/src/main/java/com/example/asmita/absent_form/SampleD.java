package com.example.asmita.absent_form;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;

public class SampleD extends AppCompatActivity {
   CardView layout1,layoy2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_d);

        layout1= (CardView) findViewById(R.id.card);
        //layoy2=(CardView)findViewById(R.id.card1);
    }
}
