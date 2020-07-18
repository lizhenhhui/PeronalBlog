package com.example.personalblog.logic.network

import com.example.personalblog.logic.model.Article
import com.example.personalblog.logic.model.LoginMsg
import com.example.personalblog.logic.model.Msg
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class ParseJsonWithJsonObject {
    companion object{
        @JvmStatic
        //解析登录数据
        fun parseLoginMessage(jsonData:String): LoginMsg {
            try {
                val jsonObject=JSONObject(jsonData)
                val msg=jsonObject.getString("msg");
                val position=jsonObject.getBoolean("position")
                val uid=jsonObject.getInt("uid");
                return LoginMsg(msg, position, uid);
            }catch (e:Exception){
                e.printStackTrace()
                return LoginMsg("登录异常", false, 0);
            }
        }
        @JvmStatic
        fun parseRegisterMessage(jsonData:String): LoginMsg {
            try {
                val jsonObject=JSONObject(jsonData)
                val msg=jsonObject.getString("msg");
                val position=jsonObject.getBoolean("position")
                return LoginMsg(msg, position,0);
            }catch (e:Exception){
                return LoginMsg(
                    "注册异常",
                    false,
                    0
                );
            }
        }
        fun parseArticle(jsonData: String):List<Article>{
            val jsonArray=JSONArray(jsonData)
            val lists=ArrayList<Article>()
            for(i in 0 until jsonArray.length()){
                val jsonObject=jsonArray.getJSONObject(i)
                val article=Article(jsonObject.getString("title"),
                    jsonObject.getString("content"),
                    jsonObject.getInt("dianzhan_number").toString(),
                    jsonObject.getInt("msg_number"),
                    jsonObject.getInt("uid"))
                lists.add(article)
            }
            return lists
        }
    }

}