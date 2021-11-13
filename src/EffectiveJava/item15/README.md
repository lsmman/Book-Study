
# 🗨️ Item 15. 클래스와 멤버의 접근 권한을 최소화하라

<br/>

어설프게 설계된 컴포넌트와 잘 설계된 컴포넌트의 가장 큰 차이는 

  ➡️ 바로 클래스 내부 데이터와 구현을 **외부 컴포넌트로부터 얼마나 잘 숨겼느냐**

  ➡️ 일 잘하는 사람들은 필요한 정보를 골라서 딱 전달한다. → 필요한 정보만 알려라.

정보 은닉 (=캡슐화) 이라는 이 개념은 소프트웨어의 근간이 되는 원리이다.

<br/>

## ❓ 정보를 적절하게 숨기면 뭐가 좋을까?

1. 서로 알고 있어야 하는 부분을 줄여 서로 수정에 개입할 여지를 줄인다.
2. 그렇게 컴포넌트들을 독립적으로 만들어, 개발, 테스트, 최적화, 수정 들을 개별적으로 수행할 수 있게 한다.

👍 **정보 숨기기 (= 캡슐화) 의 이점**

- `스피드 ↑` 각 컴포넌트를 병렬적으로 개발할 수 있기 때문에, 시스템 개발 속도 빠름
- `유지보수 비용 ↓` 컴포넌트 사이즈가 작아 디버깅 빠름. 교체 수월. → 시스템 관리 비용 줄음.
- `코드 최적화` 완성된 시스템에서 최적화할 컴포넌트만 정해 수월하게 최적화 가능
- `재사용성 ↑` 독자적으로 동작하는 컴포넌트라면, 다른 상황에서도 유용하게 쓰일 가능성 높음
- `구현 난이도 ↓` 시스템 전체가 완성되지 않은 상황에서도 개별 컴포넌트의 동작을 검증 가능

<br/>

## 🛠️ Java에서 제공하는 장치

- 클래스
- 인터페이스

```java
// 보통 외부에서도 타입형으로 사용하기 때문에 public interface로 많이 사용한다.
// 반드시 구현 해야하는 public method들을 선언
public interface MainlyPublic {

    public void mustImplement();
}
```

- 멤버의 접근성 제어
    - 선언된 위치 (Local, Global)
    - 접근 제어자 (private, protected, default, public)
    - 접근 제어자에 대해 자세히 알고 싶다면 [링크](src/EffectiveJava/item15/접근제어자.md)
---

<br/>

## 📌 적용해보기

핵심 원칙: **모든 클래스와 멤버의 접근성을 가능한 한 좁혀야 한다.**

1. 클래스의 공개 API를 세심하게 설계하자. 그 외의 모든 멤버는 private으로 만들자.
2. 그 후 같은 패키지의 다른 클래스가 접근해야 하는 멤버들은 package-private로 풀어주자.
3. 상속된 클래스에서 접근하는 멤버들은 protected로 바꿔주자

### 📝 캡슐화 구현 팁

내부와 외부에서 접근이 필요한 톱레벨 클래스에 부여할 수 있는 접근 수준은 2가지다.

- package-private (=default)
- public

1. **[Public ❌] 패키지 외부에서 쓸 이유가 없다면 package-private으로 선언하자**
    - 그럼 오픈된 API가 아니라 내부적으로만 사용 가능해 수정이 자유롭다.
    - 반면, public 으로 선언한다면 오픈된 API가 되어 영원히 관리해줘야 한다.
        - 동작 방식을 API에 적어 사용자에게 공개해야 할 수도 있다.

2. **한 곳에서만 사용하는 package-private 클래스는 private static 으로 중첩시키자**
    - private static 중첩 클래스
    
    ```java
    /* 한 곳에서만 사용하는 package-private class: Inner
     *
    class Inner {
        static int[] helpArr = {1, 2, 3};
        static void getSum(int x) {
            System.out.println(x + helpArr[0] + helpArr[1] + helpArr[2]);
        }
    }
     */
    
    public class Outer {
        private int x = 10;
    
        public void getSum(){
            Inner.getSum(x);
        }
    
        // private static 중첩 클래스
        private static class Inner {
            private static int[] helpArr = {1, 2, 3};
    
            static void getSum(int x) {
                System.out.println(x + helpArr[0] + helpArr[1] + helpArr[2]);
            }
        }   
    }
    ```
    

3. **Public 클래스의 인스턴스 필드는 되도록 public이 아니어야 한다. (아이템 16)**
    - 필드가 가변 객체를 참조하거나, final이 아닌 인스턴스 필드를 public으로 선언하면 값을 제한할 수 없게 된다.
        - 가변 객체를 갖는 클래스는 스레드 사용시 안전하지 않다.
        - public final이면서 불변 객체를 참조하게 만들더라도 내부 구현을 바꾸기 힘든 문제가 여전히 존재한다.
        
4. **해당 클래스에 꼭 필요한 추상 개념을 담은 상수라면 `public static final` 사용해라**
    - 상수의 이름은 대문자 알파벳을 쓰고, `_` 를 단어 사이에 넣는다.
    - 반드시 기본 타입 값이나 불변 객체를 참조해야 한다.
        
        ```java
        public class User {
            public static final int USERNAME_LENGTH = 8;
        }
        
        public class UserTest {
            public static void main(String[] args){
                static final int LENGTH = User.USERNAME_LENGTH;
            }
        }
        ```
        

5. 정말 필요하다면 모듈화를 사용해라
    - 자신에 속하는 패키지 중 공개할 것들을 선언하게 된다.
        - export 요소는 `module-info.java` 파일에 보통 선언한다.
    - `public`이라도 패키지에서 공개하지 않으면 접근 할 수 없다.
    - 모듈 궁금하다면 [링크](https://xlffm3.github.io/java/chapter14-module-system/)

<br/>

### ⚡ 캡슐화 주의 사항

- [Serializable](https://nesoy.github.io/articles/2018-04/Java-Serialize)을 구현한 클래스에서는 공개하지 않은 필드들도 의도치 않게 공개 API가 될 수 있다.

- 상속했을 때는 상위 클래스의 접근 범위를 고려해야 한다.
    - 상위 클래스에서보다 하위 클래스에서 접근성을 좁게 설정할 수 없다. (리스코프 치환 원칙)
    - 접근성을 좁게 설정하고 싶다면 상속보다는 컴포지션 (아이템 18)
    
- 코드를 테스트하기 위해 public으로 설정하지 마라.
    - 테스트를 위해 private을 package-private으로 풀어주는 것은 허용되지만 그 이상은 안된다.
    - 테스트 코드를 같은 패키지에 두고 package-private 요소를 테스트하라
    
- Final을 사용하여도 길이가 0이 아닌 배열은 모두 변경 가능하므로 접근자 메소드를 제공해서는 안된다.
    
    ```java
    public class FinalDanger {
        public static final ArrayList<String> NAMES = new ArrayList<>();
    
        public static void main(String[] args) {
            System.out.println("NAMES = " + FinalDanger.NAMES);
    
            NAMES.add("아주 위험해");
            NAMES.add("가변 객체이기 때문에 얼마든지 추가 가능하다");
    
            System.out.println("NAMES = " + FinalDanger.NAMES);
        }
    }
    ```
    
    - 해결책은 2가지다.
        - public 불변 리스트로 변환하는 것
        - 복사본을 반환하는 public 메소드를 추가하는 방법
            
            (방어적 복사 = 새 인스턴스에 내용만 복사)
            
    
    ```java
        private static final Integer[] PRIVATE_VALUES = {12, 25};
    
        // 해결 방법 1: 불변 리스트로 변환
        public static final List<Integer> VALUES =
                Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES)); 
    
        // 해결 방법 2: 방어적 복사
        public static final Integer[] values() {
            return PRIVATE_VALUES.clone();
    ```
    

## ✔️ 참고

- [Java] 불변 객체(Immutable Object) 및 final을 사용해야 하는 이유: [https://mangkyu.tistory.com/131](https://mangkyu.tistory.com/131)
- [Java] 접근 제어자 (private, protected, default, public):  [http://www.tcpschool.com/java/java_modifier_accessModifier](http://www.tcpschool.com/java/java_modifier_accessModifier)
- 직렬화: [https://nesoy.github.io/articles/2018-04/Java-Serialize](https://nesoy.github.io/articles/2018-04/Java-Serialize)
- 자바 모듈 시스템 : [https://xlffm3.github.io/java/chapter14-module-system/](https://xlffm3.github.io/java/chapter14-module-system/)
