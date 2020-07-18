package com.example.personalblog.ui.Register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.personalblog.R
import com.example.personalblog.logic.model.RegisterRequest
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() , View.OnClickListener {
    val viewModel by lazy { ViewModelProvider(this)[RegisterViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        register_btn.setOnClickListener(this)
        viewModel.registerLiveData.observe(this, Observer {result->
            val registerMsg=result.getOrNull()
            if(registerMsg!=null&&registerMsg.position){
                Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"注册失败",Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onClick(v: View?) {
        when (v) {
            register_btn -> {
                val registerRequest = RegisterRequest(
                    username_register_Input.text.toString(),
                    username_register_Input.text.toString(),
                    email_register_Input.text.toString()
                )
                viewModel.register(registerRequest)
            }
        }
    }
}