package liyihuan.app.android.myannotation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.liyihuanx.lib_annotationapi.DownLoadBuilder
import com.liyihuanx.lib_annotationapi.IDownLoad
import kotlinx.android.synthetic.main.activity_main.*
import liyihuan.app.android.lib_annotation.DownLoad

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDownLoad.setOnClickListener {
            DownLoadBuilder().findProxy(this)
                .download()
        }
    }


    /**
     * 下载时到相应的生命周期就会调用到
     */

    @DownLoad.onPre
    fun onMainPre(){
        Log.d("QWER", "onMainPre: ")
    }

    @DownLoad.onSuc
    fun onMainSuc(){
        Log.d("QWER", "onMainSuc: ")

    }

    @DownLoad.onFail
    fun onMainFail(){
        Log.d("QWER", "onMainFail: ")

    }

    @DownLoad.onComplete
    fun onMainComplete(){
        Log.d("QWER", "onMainComplete: ")

    }


}