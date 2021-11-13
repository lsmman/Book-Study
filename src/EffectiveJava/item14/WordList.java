package EffectiveJava.item14;

import java.util.*;

// Comparable 구현 시의 이점 (87쪽)
public class WordList {
    public static void main(String[] args) {
        Set<String> s = new TreeSet<>();
        // Collections.addAll(s, );
        s.add("hi");
        s.add("abc");
        s.add("zzz");
        s.add("hey");
        System.out.println(s);
    }
}
