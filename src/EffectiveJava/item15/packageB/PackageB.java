package EffectiveJava.item15.packageB;

import EffectiveJava.item15.packageA.EveryWhere;
import EffectiveJava.item15.packageA.Protected;
import EffectiveJava.item15.packageA.SamePackage;

public class PackageB {
    // 다른 패키지
    public static void main(String[] args) {

        // default
        SamePackage samePackage = new SamePackage();
//        System.out.println(samePackage.var); // 다른 패키지니까 접근 불가능

        // protected
        Protected aProtected = new Protected();
//        System.out.println(aProtected.var); // 그냥 다른 패키지니까 접근 불가능

        // 상속받은 protected
        ExtendsProtected extendsProtected = new ExtendsProtected();
//        System.out.println(extendsProtected.getVar()); // 다른 패키지여도 상속 받은 protected니까 가능

        // public
        EveryWhere everyWhere = new EveryWhere();
//        System.out.println(everyWhere.var); // 열려있는 퍼블릭
    }
}
