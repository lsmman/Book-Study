package EffectiveJava.item37;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Plant {
    enum LifeCycle { ANNUAL, PERNNIAL, BIENNIAL}

    final String name;
    final LifeCycle lifeCycle;

    public Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void usingEnumMap(List<Plant> garden) {
        Map<LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(LifeCycle.class);

        for (LifeCycle lifeCycle : LifeCycle.values()) {
            plantsByLifeCycle.put(lifeCycle,new HashSet<>());
        }

        for (Plant plant : garden) {
            plantsByLifeCycle.get(plant.lifeCycle).add(plant);
        }

        //EnumMap은 toString을 재정의하였다.
        System.out.println(plantsByLifeCycle);
    }

    public static void streamV1(List<Plant> garden) {
        Map plantsByLifeCycle = garden.stream().collect(Collectors.groupingBy(plant -> plant.lifeCycle));
        System.out.println(plantsByLifeCycle);
    }

    // 첫번째 매개인자 Function으로 스트림의 각 요소를 반환한 결과를 리턴하는 객체,
    // 두번째 매개인자는 리턴되는 객체를 Map으로 지정하고
    // 세번째 매개인자는 Map을 어떻게 유지할지 결정한다.
    public static void streamV2(List<Plant> garden) {
        Map plantsByLifeCycle = garden.stream().collect(Collectors.groupingBy(plant -> plant.lifeCycle,
                () -> new EnumMap<>(LifeCycle.class),Collectors.toSet()));
        System.out.println(plantsByLifeCycle);
    }

    public static void main(String[] args) {
        List<Plant> list = new ArrayList<>();

        Plant plant = new Plant("재근", LifeCycle.ANNUAL);
        Plant plant2 = new Plant("재근2", LifeCycle.PERNNIAL);

        list.add(plant);
        list.add(plant2);

        usingEnumMap(list);
        streamV1(list);
        streamV2(list);
    }
}