package EffectiveJava.item54;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.EnumSet;
import java.util.Timer;

public class VariadicArguments {
    static int sum(int... args) {
        int sum = 0;
        for (int arg : args)
            sum += arg;
        return sum;
    }

    static int min(int... args) {
        if (args.length == 0)
            throw new IllegalArgumentException("인수가 1개 이상 필요합니다.");
        int min = args[0];
        for (int i = 1; i < args.length; i++)
            if (args[i] < min)
                min = args[i];
        return min;
    }

    static int min(int firstArg, int... remainingArgs) {
        int min = firstArg;
        for (int arg : remainingArgs)
            if (arg < min)
                min = arg;
        return min;
    }

    public int foo() {
        return 0;
    }

    public int foo(int a1) {
        return a1;
    }

    public int foo(int a1, int a2) {
        return a1+a2;
    }

    public static int foo(int a1, int a2, int a3) {
        return a1+a2+a3;
    }

    public int foo(int a1, int a2, int a3, int... rest) {
        int sumVal = a1+a2+a3;
        for (int r:rest){
            sumVal += r;
        }
        return sumVal;
    }

    public static void main(String[] args) {
//        EnumSet
        int result;
        Instant instant;

        instant = Instant.now();
        for (Integer i = 0; i < Integer.MAX_VALUE; i++) {
            result = VariadicArguments.foo(1, 2, 3);
        }
        System.out.println((Instant.now().toEpochMilli() - instant.toEpochMilli()) / 1000 + "초");

        System.out.println("가변인수 사용한 경우");
        instant = Instant.now();
        for (Integer i = 0; i < Integer.MAX_VALUE; i++) {
            result = VariadicArguments.sum(1, 2, 3);
        }
        System.out.println((Instant.now().toEpochMilli() - instant.toEpochMilli()) / 1000 + "초");
    }
}
