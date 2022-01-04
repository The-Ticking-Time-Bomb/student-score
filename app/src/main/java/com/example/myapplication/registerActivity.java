package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.bean.studentbean;
import com.example.myapplication.bean.teacherbean;
import com.example.myapplication.server.studentServer;
import com.example.myapplication.db.StudentDB;

public class registerActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private EditText className;
    private EditText xID;
    private EditText shenfen;
    private Button register;
    StudentDB studentDB;
    studentServer Server = new studentServer(registerActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.reg_username);
        password = findViewById(R.id.reg_password);
        className = findViewById(R.id.reg_className);
        xID = findViewById(R.id.reg_xid);
        shenfen = findViewById(R.id.reg_shenfen);

        studentDB = new StudentDB(registerActivity.this);
    }

    public void infor_reg(View view) {
        ((Button) findViewById(R.id.reg_btn_sure)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString().trim();
                String key = password.getText().toString().trim();
                String classname = className.getText().toString().trim();
                String xid = xID.getText().toString().trim();
                String shen = shenfen.getText().toString().trim();
                switch (shen){
                    case "1":
                        if (shen.equals("1") && !TextUtils.isEmpty(classname) && !TextUtils.isEmpty(xid) && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(key)) {
                            studentbean bean = new studentbean();
                            bean.setName(name);
                            bean.setPassword(key);
                            bean.setClassName(classname);
                            bean.setxID(xid);
                            boolean result = Server.SaddData(bean);
                            if (result) {
                                Toast.makeText(registerActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }else {
//                        Toast.makeText(registerActivity.this, "注册失败，请检查学号和姓名", Toast.LENGTH_SHORT).show();
                            new AlertDialog.Builder(registerActivity.this)
                                    .setTitle("注册失败")//对话框标题
                                    .setMessage("请同学检查信息是否正确或完全")
                                    .create()
                                    .show();//对话框中显示的内容
                        }
                    break;
                    case "857":
                        if (shen.equals("857") && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(key)) {
                            teacherbean bean = new teacherbean();
                            bean.setAccount(name);
                            bean.setPassword(key);
                            boolean result = Server.TaddData(bean);
                            if (result) {
                                Toast.makeText(registerActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }else {
                            new AlertDialog.Builder(registerActivity.this)
                                    .setTitle("注册失败")//对话框标题
                                    .setMessage("请老师检查工号")
                                    .create()
                                    .show();//对话框中显示的内容
                        }
                        break;
                }
            }


        });
    }

    public void register(View view) {
        ((Button) findViewById(R.id.reg_btn_login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(registerActivity.this, MainActivity.class));
            }
        });
    }
}
