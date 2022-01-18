package liyihuan.app.android.lib_compiler.example

/**
 * @author liyihuan
 * @date 2022/01/18
 * @Description 所有下载的公用接口类
 */

// 所有下载的公用接口类
interface IDownLoad {

    fun onPre()

    fun onStart()

    fun onComplete()

}

// 回调出去给调用者的接口
interface IDownLoadCallBack {

    fun onPre()

    fun onStart()

    fun onComplete()

}