package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.myapplication.Adapter.MyAdapter;
import com.example.myapplication.bean.studentbean;
import com.example.myapplication.bean.teacherbean;
import com.example.myapplication.db.StudentDB;
import com.example.myapplication.server.studentServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherActivity extends AppCompatActivity {
    private List<studentbean> data = new ArrayList<>();
    studentServer server = new studentServer(TeacherActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_activity);
        findViewById(R.id.teacher_back).setOnClickListener(this::Onclick);

        SharedPreferences sh = getSharedPreferences("data", MODE_PRIVATE);
        String name = sh.getString("name", "");
        TextView tname = findViewById(R.id.teacher_back);
        tname.setText(name);
//        findViewById(R.id.teacher_back).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(TeacherActivity.this, MainActivity.class));
//            }
//        });
        ListView view = findViewById(R.id.LV);
        server = new studentServer(TeacherActivity.this);
        final List<studentbean> studentbeans = server.ClassName();
        final List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (studentbean studentbean : studentbeans) {
            Map<String, Object> Map = new HashMap<>();
            Map.put("id", studentbean.get_id());
            Map.put("name", studentbean.getName());
            data.add(Map);
        }
//        System.out.println(data);
        view.setAdapter(new MyAdapter(TeacherActivity.this, data, R.layout.list_view, new String[]{"id", "name"},
                new int[]{R.id.list_className, R.id.list_persons}));
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = (String) data.get(i).get("name");
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("name", name);
                editor.commit();
                if (editor != null) {
                    startActivity(new Intent(TeacherActivity.this, StudentManageActivity.class));
                }
            }
        });
        findViewById(R.id.btn_search).setOnClickListener(this::Onclick);
//        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(TeacherActivity.this);
//                builder.setTitle("????????????");
//                final View view1 = LayoutInflater.from(TeacherActivity.this).inflate(R.layout.dialog3, null);
//                builder.setView(view1);
//                builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        final EditText xuehao = view1.findViewById(R.id.xuehao12345);
//                        final String xid = xuehao.getText().toString().trim();
//                        System.out.println(xid);
//
//
//                        final AlertDialog.Builder builder = new AlertDialog.Builder(TeacherActivity.this);
//                        builder.setTitle("????????????");
//                        View view2 = LayoutInflater.from(TeacherActivity.this).inflate(R.layout.dialog, null);
//                        builder.setView(view2);
//                        //??????
//                        final studentbean student = server.student(xid, null);
//                        final EditText name123 = view2.findViewById(R.id.name123);
//                        final EditText xid123 = view2.findViewById(R.id.xuehao123);
//                        final EditText age123 = view2.findViewById(R.id.age123);
//                        final EditText phone123 = view2.findViewById(R.id.dianhua123);
//                        final EditText address123 = view2.findViewById(R.id.dizhi123);
//                        final EditText shuxue123 = view2.findViewById(R.id.shuxue123);
//                        final EditText jisuanji123 = view2.findViewById(R.id.computer123);
//                        final EditText yingyu123 = view2.findViewById(R.id.English123);
//                        final EditText classname123 = view2.findViewById(R.id.class_Name123);
//
//
//                        name123.setText(student.getName());
//                        xid123.setText(student.getxID());
//                        age123.setText(student.getAge());
//                        phone123.setText(student.getPhone());
//                        address123.setText(student.getAddress());
//                        shuxue123.setText(student.getMath());
//                        jisuanji123.setText(student.getComputer());
//                        yingyu123.setText(student.getEnglish());
//                        classname123.setText(student.getClassName());
//
//                        builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                studentbean studentbean = new studentbean();
//                                studentbean.setName(name123.getText().toString().trim());
//                                studentbean.setxID(xid123.getText().toString().trim());
//                                studentbean.setAge(age123.getText().toString().trim());
//                                studentbean.setPhone(phone123.getText().toString().trim());
//                                studentbean.setClassName(classname123.getText().toString().trim());
//                                studentbean.setAddress(address123.getText().toString().trim());
//                                studentbean.setMath(shuxue123.getText().toString().trim());
//                                studentbean.setComputer(jisuanji123.getText().toString().trim());
//                                studentbean.setEnglish(yingyu123.getText().toString().trim());
//                                Integer all = Integer.valueOf(shuxue123.getText().toString().trim()) +
//                                        Integer.valueOf(jisuanji123.getText().toString().trim()) +
//                                        Integer.valueOf(yingyu123.getText().toString().trim());
//                                studentbean.setAllsore(all.toString());
//                                Boolean result = server.Informtion(studentbean);
//                                if (result) {
//                                    startActivity(new Intent(TeacherActivity.this, TeacherActivity.class));
//                                }
//                            }
//                        });
//                        builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                new AlertDialog.Builder(TeacherActivity.this).setTitle("???????????????????????????").setPositiveButton("??????", null).setPositiveButton("??????", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                        Boolean re = server.delete_Inform(xid);
//                                        if (re) {
//                                            startActivity(new Intent(TeacherActivity.this, TeacherActivity.class));
//                                        }
//                                    }
//                                }).show();
//
//                            }
//                        });
//                        builder.show();
//
//                    }
//
//                });
//                builder.show();

//            }
//        });
    }

    public void Onclick(View view) {
        switch (view.getId()) {
            case R.id.teacher_back:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(TeacherActivity.this)
                        .setTitle("???????????????");
                View view2 = LayoutInflater.from(TeacherActivity.this).inflate(R.layout.dialog4, null);
                TextView zhanghao = view2.findViewById(R.id.xuehao12345);
                EditText mima = view2.findViewById(R.id.mima12345);
                TextView name = findViewById(R.id.teacher_back);
                teacherbean teacherbean = server.TInformation(name.getText().toString().trim());
                zhanghao.setText(teacherbean.getAccount());
                mima.setText(teacherbean.getPassword());

                builder2.setView(view2);
                builder2.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        teacherbean.setAccount(zhanghao.getText().toString().trim());
                        teacherbean.setPassword(mima.getText().toString().trim());
                        Boolean result3 = server.CInformation(teacherbean);
                        if (result3) {
                            Toast.makeText(TeacherActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(TeacherActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                    }
                });
                builder2.show();
                break;
            case R.id.btn_search:
                AlertDialog.Builder builder = new AlertDialog.Builder(TeacherActivity.this);
                builder.setTitle("????????????");
                final View view1 = LayoutInflater.from(TeacherActivity.this).inflate(R.layout.dialog3, null);
                builder.setView(view1);
                builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final EditText xuehao = view1.findViewById(R.id.xuehao12345);
                        final String xid = xuehao.getText().toString().trim();

                        boolean result = server.SSearch(xid);
                        if (result) {

                            final AlertDialog.Builder builder = new AlertDialog.Builder(TeacherActivity.this);
                            builder.setTitle("????????????");
                            View view2 = LayoutInflater.from(TeacherActivity.this).inflate(R.layout.dialog, null);
                            builder.setView(view2);
                            //??????
                            System.out.println(xid);
                            final studentbean student = server.student(xid, null);
                            final EditText name123 = view2.findViewById(R.id.name123);
                            final EditText xid123 = view2.findViewById(R.id.xuehao123);
                            final EditText age123 = view2.findViewById(R.id.age123);
                            final EditText phone123 = view2.findViewById(R.id.dianhua123);
                            final EditText address123 = view2.findViewById(R.id.dizhi123);
                            final EditText shuxue123 = view2.findViewById(R.id.shuxue123);
                            final EditText jisuanji123 = view2.findViewById(R.id.computer123);
                            final EditText yingyu123 = view2.findViewById(R.id.English123);
                            final EditText classname123 = view2.findViewById(R.id.class_Name123);
                            EditText key = view2.findViewById(R.id.key);

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

                            builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    studentbean studentbean = new studentbean();
                                    studentbean.setName(name123.getText().toString().trim());
                                    studentbean.setxID(xid123.getText().toString().trim());
                                    studentbean.setPassword(key.getText().toString().trim());
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
                                        startActivity(new Intent(TeacherActivity.this, TeacherActivity.class));
                                    }
                                }
                            });
                            builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new AlertDialog.Builder(TeacherActivity.this).setTitle("???????????????????????????").setPositiveButton("??????", null).setPositiveButton("??????", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Boolean re = server.delete_Inform(xid);
                                            if (re) {
                                                startActivity(new Intent(TeacherActivity.this, TeacherActivity.class));
                                            }
                                        }
                                    }).show();

                                }
                            });
                            builder.show();
                        } else {
                            Toast.makeText(TeacherActivity.this, "????????????", Toast.LENGTH_SHORT).show();

                        }
                    }

                });
                builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(TeacherActivity.this);
                        builder.setTitle("????????????");
                        View view2 = LayoutInflater.from(TeacherActivity.this).inflate(R.layout.dialog, null);
                        builder.setView(view2);
                        //??????
                        final EditText name123 = view2.findViewById(R.id.name123);
                        final EditText xid123 = view2.findViewById(R.id.xuehao123);
                        final EditText age123 = view2.findViewById(R.id.age123);
                        final EditText phone123 = view2.findViewById(R.id.dianhua123);
                        final EditText address123 = view2.findViewById(R.id.dizhi123);
                        final EditText shuxue123 = view2.findViewById(R.id.shuxue123);
                        final EditText jisuanji123 = view2.findViewById(R.id.computer123);
                        final EditText yingyu123 = view2.findViewById(R.id.English123);
                        final EditText classname123 = view2.findViewById(R.id.class_Name123);
                        EditText key = view2.findViewById(R.id.key);

                        builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
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
                                Boolean result = server.SaddData2(studentbean);
                                if (result) {
                                    Toast.makeText(TeacherActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(TeacherActivity.this, TeacherActivity.class));
                                }else {
                                    Toast.makeText(TeacherActivity.this, "???????????????????????????", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                        builder.setNegativeButton("??????", null);
                        builder.show();
                    }
                });
                builder.show();

                break;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//????????????????????????
            startActivity(new Intent(TeacherActivity.this, MainActivity.class));
        }
        return false;
    }
}
