package EffectiveJava.item15.practice.team;

import java.math.BigInteger;

/*
클래스 어디에서 class 적어 JVM -> static

class inst

 */
public class Developer {
    int x = 10;
    static int staticX = 11;

    public void programming(){
        Plan.makeCodingPlan();
        System.out.println("코드 구현");
    }


    public static class Plan {
         static void makeCodingPlan(){
            System.out.println("프로그래밍 계획 세우기");
        }
    }


    public static void main(String[] args) {
        Developer developer = new Developer();
        System.out.println(developer.x);

//        Developer.Plan
    }
}
