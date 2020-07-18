package com.example.personalblog.ui.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.personalblog.MainActivity
import com.example.personalblog.R
import com.example.personalblog.ui.Register.RegisterActivity
import com.example.personalblog.logic.model.LoginRequest
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() ,View.OnClickListener{
    val viewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login_btn.setOnClickListener (this)
        register_text.setOnClickListener(this)
        viewModel.loginLiveData.observe(this, Observer {result->
            val loginMsg=result.getOrNull()
            if(loginMsg!=null&&loginMsg.position){
                startActivity(Intent(this,
                    MainActivity::class.java))
            }else{
                Toast.makeText(this,"登录失败，账号或者密码错误",Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onClick(v: View?) {
        when(v){
            login_btn->{
                val loginRequest=LoginRequest(username_Input.text.toString(),password_Input.text.toString())
                viewModel.login(loginRequest)
            }
            register_text->{
                startActivity(Intent(this,
                    RegisterActivity::class.java))}
        }
    }
}