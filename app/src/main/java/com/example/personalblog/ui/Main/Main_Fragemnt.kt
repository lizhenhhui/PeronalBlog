package com.example.personalblog.ui.Main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.personalblog.R
import com.example.personalblog.logic.model.Article

class Main_Fragemnt :Fragment(){
    val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    private val articleList=ArrayList<Article>()
    private var recyclerView: RecyclerView?=null;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val view= inflater.inflate(R.layout.fragment_main,container,false)
        recyclerView=view.findViewById(R.id.recyclerView_fragment_main);
        println(recyclerView)
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        val manager= LinearLayoutManager(activity)
        val adapter= ArticleAdapter(articleList)
        recyclerView?.layoutManager=manager
        recyclerView?.adapter=adapter
    }

    fun init(){
//            val requestBody=FormBody.Builder()
//                .build()
//            val data= HttpGetData.getData(requestBody,"http://192.168.1.6:8080/App_war/article/findAll")
//            println(data)
//            if(data!=null){
//                val lists=ParseJsonWithJsonObject.parseArticle(data)
//                for (i in 0 until lists.size){
//                    articleList.add(lists[i])
//                }
//            }
    }
}