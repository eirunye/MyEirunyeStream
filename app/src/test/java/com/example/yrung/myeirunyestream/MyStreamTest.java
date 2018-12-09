package com.example.yrung.myeirunyestream;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Author Eirunye
 * Created by on 2018/12/9.
 * Describe
 */
public class MyStreamTest {

    @Test
    public void myStreamTest() {
        List<String> strings = Arrays.asList("1", "2", "2", "3", "4");

        Stream.of(strings).forEach(System.out::println);
        strings.forEach(System.out::println);
    }

    @Test
    public void myStreamTest1() {
        List<String> strings = Arrays.asList("1", "2", "2", "", "3", "4");

        Stream.of(strings).filter(s -> !s.isEmpty()).forEach(System.out::println);
    }

    @Test
    public void myStreamTest2() {
        List<String> strings = Arrays.asList("1", "2", "2", "", "3", "4");

        Stream.of(strings).filter(s -> !s.isEmpty()).map(Integer::parseInt).forEach(System.out::println);
        Stream.of(strings).filter(s -> !s.isEmpty()).distinct().map(Integer::parseInt).forEach(System.out::println);

    }

    @Test
    public void myStreamTest3() {
        List<String> strings = Arrays.asList("1", "2", "2", "3", "4");

//        Stream.of(strings).map(Integer::parseInt).forEach(System.out::println);

        Stream.of(strings).mapToInt(Integer::parseInt).forEach(System.out::println);
    }

    @Test
    public void myStreamTest4() {
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.flatMap(Stream::of);

        List<String> strings = Arrays.asList("1", "2", "2", "3", "4");
        List<String> strings1 = Arrays.asList("a", "b", "c");
        Stream.of(strings, strings1).flatMap(Stream::of).forEach(System.out::println);

//        Stream.of(strings).mapToInt(Integer::parseInt).forEach(System.out::println);
    }

    @Test
    public void myStreamTest5() {

        List<String> strings = Arrays.asList("1", "2", "2", "3", "4");
        List<String> strings1 = Arrays.asList("a", "b", "c");
        Stream.of(strings, strings1).flatMap(Stream::of).forEach(System.out::println);
//        strings.stream().flatMapToInt(s->InputStream.of(s))

//        Stream.of(strings).mapToInt(Integer::parseInt).forEach(System.out::println);

    }

    @Test
    public void myStreamTest6() {

        List<String> strings = Arrays.asList("1", "2", "2", "3", "4");
        Stream.of(strings).limit(3).forEach(System.out::println);

    }

    @Test
    public void myStreamTest7() {

        List<String> strings = Arrays.asList("1", "2", "2", "3", "4");
        Stream.of(strings).skip(3).limit(3).forEach(System.out::println);

    }

    @Test
    public void myStreamTest8() {

        List<String> strings = Arrays.asList("1", "5", "2", "6", "4");
        Stream.of(strings).sorted().forEach(System.out::println);

    }

    @Test
    public void myStreamTest9() {

        List<String> strings = Arrays.asList("1", "5", "2", "6", "4");
        System.out.println(Stream.of(strings).allMatch(s -> strings.contains("1")));

    }

    @Test
    public void myStreamTest10() {

        List<String> strings = Arrays.asList("1", "5", "2", "6", "4");
        System.out.println(Stream.of(strings).anyMatch(s -> s.contains("1")));

    }

    @Test
    public void myStreamTest11() {

        List<String> strings = Arrays.asList("1", "5", "", "6", "4");
        System.out.println(Stream.of(strings).noneMatch(s -> s.contains("8")));

    }

    @Test
    public void myStreamTest12() {

        List<String> strings = Arrays.asList("1", "5", "", "6", "4");
        List<String> strings1 = Stream.of(strings).filter(s -> !s.isEmpty()).collect(Collectors.toList());
//        strings1.forEach(System.out::println);
        List<String> string = Arrays.asList("1", "5", "6", "6", "4");
        Stream.of(string).filter(s -> !s.isEmpty()).collect(Collectors.toSet()).forEach(System.out::println);
    }

    @Test
    public void myStreamTest13() {

        List<String> strings = Arrays.asList("1", "5", "", "6", "4");
        Stream strings1 = Stream.of(strings).filter(s -> !s.isEmpty());
        List<String> string = Arrays.asList("8", "5", "6", "6", "4");
        Stream strings2 = Stream.of(string).filter(s -> !s.isEmpty());
        Stream.concat(strings1, strings2).forEach(System.out::println);
    }

    @Test
    public void myStreamTest14() {

        System.out.println(Stream.of(1, 2, 3, 4).reduce(Integer::sum).get());
        List<Integer> num = Arrays.asList(1, 3, 8, 5);
        System.out.println(Stream.of(num).reduce(Integer::max).get());
        System.out.println(Stream.of(num).reduce(Integer::min).get());
    }

    @Test
    public void myStreamTest15() {

        Stream<Integer> stream = Stream.of(1, 2, 3, 4);
        System.out.println(stream.count());
    }

    @Test
    public void myStreamTest16() {

        Stream<Integer> stream = Stream.of(1, 2, 3, 4);
        Stream<Integer> stream1 = stream.empty();
        System.out.println(stream1.count());
    }

    @Test
    public void myStreamTest17() {

        Stream<Integer> stream = Stream.of(1, 2, 3, 4);
        System.out.println(stream.findFirst().get());
    }

    @Test
    public void myStreamTest18() {

        Stream<String> stream = Stream.of("a", "c", "C", "f");
        List<String> list = stream.peek(e -> System.out.println("value: " + e)).map(String::toUpperCase).collect(Collectors.toList());
        list.forEach(System.out::println);
    }

    @Test
    public void myStreamTest20() {


        int sumValue1 = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println(sumValue1);

        int sumValue = Stream.of(1, 2, 3, 4).reduce(8, Integer::sum);
        System.out.println(sumValue);
    }

    @Test
    public void myStreamTest21() {


        Integer[] Nums = {1, 2, 3, 4, 5, 6};
        Integer[] evens = Stream.of(Nums).filter(n -> n % 2 == 0).toArray(Integer[]::new);

        Integer[] n = Stream.of(1, 2, 4, 5, 7, 3, 11).skip(3).sorted().toArray(Integer[]::new);

        Stream.of(n).forEach(System.out::println);

    }
}
