package com.example.asmita.absent_form;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by asmita on 17/6/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {


    LayoutInflater li;
    List<ParsingClass> data= Collections.emptyList();

    public MyAdapter(Context context, List<ParsingClass> myData)
    {
        li=LayoutInflater.from(context);
        data=myData;
    }


    @Override
    public  MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=li.inflate(R.layout.singlerow,parent,false);
        MyHolder myHolder=new MyHolder(view);
        return myHolder;

    }

    @Override
    public void onBindViewHolder(MyHolder myHolder, int position) {

        ParsingClass parsingClass=data.get(position);
        myHolder.textView1.setText(parsingClass.team);
        myHolder.textView2.setText(parsingClass.manager);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //MyHoderClass starts here


    public class MyHolder extends RecyclerView.ViewHolder{
        TextView textView1,textView2;

        public MyHolder(View itemView) {

            super(itemView);
            textView1=(TextView)itemView.findViewById(R.id.text1);
            textView2=(TextView)itemView.findViewById(R.id.text2);

        }
    }






}
