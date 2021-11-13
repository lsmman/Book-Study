package EffectiveJava.item15.packageB;

import EffectiveJava.item15.packageA.Protected;

public class ExtendsProtected extends Protected {

    public static void main(String[] args) {
        ExtendsProtected extendsProtected = new ExtendsProtected();

        System.out.println(extendsProtected.var); // 상속받은 클래스에서 protected 접근 가능
    }

    public String getVar(){
        return this.var;
    }
}
