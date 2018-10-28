package com.hie2j.ad1023;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IOnDelListener {
    private RecyclerView rlvStu;
    private ArrayList<Student> studentArrayList = new ArrayList<Student>();
    public ArrayList<Student> searchResultList = new ArrayList<Student>();
    private StudentAdapter adapter;
    private Button addStuBtn;
    private EditText editKeyWord;
    private ImageView ivSearch;
    public boolean isResultList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("RecyclerView");

        rlvStu = findViewById(R.id.rlv_stu);

        initSearchView();
        initStudentList();
        initAddStudentBtn();
        initStuAdapter();
        initLayoutManager();
    }
    /**
     * 初始化搜索模块
     */
    private void initSearchView() {
        editKeyWord = findViewById(R.id.et_key);
        ivSearch = findViewById(R.id.iv_search);
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyWord = editKeyWord.getText().toString().trim();
                search(studentArrayList, keyWord);
                adapter.changeData(searchResultList);
                isResultList = true;
            }
        });
        editKeyWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String keyWord = editKeyWord.getText().toString().trim();
                search(studentArrayList,keyWord);
                adapter.changeData(searchResultList);
                isResultList = true;
            }
        });
    }

    /**
     * 使用学生列表和关键字 得到搜索结果列表
     * @param studentArrayList
     * @param keyWord
     */
    private void search(ArrayList<Student> studentArrayList, String keyWord) {
        searchResultList.clear();
        for(int i=0; i<studentArrayList.size(); i++){
            Student s = studentArrayList.get(i);
            // 如果学生姓名包含了关键字 这个学生就加入到结果列表
            if(s.getName().contains(keyWord)){
                searchResultList.add(s);
            }
            // 如果学生年龄包含关键字 这个学生就加入到结果列表
            else if(String.valueOf(s.getAge()).contains(keyWord)){
                searchResultList.add(s);
            }
            // 如果学生学号等于关键字 这个学生就加入到结果列表
            else if (s.getNum().equals(keyWord)){
                searchResultList.add(s);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.e("MainActivity","requestCode ="+requestCode+" resultCode ="+resultCode);
        if (data == null){
            return;
        }
        if (resultCode == 2002){
            String name = data.getStringExtra("NAME");
            int age = data.getIntExtra("AGE",0);
            String num = data.getStringExtra("NUM");
            int imgId = data.getIntExtra("IMG",R.drawable.dog1);

            Student s = new Student(num,name,age,imgId);
            studentArrayList.add(s);
            adapter.notifyDataSetChanged();
        }else if (resultCode == 3003){
            String name = data.getStringExtra("NAME");
            int age = data.getIntExtra("AGE",0);
            int imgId = data.getIntExtra("IMG", R.drawable.dog1);
            String num = data.getStringExtra("NUM");

            for(int i=0; i<studentArrayList.size(); i++){
                Student s = studentArrayList.get(i);
//                Log.e("Mainactivity","data.num = "+num+"s.getNum() = "+s.getNum());

                if(s.getNum().equals(num)) {
//                    Log.e("Mainactivity",name + age );
                    s.setName(name);
                    s.setAge(age);
                    s.setImgId(imgId);
                    adapter.notifyDataSetChanged();
                }
            }
//            String keyword = edtCheck.getText().toString().trim();
//            // 使用学生列表和关键字 得到搜索结果列表
//            search(studentArrayList, keyword);
//            stuAdapter.changeData(searchResultList);
//            isResultList = true;
        }
    }

    //初始化添加学生按钮
    private void initAddStudentBtn() {
        addStuBtn = findViewById(R.id.btn_addStu);
        addStuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddstudentActivity.class);
                startActivityForResult(intent,1001);
            }
        });
    }
    //初始化manager
    private void initLayoutManager() {
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
        rlvStu.setLayoutManager(manager);
    }
    //初始化adapter
    private void initStuAdapter() {
        adapter = new StudentAdapter(MainActivity.this,studentArrayList,MainActivity.this);
        rlvStu.setAdapter(adapter);
    }
    //初始化学生列表
    private void initStudentList() {
        Student s1 = new Student("18001","张一",19,R.drawable.dog1);
        Student s2 = new Student("18002","张二",20,R.drawable.dog2);
        Student s3 = new Student("18003","张三",21,R.drawable.dog3);
        Student s4 = new Student("18004","张四",22,R.drawable.dog4);
        Student s5 = new Student("18005","张五",23,R.drawable.dog5);
        studentArrayList.add(s1);
        studentArrayList.add(s2);
        studentArrayList.add(s3);
        studentArrayList.add(s4);
        studentArrayList.add(s5);
    }
    //删除指定学生
    @Override
    public void delStudent(int i) {
        studentArrayList.remove(i);
        adapter.notifyDataSetChanged();
    }
}
