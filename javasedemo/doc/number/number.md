# javase类

## Number

Number是java数值类型的父类，数值类型是原始类型的包装，下面介绍数值类型及数值格式化相关类，还有与数值计算相关的类Math

当在java中使用数值时，通常使用可以直接使用原始类型，如：

```java
int i = 500;
float gpa = 3.65f;
byte mask = 0x7f;
```

在以下三种情况下，使用数值对象类型比使用原始类型更适合。

- 方法的参数需要数值对象类型时，通常操作集合对象时会遇到
- 需要使用数值对象类型的MIN_VALUE和MAX_VALUE表示常量时
- 需要使用数值对象的方法来与原始类型，字符串或者其他数值类型互相转换时

数值类型提供了如下几类实例方法：

- 用于将数值对象转换成原始类型

```java
byte byteValue()
short shortValue()
int intValue()
long longValue()
float floatValue()
double doubleValue()
```

- 用于与参数进行比较

```java
int compareTo(Byte anotherByte)
int compareTo(Double anotherDouble)
int compareTo(Float anotherFloat)
int compareTo(Integer anotherInteger)
int compareTo(Long anotherLong)
int compareTo(Short anotherShort)
```

- 用于对象相等比较

```java
boolean equals(Object obj)
```

数值类型还提供了用于和字符串或其他数值类型互转的静态方法，一下表格展示了Integer类的相关方法，其他对象类似，并且各自的类还分别提供了相应的方便方法。


| 方法                                        | 描述                                                                   |
| --------------------------------------------- | ------------------------------------------------------------------------ |
| static Integer decode(String s)             | 将字符串转为Integer对象，可以接收二进制、十进制和十六进制的数值        |
| static int parseInt(String s)               | 将字符串转为十进制数字                                                 |
| static int parseInt(String s, int radix)    | 根据radix值(10, 2, 8, or 16)，将其他进制的字符串，转换成十进制数值     |
| String toString()                           | 将当前数值对象转成字符串                                               |
| static String toString(int i)               | 将参数数值转为字符串                                                   |
| static Integer valueOf(int i)               | 将原始数值转为对应的包装对象                                           |
| static Integer valueOf(String s)            | 将字符串转为对应的数值对象                                             |
| static Integer valueOf(String s, int radix) | 根据radix值(10, 2, 8, or 16)，将其他进制的字符串，转换成十进制数值对象 |

### Math

Math类提供了一系列的静态方法用于数学计算

估算

- double ceil(double d)
- double floor(double d)
- long round(double d)

比较大小

- int min(int arg1, int arg2)
- int max(int arg1, int arg2)

对数指数运算

- double exp(double d)
- double log(double d)
- double pow(double base, double exponent)
- double sqrt(double d)

三角函数

- double sin(double d)
- double cos(double d)
- double tan(double d)
- double toDegrees(double d)
- double toRadians(double d)

随机值

- double random()，返回值在【0,1）之间

### BigInteger

BigInteger和BigDecimal用于精确计算

### 源码分析

Integer源码分析：[Java 基础：Integer 源码分析](https://zhuanlan.zhihu.com/p/107779039)

## Stirng

### 源码分析

String源码分析：https://blog.csdn.net/Herishwater/article/details/101030412
