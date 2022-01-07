package com.zephyr.compiler

import java.util.HashSet
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.PackageElement
import javax.lang.model.element.TypeElement
import javax.lang.model.element.VariableElement

/**
 * User: Zephyr
 * Date: 2022/1/6
 * Time: 17:04
 */
class ComponentStarterModel(private val classElement: TypeElement) {

    /**
     * 成员变量
     */
    private var variableElements: HashSet<VariableElement>? = null

    /**
     * 方法
     */
    private var executableElements: HashSet<ExecutableElement>? = null

    /**
     * 包
     */
    private var packageElement: PackageElement? = null

    init {
        variableElements = HashSet()
        executableElements = HashSet()
        packageElement = classElement.enclosingElement as PackageElement
    }

    fun addVariableElement(element: VariableElement) {
        variableElements!!.add(element)
    }

    fun getClassElement(): TypeElement? {
        return classElement
    }

    fun getVariableElements(): HashSet<VariableElement>? {
        return variableElements
    }

    fun getPackageElement(): PackageElement? {
        return packageElement
    }

    fun addExecutableElement(element: ExecutableElement) {
        executableElements!!.add(element)
    }

    fun getExecutableElements(): HashSet<ExecutableElement>? {
        return executableElements
    }
}