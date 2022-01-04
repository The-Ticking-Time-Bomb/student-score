package com.example.myapplication.server;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.myapplication.bean.studentbean;
import com.example.myapplication.bean.teacherbean;
import com.example.myapplication.db.StudentDB;

import java.util.ArrayList;
import java.util.List;

public class studentServer {
    private StudentDB studentDB;
    private List<studentbean> list;
    studentbean studentbean = null;
    teacherbean teacherbean = null;
    Cursor cursor = null;

    public studentServer(Context context) {
        studentDB = new StudentDB(context);
    }

    //学生登录
    public boolean SLogin(String xid, String password) {
        SQLiteDatabase dbR = studentDB.getReadableDatabase();
        String sql = "select * from student where xID=? and password=?";
        cursor = dbR.rawQuery(sql, new String[]{xid, password});
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }

    //学生查找
    public boolean SSearch(String xid) {
        SQLiteDatabase dbR = studentDB.getReadableDatabase();
        String sql = "select * from student where xID=?";
        cursor = dbR.rawQuery(sql, new String[]{xid});
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }

    //   学生注册
    public boolean SaddData(studentbean student) {
        SQLiteDatabase dbR = studentDB.getReadableDatabase();
        SQLiteDatabase dbW = studentDB.getWritableDatabase();

        String sqlc = "select * from student where name=? and xID=?";
        cursor = dbR.rawQuery(sqlc, new String[]{student.getName(), student.getxID()});
        if (!cursor.moveToFirst()) {
            String sql = "insert into student(name,password,className,xID) values(?,?,?,?)";
            Object obj[] = {student.getName(), student.getPassword(), student.getClassName(), student.getxID()};
            dbW.execSQL(sql, obj);
            cursor.close();
            return true;
        } else {
            return false;
        }
    }

    //   学生登记
    public boolean SaddData2(studentbean student) {
        SQLiteDatabase dbR = studentDB.getReadableDatabase();
        SQLiteDatabase dbW = studentDB.getWritableDatabase();

        String sqlc = "select * from student where xID=?";
        cursor = dbR.rawQuery(sqlc, new String[]{student.getxID()});
        if (!cursor.moveToFirst()) {
            String sql = "insert into student(name,password,className,xID,age,phone,address,Math,computer,English,Allsore) values(?,?,?,?,?,?,?,?,?,?,?)";
            Object obj[] = {student.getName(), student.getPassword(), student.getClassName(), student.getxID(), student.getAge(), student.getPhone(), student.getAddress()
                    , student.getMath(), student.getComputer(), student.getEnglish(), student.getAllsore()};
            dbW.execSQL(sql, obj);
            cursor.close();
            return true;
        } else {
            return false;
        }
    }

    //老师登录
    public boolean TLogin(String username, String password) {
        SQLiteDatabase dbR = studentDB.getReadableDatabase();
        if (username.equals("") && password.equals("")) {
            return false;
        } else {
            String sql = "select * from teacher where account=? and password=?";
            cursor = dbR.rawQuery(sql, new String[]{username, password});
            if (cursor.moveToFirst()) {
                return true;
            }
        }
        return false;
    }

    //   老师注册
    public Boolean TaddData(teacherbean teacher) {
        SQLiteDatabase dbW = studentDB.getWritableDatabase();
        SQLiteDatabase dbR = studentDB.getReadableDatabase();

        String sqlc = "select * from teacher where account=? and password=?";
        cursor = dbR.rawQuery(sqlc, new String[]{teacher.getAccount(), teacher.getPassword()});
        if (!cursor.moveToFirst()) {
            String sql = "insert into teacher(account,password) values(?,?)";
            Object obj[] = {teacher.getAccount(), teacher.getPassword()};
            dbW.execSQL(sql, obj);
            return true;
        } else {
            return false;
        }
    }

    //班级展示
    @SuppressLint("Range")
    public List<studentbean> ClassName() {
        list = new ArrayList<>();

        SQLiteDatabase dbR = studentDB.getReadableDatabase();
        String sql = "select *,count(className) from student group by className";
        cursor = dbR.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                studentbean = new studentbean();
                studentbean.set_id(cursor.getString(cursor.getColumnIndex("count(className)")));
                studentbean.setName(cursor.getString(cursor.getColumnIndex("className")));
                list.add(studentbean);
            } while (cursor.moveToNext());
        }
        return list;
    }

    //学生个数展示
    @SuppressLint("Range")
    public List<studentbean> ClassPeople(String className) {
        list = new ArrayList<>();

        SQLiteDatabase dbR = studentDB.getReadableDatabase();
        String sql = "select * from student where className=? order by Allsore DESC";
        cursor = dbR.rawQuery(sql, new String[]{className});
        if (cursor.moveToFirst()) {
            do {
                studentbean = new studentbean();
                studentbean.setName(cursor.getString(cursor.getColumnIndex("name")));
                studentbean.setxID(cursor.getString(cursor.getColumnIndex("xID")));
                studentbean.setMath(cursor.getString(cursor.getColumnIndex("Math")));
                studentbean.setComputer(cursor.getString(cursor.getColumnIndex("computer")));
                studentbean.setEnglish(cursor.getString(cursor.getColumnIndex("English")));
                studentbean.setAllsore(cursor.getString(cursor.getColumnIndex("Allsore")));
                list.add(studentbean);
            } while (cursor.moveToNext());
        }
        return list;
    }

    //    查询学生信息学
    @SuppressLint("Range")
    public studentbean student(String xid, String name) {
        SQLiteDatabase dbR = studentDB.getReadableDatabase();
        String sql = "select * from student where xID=?";
        String sql2 = "select * from student where name=?";
        if (name == null && xid != null) {
            cursor = dbR.rawQuery(sql, new String[]{xid});
        }
        if (name != null && xid == null) {
            cursor = dbR.rawQuery(sql2, new String[]{name});
        }
        if (cursor.moveToFirst()) {
            studentbean = new studentbean();
            studentbean.setName(cursor.getString(cursor.getColumnIndex("name")));
            studentbean.setxID(cursor.getString(cursor.getColumnIndex("xID")));
            studentbean.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            studentbean.setClassName(cursor.getString(cursor.getColumnIndex("className")));
            studentbean.setAge(cursor.getString(cursor.getColumnIndex("age")));
            studentbean.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            studentbean.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            studentbean.setMath(cursor.getString(cursor.getColumnIndex("Math")));
            studentbean.setComputer(cursor.getString(cursor.getColumnIndex("computer")));
            studentbean.setEnglish(cursor.getString(cursor.getColumnIndex("English")));
            studentbean.setAllsore(cursor.getString(cursor.getColumnIndex("Allsore")));
        }
        return studentbean;
    }

    //管理员信息查
    @SuppressLint("Range")
    public teacherbean TInformation(String account) {
        SQLiteDatabase dbR = studentDB.getReadableDatabase();
        String sql = "select * from teacher where account=?";
        cursor = dbR.rawQuery(sql, new String[]{account});
        if (cursor.moveToFirst()) {
            teacherbean = new teacherbean();
            teacherbean.setAccount(cursor.getString(cursor.getColumnIndex("account")));
            teacherbean.setPassword(cursor.getString(cursor.getColumnIndex("password")));
        }
        return teacherbean;
    }

    //    管理员信息更新
    public Boolean CInformation(teacherbean teacherbean) {
        SQLiteDatabase dbW = studentDB.getWritableDatabase();
        String sql = "update teacher set password=? where account=?";
        cursor = dbW.rawQuery(sql, new String[]{teacherbean.getPassword(), teacherbean.getAccount()});
        if (!cursor.moveToNext()) {
            cursor.close();
            return true;
        }
        return false;
    }

    //    信息更新
    public Boolean Informtion(studentbean studentbean) {
        SQLiteDatabase dbW = studentDB.getWritableDatabase();
        String sql = "update student set  xID=?, password=?, age=?,phone=?,address=?,Math=?,computer=?,English=?,Allsore=? ,className=? where name=?";
        cursor = dbW.rawQuery(sql, new String[]{studentbean.getxID(), studentbean.getPassword(), studentbean.getAge(), studentbean.getPhone(), studentbean.getAddress(),
                studentbean.getMath(), studentbean.getComputer(), studentbean.getEnglish(), studentbean.getAllsore(), studentbean.getClassName(), studentbean.getName()});
        if (!cursor.moveToNext()) {
            cursor.close();
            return true;
        }
        return false;
    }

    //    信息更新
    public Boolean S_Informtion(studentbean studentbean) {
        SQLiteDatabase dbW = studentDB.getWritableDatabase();

        String sql = "update student set name=?,password=? ,age=?,phone=?,address=? ,className=? where xID=?";

        cursor = dbW.rawQuery(sql, new String[]{studentbean.getName(), studentbean.getPassword(), studentbean.getAge(), studentbean.getPhone(), studentbean.getAddress(),
                studentbean.getClassName(), studentbean.getxID()});
        if (!cursor.moveToNext()) {
            cursor.close();
            return true;
        }
        return false;
    }

    //信息删除
    public Boolean delete_Inform(String xid) {
        SQLiteDatabase dbW = studentDB.getWritableDatabase();

        String sql = "delete from student where xID=?";
        cursor = dbW.rawQuery(sql, new String[]{xid});
        if (!cursor.moveToNext()) {
            cursor.close();
            return true;
        }
        return false;
    }

}
