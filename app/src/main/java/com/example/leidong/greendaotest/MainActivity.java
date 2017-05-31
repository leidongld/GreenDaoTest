package com.example.leidong.greendaotest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.leidong.greendaotest.beans.User;
import com.example.leidong.greendaotest.gen.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private UserDao userDao;

    private EditText editText1, editText2, editText3;
    private TextView textView;
    private Button button1, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        initActions();
    }

    /**
     * 获取组件
     */
    private void initWidgets() {
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);

        textView = (TextView) findViewById(R.id.textView);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
    }

    /**
     * 初始化动作
     */
    private void initActions() {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    /**
     * 按钮点击事件
     * @param v 视图
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                addUser();
                break;
            case R.id.button2:
                deleteUser();
                break;
            case R.id.button3:
                modifyUser();
                break;
            case R.id.button4:
                queryUser();
                break;
            default:
                break;
        }
    }

    /**
     * 查
     */
    private void queryUser() {
        StringBuilder sb = new StringBuilder();
        userDao = MyApplication.getInstances().getDaoSession().getUserDao();
        List<User> userList = userDao.loadAll();
        for(int i = 0; i < userList.size(); i++){
            String temp = "";
            temp += "ID = " + userList.get(i).getId() + " ";
            temp += "Name = " + userList.get(i).getName() + " ";
            temp += "Age = " + userList.get(i).getAge() + "\n";
            sb.append(temp);
        }
        textView.setText(sb.toString());
    }

    /**
     * 改
     */
    private void modifyUser() {
        long id = Long.parseLong(editText1.getText().toString().trim());
        String name = editText2.getText().toString().trim();
        int age = Integer.parseInt(editText3.getText().toString().trim());
        userDao = MyApplication.getInstances().getDaoSession().getUserDao();
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        userDao.update(user);
    }

    /**
     * 删
     */
    private void deleteUser() {
        long id = Long.parseLong(editText1.getText().toString().trim());
        userDao = MyApplication.getInstances().getDaoSession().getUserDao();
        userDao.deleteByKey(id);
    }

    /**
     * 增
     */
    private void addUser() {
        long id = Long.parseLong(editText1.getText().toString().trim());
        String name = editText2.getText().toString().trim();
        int age = Integer.parseInt(editText3.getText().toString().trim());

        userDao = MyApplication.getInstances().getDaoSession().getUserDao();
        User user = new User();
        user.setName(name);
        user.setAge(age);
        userDao.insert(user);
    }
}
