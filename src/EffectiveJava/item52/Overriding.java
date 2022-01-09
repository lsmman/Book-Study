package EffectiveJava.item52;

import EffectiveJava.item52.wines.Champagne;
import EffectiveJava.item52.wines.SparklingWine;
import EffectiveJava.item52.wines.Wine;

import java.util.List;

// 재정의된 메서드 호출 메커니즘 (313쪽, 코드 52-2의 일부)
public class Overriding {
    public static void main(String[] args) {
        List<Wine> wineList = List.of(
                new Wine(), new SparklingWine(), new Champagne());

        for (Wine wine : wineList)
            System.out.println(wine.name());
    }
}
