package com.zephyr.api

import android.content.Context
import android.content.Intent
import com.zephyr.annotation.ComponentConfig
import java.lang.Exception

/**
 * User: Zephyr
 * Date: 2022/1/7
 * Time: 16:48
 */
object Router {
    private val routeMap = hashMapOf<String, String>()

    fun init(context: Context) {
        scanClassFile(context)
    }

    fun path(path: String):Router {
        val intent = Intent()
        return this
    }

    private fun scanClassFile(context: Context) {
//        try {
//            //扫描到所有的目标类
//            val set = ClassUtils.getFileNameByPackageName(
//                context,
//                ComponentConfig.ROUTE_PACKAGE
//            )
//            if (set != null) {
//                for (className in set) {
//                    try {
//                        //通过反射来加载实例化所有的代理类
//                        val obj = Class.forName(className).newInstance()
//                        if (obj is ComponentStarter) {
//                            ComponentManager.COMPONENTS_LIST.add(obj)
//                        }
//                    } catch (e: ClassNotFoundException) {
//                        e.printStackTrace()
//                    }
//                }
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
    }
}