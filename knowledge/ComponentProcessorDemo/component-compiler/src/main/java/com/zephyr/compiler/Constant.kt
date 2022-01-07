package com.zephyr.compiler

import com.squareup.javapoet.ClassName

/**
 * User: Zephyr
 * Date: 2022/1/7
 * Time: 10:25
 */
object Constant {
    const val FIELD_STARTER = "starter"
    const val PARAMETER_CONTEXT = "context"

    const val ROUTE_SUFFIX = "Route"

    val contextType = ClassName.get("android.content", "Context")
    val starterType = ClassName.get("com.zephyr.api","ComponentStarter")
}