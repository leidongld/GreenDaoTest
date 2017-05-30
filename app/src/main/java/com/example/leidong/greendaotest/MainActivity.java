package com.example.leidong.greendaotest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.leidong.greendaotest.beans.User;
import com.example.leidong.greendaotest.gen.UserDao;

public class MainActivity extends AppCompatActivity {
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDao = MyApplication.getInstances().getDaoSession().getUserDao();
        User user = new User();
        user.setName("He Yi");
        user.setAge(24);
        userDao.insert(user);
    }
}
