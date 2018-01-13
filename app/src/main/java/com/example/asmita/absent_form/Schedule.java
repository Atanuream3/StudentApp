package com.example.asmita.absent_form;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class Schedule extends AppCompatActivity {
    Toolbar tb;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        rv = (RecyclerView) findViewById(R.id.recyclerView);

        //setSupportActionBar(tb);
        MyAdapter myAdapter = new MyAdapter(this, getData());

        rv.setAdapter(myAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    public List<ParsingClass> getData() {
        String[] teams = {"Project Work","Extra Work","Academic","Programming"};
        String[] managers = {"8AM to 12AM","1PM to 3PM","3PM to 5PM ","6PM to 7:30PM"};
        List<ParsingClass> data = new ArrayList();
        for (int i = 0; i < teams.length; i++) {
            ParsingClass parsingClass = new ParsingClass();
            parsingClass.team = teams[i];
            parsingClass.manager = managers[i];
            data.add(parsingClass);
        }
        return data;
    }
}
