package net.jyou;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Joey Chen
 * @created 2023/4/20 22:38
 */
public class MaxTest {
    public static void main(String[] args) {
        List<Integer> list = Stream.of(1).collect(Collectors.toList());
        Integer max = list.stream()
                .max(Integer::compareTo)
                .orElse(0);
        System.out.println(max);
    }
}
