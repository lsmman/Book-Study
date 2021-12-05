package EffectiveJava.item33;

import java.util.*;

public class AnyTypeSet {
//    private Map<Class<? extends Object>, Set<? extends Object>> anyTypeSet = new HashMap<>();
//
//    public <T> void put(Class<T> type, T element){
//        if (!anyTypeSet.containsKey(type)){
//            anyTypeSet.put(type, new HashSet<>());
//        }
//
//        anyTypeSet.get(type)
//    }


}


/*
public class Favorites {
    // 코드 33-3 타입 안전 이종 컨테이너 패턴 - 구현 (200쪽)
    private Map<Class<?>, Object> favorites = new HashMap<>();

    public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(Objects.requireNonNull(type), instance);
    }

    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }

 */