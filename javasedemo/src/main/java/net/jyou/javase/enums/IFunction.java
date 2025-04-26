package net.jyou.javase.enums;

import java.io.Serializable;
import java.util.function.Function;

@FunctionalInterface
public interface IFunction<T,R> extends Function<T,R>, Serializable {
}
