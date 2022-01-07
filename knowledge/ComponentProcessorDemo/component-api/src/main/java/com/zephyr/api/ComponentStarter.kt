package com.zephyr.api

import android.content.Context

/**
 * User: Zephyr
 * Date: 2022/1/6
 * Time: 15:45
 */
interface ComponentStarter {
    fun onCreate(context: Context)

    fun onTerminate()

    fun getPriority():Int
}