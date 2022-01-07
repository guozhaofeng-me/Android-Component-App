package com.zephyr.componentprocessor

import android.app.Application
import com.zephyr.api.ComponentManager
import com.zephyr.api.Router

/**
 * User: Zephyr
 * Date: 2022/1/6
 * Time: 16:10
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ComponentManager.init(this)
        Router.init(this)
    }
}