package EffectiveJava.item15;

/*
class Inner {
    static int[] helpArr = {1, 2, 3};
    static void getSum(int x) {
        System.out.println(x + helpArr[0] + helpArr[1] + helpArr[2]);
    }
}
 */

public class Outer {
    private int x = 10;

    // private static 중첩 클래스
    private static class Inner {
        private static int[] helpArr = {1, 2, 3};
        
        static void getSum(int x) {
            System.out.println(x + helpArr[0] + helpArr[1] + helpArr[2]);
        }
    }

    public void getSum(){
        Inner.getSum(x);
    }

    public static void main(String[] args) {
        new Outer().getSum();
    }
}