package com.liyihuanx.lib_download

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import okhttp3.HttpUrl

/**
 * @author liyihuan
 * @date 2022/01/26
 * @Description
 */
data class DownLoadBean(
    // 下载路径
    val url: String = "",
    // 文件名
    var fileName: String = "",
    // 总长度
    var totalLength: Long = 0L,
    // 已经下载的长度
    var hasDownloadLength: Long = 0L,
)