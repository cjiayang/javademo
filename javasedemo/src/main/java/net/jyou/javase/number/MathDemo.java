package net.jyou.javase.number;

import static net.jyou.util.PrintUtil.*;

/**
 * @author Joey Chen
 * @created 2023/3/18 21:37
 */
public class MathDemo {
    public static void main(String[] args) {
        Math.max(1, 2);
        println("-----------常数---------");
        println(Math.PI);
        println(Math.E);


        double degrees = 45.0;
        double radians = Math.toRadians(degrees);
        println("-----------三角函数---------");
        printf("The radians of %.1f is %.4f", degrees, radians);
        printf("The sine of %.1f degrees is %.4f", degrees, Math.sin(radians));
        printf("The cosine of %.1f degrees is %.4f", degrees, Math.cos(radians));
        printf("The tangent of %.1f degrees is %.4f", degrees, Math.tan(radians));
        printf("The arcsine of %.4f " + "is %.4f degrees %n",
                Math.sin(radians),
                Math.toDegrees(Math.asin(Math.sin(radians)))
        );

        printf("The arccosine of %.4f " + "is %.4f degrees %n",
                Math.cos(radians),
                Math.toDegrees(Math.acos(Math.cos(radians)))
        );

        printf("The arctangent of %.4f " + "is %.4f degrees %n",
                Math.tan(radians),
                Math.toDegrees(Math.atan(Math.tan(radians)))
        );

        double a = -191.635;
        double b = 43.74;
        int c = 16, d = 45;
        println("-----------估算---------");
        printf("The absolute value of %.3f is %.3f%n", a, Math.abs(a));
        printf("The ceiling value of %.2f is %.2f%n", b, Math.ceil(b));
        printf("The floor  value of %.2f is %.2f%n", b, Math.floor (b));
        printf("The rint value of %.2f is %.2f", b, Math.rint(b));

        println("-----------求最大值、最小值---------");
        printf("The max value of %d an %d is %d%n", c, d, Math.max(c, d));
        printf("The min value of %d an %d is %d%n", c, d, Math.min(c, d));

        double x = 11.635;
        double y = 2.76;
        println("-----------指数和对数计算---------");
        printf("The value of e is %.4f%n", Math.E);
        printf("exp(%.3f) is %.3f%n", x, Math.exp(x));
        printf("log(%.3f) is %.3f%n", x, Math.log(x));
        printf("pow(%.3f, %.3f) is %.3f%n", x, y, Math.pow(x, y));

        println("-----------求绝对值---------");
        println(Math.abs(-2));

        println("-----------求随机值---------");
        double random = Math.random() * 10;
        printf("random number between [0, 10) %.4f", random);
    }
}
