package EffectiveJava.item58;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CantUseForEach {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList(Arrays.asList("h", "e", "y"));
        for (String s: stringList){
            System.out.println(s);
        }

        // 1. 파괴적인 필터링(destructive filtering)
        System.out.println("#1");
        System.out.println("---forEach 중간에 탐색하는 요소를 remove 하게되면.. --");
//        for (String s: stringList){
//            System.out.println(s);
//            stringList.remove(s); // 에러를 불러온다 ConcurrentModificationException
//        }

        // -----------------
        // 2. 변형시
        System.out.println("#2");
        for (String s : stringList) {
            s = "Let's change";
        }

//        System.out.println(stringList);

    }
}
// stringList.removeIf(s1 -> s1.contains("e"));
