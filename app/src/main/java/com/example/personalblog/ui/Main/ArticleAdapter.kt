package com.example.personalblog.ui.Main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.personalblog.R

class ArticleAdapter (val articleList:List<Article>): RecyclerView.Adapter<ArticleAdapter.ViewHolder>(){

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val title=view.findViewById<TextView>(R.id.item_title)
        val content=view.findViewById<TextView>(R.id.item_content)
        val user=view.findViewById<TextView>(R.id.FaBu_User)
        val dianzhan_number=view.findViewById<TextView>(R.id.DianZhan_Number)
        val msg_number=view.findViewById<TextView>(R.id.Msg_Number)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview_fragment_main,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =articleList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val article=articleList[position]
        holder.title.text=article.title
        holder.content.text=article.content
        holder.user.text=article.username
        holder.dianzhan_number.text= article.dianzhan_number.toString()
        holder.msg_number.text=article.msg_number.toString()
    }

}