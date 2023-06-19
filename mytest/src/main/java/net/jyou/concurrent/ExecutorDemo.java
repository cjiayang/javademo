package net.jyou.concurrent;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Joey Chen
 * @created 2023/5/4 13:44
 */
public class ExecutorDemo {
    public static void main(String[] args) {
        Set<String> collect1 = Stream.of("1", "2", "3", "4", "5", "6").collect(Collectors.toSet());
        Set<String> collect2 = Stream.of("3", "4", "5", "6", "7", "8").collect(Collectors.toSet());
        Set<String> forAdd = new HashSet<>(collect1);
        boolean b = forAdd.removeAll(collect2);
        Set<String> forUpdate = new HashSet<>(collect1);
        boolean b1 = forUpdate.retainAll(collect2);
        System.out.println(forUpdate);

        System.out.println(forAdd);

    }
}
