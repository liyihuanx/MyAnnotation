package liyihuan.app.android.lib_annotation

/**
 * @ClassName: DownLoad
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2022/1/17 21:02
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class DownLoad() {

    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.SOURCE)
    annotation class onPre() {

    }

    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.SOURCE)
    annotation class onStart() {

    }

    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.SOURCE)
    annotation class onComplete() {

    }


}
