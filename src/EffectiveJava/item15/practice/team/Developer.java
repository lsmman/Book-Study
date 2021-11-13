package EffectiveJava.item15.practice.team;

public class Developer {
    public final Plan plan = new Plan();

    public void programming(){
        plan.makeCodingPlan();
        System.out.println("코드 구현");
    }
}
