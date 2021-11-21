package EffectiveJava.item15.packageA;

public class PackageA {
    public static void main(String[] args) {
        // private
        SameClass sameClass = new SameClass();
//        sameClass.var // 접근 불가능

        // public
        EveryWhere everyWhere = new EveryWhere();
//        System.out.println(everyWhere.var); // 열려있는 퍼블릭

        // default
        SamePackage samePackage = new SamePackage();
        System.out.println(samePackage.var); // 같은 패키지니까 접근 가능

        // protected
        Protected aProtected = new Protected();
//        System.out.println(aProtected.var); // 같은 패키지니까 접근 가능
    }
}
