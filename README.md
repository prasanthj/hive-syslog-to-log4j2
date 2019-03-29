# hive-syslog-to-log4j2
Apache Hive has support for syslog parser specifically to read its own log written in syslog RFC5424 format. This repo uses the same parser but includes a tool to convert RFC5424 msgs to Log4j2 log lines.

```bash
mvn clean install
```

```bash
cat test.log | sys2log4j
```

The above command should print output like below

```
2019-03-22 19:56:15.720 DEBUG [HiveServer2-Background-Pool: Thread-1357] log.PerfLogger: </PERFLOG method=releaseLocks start=1553309775714 end=1553309775720 duration=6 from=org.apache.hadoop.hive.ql.Driver>
2019-03-22 19:56:15.722 ERROR [HiveServer2-Background-Pool: Thread-1357] operation.Operation: Error running hive query:
org.apache.hive.service.cli.HiveSQLException: Error while processing statement: FAILED: Execution Error, return code 2 from org.apache.hadoop.hive.ql.exec.tez.TezTask. Vertex failed, vertexName=Map 1, vertexId=vertex_1553301184128_0000_7_00, diagnostics=[Task failed, taskId=task_1553301184128_0000_7_00_000000, diagnostics=[TaskAttempt 0 failed, info=[Error: Error while running task ( failure ) : attempt_1553301184128_0000_7_00_000000_0:java.lang.IllegalArgumentException
	at com.google.common.base.Preconditions.checkArgument(Preconditions.java:108)
	at org.apache.tez.runtime.common.resources.MemoryDistributor.registerRequest(MemoryDistributor.java:177)
	at org.apache.tez.runtime.common.resources.MemoryDistributor.requestMemory(MemoryDistributor.java:110)
	at org.apache.tez.runtime.api.impl.TezTaskContextImpl.requestInitialMemory(TezTaskContextImpl.java:214)
	at org.apache.tez.runtime.library.output.UnorderedPartitionedKVOutput.initialize(UnorderedPartitionedKVOutput.java:76)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable._callInternal(LogicalIOProcessorRuntimeTask.java:550)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable.callInternal(LogicalIOProcessorRuntimeTask.java:533)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable.callInternal(LogicalIOProcessorRuntimeTask.java:518)
	at org.apache.tez.common.CallableWithNdc.call(CallableWithNdc.java:36)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
], TaskAttempt 1 failed, info=[Error: Error while running task ( failure ) : attempt_1553301184128_0000_7_00_000000_1:java.lang.IllegalArgumentException
	at com.google.common.base.Preconditions.checkArgument(Preconditions.java:108)
	at org.apache.tez.runtime.common.resources.MemoryDistributor.registerRequest(MemoryDistributor.java:177)
	at org.apache.tez.runtime.common.resources.MemoryDistributor.requestMemory(MemoryDistributor.java:110)
	at org.apache.tez.runtime.api.impl.TezTaskContextImpl.requestInitialMemory(TezTaskContextImpl.java:214)
	at org.apache.tez.runtime.library.output.UnorderedPartitionedKVOutput.initialize(UnorderedPartitionedKVOutput.java:76)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable._callInternal(LogicalIOProcessorRuntimeTask.java:550)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable.callInternal(LogicalIOProcessorRuntimeTask.java:533)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable.callInternal(LogicalIOProcessorRuntimeTask.java:518)
	at org.apache.tez.common.CallableWithNdc.call(CallableWithNdc.java:36)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
], TaskAttempt 2 failed, info=[Error: Error while running task ( failure ) : attempt_1553301184128_0000_7_00_000000_2:java.lang.IllegalArgumentException
	at com.google.common.base.Preconditions.checkArgument(Preconditions.java:108)
	at org.apache.tez.runtime.common.resources.MemoryDistributor.registerRequest(MemoryDistributor.java:177)
	at org.apache.tez.runtime.common.resources.MemoryDistributor.requestMemory(MemoryDistributor.java:110)
	at org.apache.tez.runtime.api.impl.TezTaskContextImpl.requestInitialMemory(TezTaskContextImpl.java:214)
	at org.apache.tez.runtime.library.output.UnorderedPartitionedKVOutput.initialize(UnorderedPartitionedKVOutput.java:76)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable._callInternal(LogicalIOProcessorRuntimeTask.java:550)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable.callInternal(LogicalIOProcessorRuntimeTask.java:533)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable.callInternal(LogicalIOProcessorRuntimeTask.java:518)
	at org.apache.tez.common.CallableWithNdc.call(CallableWithNdc.java:36)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
], TaskAttempt 3 failed, info=[Error: Error while running task ( failure ) : attempt_1553301184128_0000_7_00_000000_3:java.lang.IllegalArgumentException
	at com.google.common.base.Preconditions.checkArgument(Preconditions.java:108)
	at org.apache.tez.runtime.common.resources.MemoryDistributor.registerRequest(MemoryDistributor.java:177)
	at org.apache.tez.runtime.common.resources.MemoryDistributor.requestMemory(MemoryDistributor.java:110)
	at org.apache.tez.runtime.api.impl.TezTaskContextImpl.requestInitialMemory(TezTaskContextImpl.java:214)
	at org.apache.tez.runtime.library.output.UnorderedPartitionedKVOutput.initialize(UnorderedPartitionedKVOutput.java:76)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable._callInternal(LogicalIOProcessorRuntimeTask.java:550)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable.callInternal(LogicalIOProcessorRuntimeTask.java:533)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable.callInternal(LogicalIOProcessorRuntimeTask.java:518)
	at org.apache.tez.common.CallableWithNdc.call(CallableWithNdc.java:36)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
]], Vertex did not succeed due to OWN_TASK_FAILURE, failedTasks:1 killedTasks:0, Vertex vertex_1553301184128_0000_7_00 [Map 1] killed/failed due to:OWN_TASK_FAILURE]Vertex killed, vertexName=Reducer 2, vertexId=vertex_1553301184128_0000_7_01, diagnostics=[Vertex received Kill while in RUNNING state., Vertex did not succeed due to OTHER_VERTEX_FAILURE, failedTasks:0 killedTasks:1, Vertex vertex_1553301184128_0000_7_01 [Reducer 2] killed/failed due to:OTHER_VERTEX_FAILURE]DAG did not succeed due to VERTEX_FAILURE. failedVertices:1 killedVertices:1
	at org.apache.hive.service.cli.operation.Operation.toSQLException(Operation.java:348)
	at org.apache.hive.service.cli.operation.SQLOperation.runQuery(SQLOperation.java:228)
	at org.apache.hive.service.cli.operation.SQLOperation.access$700(SQLOperation.java:87)
	at org.apache.hive.service.cli.operation.SQLOperation$BackgroundWork$1.run(SQLOperation.java:324)
	at java.security.AccessController.doPrivileged(Native Method)
	at javax.security.auth.Subject.doAs(Subject.java:422)
	at org.apache.hadoop.security.UserGroupInformation.doAs(UserGroupInformation.java:1730)
	at org.apache.hive.service.cli.operation.SQLOperation$BackgroundWork.run(SQLOperation.java:342)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
Caused by: org.apache.hadoop.hive.ql.metadata.HiveException: Vertex failed, vertexName=Map 1, vertexId=vertex_1553301184128_0000_7_00, diagnostics=[Task failed, taskId=task_1553301184128_0000_7_00_000000, diagnostics=[TaskAttempt 0 failed, info=[Error: Error while running task ( failure ) : attempt_1553301184128_0000_7_00_000000_0:java.lang.IllegalArgumentException
	at com.google.common.base.Preconditions.checkArgument(Preconditions.java:108)
	at org.apache.tez.runtime.common.resources.MemoryDistributor.registerRequest(MemoryDistributor.java:177)
	at org.apache.tez.runtime.common.resources.MemoryDistributor.requestMemory(MemoryDistributor.java:110)
	at org.apache.tez.runtime.api.impl.TezTaskContextImpl.requestInitialMemory(TezTaskContextImpl.java:214)
	at org.apache.tez.runtime.library.output.UnorderedPartitionedKVOutput.initialize(UnorderedPartitionedKVOutput.java:76)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable._callInternal(LogicalIOProcessorRuntimeTask.java:550)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable.callInternal(LogicalIOProcessorRuntimeTask.java:533)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable.callInternal(LogicalIOProcessorRuntimeTask.java:518)
	at org.apache.tez.common.CallableWithNdc.call(CallableWithNdc.java:36)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
], TaskAttempt 1 failed, info=[Error: Error while running task ( failure ) : attempt_1553301184128_0000_7_00_000000_1:java.lang.IllegalArgumentException
	at com.google.common.base.Preconditions.checkArgument(Preconditions.java:108)
	at org.apache.tez.runtime.common.resources.MemoryDistributor.registerRequest(MemoryDistributor.java:177)
	at org.apache.tez.runtime.common.resources.MemoryDistributor.requestMemory(MemoryDistributor.java:110)
	at org.apache.tez.runtime.api.impl.TezTaskContextImpl.requestInitialMemory(TezTaskContextImpl.java:214)
	at org.apache.tez.runtime.library.output.UnorderedPartitionedKVOutput.initialize(UnorderedPartitionedKVOutput.java:76)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable._callInternal(LogicalIOProcessorRuntimeTask.java:550)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable.callInternal(LogicalIOProcessorRuntimeTask.java:533)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable.callInternal(LogicalIOProcessorRuntimeTask.java:518)
	at org.apache.tez.common.CallableWithNdc.call(CallableWithNdc.java:36)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
], TaskAttempt 2 failed, info=[Error: Error while running task ( failure ) : attempt_1553301184128_0000_7_00_000000_2:java.lang.IllegalArgumentException
	at com.google.common.base.Preconditions.checkArgument(Preconditions.java:108)
	at org.apache.tez.runtime.common.resources.MemoryDistributor.registerRequest(MemoryDistributor.java:177)
	at org.apache.tez.runtime.common.resources.MemoryDistributor.requestMemory(MemoryDistributor.java:110)
	at org.apache.tez.runtime.api.impl.TezTaskContextImpl.requestInitialMemory(TezTaskContextImpl.java:214)
	at org.apache.tez.runtime.library.output.UnorderedPartitionedKVOutput.initialize(UnorderedPartitionedKVOutput.java:76)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable._callInternal(LogicalIOProcessorRuntimeTask.java:550)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable.callInternal(LogicalIOProcessorRuntimeTask.java:533)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable.callInternal(LogicalIOProcessorRuntimeTask.java:518)
	at org.apache.tez.common.CallableWithNdc.call(CallableWithNdc.java:36)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
], TaskAttempt 3 failed, info=[Error: Error while running task ( failure ) : attempt_1553301184128_0000_7_00_000000_3:java.lang.IllegalArgumentException
	at com.google.common.base.Preconditions.checkArgument(Preconditions.java:108)
	at org.apache.tez.runtime.common.resources.MemoryDistributor.registerRequest(MemoryDistributor.java:177)
	at org.apache.tez.runtime.common.resources.MemoryDistributor.requestMemory(MemoryDistributor.java:110)
	at org.apache.tez.runtime.api.impl.TezTaskContextImpl.requestInitialMemory(TezTaskContextImpl.java:214)
	at org.apache.tez.runtime.library.output.UnorderedPartitionedKVOutput.initialize(UnorderedPartitionedKVOutput.java:76)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable._callInternal(LogicalIOProcessorRuntimeTask.java:550)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable.callInternal(LogicalIOProcessorRuntimeTask.java:533)
	at org.apache.tez.runtime.LogicalIOProcessorRuntimeTask$InitializeOutputCallable.callInternal(LogicalIOProcessorRuntimeTask.java:518)
	at org.apache.tez.common.CallableWithNdc.call(CallableWithNdc.java:36)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
]], Vertex did not succeed due to OWN_TASK_FAILURE, failedTasks:1 killedTasks:0, Vertex vertex_1553301184128_0000_7_00 [Map 1] killed/failed due to:OWN_TASK_FAILURE]Vertex killed, vertexName=Reducer 2, vertexId=vertex_1553301184128_0000_7_01, diagnostics=[Vertex received Kill while in RUNNING state., Vertex did not succeed due to OTHER_VERTEX_FAILURE, failedTasks:0 killedTasks:1, Vertex vertex_1553301184128_0000_7_01 [Reducer 2] killed/failed due to:OTHER_VERTEX_FAILURE]DAG did not succeed due to VERTEX_FAILURE. failedVertices:1 killedVertices:1
	at org.apache.hadoop.hive.ql.exec.tez.TezTask.execute(TezTask.java:240)
	at org.apache.hadoop.hive.ql.exec.Task.executeTask(Task.java:212)
	at org.apache.hadoop.hive.ql.exec.TaskRunner.runSequential(TaskRunner.java:97)
	at org.apache.hadoop.hive.ql.Driver.launchTask(Driver.java:2727)
	at org.apache.hadoop.hive.ql.Driver.execute(Driver.java:2394)
	at org.apache.hadoop.hive.ql.Driver.runInternal(Driver.java:2066)
	at org.apache.hadoop.hive.ql.Driver.run(Driver.java:1764)
	at org.apache.hadoop.hive.ql.Driver.run(Driver.java:1758)
	at org.apache.hadoop.hive.ql.reexec.ReExecDriver.run(ReExecDriver.java:157)
	at org.apache.hive.service.cli.operation.SQLOperation.runQuery(SQLOperation.java:226)
	... 13 more
2019-03-22 19:56:15.725  INFO [HiveServer2-HttpHandler-Pool: Thread-1293] conf.HiveConf: Using the default value passed in for log id: 97f70f36-5f40-4604-a5d3-b54adcad85c5
```

