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

public class login extends Activity {
    EditText edtName,edtPwd;
    Button hide_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void click(View v){
        edtName=(EditText)findViewById(R.id.edtName);
        edtPwd=(EditText)findViewById(R.id.edtPwd);
        if(v.getId()==R.id.btnLogin){
            UserService service=new UserService(this);
            String name=String.valueOf(edtName.getText());
            String pwd=String.valueOf(edtPwd.getText());
            Users user=new Users(name,pwd);
            if(name.equals(""))
                Toast.makeText(this,"请输入用户名",Toast.LENGTH_SHORT).show();
            else if(pwd.equals(""))
                Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
            else if(service.getUser(user)){
                SharedPreferences.Editor editor=getSharedPreferences("status",MODE_PRIVATE).edit();
                editor.putString("logStatus","login");
                editor.commit();
                startActivity(new Intent(this,MainActivity.class));
                finish();
            }
            else
                Toast.makeText(this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
        }
        if(v.getId()==R.id.btnRegister){
            startActivityForResult(new Intent(this,register.class),1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==2){
            edtName.setText(data.getStringExtra("name"));
            edtPwd.setText(data.getStringExtra("pwd"));
        }
    }
}
