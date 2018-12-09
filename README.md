# Java 8 Stream
# 简介
在Java 8中API添加了一个新的抽象称为流Stream，可以让你以一种声明的方式处理数据。在Java中只要你应用的版本是java 8以上的话都能使用该API，但是在Android中如果API低于24时，是无法使用该java API，今天我们来介绍在Android中如何使用[**Stream**](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)，还有常用的操作符。
![Stream](http://upload-images.jianshu.io/upload_images/3012005-4a75794c3fa4871e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
# 什么是Stream
在操作之前我们来认识一下什么是Stream? 有什么特性呢？ 进入[**Sream API**](https://docs.oracle.com/javase/8/docs/api/)
* 支持顺序和并行聚合操作的一系列元素，有丰富的操作符。
* 除了Stream（对象引用流）之外，还有IntStream，LongStream和DoubleStream的原始特化，所有这些特性都被称为“流”，并且符合此处描述的特征和限制
* **Stream Pipeline** 可以**顺序执行**或**并行执行**。此执行模式是流的属性。通过初始选择的顺序或并行执行来创建流。(例如Collection.stream()创建一个顺序流Collection.parallelStream() 创建一个并行流。)这种执行模式的选择可以通过BaseStream.sequential()或BaseStream.parallel()方法修改，并且可以使用BaseStream.isParallel()方法查询。
* 和Collections有相似之处，但是Stream不提供直接访问或操纵其元素的手段，而是涉及声明性地描述它们的源以及将在该源上聚合执行的计算操作。
* 支持lambda，更加方便简洁。
# Android 使用Stream
上文已经提及了在默认的Android API中只要在API 24以上才能使用如下：
```java
@RequiresApi(api = Build.VERSION_CODES.N) //必须标志在Android N才能使用，也就是API>=24才能使用
public void testStream() {
  List<String> strings = Arrays.asList("1", "2", "2", "3", "4");
  Stream.of(strings).filter(s -> !strings.isEmpty()).forEach(System.out::println);
}
```
但是为了使用Stream的话，我们可以引用Stream依赖
```
//在Project build.gradle中添加
allprojects {
	repositories {
	//...
	maven { url 'https://jitpack.io' }
	}
}
//在Module app build.gradle 中添加
implementation 'com.annimon:stream:1.2.1'
```
* 注：这里的Stream和java 8的Stream 包名是不一样的，这是一个非官方兼容方案为一个依赖库 ，不过语法是一样的，java 8的包名是`java.util.stream.Stream`，而依赖库的包是`com.annimon.stream.Stream`，这里需要大家在使用的时候注意。
* 我们不能将我们Android API设置为24，那么这样是不可取的，使用选择一个依赖库也是完全可以解决需求的。
# 操作符使用
我们先来一个简单的例子
```java
List<String> strings = Arrays.asList("1", "2", "2", "3", "4");
//输出list的元素
Stream.of(strings).forEach(System.out::println);
```
当然了，熟悉lambda表达式的同学肯定觉得这有什么好的呢？List也直接可以输出而且很方便呢？如下:
```java
List<String> strings = Arrays.asList("1", "2", "2", "3", "4");
strings.forEach(System.out::println);
```
上面两个例子并没有让我们觉得Stream很牛逼是吧，不急，我们继续进行看看，Stream是不是很强大。
### of
返回其元素为指定值的顺序有序流。
```java
Stream<Integer> stream = Stream.of(1, 2, 3, 4);
System.out.println(stream.count()); //stream.count()：返回此流中元素的数量。
输出：
4
```
<!-- ## 转换操作符-->
### map
返回一个流，该流包含将给定函数应用于此流的元素的结果
```java
List<String> strings = Arrays.asList("1", "2", "2", "3", "4");
//将字符串转换为int输出
Stream.of(strings).map(Integer::parseInt).forEach(System.out::println);
输出：
1
2
2
3
4
```
### mapToInt
返回一个IntStream，它包含将给定函数应用于此流的元素的结果。
相同的还有mapToLong、mapToDouble
```java
List<String> strings = Arrays.asList("1", "2", "2", "3", "4");
Stream.of(strings).mapToInt(Integer::parseInt).forEach(System.out::println);
输出：
1
2
2
3
4
```
### flatMap
返回一个流，该流包含将此流的每个元素替换为通过将提供的映射函数应用于每个元素而生成的映射流的内容的结果。
```java
List<String> strings = Arrays.asList("1", "2", "2", "3", "4");
List<String> strings1 = Arrays.asList("a", "b", "c");
Stream.of(strings,strings1).flatMap(Stream::of).forEach(System.out::println);
输出：
1
2
2
3
4
a
b
c
```
### filter
返回由与此给定谓词匹配的此流的元素组成的流。过滤器，如下下面例子过滤空字符串
```java
List<String> strings = Arrays.asList("1", "2", "2", "", "3", "4");
Stream.of(strings).filter(s -> !s.isEmpty()).map(Integer::parseInt).forEach(System.out::println);
输出：
1
2
2
3
4
```
### distinct
去除List重复的元素，返回由此流的不同元素（根据Object.equals（Object））组成的流。
```java
List<String> strings = Arrays.asList("1", "2", "2", "", "3", "4");
Stream.of(strings).filter(s -> !s.isEmpty()).distinct().map(Integer::parseInt).forEach(System.out::println);
输出：
1
2
3
4
```
### limit
返回由此流的元素组成的流，截断为长度不超过maxSize。如下maxSize=3时。
```java
List<String> strings = Arrays.asList("1", "2", "2", "3", "4");
Stream.of(strings).limit(3).forEach(System.out::println);
输出：
1
2
2
```
### skip
在丢弃流的前n个元素后，返回由此流的其余元素组成的流。如下n=3，则丢弃前面3个元素。
```java
List<String> strings = Arrays.asList("1", "2", "2", "3", "4");
Stream.of(strings).skip(3).limit(3).forEach(System.out::println);
输出：
3
4
```
### sorted
返回由此流的元素组成的流，按照自然顺序排序。默认为升序
```java
List<String> strings = Arrays.asList("1", "5", "2", "6", "4");
Stream.of(strings).sorted().forEach(System.out::println);
输出：
1
2
4
5
6
```
<!-- ## 匹配操作符-->
### allMatch
返回此流的所有元素是否与提供的断言匹配。boolean值
```java
List<String> strings = Arrays.asList("1", "5", "2", "6", "4");
System.out.println(Stream.of(strings).allMatch(s -> strings.contains("a")));
输出：
false

System.out.println(Stream.of(strings).allMatch(s->strings.contains("1")));
输出：
true
```
### anyMatch

返回此流的任何元素是否与提供的断言匹配。
```java
List<String> strings = Arrays.asList("1", "5", "2", "6", "4");
System.out.println(Stream.of(strings).anyMatch(s -> s.contains("1")));
输出：
true
```
### noneMatch
Stream 中没有一个元素符合传入的 断言
```java
List<String> strings = Arrays.asList("1", "5", "", "6", "4");
System.out.println(Stream.of(strings).noneMatch(s -> s.contains("8")));
输出：
true
```
### collect
输出为一个新的集合数据。
```java
List<String> strings = Arrays.asList("1", "5", "", "6", "4");
List<String> strings1 = Stream.of(strings).filter(s -> !s.isEmpty()).collect(Collectors.toList());
strings1.forEach(System.out::println);
输出：
1
5
6
4

List<String> string = Arrays.asList("1", "5", "6", "6", "4");
Stream.of(string).filter(s -> !s.isEmpty()).collect(Collectors.toSet()).forEach(System.out::println);//Collectors.toSet() 不能出现重复元素，并进行排序输出。hashSet类似，但是Collectors.toSet()会进行排序
输出：
1
4
5
6
```
### concat
创建一个延迟连接的流，其元素是第一个流的所有元素，后跟第二个流的所有元素。
```java
List<String> strings = Arrays.asList("1", "5", "", "6", "4");
Stream strings1 = Stream.of(strings).filter(s -> !s.isEmpty());
List<String> string = Arrays.asList("8", "5", "6", "6", "4");
Stream strings2 = Stream.of(string).filter(s -> !s.isEmpty());
Stream.concat(strings1, strings2).forEach(System.out::println);
输出：
1
5
6
4
8
5
6
6
4
```
### reduce
使用关联累加函数对此流的元素执行减少，并返回描述减少值的Optional（如果有）。
```java
int sum = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
System.out.println(sumValue1);
int sum1 = Stream.of(1, 2, 3, 4).reduce(2, Integer::sum);//表示identity=2，即该sum的初始值，所以Stream中的总和等于2+(1+2+3+4)=12
System.out.println(sumValue);
输出：
10
12
```

### sum、max、min
输出Stream流的和、最大值、最小值，返回的是`Optional `
```java
System.out.println(Stream.of(1, 2, 3, 4).reduce(Integer::sum).get());
List<Integer> num = Arrays.asList(1, 3, 8, 5);
System.out.println(Stream.of(num).reduce(Integer::max).get());
System.out.println(Stream.of(num).reduce(Integer::min).get());
输出：
10
8
1
```
### empy
返回一个空的顺序Stream。
```java
Stream<Integer> stream = Stream.of(1, 2, 3, 4);
Stream<Integer> stream1 = stream.empty();
System.out.println(stream1.count());
输出：
0
```
### findFirst
返回描述此流的第一个元素的Optional，如果流为空，则返回空Optional。
```java
Stream<Integer> stream = Stream.of(1, 2, 3, 4);
System.out.println(stream.findFirst().get());
输出：
1
```
### peek
返回由此流的元素组成的流，另外在每个元素上执行提供的操作，因为元素是从结果流中消耗的。
```java
Stream<String> stream = Stream.of("a", "c", "C", "f");
List<String> list = stream.peek(e -> System.out.println("value: " + e)).map(String::toUpperCase).collect(Collectors.toList());
list.forEach(System.out::println);
输出：
value: a
value: c
value: C
value: f
A
C
C
F
```
### toArray
```java
Object[] array = Stream.of(1, 2, 3, 4).toArray();
Integer[] n = Stream.of(1, 2, 4, 5, 7, 3, 11).skip(3).toArray(Integer[]::new);
Stream.of(n).forEach(System.out::println);
输出：
5
7
3
11
```
# 下载
[**案例下载**](https://github.com/eirunye/MyEirunyeStream/blob/master/app/src/test/java/com/example/yrung/myeirunyestream/MyStreamTest.java)
# 总结
[**Stream**](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html) 常用的操作符，我们在这里的案例都差别多测试了，是不是感觉[**Stream**](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)给我们的感觉是一种很棒的开发案例，我们可以在开发中使用它，这样方便我们对集合的操作。

可能有些同学看到第一个的时候就觉得在哪里见过？是的，没错，是不是和[**RxJava**](https://github.com/ReactiveX/RxJava)的操作符的作用差不多都是非常类似的。当然了，[RxJava](https://github.com/ReactiveX/RxJava)的操作是非常丰富的，希望大家可以多多去了解。

# 推荐
[**我的博客**](https://eirunye.github.io/)
[**我的简书**](https://www.jianshu.com/u/447fdef5fb8f)

