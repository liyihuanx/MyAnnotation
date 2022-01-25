package com.liyihuanx.lib_annotationapi

import android.app.Activity

/**
 * @author liyihuan
 * @date 2022/01/25
 * @Description
 */
abstract class BaseDownLoadProxy : IDownLoad {

    abstract fun setActivity(activity: Activity?)
}