package EffectiveJava.item58;

import java.util.*;

public class IteratorExample {
    public static void main(String[] args) {
        A objectA = new A();
        B objectB = new B();

        for (Integer integer : objectA) {
            // ..
            System.out.println(integer);
        }
    }
}

// Iterable의 iterator 메소드를 구현한다.
class A implements Iterable<Integer>{
    List<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

    @Override
    public Iterator<Integer> iterator() {
        return a.iterator();
    }
}

class B {
    List<Integer> b = new ArrayList<>(Arrays.asList(5, 6, 7, 8));
}


//    public Iterator iterator() {
//        return b.iterator();
//    }
//}


// Iterator를 implement 구현하면 foreach를 사용할 수 있다.
//interface Iterator<E> {
//    boolean hasNext();
//    E next();
//    default void forEachRemaining(Consumer<? super E> action) {
//        Objects.requireNonNull(action);
//        while (hasNext())
//            action.accept(next());
//    }
//}
