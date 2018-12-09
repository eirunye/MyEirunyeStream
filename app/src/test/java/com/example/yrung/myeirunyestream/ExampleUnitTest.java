package com.example.yrung.myeirunyestream;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void MyStreamTest() {

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println(filtered.toString());

        String[] str = new String[]{"1", "2", "3", "4"};

        Stream<String> arrays = Arrays.stream(str);

        Arrays.asList(str).stream().forEach(System.out::println);

        arrays.forEach(System.out::println);
        System.out.println(Arrays.stream(str).count());


    }
}