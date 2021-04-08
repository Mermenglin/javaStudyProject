JavaAgent 是JDK 1.5 以后引入的，也可以叫做Java代理。

JavaAgent 是运行在 main方法之前的拦截器，它内定的方法名叫 premain ，也就是说先执行 premain 方法然后再执行 main 方法。

那么如何实现一个 JavaAgent 呢？很简单，只需要增加 premain 方法即可，后续可以在此基础上实现注入拦截，AOP等。

TestAgent 是代理类，打包进jar中，AppRun 是业务方法，执行时，需要配置
```
-javaagent:E:\mml\Github\javaStudyProject\AgentTest\out\AgentTest_jar\AgentTest.jar=helloworld
```

也可以将agentTest打成jar包通过java命令行执行，我们通过 -javaagent 参数来指定我们的Java代理包，值得一说的是 -javaagent 这个参数的个数是不限的，如果指定了多个，则会按指定的先后执行，执行完各个 agent 后，才会执行主程序的 main 方法。命令如下：
```
java -javaagent:E:\mml\Github\javaStudyProject\AgentTest\out\AgentTest_jar\AgentTest.jar=helloworld1 -javaagent:E:\mml\Github\javaStudyProject\AgentTest\out\AgentTest_jar\AgentTest.jar=helloworld2 -jar E:\mml\Github\javaStudyProject\AgentTest\out\AgentTest_jar\AppRun.jar
```

如果你把 -javaagent 放在 -jar 后面，则不会生效。也就是说，放在主程序后面的 agent 是无效的。