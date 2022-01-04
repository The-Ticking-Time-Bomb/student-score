package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import com.example.myapplication.db.StudentDB;
import com.example.myapplication.server.studentServer;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    RadioGroup radioGroup;
    RadioButton radioButton;
    StudentDB studentDB;
    studentServer Server = new studentServer(MainActivity.this);

    private String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.Login_username);
        password = findViewById(R.id.Login_password);
        studentDB = new StudentDB(MainActivity.this);

        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.check(R.id.student);


        findViewById(R.id.Login).setOnClickListener(new View.OnClickListener() {
            //获取sp对象
            SharedPreferences sp = getSharedPreferences("data", Context.MODE_PRIVATE);
            //获取EditText对象
            SharedPreferences.Editor editor = sp.edit();
            @Override
            public void onClick(View view) {
                radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                value = radioButton.getText().toString().trim();
                switch (value) {
                    case "学生":
                        String name = username.getText().toString().trim();
                        String key = password.getText().toString().trim();
                        boolean result = Server.SLogin(name, key);
                        if (result) {
                            //保存用户名
                            editor.putString("name", name);
                            editor.commit();
                            startActivity(new Intent(MainActivity.this, StudentActivity.class));
                            username.setText("");
                            password.setText("");
                        } else {
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("登录失败,用户不存在！")//对话框标题
                                    .setMessage("请同学检查账号和密码")
                                    .create()
                                    .show();//对话框中显示的内容
                        }
                        break;
                    case "管理员":
                        String name2 = username.getText().toString().trim();
                        String key2 = password.getText().toString().trim();
                        boolean result2 = Server.TLogin(name2, key2);
                        if (result2) {
                            editor.putString("name",name2);
                            editor.commit();
                            startActivity(new Intent(MainActivity.this, TeacherActivity.class));
                            username.setText("");
                            password.setText("");
                        } else {
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("登录失败")//对话框标题
                                    .setMessage("请老师检查工号和密码")
                                    .create()
                                    .show();//对话框中显示的内容
                        }
                        break;
                }
            }
        });


////学生登录
//        SLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //获取sp对象
//                SharedPreferences sp = getSharedPreferences("data", Context.MODE_PRIVATE);
//                //获取EditText对象
//                SharedPreferences.Editor editor = sp.edit();
//
//                String name = username.getText().toString().trim();
//                String key = password.getText().toString().trim();
//                boolean result = Server.SLogin(name, key);
//                if (result) {
//                    //保存用户名
//                    editor.putString("name", name);
//                    editor.commit();
//                    findViewById(R.id.Student_Login).setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            startActivity(new Intent(MainActivity.this, StudentActivity.class));
//                            username.setText("");
//                            password.setText("");
//                        }
//                    });
//                } else {
//                    new AlertDialog.Builder(MainActivity.this)
//                            .setTitle("登录失败,用户不存在！")//对话框标题
//                            .setMessage("请同学检查账号和密码")
//                            .create()
//                            .show();//对话框中显示的内容
//                }
//            }
//        });
//
//        TLogin.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                String name = username.getText().toString().trim();
//                String key = password.getText().toString().trim();
//                boolean result = Server.TLogin(name, key);
//                if (result) {
//                    findViewById(R.id.Teacher_Login).setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            startActivity(new Intent(MainActivity.this, TeacherActivity.class));
//                            username.setText("");
//                            password.setText("");
//                        }
//                    });
//                } else {
//                    new AlertDialog.Builder(MainActivity.this)
//                            .setTitle("登录失败")//对话框标题
//                            .setMessage("请老师检查工号和密码")
//                            .create()
//                            .show();//对话框中显示的内容
//                }
//            }
//        });
    }


    public void register(View view) {
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, registerActivity.class));
            }
        });
    }
}
