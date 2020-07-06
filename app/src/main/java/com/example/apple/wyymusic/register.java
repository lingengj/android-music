package com.example.apple.wyymusic;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apple.wyymusic.database.UserService;
import com.example.apple.wyymusic.model.Users;

public class register extends Activity {

    EditText edtName,edtPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void click(View v){
        if(v.getId()==R.id.btnRegister){
            UserService service=new UserService(this);
            edtName=(EditText)findViewById(R.id.edtName);
            edtPwd=(EditText)findViewById(R.id.edtPwd);
            String name=String.valueOf(edtName.getText());
            String pwd=String.valueOf(edtPwd.getText());
            Users user=new Users(name,pwd);
            if(name.equals(""))
                Toast.makeText(this,"请输入用户名",Toast.LENGTH_SHORT).show();
            else if(pwd.equals(""))
                Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
            else if(service.addUser(user)){
                Intent result=new Intent();
                result.putExtra("name",name);
                result.putExtra("pwd",pwd);
                setResult(2,result);
                finish();
            }
            else
                Toast.makeText(this,"输入格式不正确",Toast.LENGTH_SHORT).show();
        }
        if(v.getId()==R.id.btnExit){
            finish();
        }
    }
}
