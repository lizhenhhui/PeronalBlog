package com.example.personalblog.ui.Login

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.personalblog.MainActivity
import com.example.personalblog.R
import com.example.personalblog.ui.Register.RegisterActivity
import com.example.personalblog.dao.MyDatabaseHelper
import com.example.personalblog.logic.network.HttpGetData
import com.example.personalblog.logic.network.ParseJsonWithJsonObject
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.FormBody

class LoginActivity : AppCompatActivity() ,View.OnClickListener{
    val viewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }
    lateinit var dbHelper:MyDatabaseHelper
    lateinit var db:SQLiteDatabase;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login_btn.setOnClickListener (this)
        register_text.setOnClickListener(this)
        dbHelper= MyDatabaseHelper(this,"User.db",2)
        db= dbHelper.writableDatabase
        val cursor=db.query("User",null,null,null,null,null,null)
        if(cursor.moveToFirst()){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onClick(v: View?) {
        when(v){
            login_btn->{val requestBody=FormBody.Builder()
                .add("username",username_Input.text.toString())
                .add("password",password_Input.text.toString())
                .build()
                val data=HttpGetData.getData(requestBody,"http://192.168.1.6:8080/App_war/user/login")
                if(data!=null){
                    val loginMsg= ParseJsonWithJsonObject.parseLoginMessage(data)
                    if(loginMsg.position&&!::db.isInitialized){
                       val value=ContentValues().apply {
                           put("uid",loginMsg.uid)
                           put("username",username_Input.text.toString())
                           put("password",password_Input.text.toString())
                           put("name","no")
                       }
                        db.insert("User",null,value)
                        startActivity(Intent(this,
                            MainActivity::class.java))
                   }
                }
            }
            register_text->{
                startActivity(Intent(this,
                    RegisterActivity::class.java))}
        }
    }

}