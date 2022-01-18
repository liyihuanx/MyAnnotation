package liyihuan.app.android.lib_compiler.example

/**
 * @author liyihuan
 * @date 2022/01/18
 * @Description
 */
class MainActivityXXDownloadProxy : IDownLoad {
    val MainActivityProxy = ""


    override fun onPre() {
        // 这里应该调用，使用注解修饰了的那个方法
        // 需要MainActivity对象,然后调用MainActivity使用注解的方法
        // MainActivityProxy.xxx()
    }

    override fun onStart() {
    }

    override fun onComplete() {
    }

}