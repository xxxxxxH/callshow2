package ftp.callshow.colorss.app.utils

import androidx.appcompat.app.AppCompatActivity

var b = false

fun AppCompatActivity.showOpen(clazz: Class<*>,b:()->Unit){
    b()
    nextActivity(clazz, true)
}

fun AppCompatActivity.whenInsertAdClose(clazz: Class<*>){
    if (configEntity.showOpenAdWithInsertAd()){
        jump(clazz)
    }
}

fun AppCompatActivity.whenSplashAdClose(clazz: Class<*>){
    jump(clazz)
}

fun AppCompatActivity.jump(clazz: Class<*>){
    if (b){
        b = !b
        nextActivity(clazz, true)
    }
}