package com.example.personalblog

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.personalblog.ui.Bell.Bell_Fragment
import com.example.personalblog.ui.Main.Main_Fragemnt
import com.example.personalblog.ui.Person.Person_Fragment
import com.example.personalblog.ui.Star.Star_Fragement
import com.example.personalblog.ui.Study.Study_Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        house_btn.setOnClickListener(this)
        study_btn.setOnClickListener(this)
        star_btn.setOnClickListener(this)
        bell_btn.setOnClickListener(this)
        person_btn.setOnClickListener(this)
        replaceFragment(Main_Fragemnt())
    }

    override fun onClick(v: View?) {
        when(v){
            house_btn->{
                replaceFragment(Main_Fragemnt())
                changeColor(house_image.id)
            }
            study_btn->{
                replaceFragment(Study_Fragment())
                changeColor(main_book_image.id)
            }
            star_btn->{
                replaceFragment(Star_Fragement())
                changeColor(star_image.id)
            }
            bell_btn->{
                replaceFragment(Bell_Fragment())
                changeColor(bell_image.id)
            }
            person_btn->{
                replaceFragment(Person_Fragment())
                changeColor(person_image.id)
            }
        }
    }

    private fun replaceFragment(fragment:Fragment){
        val fragmentManager=supportFragmentManager
        val transaction=fragmentManager.beginTransaction()
        transaction.replace(R.id.main_view,fragment)
        transaction.commit()
    }
    fun changeColor(id:Int){
        val list= listOf<Int>(R.id.house_image,R.id.house_text,
            R.id.main_book_image,R.id.study_text,
            R.id.star_image,R.id.star_text,
            R.id.bell_image,R.id.bell_text,
            R.id.person_image,R.id.person_text)
        for(i  in 0 .. 8 step 2){
            if(id!=list[i]){
                findViewById<ImageView>(list[i]).setColorFilter(Color.GRAY)
                val textView=  findViewById<TextView>(list[i+1])
                textView.setTextColor(Color.GRAY)
                 textView.textSize= 13F;
            }else{
                findViewById<ImageView>(list[i]).setColorFilter(Color.RED)
                val textView=  findViewById<TextView>(list[i+1])
                textView.setTextColor(Color.RED)
                textView.textSize= 15F;
            }
        }
    }

}