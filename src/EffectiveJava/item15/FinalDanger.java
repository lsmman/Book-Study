package EffectiveJava.item15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FinalDanger {
    public static final ArrayList<String> NAMES = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("NAMES = " + FinalDanger.NAMES);

        NAMES.add("아주 위험해");
        NAMES.add("가변 객체이기 때문에 얼마든지 추가 가능하다");

        System.out.println("NAMES = " + FinalDanger.NAMES);

        // ------------------------------
//        // 해결 방법 1
//        System.out.println("VALUES = " + FinalDanger.VALUES);
//        VALUES.add(2); // UnsupportedOperationException
//        System.out.println("VALUES = " + FinalDanger.VALUES);

//        // 해결 방법 2
//        Integer[] values = values();
//        System.out.println("VALUES = " + values[0] + " " + values[1]);
//        values[0] = 1; // 값은 수정 가능
//        System.out.println("VALUES = " + values[0] + " " + values[1]);


    }

    private static final Integer[] PRIVATE_VALUES = {12, 25};

    // 해결 방법 1
    public static final List<Integer> VALUES =
            Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));

    // 해결 방법 2
    public static final Integer[] values() {
        return PRIVATE_VALUES.clone();
    }
}
