package com.liyihuanx.lib_annotationapi

import android.app.Activity

/**
 * @author liyihuan
 * @date 2022/01/25
 * @Description
 */
class DownLoadBuilder {

    private var activityName = ""
    private var baseDownLoadProxy: BaseDownLoadProxy? = null

    fun findProxy(activity: Activity): DownLoadBuilder {
        activityName = activity::class.java.simpleName
        val path = "${activity.packageName}.${activityName}XXProxy"


        baseDownLoadProxy = DownLoadManager.proxyHashMap[activityName]
        if (baseDownLoadProxy == null) {
            val aClass = Class.forName(path)
            baseDownLoadProxy = aClass.newInstance() as BaseDownLoadProxy
            DownLoadManager.proxyHashMap[activityName] = baseDownLoadProxy!!
        }
        baseDownLoadProxy?.setActivity(activity)


        return this
    }


    fun download() {
        baseDownLoadProxy?.onPre()
        baseDownLoadProxy?.onStart()
        HttpUtil.startDownLoad(object : DownloadStatus {
            override fun onSuc() {
                baseDownLoadProxy?.onSuc()
            }

            override fun onFail() {
                baseDownLoadProxy?.onFail()

            }

        })
        baseDownLoadProxy?.onComplete()
    }

}