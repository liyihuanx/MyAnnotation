package liyihuan.app.android.myannotation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import liyihuan.app.android.lib_annotation.DownLoad

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    @DownLoad
    fun download(){

    }

    /**
     * 下载时到相应的生命周期就会调用到
     */

    @DownLoad.onPre
    fun onPre(){

    }

    @DownLoad.onStart
    fun onNewStart(){

    }

    @DownLoad.onComplete
    fun onComplete(){

    }


}