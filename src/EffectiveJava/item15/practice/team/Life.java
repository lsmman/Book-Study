package EffectiveJava.item15.practice.team;

public class Life {
    protected void eat(){
        cook();
        System.out.println("먹기");
    }
    private void cook(){
        System.out.println("간단한 요리");

    }

    public static void main(String[] args) {
        Developer.Plan plan = new Developer.Plan();

    }
}
