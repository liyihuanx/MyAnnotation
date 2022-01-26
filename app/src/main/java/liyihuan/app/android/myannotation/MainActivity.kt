package liyihuan.app.android.myannotation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.liyihuanx.lib_annotationapi.DownLoadBuilder
import com.liyihuanx.lib_annotationapi.DownLoadManager
import com.liyihuanx.lib_annotationapi.IDownLoad
import com.liyihuanx.lib_download.DownloadManager
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import liyihuan.app.android.lib_annotation.DownLoad
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    val path = "http://files.ibaodian.com/v2/teamfile/1ca447a600580cdcb575ab9348536f38/CM10086_android_V4.8.0_20180708_A0001.apk"
    val mRxPermission by lazy { RxPermissions(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDownLoad.setOnClickListener {
//            DownLoadBuilder().findProxy(this)
//                .download()
            startDown()
        }
    }

    @SuppressLint("CheckResult")
    private fun startDown() {
        mRxPermission.request(Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .subscribe {
                if (it) {
                    DownloadManager.INSTANCE.downloadFile(this, path)
                }
            }
    }


    private fun startDownload(callback: () -> Unit) {
        lifecycleScope.launch {
            flow<Int> {
                emit(10)
                Log.d("QWER", "flow-start: ${Thread.currentThread().name}")
            }.map {
                Log.d("QWER", "map: ${Thread.currentThread().name}")
                it + 1
            }.flatMapConcat {
                flatmap(it)
            }.flowOn(Dispatchers.IO)
                .collect {
                    callback.invoke()
                    Log.d("QWER", "collect:$it --> ${Thread.currentThread().name}")
                }
        }
    }

    private fun flatmap(i: Int): Flow<String> {
        return flow<String> {
            emit("123456-$i")
        }
    }


    /**
     * 下载时到相应的生命周期就会调用到
     */

    @DownLoad.onPre
    fun onMainPre() {
        Log.d("QWER", "onMainPre: ")
    }

    @DownLoad.onSuc
    fun onMainSuc() {
        Log.d("QWER", "onMainSuc: ")

    }

    @DownLoad.onFail
    fun onMainFail() {
        Log.d("QWER", "onMainFail: ")

    }

    @DownLoad.onComplete
    fun onMainComplete() {
        Log.d("QWER", "onMainComplete: ")

    }
}
