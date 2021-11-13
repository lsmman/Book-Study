package EffectiveJava.item15.packageA;

public class SamePackage {
    String var = "같은 패키지는 접근 가능"; // default 필드

    public String getVar() {
        return var;
    }

    public static void main(String[] args) {
        SamePackage sp = new SamePackage();
        System.out.println(sp.var);     // 같은 패키지는 허용
    }
}