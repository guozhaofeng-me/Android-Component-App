package com.zephyr.annotation

/**
 * User: Zephyr
 * Date: 2022/1/7
 * Time: 16:24
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class Route(val path: String = "")
