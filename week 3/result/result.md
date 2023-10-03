# 吞吐量优先，低延时接口(PS-PO)

输出文件 [gc-ps-po-fast-th.log](./gc-ps-po-fast-th.log)

## GC easy 报告

### 内存规划

![Alt text](pics/gc-easy-1.png)

需要设置 -XX:MaxMetaspaceSize=128m 否则报告的 metaspace 会大于 `-XX:MetaspaceSize=128m` 期望的 128m

### 总体指标

![Alt text](pics/gc-easy-2.png)

### 其他

![Alt text](pics/gc-easy-3.png)

![img.png](pics/gc-easy-4.png)

## jmeter 报告

![img.png](pics/jmeter-fast-th.png)

## jvm 报告
![img.png](pics/jmeter-fast-th.png)
![img.png](pics/jvm-fast-th-2.png)


# CMS,低延迟接口

输出文件： [gc-parnew-cms.log](./gc-parnew-cms.log)

## gc easy 报告

![Alt text](pics/gc-cms-geasy-1.png)

gc easy 对 meta space 峰值识别出现问题

![img.png](pics/gc-cms-geasy-2.png)

gc 平局时间和最大暂停时间优于 ps-po 模式

![img.png](pics/gc-cms-geasy-3.png)

![img.png](pics/gc-cms-geasy-4.png)

![img.png](pics/gc-cms-geasy-5.png)

![img.png](pics/gc-cms-geasy-6.png)

## jmeter 报告

![img.png](pics/gc-cms-jemter.png)

## jvm 报告

![img.png](pics/gc-cms-jvm-1.png)
![img.png](pics/gc-cms-jvm-2.png)

# 大内存收集器，低延时接口(G1)

输出文件 [gc-g-one-fast.lo](./gc-g-one-fast.log)

## gc easy 报告

![img.png](pics/gc-g1-easy-1.png)
![img.png](pics/gc-g1-easy-2.png)
![img.png](pics/gc-g1-easy-3.png)

**没有出现 full gc 了，最大暂停时间很小！**

![img.png](pics/gc-g1-easy-4.png)

## jvm 报告

![img.png](pics/gc-g1-jvm-1.png)
![img.png](pics/gc-g1-jvm-2)

## jmeter 报告

![img.png](pics/gc-g1-jvm.png)

# 吞吐量优先，高延迟接口(PS-PO)

输出文件 [gc-ps-po-slow.log](gc-ps-po-slow.log)

## gc easy 报告

![img.png](pics/gc-easy-th-slow-1.png)

![img.png](pics/gc-easy-th-slow-2.png)

![img.png](pics/gc-easy-th-slow-3.png)

![img.png](pics/gc-easy-th-slow-4.png)

## jvm 报告

![img.png](gc-th-slow-1.png)
![img.png](gc-th-slow-2.png)

## jmeter 报告

![img.png](gc-th-slow-3.png)

# 响应时间优先，高延迟接口(CMS)

输出文件 [gc-parnew-cms-slow.log](./gc-parnew-cms-slow.log)

## gc easy 报告

TODO:: 使用次数受限，暂时没法获取了.

## jvm 报告

![img.png](pics/gc-cms-jvm-slow-1.png)
![img.png](pics/gc-cms-jvm-slow-2.png)

## jmeter 报告

![img.png](pics/gc-cms-jmeter-slow-1.png)
