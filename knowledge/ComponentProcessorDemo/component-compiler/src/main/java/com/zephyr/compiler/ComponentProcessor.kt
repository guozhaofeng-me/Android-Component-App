package com.zephyr.compiler

import com.google.auto.service.AutoService
import com.squareup.javapoet.*
import com.zephyr.annotation.Component
import com.zephyr.annotation.ComponentConfig
import com.zephyr.annotation.Route
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement

/**
 * User: Zephyr
 * Date: 2022/1/6
 * Time: 16:27
 */
@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
class ComponentProcessor : AbstractProcessor() {
    private val componentStarterMap = hashMapOf<String, ComponentStarterModel>()
    private val routeMap = hashMapOf<String, RouteModel>()
    private var filer: Filer? = null

    override fun init(processingEnv: ProcessingEnvironment?) {
        super.init(processingEnv)
        filer = processingEnv!!.filer
    }

    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {
        componentStarterMap.clear()
        routeMap.clear()
        val componentElements = p1?.getElementsAnnotatedWith(Component::class.java)
        componentElements?.let {
            it.forEach { element ->
                createModel(element as TypeElement)
            }
        }
        val routeElements = p1?.getElementsAnnotatedWith(Route::class.java)
        routeElements?.let {
            it.forEach { element ->
                createRouteModel(element as TypeElement)
            }
        }
        generateJavaFile()
        return false
    }

    private fun createModel(element: TypeElement) {
        if (componentStarterMap[element.qualifiedName.toString()] == null) {
            val model = ComponentStarterModel(element)
            componentStarterMap[element.qualifiedName.toString()] = model
        }
    }

    private fun createRouteModel(element: TypeElement) {
        if (routeMap[element.qualifiedName.toString()] == null) {
            val model = RouteModel(element)
            routeMap[element.qualifiedName.toString()] = model
        }
    }

    private fun generateJavaFile() {
        componentStarterMap.forEach {
            createJavaFile(it.value)
        }
        routeMap.forEach {
            createRouteJavaFile(it.value)
        }
    }

    private fun createJavaFile(model: ComponentStarterModel) {
        val className = model.getClassElement()!!.simpleName
        val classBuilder = TypeSpec.classBuilder("${className}\$\$Proxy")
            .addSuperinterface(Constant.starterType)
            .addModifiers(Modifier.PUBLIC)
        println(">>>>>>>>>>>>>>>>>>>>> ${model.getPackageElement()!!.qualifiedName}")

        val fieldStarter =
            FieldSpec.builder(
                ClassName.get(model.getClassElement()),
                Constant.FIELD_STARTER,
                Modifier.PRIVATE
            )
                .initializer("new \$T()", ClassName.get(model.getClassElement()))
                .build()

        val context =
            ParameterSpec.builder(Constant.contextType, Constant.PARAMETER_CONTEXT).build()
        val onCreate = MethodSpec
            .methodBuilder("onCreate")
            .addAnnotation(Override::class.java)
            .addModifiers(Modifier.PUBLIC)
            .addParameter(context)
            .addStatement("${Constant.FIELD_STARTER}.onCreate(context)")
            .returns(TypeName.VOID)
            .build()

        val onTerminate = MethodSpec
            .methodBuilder("onTerminate")
            .addAnnotation(Override::class.java)
            .addModifiers(Modifier.PUBLIC)
            .addStatement("${Constant.FIELD_STARTER}.onTerminate()")
            .returns(TypeName.VOID)
            .build()

        val getPriority = MethodSpec
            .methodBuilder("getPriority")
            .addAnnotation(Override::class.java)
            .addModifiers(Modifier.PUBLIC)
            .addStatement("return ${Constant.FIELD_STARTER}.getPriority()")
            .returns(TypeName.INT)
            .build()

        classBuilder
            .addField(fieldStarter)
            .addMethod(onCreate)
            .addMethod(onTerminate)
            .addMethod(getPriority)

        val javaFile = JavaFile.builder(
            ComponentConfig.COMPONENT_PACKAGE,
            classBuilder.build()
        ).build()
        try {
            javaFile?.writeTo(filer)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun createRouteJavaFile(model: RouteModel) {
        val className = model.getClassElement()!!.simpleName
        val classBuilder = TypeSpec.classBuilder("${className}\$\$${Constant.ROUTE_SUFFIX}")
            .addModifiers(Modifier.PUBLIC)
        var route = model.getClassElement()!!.getAnnotation(Route::class.java)

        val key = route.path
        val activityPackage = model.getPackageElement()!!.qualifiedName
        val activityName = model.getClassElement()!!.qualifiedName

        val fieldKey = FieldSpec
            .builder(String::class.java, "key", Modifier.PUBLIC)
            .initializer("\$S", key)
            .build()

        val fieldValue = FieldSpec
            .builder(String::class.java, "activityName", Modifier.PUBLIC)
            .initializer("\$S", activityName)
            .build()

        val fieldActivityPackage = FieldSpec
            .builder(String::class.java, "activityPackage", Modifier.PUBLIC)
            .initializer("\$S", activityPackage)
            .build()

        classBuilder.addField(fieldKey).addField(fieldValue).addField(fieldActivityPackage)

        val javaFile = JavaFile.builder(
            ComponentConfig.ROUTE_PACKAGE,
            classBuilder.build())
            .build()
        try {
            javaFile?.writeTo(filer)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        val set = mutableSetOf<String>()
        set.add(Component::class.java.canonicalName)
        set.add(Route::class.java.canonicalName)
        return set
    }
}