package com.hie2j.ad1023;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class AddstudentActivity extends AppCompatActivity {

    private EditText editNum;
    private EditText editName;
    private EditText editAge;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private RadioButton radioButton5;
    private Button saveStuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);
        setTitle("添加学生");

        initFindModule();

        saveStuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                String num = editNum.getText().toString();
                int age = Integer.parseInt(editAge.getText().toString());

                int imgId = R.drawable.dog1;
                if (radioButton1.isChecked()){
                    imgId = R.drawable.dog1;
                }else if (radioButton2.isChecked()){
                    imgId = R.drawable.dog2;
                }else if (radioButton3.isChecked()){
                    imgId = R.drawable.dog3;
                }else if (radioButton4.isChecked()){
                    imgId = R.drawable.dog4;
                }else if (radioButton5.isChecked()){
                    imgId = R.drawable.dog5;
                }

                Intent intent = new Intent();
                intent.putExtra("NAME",name);
                intent.putExtra("NUM",num);
                intent.putExtra("AGE",age);
                intent.putExtra("IMG",imgId);
                setResult(2002,intent);

                finish();
            }
        });

    }
    //绑定各个组件
    private void initFindModule() {
        editNum = findViewById(R.id.et_num);
        editAge = findViewById(R.id.et_age);
        editName = findViewById(R.id.et_name);
        radioButton1 = findViewById(R.id.rb_1);
        radioButton2 = findViewById(R.id.rb_2);
        radioButton3 = findViewById(R.id.rb_3);
        radioButton4 = findViewById(R.id.rb_4);
        radioButton5 = findViewById(R.id.rb_5);
        saveStuBtn = findViewById(R.id.btn_saveStu);
    }
}
