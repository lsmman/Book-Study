# Item 14. Comparable을 구현할 지 고려하라

## ❓ 어디에 쓰는 걸까?

**"객체를 비교할 수 있도록 만든다."**

- Comparable interface는 클래스 인스턴스 간 상대적인 비교를 위한 method compareTo를 가지고 있다.
- 클래스 동작  중 정렬이 필요할 경우
- Comparable을 구현했다는 건 그 클래스의 인스턴스들에는 자연적인 순서가 있음을 뜻한다.
    - Integer 정수 타입의 크고 작음
    - String 타입 ASCII의 크고 작음
- Object에 속한 메소드는 아니다.
- 제네릭 타입을 지원한다.

```java
public interface Comparable<T> {
    public int compareTo(T o);
}
```

- Comparable을 구현한 객체들의 배열은 sort를 통해 정렬할 수 있다.
    - Element 간의 비교에 compareTo 메소드를 사용한다.

```java
Arrays.sort()
```

- 다음 WordList 예제에서 TreeSet 이 알파벳 순으로 출력할 수 있는 이유

    ```java
    public class WordList {
        public static void main(String[] args) {
            Set<String> s = new TreeSet<>();
    //        Collections.addAll(s, );
            s.add("hi");
            s.add("abc");
            s.add("zzz");
            System.out.println(s);
        }
    }
    ```

    - TreeSet은 각 Element를 정렬된 형태인 Tree로 관리하기 때문에, 내부적으로 `SortedSet` 으로 관리한다.

    ```java
    // TreeSet의 생성자
    public TreeSet(Collection<? extends E> c) {
        this();
        addAll(c);
    }
    
    public  boolean addAll(Collection<? extends E> c) {
            // Use linear-time version if applicable
            if (m.size()==0 && c.size() > 0 &&
                c instanceof SortedSet &&
                m instanceof TreeMap) {
                SortedSet<? extends E> set = (SortedSet<? extends E>) c;
                TreeMap<E,Object> map = (TreeMap<E, Object>) m;
                Comparator<?> cc = set.comparator();
                Comparator<? super E> mc = map.comparator();
                if (cc==mc || (cc != null && cc.equals(mc))) {
                    map.addAllForTreeSet(set, PRESENT);
                    return true;
                }
            }
            return super.addAll(c);
        }
    ```


```java
public class WordList {
    public static void main(String[] args) {
        Set<String> s = new TreeSet<>();
        Collections.addAll(s, args);
        System.out.println(s); // 정렬된 형태로 출력된다.
    }
}
```

---

## 💼 Comparable을 구현 할 때

### 🧵 compareTo 메서드를 구현할 때 지켜야 할 것들

1. 주어진 객체가 null이면 `NullPointerException`을,  비교할 수 없는 타입의 객체가 주어지면 `ClassCastException` 를  출력한다.
2. `x.compareTo(y)` 의 반환 값은 다음 설명에 따라야 한다.
    - x < y 이면 -1
    - x = y 이면 0
    - x > y 이면 1
3. 반사성
    - `x.compareTo(x)` 의 결과가  `0`이다.
4. 대칭성
    - `x.compareTo(y)` 의 부호는 `y.compareTo(x)` 와 반대여야 한다.
5. 추이성
    - `x > y`이고 `y > z` 이면 `x > z` 이다.
    - `x.compareTo(y) > 0` 이고 `y.compareTo(z) > 0` 이면 `x.compareTo(z)` 도 0보다 크다.
6. equals 와 compareTo (Optional)
    - compareTo로 비교하였을 때 두 객체가 같다면 `x.equals(y)`도 True
    - `x.compareTo(y) == 0` 이 `x.equals(y)` 와 같아야 한다.
    - 만약, 6번 조건이 지켜지지 않는 다면, Collection으로 구현된 자료구조 클래스들이 예상하던 동작과 다르게 흘러갈 수 있다.

### 📌 관계 연산자 `<` 와 `>`  대신 타입의 정적 메소드 compare를 이용하라

```java
public final class CaseInsensitiveString
        implements Comparable<CaseInsensitiveString> {
    private final String s;

    // 자바가 제공하는 비교자를 사용해 클래스를 비교한다.
    public int compareTo(CaseInsensitiveString cis) {
        return String.compare(s, cis.s);
        // return String.CASE_INSENSITIVE_ORDER.compare(s, cis.s);
    }

    public static void main(String[] args) {
        Set<CaseInsensitiveString> s = new TreeSet<>();
        for (String arg : args)
            s.add(new CaseInsensitiveString(arg));
        System.out.println(s);
    }

    /*
    public int compareTo(PhoneNumber pn){
        // 이렇게 구현 하지 말아라
        if (areaCode < pn.areaCode) return -1;
        elif (areaCode == pn.areaCode) return 0;
        return 1;
    }
     */
}
```

- 박싱된 기본 타입의 정적 메소드 compare
    - int 형 : `Integer.compare`
    - short 형 : `Short.compare`
    - Double 형: `Double.compare`

### 📌 비교할 타입 필드가 여러 개 일 때

```java
// 코드 14-2 기본 타입 필드가 여럿일 때의 비교자
short areaCode, prefix, lineNum;

public int compareTo(PhoneNumber pn) {
    int result = Short.compare(areaCode, pn.areaCode); // 제일 우선적으로 비교
    if (result == 0)  {
        result = Short.compare(prefix, pn.prefix);
        if (result == 0)
            result = Short.compare(lineNum, pn.lineNum); // 후 순위
    }
    return result;
}

```

### 📌 Comparable이 구현된 기존 클래스를 확장할 때

[일급 컬렉션](https://jojoldu.tistory.com/412)을 만들 때 Comparble을 구현하기

```java
public class Closet implements Comparable<Closet>{

    private String clothes;

    public Closet(String clothes) {
        this.clothes = clothes;
    }

    public String getClothes() {
        return clothes;
    }

    @Override
    public int compareTo(Closet o) {
        return this.clothes.compareTo(o.getClothes());
    }

    public static void main(String[] args) {
        Closet closetA = new Closet("모자");
        Closet closetB = new Closet("코트");

        int compareResult = closetA.compareTo(closetB); // 만든 객체도 비교 가능!
        System.out.println(compareResult);
    }
}
```

### 📌 Comparator 사용하기

Comparator는 비교할 두 개의 객체를 파라미터로 받는 `compare` 를 가지고 있다.

- Comparable은 **자기 자신과 매개변수 객체를 비교**한다.
- Comparator는 **두 매개변수 객체를 비교**한다.
- 주로 참조형 타입에 적용

```java
public interface Comparator<T> {

    int compare(T o1, T o2);
...
}
```

```java
public final class PhoneNumber implements Comparable<PhoneNumber> {
    private final short areaCode, prefix, lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = areaCode
        this.prefix   = prefix
        this.lineNum  = lineNum
    }

    // 람다 방식의 비교자 생성 메서드를 활용한 비교자 (92쪽)
    // areaCode, prefix, lineNum 순으로 비교!

    private static final Comparator<PhoneNumber> COMPARATOR =
                 comparingInt((PhoneNumber pn) -> pn.areaCode) //첫번째
                    .thenComparingInt(pn -> pn.prefix)
                    .thenComparingInt(pn -> pn.lineNum);

    public int compareTo(PhoneNumber pn) {
        return COMPARATOR.compare(this, pn);
    }

    private static PhoneNumber randomPhoneNumber() {
				short cur = (short) ThreadLocalRandom.current().nextInt(1000);
        return new PhoneNumber(cur, cur, cur);
    }

    public static void main(String[] args) {
        NavigableSet<PhoneNumber> s = new TreeSet<PhoneNumber>();
        for (int i = 0; i < 10; i++)
            s.add(randomPhoneNumber());
        System.out.println(s);
    }
}
```

### 📌 빼기를 기준으로 하는 compareTo는 사용하지 마라

- `1, 0, -1` 이 결과로 나와야 한다.
- **잘못된 사례**

```java
static Comparator<Object> hashCodeOrder = new Comparator<>(){
    public int compare(Object o1, Object o2){
        return o1.hashCode() - o2.hashCode();
    }
}
```

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/88893d85-0a29-4c23-9409-7aa13fdda2a9/Untitled.png)

- Better example 1 - **타입의 정적 메소드 활용**

```java
static Comparator<Object> hashCodeOrder = new Comparator<>(){
    public int compare(Object o1, Object o2){
        return Integer.compare(o1.hashCode(), o2.hashCode());
    }
}
```

- Better example 2 - **Comparator 사용**

```java
static Comparator<Object> hashCodeOrder = Comparator
    .comparingInt(o -> o.hashCode());
}
```

---

## Reference

---

- [https://st-lab.tistory.com/243](https://st-lab.tistory.com/243)