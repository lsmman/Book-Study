package EffectiveJava.item43;

/*
    @FunctionalInterface
    Functional Interface는 일반적으로 '구현해야 할 추상 메소드가 하나만 정의된 인터페이스'를 가리킵니다.


    //구현해야 할 메소드가 한개이므로 Functional Interface이다.
    @FunctionalInterface
    public interface Math {
        public int Calc(int first, int second);
    }

    //구현해야 할 메소드가 두개이므로 Functional Interface가 아니다. (오류 사항)
    @FunctionalInterface
    public interface Math {
        public int Calc(int first, int second);
        public int Calc2(int first, int second);
    }
 */

//    함수형 인터페이스 람다 사용예제

import java.util.function.BinaryOperator;
import java.util.function.IntFunction;

//     함수형 Interface 선언
@FunctionalInterface
interface Math {
    public int Calc(int first, int second);
}

public class Lambda {
    //    추상 메소드 구현 및 함수형 인터페이스 사용
    public static void main(String[] args){

        Math plusLambda = (first, second) -> first + second;
        System.out.println(plusLambda.Calc(4, 2));

        Math minusLambda = (first, second) -> first - second;
        System.out.println(minusLambda.Calc(4, 2));

        // int 값 하나 받아서 결과 값 도출
        IntFunction intSum = (x) -> x+1;
        System.out.println(intSum.apply(1));

        // 두 개의 값 받아서 결과 값 도출
        BinaryOperator stringSum=(x, y)->x+" "+y;
        System.out.println(stringSum.apply("Welcome","blog"));

        // 그 외 functional interface들 : https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
    }
}
