package liyihuan.app.android.lib_compiler

import com.google.auto.service.AutoService
import liyihuan.app.android.lib_annotation.DownLoad
import liyihuan.app.android.lib_compiler.types.AptContext
import liyihuan.app.android.lib_compiler.types.simpleName
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.TypeElement

/**
 * @ClassName: DowloadProcessor
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2022/1/17 21:22
 */
@AutoService(Processor::class)
@SupportedAnnotationTypes(ANNOTATION_NAME)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
class DownloadProcessor : AbstractProcessor() {


    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        AptContext.init(processingEnv)
        AptContext.note(" 注解 init")
    }

    /**
     * 存放使用download注解的类，和该类使用了的的注解
     */
    private val downLoadTypeMap = HashMap<String, HashMap<String, Class<out Annotation>>>()


    override fun process(annotations: MutableSet<out TypeElement>, env: RoundEnvironment): Boolean {
        if (annotations.isEmpty()) {
            AptContext.note("没有使用过注解")
            return false
        }

        handleAnnotation(annotations,env,DownLoad.onPre::class.java)
        handleAnnotation(annotations,env,DownLoad.onStart::class.java)
        handleAnnotation(annotations,env,DownLoad.onComplete::class.java)

        return true
    }


    /**
     * VariableElement 一般代表成员变量
     * ExecutableElement 一般代表类中的方法
     * TypeElement 一般代表代表类
     * PackageElement 一般代表Package
     */
    private fun handleAnnotation(
        annotations: MutableSet<out TypeElement>,
        env: RoundEnvironment,
        annotationClazz: Class<out Annotation>
    ) {
        env.getElementsAnnotatedWith(annotationClazz).forEach {
            // 拿到使用注解的类名
            val clazzName = (it.enclosingElement as TypeElement).simpleName()
            // 拿到使用的方法名
            val methodName = (it as ExecutableElement).simpleName
            AptContext.note(" $clazzName 使用了注解 方法为$methodName")

            val methodMap = downLoadTypeMap[clazzName]
            if (methodMap == null) {
                downLoadTypeMap[clazzName] = HashMap()
            }
            //[activity,ArrayList<String> methodName]


        }
    }

}