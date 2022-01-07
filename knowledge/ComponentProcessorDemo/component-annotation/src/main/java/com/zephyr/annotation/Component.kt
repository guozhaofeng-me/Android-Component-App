package com.zephyr.annotation

/**
 * User: Zephyr
 * Date: 2022/1/6
 * Time: 15:49
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class Component(val value: String = "")
