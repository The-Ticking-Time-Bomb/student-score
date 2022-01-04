package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.Adapter.MyAdapter;
import com.example.myapplication.bean.studentbean;
import com.example.myapplication.server.studentServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StudentManageActivity extends AppCompatActivity {
    private TextView text1;
    studentServer server = new studentServer(StudentManageActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_manage);

        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        final String name = pref.getString("name", "");

        TextView view = findViewById(R.id.name);
        view.setText(name + "班名单");


        ListView listView = findViewById(R.id.student_manage);

        List<studentbean> studentbeans = server.ClassPeople(name);
        final List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (studentbean studentbean : studentbeans) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", studentbean.getName());
            map.put("xID", studentbean.getxID());
            map.put("Math", studentbean.getMath());
            map.put("computer", studentbean.getComputer());
            map.put("English", studentbean.getEnglish());
            map.put("allsore", studentbean.getAllsore());
            data.add(map);
        }
        System.out.println(data);
        listView.setAdapter(new MyAdapter(StudentManageActivity.this, data,
                R.layout.list_manage,
                new String[]{"name", "xID", "Math", "computer", "English", "allsore"},
                new int[]{R.id.name, R.id.xuehao, R.id.shuxue, R.id.jisuanji, R.id.yingyu, R.id.allsore}));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {
                final String xid = (String) data.get(i).get("xID");

                if (xid != null) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(StudentManageActivity.this);
                    builder.setTitle("信息修改");
                    View view1 = LayoutInflater.from(StudentManageActivity.this).inflate(R.layout.dialog, null);
                    builder.setView(view1);
                    //操作
                    final studentbean student = server.student(xid, null);
                    final EditText name123 = view1.findViewById(R.id.name123);
                    final EditText xid123 = view1.findViewById(R.id.xuehao123);
                    final EditText age123 = view1.findViewById(R.id.age123);
                    final EditText phone123 = view1.findViewById(R.id.dianhua123);
                    final EditText address123 = view1.findViewById(R.id.dizhi123);
                    final EditText shuxue123 = view1.findViewById(R.id.shuxue123);
                    final EditText jisuanji123 = view1.findViewById(R.id.computer123);
                    final EditText yingyu123 = view1.findViewById(R.id.English123);
                    final EditText classname123 = view1.findViewById(R.id.class_Name123);
                    final EditText key = view1.findViewById(R.id.key);

                    name123.setText(student.getName());
                    xid123.setText(student.getxID());
                    age123.setText(student.getAge());
                    phone123.setText(student.getPhone());
                    address123.setText(student.getAddress());
                    shuxue123.setText(student.getMath());
                    jisuanji123.setText(student.getComputer());
                    yingyu123.setText(student.getEnglish());
                    classname123.setText(student.getClassName());
                    key.setText(student.getPassword());
                    builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            studentbean studentbean = new studentbean();
                            studentbean.setName(name123.getText().toString().trim());
                            studentbean.setPassword(key.getText().toString().trim());
                            studentbean.setxID(xid123.getText().toString().trim());
                            studentbean.setAge(age123.getText().toString().trim());
                            studentbean.setPhone(phone123.getText().toString().trim());
                            studentbean.setClassName(classname123.getText().toString().trim());
                            studentbean.setAddress(address123.getText().toString().trim());
                            studentbean.setMath(shuxue123.getText().toString().trim());
                            studentbean.setComputer(jisuanji123.getText().toString().trim());
                            studentbean.setEnglish(yingyu123.getText().toString().trim());
                            Integer all = Integer.valueOf(shuxue123.getText().toString().trim()) +
                                    Integer.valueOf(jisuanji123.getText().toString().trim()) +
                                    Integer.valueOf(yingyu123.getText().toString().trim());
                            studentbean.setAllsore(all.toString());
                            Boolean result = server.Informtion(studentbean);
                            if (result) {
                                Toast.makeText(StudentManageActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(StudentManageActivity.this, StudentManageActivity.class));
                            }
                        }
                    });
                    builder.setNegativeButton("删除", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new AlertDialog.Builder(StudentManageActivity.this).setTitle("确认删除该学生信息").setPositiveButton("取消", null).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Boolean re = server.delete_Inform(xid);
                                    if (re) {
                                        startActivity(new Intent(StudentManageActivity.this, TeacherActivity.class));
                                    }
                                }
                            }).show();

                        }
                    });
                    builder.show();
                }

            }
        });

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
           startActivity(new Intent(StudentManageActivity.this,TeacherActivity.class));
        }
        return false;
    }

}
