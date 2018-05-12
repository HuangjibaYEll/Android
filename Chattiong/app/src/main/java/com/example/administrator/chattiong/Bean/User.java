package com.example.administrator.chattiong.Bean;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.chattiong.App.NoteApplication;
import com.example.administrator.chattiong.LoginActivity;
import com.example.administrator.chattiong.MainActivity;
import com.example.administrator.chattiong.NoteActivity;
import com.example.administrator.chattiong.RegisterActivity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import static java.security.AccessController.getContext;

/**
 * Created by Administrator on 2017/5/24.
 */

public class User extends BmobUser{
    private String myusername;
    private String mypassword;
    private String e_mail;


    public void RegisterClick(final View view){
        SharedPreferences sp = NoteApplication.getUtils().getSpUtil();
        final SharedPreferences.Editor edit = sp.edit();
        BmobUser user = new BmobUser();
        user.setUsername(myusername);
        user.setPassword(mypassword);
        user.setEmail(e_mail);
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User s, BmobException e) {
                if (e == null) {
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    view.getContext().startActivity(intent);
                    edit.putString("username",myusername);
                    edit.commit();
                }else{
                    Toast.makeText(view.getContext(),"您的用户名或者邮箱已被注册",Toast.LENGTH_SHORT).show();
                    System.out.println(e.toString());
                }
            }
        });
    }

    public void LoginClick(final View view){
        SharedPreferences sp = NoteApplication.getUtils().getSpUtil();
        final SharedPreferences.Editor edit = sp.edit();
        BmobUser user = new BmobUser();
        user.setUsername(myusername);
        user.setPassword(mypassword);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    view.getContext().startActivity(intent);
                    edit.putString("username",myusername);
                    edit.commit();
                }else{
                    Toast.makeText(view.getContext(),"您的密码有误",Toast.LENGTH_SHORT).show();
                    System.out.println(e.toString());
                }
            }
        });
    }

    public String getUsrname(){
        SharedPreferences sp = NoteApplication.getUtils().getSpUtil();
        myusername = sp.getString("username", null);
        return myusername;
    }

    public void GetOut(View view){
        Intent intent = new Intent(view.getContext(), LoginActivity.class);
        view.getContext().startActivity(intent);
    }

    public void WriteNote(View view){
        Intent intent = new Intent(view.getContext(), NoteActivity.class);
        view.getContext().startActivity(intent);
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getMyusername() {
        return myusername;
    }

    public void setMyusername(String myusername) {
        this.myusername = myusername;
    }

    public String getMypassword() {
        return mypassword;
    }

    public void setMypassword(String mypassword) {
        this.mypassword = mypassword;
    }
}
