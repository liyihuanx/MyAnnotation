package liyihuan.app.android.lib_compiler

import com.squareup.kotlinpoet.ClassName
import java.io.File

/**
 * @author created by liyihuanx
 * @date 2021/9/6
 * @description: 类的描述
 */
const val ANNOTATION_PACKAGE = "liyihuan.app.android.lib_annotation"
const val ANNOTATION_NAME = "$ANNOTATION_PACKAGE.DownLoad.*"

const val POSIX = "XXProxy"

const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"

//接收 包名（APT 存放的包名）
const val APT_PACKAGE = "moduleName"


const val API_PACKAGE = "com.liyihuanx.lib_annotationapi"

val SUPER_INTERFACE = ClassName(API_PACKAGE, "BaseDownLoadProxy")

val DOWNLOAD_MANAGER = ClassName(API_PACKAGE, "DownLoadManager")

val ACTIVITY = ClassName("android.app","Activity")

const val I_DOWNLOAD_PATH = "$API_PACKAGE.IDownLoad"

