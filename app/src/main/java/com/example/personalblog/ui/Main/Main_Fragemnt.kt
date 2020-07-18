package com.example.personalblog.ui.Main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.personalblog.R
import com.example.personalblog.logic.model.Article
import kotlinx.android.synthetic.main.fragment_main.*

class Main_Fragemnt :Fragment(){
    val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main,container,false);
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        val manager= LinearLayoutManager(activity)
        val adapter= ArticleAdapter(viewModel.articleList)
        recyclerView_fragment_main.layoutManager=manager
        recyclerView_fragment_main.adapter=adapter
        viewModel.getAllResult.observe(viewLifecycleOwner, Observer {result->
            val resultLists=result.getOrNull()
            if(resultLists!=null&&resultLists.size>0){
                viewModel.articleList.clear()
                viewModel.articleList.addAll(resultLists)
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(context,"没有文章",Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun init(){
        viewModel.getAll()
    }
}