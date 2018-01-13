package com.example.asmita.absent_form;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

/**
 * Created by asmita on 26/6/17.
 */

public class CardLayout extends AppCompatActivity {


    CardView layout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.cardlayout);
        layout= (CardView) findViewById(R.id.card);
        View view=getLayoutInflater().inflate(R.layout.cardlayout,null);
        setContentView(view);
    }
}
