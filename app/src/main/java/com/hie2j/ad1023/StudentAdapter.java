package com.hie2j.ad1023;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StuViewHolder>{
    private Context context;
    private ArrayList<Student> studentArrayList;
//    private ArrayList<Student> searchResultList = new ArrayList<Student>();
    private IOnDelListener listener;

    public StudentAdapter(Context context, ArrayList<Student> studentArrayList,IOnDelListener listener) {
        this.context = context;
        this.studentArrayList = studentArrayList;
        this.listener = listener;
    }

    /**
     * 改变数据源
     * */
    public void changeData(ArrayList<Student> studentArrayList){
        this.studentArrayList = studentArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item,viewGroup,false);

        StuViewHolder viewHolder = new StuViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StuViewHolder stuViewHolder, final int i) {

        final Student s = studentArrayList.get(i);

        stuViewHolder.headView.setImageResource(s.getImgId());
        stuViewHolder.nameView.setText(s.getName());
        stuViewHolder.ageView.setText(String.valueOf(s.getAge()));
        stuViewHolder.numView.setText(s.getNum());
        stuViewHolder.delView.setImageResource(R.drawable.del);

        stuViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student s = studentArrayList.get(i);
                Log.e("okay","isResultList = " + ((MainActivity)context).isResultList);
                if(((MainActivity)context).isResultList){
                    s = ((MainActivity)context).searchResultList.get(i);
                }

                Toast.makeText(context, "学号："+s.getNum()+" 姓名："+s.getName()+" 年龄："+s.getAge(),
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context,EditActivity.class);
                intent.putExtra("NAME",s.getName());
                intent.putExtra("AGE",s.getAge());
                intent.putExtra("IMG",s.getImgId());
                intent.putExtra("NUM",s.getNum());
                ((MainActivity)context).startActivityForResult(intent,1002);
            }
        });

        stuViewHolder.delView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.delStudent(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }
}
