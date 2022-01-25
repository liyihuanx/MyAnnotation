package com.liyihuanx.lib_annotationapi

/**
 * @author liyihuan
 * @date 2022/01/25
 * @Description
 */
interface IDownLoad {
    fun onPre() {}

    fun onSuc() {}

    fun onFail() {}

    fun onComplete() {}

    fun onStart() {}
}