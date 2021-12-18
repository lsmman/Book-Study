package EffectiveJava.item43;

import java.util.*;
import java.util.function.*;

public class MethodRef {

    public static void toInt(ToIntFunction<? super String> op){
        String[] a = new String[] {"1", "2"};

        int[] ints = Arrays.stream(a)
                .mapToInt(op)
                .toArray();

        System.out.println(Arrays.toString(ints));
    }

    public static void main(String[] args) {
        // 정적

        // 직접 생성
        MethodRef.toInt(new ToIntFunction<String>() {
            @Override
            public int applyAsInt(String value) {
                return Integer.parseInt(value);
            }
        });

        // 람다 사용
        MethodRef.toInt(t -> Integer.parseInt(t));

        // 메소드 참조
        MethodRef.toInt(Integer::parseInt);

        // 생성자
        "word".chars()
                .sorted()
                .collect(
                        StringBuilder::new,
                        (sb, c) -> sb.append((char) c),
                        StringBuilder::append
                )
                .toString();
    }
}

// 한정적, 비한정적
class Test {

    private final String name;

    public Test(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Test t1 = new Test("t1");
        Test t2 = new Test("t2");

        Supplier<String> supplier = t2::method;
        Function<Test, String> function = Test::method;

        // No need to say which instance to call it on -
        // the supplier is bound to t2
        System.out.println(supplier.get());

        // The function is unbound, so you need to specify
        // which instance to call it on
        System.out.println(function.apply(t1));
        System.out.println(function.apply(t2));
    }

    public String method() {
        return name;
    }
}
