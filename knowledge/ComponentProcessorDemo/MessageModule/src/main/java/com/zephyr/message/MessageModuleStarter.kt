package com.zephyr.message

import android.content.Context
import com.zephyr.annotation.Component
import com.zephyr.api.ComponentStarter
import com.zephyr.base.MainPageFrame

/**
 * User: Zephyr
 * Date: 2022/1/6
 * Time: 16:02
 */
@Component("com.zephyr.message.MessageModuleStarter")
class MessageModuleStarter : ComponentStarter {
    override fun onCreate(context: Context) {
        var messageFragment = MessageFragment.newInstance(1)
        MainPageFrame.fragments.add(messageFragment)
    }

    override fun onTerminate() {
        println(">>>>>>>>>> onTerminate is called from MessageModule")
    }

    override fun getPriority(): Int {
        println(">>>>>>>>>> getPriority is called from MessageModule")
        return 2
    }
}