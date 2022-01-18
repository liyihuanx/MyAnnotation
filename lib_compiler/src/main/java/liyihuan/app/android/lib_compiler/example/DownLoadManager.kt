package liyihuan.app.android.lib_compiler.example

/**
 * @author liyihuan
 * @date 2022/01/18
 * @Description
 */
object DownLoadManager {


    // 这里传入调用者context
    fun download(){
        // 反射拿到 MainActivityXXDownloadProxy 对象
        val proxy = MainActivityXXDownloadProxy()
        // 发起DownLoad的Http请求
        getHttpDownLoad()
        // 在http请求调用对应的方法
        proxy.onComplete()
        proxy.onStart()
        proxy.onPre()

    }


    private fun getHttpDownLoad(){

    }




}