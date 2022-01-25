package com.liyihuanx.lib_annotationapi

/**
 * @author liyihuan
 * @date 2022/01/25
 * @Description
 */
object HttpUtil {

    fun startDownLoad(callback: DownloadStatus) {
        callback.onFail()
        callback.onSuc()
    }

}

