package com.example.myapplication;

import android.content.SharedPreferences;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.Adapter.MyAdapter;
import com.example.myapplication.bean.studentbean;
import com.example.myapplication.server.studentServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student_classnameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_classname);

        studentServer server = new studentServer(Student_classnameActivity.this);

        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
       String name = pref.getString("className", "");
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
        listView.setAdapter(new MyAdapter(Student_classnameActivity.this, data,
                R.layout.list_manage,
                new String[]{"name", "xID", "Math", "computer", "English", "allsore"},
                new int[]{R.id.name, R.id.xuehao, R.id.shuxue, R.id.jisuanji, R.id.yingyu, R.id.allsore}));


    }


}
