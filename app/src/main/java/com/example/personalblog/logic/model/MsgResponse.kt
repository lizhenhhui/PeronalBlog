package com.example.personalblog.logic.model

data class Msg(val msg:String ,val position:Boolean)

data class LoginMsg( val msg:String ,val position:Boolean,val uid:Int)