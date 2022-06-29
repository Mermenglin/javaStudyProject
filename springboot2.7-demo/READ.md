
## 循环依赖

springboot 2.6 以上版本，限制循环依赖，如果存在循环依赖，会报错，出现如下提示信息
```text
The dependencies of some of the beans in the application context form a cycle:

┌─────┐
|  test1Impl (field com.mml.springbootdemo.service.Test2Interface com.mml.springbootdemo.service.impl.Test1Impl.test2Impl)
↑     ↓
|  test2Impl (field com.mml.springbootdemo.service.Test1Interface com.mml.springbootdemo.service.impl.Test2Impl.test1Impl)
└─────┘


Action:

Relying upon circular references is discouraged and they are prohibited by default. Update your application to remove the dependency cycle between beans. As a last resort, it may be possible to break the cycle automatically by setting spring.main.allow-circular-references to true.
```
大致意思就是：应用上下文中一些bean的依赖形成了一个循环，不鼓励依赖循环引用，默认情况下是禁止的。更新您的应用程序以删除 bean 之间的依赖循环。作为最后的手段，可以通过将 spring.main.allow-circular-references 设置为 true 来自动中断循环。


如果需要开启循环依赖，则在配置文件中添加以下配置，开启循环依赖，但是循环依赖本身是因为代码结构划分有问题所导致的，所以应该尽量避免
```properties
spring.main.allow-circular-references=true
```