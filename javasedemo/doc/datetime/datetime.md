# Date-Time API

java8的Date-Time API使用[ISO-8601](http://www.iso.org/iso/home/standards/iso8601.htm)定义的日期系统作为默认的日历系统，除了ISO-8601默认的日历系统，还支持其他的日历系统，如佛历，也可以定义自己的日历系统实现。

> 注1：ISO-8601文档地址：https://www.iso.org/iso-8601-date-and-time-format.html，有版权保护，下载需支付费用
>
> 注2：旧版国标地址：[数据元和交换格式 信息交换 日期和时间表示法](http://spec.nstl.gov.cn/metadata/download/upload/attpdf/60ab3074-78fb-47b7-96ba-91b66f9c849a.pdf)
>
> 注3：java11 java.time包中文文档：https://www.apiref.com/java11-zh/java.base/module-summary.html

### 设计原则

- 清晰
- 流畅
- 不可变
- 可扩展

### 时间包

- `java.time`
  日期，时间，瞬间和持续时间的主要API。
  这里定义的类表示核心的日期-时间概念，包括时刻，持续时间，日期，时间，时区和时段。 它们基于ISO-8601日历系统，这是遵循格里高利规则的*事实上的*世界日历。 所有类都是不可变的和线程安全的。
- `java.time.chrono`
  除默认ISO之外的日历系统的通用API。
  主API基于ISO-8601中定义的日历系统。 但是，还有其他日历系统，此软件包为它们提供基本支持。 [`java.time.chrono`](package-summary.html)包中提供了备用日历。
- `java.time.format`
  提供打印和解析日期和时间的类。
- `java.time.temporal`
  使用字段和单位以及日期时间调整器访问日期和时间。
  该软件包扩展了基础软件包，为更强大的用例提供了额外的功能。 支持包括：

  * 日期时间单位，例如年，月，日和小时
  * 日期时间字段，例如月份，星期几或小时
  * 日期时间调整功能
  * 周的不同定义
- `java.time.zone`
  支持时区、时区偏移量和时区规则的类

### 方法命名惯例

日期时间API提供了一组丰富的类，这些类提供了多种方法。这些类的方法名称在可能的情况下保持一致。比如很多类都有`now()`方法用来表示类所要表示的当前时间，

还有关于方法名称前缀的标准化，以下表格列出了常用的方法前缀：


| Prefix | Method Type    | Use                                                                                |
| -------- | ---------------- | ------------------------------------------------------------------------------------ |
| of     | static factory | 创建一个实例，其中工厂主要验证输入参数，而不是转换它们。                           |
| from   | static factory | 将输入参数转换为目标类的实例，这可能涉及从输入中丢失信息。                         |
| parse  | static factory | 解析输入字符串以生成目标类的实例。                                                 |
| format | instance       | 使用指定的格式化器来格式化临时对象中的值，以生成字符串形式返回。                   |
| get    | instance       | 返回目标对象的部分状态。                                                           |
| is     | instance       | 查询目标对象的状态。                                                               |
| with   | instance       | 返回一个元素已更改的目标对象的副本； 这是不可变的，相当于 JavaBean 上的 set 方法。 |
| plus   | instance       | 返回增加了一定时间的目标对象的副本。                                               |
| minus  | instance       | 返回减去时间量的目标对象的副本。                                                   |
| to     | instance       | 将此对象转换为另一种类型。                                                         |
| at     | instance       | 将此对象与另一个对象组合。                                                         |

## 标准日历类

### DayOfWeek和Month枚举

#### DayOfWeek

`DayOfWeek`枚举包含周一到周日的常量值，星期对应的数值表示为从1(周一)到7(周日)。使用常量（比如：`DayOfWeek.FRIDAY`）来表示星期可以使代码可读性更好。

`DayOfWeek`枚举含有基于时间类所提供的常用方法，比如`plus()`,`minus()`等。

`getDisplayName(TextStyle, Locale)`方法用于日期的本地语言显示，`TextStyle`枚举有三个值：FULL, NARROW (通常用单个字母表示), SHORT (缩写).

#### Month

`Month`枚举包含了12个月份的常量表示，从`JANUARY`到`DECEMBER`，月份对应的数值表示是从1(JANUARY)到12(DECEMBER)。同样，使用（`Month.SEPTEMBER`）可以使代码可读性更好。

`Month`枚举同样包含了一系列实用方法，比如`maxLength()`方法用于打印月份可能包含的最大天数。

`Month`枚举同样实现了`getDisplayName(TextStyle, Locale)`方法，功能与`DayOfWeek`相同。

### 日期类Date

Date-Time API 提供了四个专门处理日期信息的类，与时间或时区无关，它们分别是：LocalDate, YearMonth, MonthDay, and Year.

#### LocalDate

LocalDate 用于表示 ISO 日历中的年月日，对于表示没有时间的日期很有用。

下面例子使用of和with方法来创建`LocalDate`实例：

```java
LocalDate date = LocalDate.of(2000, Month.NOVEMBER, 20);
LocalDate nextWed = date.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
```

LocalDate类提供了相关的getter方法，用于获取LocalDate实例的相关信息。例如，getDayOfWeek方法用于获取星期信息。

```java
DayOfWeek dotw = LocalDate.of(2012, Month.JULY, 9).getDayOfWeek();
```

下面使用TemporalAdjuster来获取下一个周三的日期。

```java
LocalDate date = LocalDate.of(2000, Month.NOVEMBER, 20);
TemporalAdjuster adj = TemporalAdjusters.next(DayOfWeek.WEDNESDAY);
LocalDate nextWed = date.with(adj);
System.out.printf("For the date of %s, the next Wednesday is %s.%n", date, nextWed);
```

#### YearMonth

YearMonth 类表示指定年份的月份。

```java
YearMonth date = YearMonth.now();
System.out.printf("%s: %d%n", date, date.lengthOfMonth());

YearMonth date2 = YearMonth.of(2010, Month.FEBRUARY);
System.out.printf("%s: %d%n", date2, date2.lengthOfMonth());

YearMonth date3 = YearMonth.of(2012, Month.FEBRUARY);
System.out.printf("%s: %d%n", date3, date3.lengthOfMonth());
```

#### MonthDay

MonthDay 类表示特定月份的日期，例如 1 月 1 日的元旦。

以下例子表示了MonthDay的isValidYear方法，用于检测2010是否是闰年

```java
MonthDay date = MonthDay.of(Month.FEBRUARY, 29);
boolean validLeapYear = date.isValidYear(2010);
```

#### Year

Year类表示年，isLeap方法用于判断Year实例是否是闰年

```java
boolean validLeapYear = Year.of(2012).isLeap();
```

### 日期和时间类

#### LocalTime

LocalTime用于表示时间，该类很适合用于表示人类易读的时间表示。

该类不包含时区和夏令时信息

#### LocalDateTime

该类包含了日期和时间信息，该类日期date (month-day-year)，时间time (hour-minute-second-nanosecond)，可以看做是`LocalDate`和`LocalTime`的组合。该类不包含时区信息。如果要包含时区信息，需要使用ZonedDateTime和`OffsetDateTime`

LocalDateTime用`of`方法来创建实例，用`from`方法来从其他时间格式转换成LocalDateTime实例，还有`plus`和`minus`方法族用于时间计算

```java
System.out.printf("now: %s%n", LocalDateTime.now());

System.out.printf("Apr 15, 1994 @ 11:30am: %s%n",
                  LocalDateTime.of(1994, Month.APRIL, 15, 11, 30));

System.out.printf("now (from Instant): %s%n",
                  LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));

System.out.printf("6 months from now: %s%n",
                  LocalDateTime.now().plusMonths(6));

System.out.printf("6 months ago: %s%n",
                  LocalDateTime.now().minusMonths(6));
```

### 时区和偏移量类(Time Zone and Offset Classes)

时区是地球上使用相同标准时间的区域。 每个时区都由一个标识符描述，通常具有区域/城市（亚洲/东京）格式和与格林威治/UTC 时间的偏移量。 例如，东京的偏移量是 +09:00。

#### ZoneId 和 ZoneOffset

Date-Time API 提供了两个用于指定时区或偏移量的类：

- ZoneId 指定时区标识符并提供在 Instant 和 LocalDateTime 之间转换的规则。
- ZoneOffset 指定与格林威治/UTC 时间的时区偏移量。

#### Date-Time类

Date-Time API提供了3个基于时间的类，用来表示时区

- ZonedDateTime：处理具有相应时区的日期和时间，时区与格林威治/UTC的时区偏移量。
- OffsetDateTime：处理日期和时间与格林威治/UTC的相应时区偏移量，没有时区 ID。
- OffsetTime：处理时间与格林威治/UTC的相应时区偏移量，没有时区 ID。

### Instant类

Instant是Date-Time API类库的一个核心类，表示时间轴上纳秒的开始。该类适用于产生时间戳来表示机器时间。

从Instant类返回的值从1970年1月1日（1970-01-01T00:00:00Z）的第一秒开始计算时间，也叫EPOCH。发生在纪元之前的瞬间为负值，发生在纪元之后的瞬间为正值。

Instant有两个常量MIN和MAX

Instant的toString输出

```java
2013-05-30T23:38:23.085Z
```

Instant的加减操作

```java
Instant oneHourLater = Instant.now().plus(1, ChronoUnit.HOURS);
```

Instant的比较操作[isAfter](https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html#isAfter-java.time.Instant-) and [isBefore](https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html#isBefore-java.time.Instant-)

Instant计算从Epoch开始到某个时间走过的秒数

```java
long secondsFromEpoch = Instant.ofEpochSecond(0L).until(Instant.now(),
                        ChronoUnit.SECONDS);
```

Instant和LocalDateTime，ZonedDateTime，OffsetTimeZone转换

```java
Instant timestamp;
...
LocalDateTime ldt = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
System.out.printf("%s %d %d at %d:%d%n", ldt.getMonth(), ldt.getDayOfMonth(),
                  ldt.getYear(), ldt.getHour(), ldt.getMinute());
```


### 解析和格式化(Parsing and Formatting)

Date-Time API 中基于时间的类提供了用于解析包含日期和时间信息的字符串的解析方法。 这些类还提供格式方法，用于格式化基于时间的对象并以一定的格式字符串显示。在这两种情况下，过程都是相似的：向DateTimeFormatter对象提供模式以创建DateTimeFormatter对象。 然后将此DateTimeFormatter对象传递给解析或格式化方法。

DateTimeFormatter提供了很多预定义的格式化器，你也可以定义自己的格式化器。

DateTimeFormatter是线程安全的

#### 解析(Parsing)

LocalDate的单参数方法[parse(CharSequence)](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html#parse-java.lang.CharSequence-)使用ISO_LOCAL_DATE格式化器。如果需要使用其他格式化器，可以使用方法[parse(CharSequence, DateTimeFormatter)](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html#parse-java.lang.CharSequence-java.time.format.DateTimeFormatter-)，该方法允许提供指定的格式化器。

可以自定义时间格式模式，如下所示：

```java
String input = ...;
try {
    DateTimeFormatter formatter =
                      DateTimeFormatter.ofPattern("MMM d yyyy");
    LocalDate date = LocalDate.parse(input, formatter);
    System.out.printf("%s%n", date);
}
catch (DateTimeParseException exc) {
    System.out.printf("%s is not parsable!%n", input);
    throw exc;      // Rethrow the exception.
}
// 'date' has been successfully parsed
```

#### 格式化(Formatting)

format(DateTimeFormatter) 方法使用指定格式将基于时间的对象转换为字符串表示形式。

### Temporal包

java.time.temporal 包提供了一组支持日期和时间代码的接口、类和枚举，尤其是日期和时间计算。

这些接口旨在用于底层抽象。应用代码应该根据具体类型（例如 LocalDate 或 ZonedDateTime）声明变量和参数，而不是根据Temporal接口。这与声明String类型而非CharSequence接口类型的变量完全相同。

#### Temporal 和 TemporalAccessor接口

Temporal接口提供了一个访问基于时间的对象的框架，并由基于时间的类实现，如Instant、LocalDateTime和ZonedDateTime。这个接口提供了加减时间单位的方法，使基于时间的算术在各种日期和时间类中变得简单和一致。TemporalAccessor接口提供了Temporal接口的一个只读版本。

Temporal和TemporalAccessor对象都是以字段来定义的，如TemporalField接口所规定的。ChronoField枚举是TemporalField接口的具体实现，提供了丰富的定义常量，如DAY_OF_WEEK、MINUTE_OF_HOUR和MONTH_OF_YEAR。

这些字段的单位是由TemporalUnit接口指定的。ChronoUnit枚举实现了TemporalUnit接口。ChronoField.DAY_OF_WEEK字段是ChronoUnit.DAYS和ChronoUnit.WEEKS的组合。ChronoField和ChronoUnit枚举将在下面几节讨论。

Temporal接口中基于算术的方法需要用TemporalAmount值来定义参数。Period和Duration类实现了TemporalAmount接口。

#### ChronoField 和 IsoFields

ChronoField枚举实现了TemporalField接口，它提供了一组丰富的常量用于访问日期和时间值。几个例子是CLOCK_HOUR_OF_DAY, NANO_OF_DAY, 和 DAY_OF_YEAR。这个枚举可以用来表达时间的概念方面，如一年中的第三周，一天中的第11个小时，或每月的第一个星期一。当你遇到一个未知类型的Temporal时，你可以使用TemporalAccessor.isSupported(TemporalField)方法来确定该Temporal是否支持一个特定的字段。下面这行代码返回false，表明LocalDate不支持ChronoField.CLOCK_HOUR_OF_DAY:

```java
boolean isSupported = LocalDate.now().isSupported(ChronoField.CLOCK_HOUR_OF_DAY);
```

#### ChronoUnit

ChronoUnit 枚举实现了 TemporalUnit 接口，并提供了一组基于日期和时间的标准单位，从毫秒到千年。 请注意，并非所有类都支持所有 ChronoUnit 对象。 例如，Instant 类不支持 ChronoUnit.MONTHS 或 ChronoUnit.YEARS。 Date-Time API 中的类包含 isSupported(TemporalUnit) 方法，可用于验证类是否支持特定时间单位。 以下对 isSupported 的调用返回 false，确认 Instant 类不支持 ChronoUnit.DAYS。

```java
Instant instant = Instant.now();
boolean isSupported = instant.isSupported(ChronoUnit.DAYS);
```

#### 时间调节器(Temporal Adjuster)

TemporalAdjusters类提供了许多预定义的调节器，这些预定义调节器都可以通过static方法访问，以下是一些例子：

```java
LocalDate date = LocalDate.of(2000, Month.OCTOBER, 15);
DayOfWeek dotw = date.getDayOfWeek();
System.out.printf("%s is on a %s%n", date, dotw);

System.out.printf("first day of Month: %s%n",
                  date.with(TemporalAdjusters.firstDayOfMonth()));
System.out.printf("first Monday of Month: %s%n",
                  date.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
System.out.printf("last day of Month: %s%n",
                  date.with(TemporalAdjusters.lastDayOfMonth()));
System.out.printf("first day of next Month: %s%n",
                  date.with(TemporalAdjusters.firstDayOfNextMonth()));
System.out.printf("first day of next Year: %s%n",
                  date.with(TemporalAdjusters.firstDayOfNextYear()));
System.out.printf("first day of Year: %s%n",
                  date.with(TemporalAdjusters.firstDayOfYear()));
```

##### 自定义调节器(Custom Adjusters)

自定义调节器需要实现TemporalAdjuster接口的 [adjustInto(Temporal)](https://docs.oracle.com/javase/8/docs/api/java/time/temporal/TemporalAdjuster.html#adjustInto-java.time.temporal.Temporal-) 方法.

#### 时间查询器(Temporal Query)

一个TemporalQuery可以用来从一个基于时间的对象中检索信息。

[TemporalQueries](https://docs.oracle.com/javase/8/docs/api/java/time/temporal/TemporalQueries.html)类提供了许多预定义的查询器，同样，这些预定义的查询器可以通过static方法访问，以下演示precision查询的例子：

```java
TemporalQuery<TemporalUnit> query = TemporalQueries.precision();
System.out.printf("LocalDate precision is %s%n",
                  LocalDate.now().query(query));
System.out.printf("LocalDateTime precision is %s%n",
                  LocalDateTime.now().query(query));
System.out.printf("Year precision is %s%n",
                  Year.now().query(query));
System.out.printf("YearMonth precision is %s%n",
                  YearMonth.now().query(query));
System.out.printf("Instant precision is %s%n",
                  Instant.now().query(query));
```

### 期间和持续时间(Period and Duration)


当你需要指定一个时间量时，最符合的类或方法是：Duration类、Period类或ChronoUnit.between方法。

Duration使用基于时间的值（秒，纳秒）来测量一个时间量。

Period使用基于日期的值（年、月、日）。

#### Duration

Duration 最适合测量基于机器的时间的情况，例如使用 Instant 对象的代码。 Duration 对象以秒或纳秒为单位进行测量，并且不使用基于日期的构造，例如年、月和日，尽管该类提供了转换为天、小时和分钟的方法。 如果 Duration 是用在起点之前出现的终点创建的，则它可以具有负值。

以下代码，计算两个时刻的纳秒时间间隔

```java
Instant t1, t2;
...
long ns = Duration.between(t1, t2).toNanos();
```

以下代码，给其实时刻加10s

```java
Instant start;
...
Duration gap = Duration.ofSeconds(10);
Instant later = start.plus(gap);
```

#### ChronoUnit

ChronoUnit枚举定义了用于测量时间的单位，当您只想测量单个时间单位（例如天或秒）中的时间量时，可以使用ChronoUnit.between方法。

#### Period

要使用基于日期的值（年、月、日）定义时间量，请使用 Period 类。 Period类提供了getMonths、getDays、getYears等各种get方法，可以从period中提取时间量。

总时间Period由所有三个单位一起表示：月、日和年。 要显示以单个时间单位（例如天）测量的时间量，可以使用 ChronoUnit.between方法。

以下示例代码用于计算年龄：

```java
LocalDate today = LocalDate.now();
LocalDate birthday = LocalDate.of(1960, Month.JANUARY, 1);

Period p = Period.between(birthday, today);
long p2 = ChronoUnit.DAYS.between(birthday, today);
System.out.println("You are " + p.getYears() + " years, " + p.getMonths() +
                   " months, and " + p.getDays() +
                   " days old. (" + p2 + " days total)");
```
