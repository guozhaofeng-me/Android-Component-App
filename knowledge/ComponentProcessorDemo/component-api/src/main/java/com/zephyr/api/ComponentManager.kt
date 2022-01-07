package com.zephyr.api

import android.content.Context
import com.zephyr.annotation.ComponentConfig
import java.lang.Exception
import java.util.*
import kotlin.Comparator

/**
 * User: Zephyr
 * Date: 2022/1/6
 * Time: 16:07
 */
// 用来管理各个组件，简单说就是将组件加到一个map中管理
object ComponentManager {
    private val COMPONENTS_LIST = arrayListOf<ComponentStarter>()

    fun init(context: Context) {
        scanClassFile(context)
        Collections.sort(COMPONENTS_LIST, ComponentComparator())
        COMPONENTS_LIST.forEach {
            it.onCreate(context)
        }
    }

    fun terminate() {
        COMPONENTS_LIST.forEach {
            it.onTerminate()
        }
    }

    private fun scanClassFile(context: Context) {
        try {
            //扫描到所有的目标类
            val set = ClassUtils.getFileNameByPackageName(
                context,
                ComponentConfig.COMPONENT_PACKAGE
            )
            if (set != null) {
                for (className in set) {
                    try {
                        //通过反射来加载实例化所有的代理类
                        val obj = Class.forName(className).newInstance()
                        if (obj is ComponentStarter) {
                            COMPONENTS_LIST.add(obj)
                        }
                    } catch (e: ClassNotFoundException) {
                        e.printStackTrace()
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    class ComponentComparator : Comparator<ComponentStarter> {
        override fun compare(p0: ComponentStarter?, p1: ComponentStarter?): Int {
            val priority0 = p0!!.getPriority()
            val priority1 = p1!!.getPriority()
            return priority1 - priority0
        }
    }
}

