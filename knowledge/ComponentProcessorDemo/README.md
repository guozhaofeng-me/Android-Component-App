# 組件化框架使用说明
1. 在主项目(Application Module)中添加如下代码(一般是在Application中):
```kotlin
ComponentManager.init(this)
```
2. 在每个组件中新建类，xxxComponentStarter,如HomeComponentStarter,实现ComponentStaerter接口，并添加
Component注解，如下;
```kotlin
@Component("com.zephyr.home.HomeModuleStarter")
class HomeModuleStarter : ComponentStarter {
    override fun onCreate(context: Context) {
        println(">>>>>>>>>> onCreate is from HomeModule")
    }

    override fun onTerminate() {
        println(">>>>>>>>>> onTerminate is called from HomeModule")
    }

    override fun getPriority(): Int {
        println(">>>>>>>>>> getPriority is called from HomeModule")
        return 1
    }
}
```   
我们可以在onCreate中增加组件初始化代码，并且可以获得Application Module中的Context实例;可以在onTerminate()方法中
释放资源;getPriority()方法用来确定组件的优先级，目前是数字越大优先级越高。

// TODO 运行时扫描所有class文件比较耗时，可以通过gradle插件，结合aspectJ或者asm在打包时就确定好目标class，运行时直接
使用即可。

3. 实现路由功能