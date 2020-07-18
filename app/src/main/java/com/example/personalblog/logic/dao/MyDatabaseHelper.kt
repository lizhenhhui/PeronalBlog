package com.example.personalblog.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper (val context: Context, name:String, version:Int):SQLiteOpenHelper(context,name,null,version){
    private val createUser="create table User(" +
            "    uid integer primary key  autoincrement," +
            "    username text," +
            "    name text," +
            "    password text" +
            ")"
    private val createArticle="create table Article("+
            " aid integer primary key autoincrement,"+
            "uid integer integer,"+
            "title text,"+
            "cid integer,"+
            "content text,"+
            "imageName text,"+
            "dianzhan_number integer,"+
            "msg_number integer"+
            ")"
    override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL(createUser)
            db?.execSQL(createArticle)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists User")
        db?.execSQL("drop table if exists Article")
        onCreate(db)
    }

}