package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.LocaleData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.bean.studentbean;
import com.example.myapplication.server.studentServer;

public class StudentActivity extends AppCompatActivity {
    studentServer server = new studentServer(StudentActivity.this);
    private TextView name, classname, age, phone, address, math, computer, yingyu, allscore;
    private Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_activity);

        name = findViewById(R.id.name);
        classname = findViewById(R.id.className);
        age = findViewById(R.id.age);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        math = findViewById(R.id.math1234);
        computer = findViewById(R.id.andriod1234);
        yingyu = findViewById(R.id.yingyu1234);
        allscore = findViewById(R.id.allsore1234);
        button1 = findViewById(R.id.change_inform);
        button2 = findViewById(R.id.check_class);


        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        String NAME = sharedPreferences.getString("name", "");

        findViewById(R.id.Student_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentActivity.this, MainActivity.class));
            }
        });

        final studentbean studentbean = server.student(NAME, null);
        name.setText(studentbean.getName());
        classname.setText(studentbean.getClassName());
        age.setText(studentbean.getAge());
        phone.setText(studentbean.getPhone());
        address.setText(studentbean.getAddress());
        math.setText(studentbean.getMath());
        computer.setText(studentbean.getComputer());
        yingyu.setText(studentbean.getEnglish());
        allscore.setText(studentbean.getAllsore());

//        修改信息设置监听
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(StudentActivity.this);
                builder.setTitle("信息修改");
                View view1 = LayoutInflater.from(StudentActivity.this).inflate(R.layout.dialog2, null);
                builder.setView(view1);
                //操作
                final EditText name123 = view1.findViewById(R.id.name123);
                final TextView xid123 = view1.findViewById(R.id.xuehao123);
                final EditText age123 = view1.findViewById(R.id.age123);
                final EditText phone123 = view1.findViewById(R.id.dianhua123);
                final EditText address123 = view1.findViewById(R.id.dizhi123);
                final EditText classname123 = view1.findViewById(R.id.className123);
                final EditText key = view1.findViewById(R.id.key);

                name123.setText(studentbean.getName());
                xid123.setText(studentbean.getxID());
                age123.setText(studentbean.getAge());
                phone123.setText(studentbean.getPhone());
                address123.setText(studentbean.getAddress());
                classname123.setText(studentbean.getClassName());
                key.setText(studentbean.getPassword());

                builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        studentbean studentbean = new studentbean();
                        studentbean.setName(name123.getText().toString().trim());
                        studentbean.setxID(xid123.getText().toString().trim());
                        studentbean.setAge(age123.getText().toString().trim());
                        studentbean.setPhone(phone123.getText().toString().trim());
                        studentbean.setAddress(address123.getText().toString().trim());
                        studentbean.setClassName(classname123.getText().toString().trim());
                        studentbean.setPassword(key.getText().toString().trim());
                        Boolean result = server.S_Informtion(studentbean);
                        if (result) {
                            Toast.makeText(StudentActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(StudentActivity.this, StudentActivity.class));
                        }
                    }
                });
                builder.setNegativeButton("关闭", null);
                builder.show();
            }
        });

        //查看班级设置监听
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("className", studentbean.getClassName());
                editor.commit();
                startActivity(new Intent(StudentActivity.this, Student_classnameActivity.class));
            }
        });

    }


}
