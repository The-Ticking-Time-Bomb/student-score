package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import com.example.myapplication.TeacherActivity;
import com.example.myapplication.bean.studentbean;

import java.util.List;
import java.util.Map;

public class MyAdapter extends SimpleAdapter {

    public MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);

    }


}
