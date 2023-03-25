package net.jyou.javase.number;


import static net.jyou.util.PrintUtil.*;

/**
 * @author Joey Chen
 * @created 2023/3/19 16:36
 */
public class CharacterDemo {
    public static void main(String[] args) {
        char c = '0';
        char d = '+';
        char e = '-';
        println((int)c);
        println((int)d);
        println((int)e);

        printInt2Char();

    }

    public static void printInt2Char() {
        char[] chars = new char[95];
        for (int i = 33; i < 128; i++) {
            chars[i - 33] = (char) i;
        }
        for (Character aChar : chars) {
            println((int)aChar + " -> " + aChar);
        }
    }
}
