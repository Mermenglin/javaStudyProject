## DFA算法
dfa中有敏感词搜寻中的DFA算法

dfa 确定有穷自动机，其特征为：有一个有限状态集合和一些从一个状态通向另一个状态的边，每条边上标记有一个符号，其中一个状态是初态，某些状态是终态。但不同于不确定的有限自动机，DFA中不会有从同一状态出发的两条边标志有相同的符号。

---

## 生产者-消费者模式
使用生产者-消费者模式编写代码实现：线程A随机间隔（10~200ms）按顺序生成1到100的数字。
放到队列中，线程B、C、D即时消费这些数据，线程B消费所有被3整除的数，
线程C消费所有被5整除的数，其他由线程D进行消费。
线程B、C、D消费这些数据时在控制台中打印出来，要求按顺序打印这些数据。