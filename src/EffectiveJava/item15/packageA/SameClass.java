package EffectiveJava.item15.packageA;

public class SameClass {
    private String var = "같은 클래스만 허용"; // private 필드
    private String getVar() {                  // private 메소드
        return this.var;
    }
}