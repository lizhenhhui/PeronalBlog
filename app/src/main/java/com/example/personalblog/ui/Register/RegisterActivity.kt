package com.example.personalblog.ui.Register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.personalblog.R
import com.example.personalblog.logic.network.HttpGetData
import com.example.personalblog.logic.network.ParseJsonWithJsonObject
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.FormBody

class RegisterActivity : AppCompatActivity() , View.OnClickListener{
    val viewModel by lazy { ViewModelProvider(this)[RegisterViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        register_btn.setOnClickListener (this)
    }

    override fun onClick(v: View?) {
        when(v){
            register_btn->{ val requestBody= FormBody.Builder()
                .add("username",username_register_Input.text.toString())
                .add("password",password_register_Input.text.toString())
                .add("email",email_register_Input.text.toString())
                .build()
                val data=HttpGetData.getData(requestBody,"http://192.168.1.6:8080/App_war/user/registerUser");
                if(data!=null){
                    val msg=ParseJsonWithJsonObject.parseRegisterMessage(data)
                    println(msg.msg)
                }}
        }
    }
}