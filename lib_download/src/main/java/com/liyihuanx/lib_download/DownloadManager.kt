package com.liyihuanx.lib_download

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.HashMap

/**
 * @author liyihuan
 * @date 2022/01/26
 * @Description
 */
class DownloadManager private constructor() {

    private val mClient: OkHttpClient = OkHttpClient.Builder().build()
    private val downCalls: HashMap<String, Call> = HashMap()

    companion object {
        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DownloadManager()
        }
    }

    private lateinit var temContext: Context

    fun downloadFile(context: Context, url: String) {
        temContext = context
        val job = GlobalScope.launch(Dispatchers.IO) {
            flow {
                emit(url)
            }.map {
                createDownInfo(it)
            }.map {
                // 创建下载的Bean类
                // 获取文件的命名
                FileUtil.getRealFileName(it)
            }.flatMapConcat {
                realDownLoad(it)
            }.debounce(1000)
                .collect {
                    Log.d("QWER", "collect: ${it.totalLength}")
//                    val file = File(Constant.FILE_PATH, it.fileName)
//                    FileUtil.startInstall(temContext, file)
                }
        }


        job.cancel()
//        GlobalScope.launch(Dispatchers.IO) {
//            val infobean = createDownInfo(url)
//            val info = FileUtil.getRealFileName(infobean)
//            realDownLoad(info)
//        }
    }


    private fun createDownInfo(url: String): DownLoadBean {
        val downloadInfo = DownLoadBean(url)
        val contentLength: Long = getContentLength(url) //获得文件大小
        downloadInfo.totalLength = contentLength
        val fileName = url.substring(url.lastIndexOf("/"))
        downloadInfo.fileName = fileName
        return downloadInfo
    }


    /**
     * 获取下载长度
     * @param downloadUrl
     * @return
     */
    private fun getContentLength(downloadUrl: String): Long {
        val request: Request = Request.Builder()
            .url(downloadUrl)
            .build()
        var contentLength = 0L

        try {
            val response = mClient.newCall(request).execute()
            if (response.isSuccessful) {
                contentLength = response.body!!.contentLength()
                response.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return contentLength
    }


    private fun realDownLoad(downLoadBean: DownLoadBean): Flow<DownLoadBean> {
        Log.d("QWER", "start-download: ")
        val url: String = downLoadBean.url
        //已经下载好的长度
        var downloadLength: Long = downLoadBean.hasDownloadLength
        //文件的总长度
        val totalLength: Long = downLoadBean.totalLength

        val request: Request = Request.Builder()
            // 确定下载的范围,添加此头,就可以跳过已经下载好的部分
            .addHeader("RANGE", "bytes=$downloadLength-$totalLength")
            .url(url)
            .build()

        val call = mClient.newCall(request)
        //把这个添加到call里,方便取消
        downCalls[url] = call
        try {
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("QWER", "onFailure: ${e.message}")
                }

                override fun onResponse(call: Call, response: Response) {
                    val file = File(Constant.FILE_PATH, downLoadBean.fileName)
                    response.body!!.byteStream().use { inputStream ->
                        val buffer = ByteArray(2048) //缓冲数组2kB
                        var len: Int
                        while (inputStream.read(buffer).also { len = it } != -1) {
                            FileOutputStream(file, true).use { fileOutputStream ->
                                fileOutputStream.write(buffer, 0, len)
                                downloadLength += len.toLong()
                                downLoadBean.hasDownloadLength = downloadLength
                                fileOutputStream.flush()
                                downCalls.remove(url)
                            }
                        }
                    }
                    Log.d("QWER", "onResponse:")


                }
            })
        } catch (e: Exception) {
            Log.d("QWER", "realDownLoad: ${e.message}")
        }
        return flow {
            emit(downLoadBean)
        }

    }


}