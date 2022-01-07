package com.zephyr.home

import android.content.Context
import android.widget.Toast
import com.zephyr.annotation.Component
import com.zephyr.api.ComponentStarter
import com.zephyr.base.MainPageFrame

/**
 * User: Zephyr
 * Date: 2022/1/6
 * Time: 16:00
 */
@Component("com.zephyr.home.HomeModuleStarter")
class HomeModuleStarter : ComponentStarter {
    override fun onCreate(context: Context) {
        var homeFragment = HomeFragment.newInstance("var1", "var2")
        MainPageFrame.fragments.add(homeFragment)
    }

    override fun onTerminate() {
        println(">>>>>>>>>> onTerminate is called from HomeModule")
    }

    override fun getPriority(): Int {
        println(">>>>>>>>>> getPriority is called from HomeModule")
        return 3
    }
}