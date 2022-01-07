package com.zephyr.mine

import android.content.Context
import com.zephyr.annotation.Component
import com.zephyr.api.ComponentStarter
import com.zephyr.base.MainPageFrame

/**
 * User: Zephyr
 * Date: 2022/1/6
 * Time: 16:03
 */
@Component
class MineModuleStarter : ComponentStarter {
    override fun onCreate(context: Context) {
        var mineFragment = MineFragment.newInstance()
        MainPageFrame.fragments.add(mineFragment)
    }

    override fun onTerminate() {
        println(">>>>>>>>>> onTerminate is called from MineModule")
    }

    override fun getPriority(): Int {
        println(">>>>>>>>>> getPriority is called from MineModule")
        return 1
    }
}