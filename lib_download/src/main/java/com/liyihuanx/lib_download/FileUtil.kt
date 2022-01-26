package com.liyihuanx.lib_download

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.content.FileProvider
import java.io.File
import java.lang.Exception

/**
 * @author liyihuan
 * @date 2022/01/26
 * @Description
 */
object FileUtil {


    /**
     * 如果文件已下载重新命名新文件名
     * @param downloadInfo
     * @return
     */
    fun getRealFileName(downloadInfo: DownLoadBean): DownLoadBean {
        // 文件名
        val fileName: String = downloadInfo.fileName
        // 下载了的文件长度
        var downloadLength: Long = 0
        // 文件总长度
        val contentLength: Long = downloadInfo.totalLength
        // 创建文件夹
        val path: File = File(Constant.FILE_PATH)
        if (!path.exists()) {
            path.mkdir()
        }
        // 找到文件
        var file: File = File(Constant.FILE_PATH, fileName)
        if (file.exists()) {
            //找到了文件,代表已经下载过,则获取其长度
            downloadLength = file.length()
        }

        // 之前下载过,需要重新来一个文件
        var i = 1
        // 下载了的文件长度 >= 文件总长度 --> 已经下载过了
        while (downloadLength >= contentLength) {
            val dotIndex = fileName.lastIndexOf(".")
            val fileNameOther: String = if (dotIndex == -1) {
                "$fileName($i)"
            } else {
                // xxxx.apk --> xxxx + (i) + .apk
                "${fileName.substring(0, dotIndex)}($i)${fileName.substring(dotIndex)}"
            }

            val newFile: File = File(Constant.FILE_PATH, fileNameOther)
            file = newFile
            downloadLength = newFile.length()
            i++
        }
        //设置改变过的文件名/大小
        downloadInfo.hasDownloadLength = downloadLength
        downloadInfo.fileName = file.name
        return downloadInfo
    }


    fun startInstall(context: Context, file: File) {
        if (Build.VERSION.SDK_INT >= 29) {
            val haveInstallPermission: Boolean =
                context.packageManager.canRequestPackageInstalls()
            Log.d("QWER", "startInstall: $haveInstallPermission")


            val intent = Intent(Intent.ACTION_VIEW)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            val apkUri: Uri = FileProvider.getUriForFile(context, "liyihuan.app.android.myannotation.fileProvider", file)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive")
            try {
                context.startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            val uri = Uri.fromFile(file)
            val intent = Intent(Intent.ACTION_VIEW)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.setDataAndType(uri, "application/vnd.android.package-archive")
            context.startActivity(intent)
        }
    }


}