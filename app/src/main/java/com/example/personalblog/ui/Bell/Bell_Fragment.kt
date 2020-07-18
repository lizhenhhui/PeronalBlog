package com.example.personalblog.ui.Bell

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.personalblog.R

class Bell_Fragment :Fragment(){
    val viewModel by lazy { ViewModelProvider(this)[BellViewModel::class.java] }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bell,container,false)
    }
}