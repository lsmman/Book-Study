package EffectiveJava.item15.practice.manager;

import EffectiveJava.item15.practice.team.Developer;
import EffectiveJava.item15.practice.team.Life;

public class ManagerDaily extends Life {
    public static void main(String[] args) {
        ManagerDaily managerDaily = new ManagerDaily();
        Developer developer = new Developer();

        managerDaily.eat();

        System.out.println("------\n개발 요청");

        developer.programming();
    }
}
