# 아이템 9: Try with resources를 사용하라

- Java 8 이상 환경에서 실행 가능한 예제입니다.
- 다루는 내용: 
    - Try with resources 용법과 그 장점
    - 두 개 이상 자원을 관리하는 Try with resources를 사용할 때, 두 개 이상의 예외를 표기해주는 Throwable의 getSuppressed 

----

## Try finally: 개선이 필요한 형태

- Try finally는 주로 명시적으로 연결을 `close` 해줘야하는 경우 사용한다. 
- 예를 들면, InputStream이나 OutputStream처럼 닫히지 않고 연결이 유지 되고 있다면 컴퓨팅 자원이 계속 소모되는 경우가 그렇다.
- 심지어 아래의 예제처럼 두 개 이상의 자원이 사용되는 경우 try-finally 구문을 중첩(Nested)하여 사용하게 된다.
- **Try-finally는 개발자가 연결의 close를 선언해줘야할 뿐만 아니라 중첩된 경우 가독성도 상당히 떨어진다.**

```java
public class Copy {
    private static final int BUFFER_SIZE = 8 * 1024;
    
    static void copy(String src, String dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                byte[] buf = new byte[BUFFER_SIZE];
                int n;
                while ((n = in.read(buf)) >= 0)
                    out.write(buf, 0, n);
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

```

## Try with resources

- try 절에 try (resources) 형태로 **AutoCloseable을 구현한 클래스 인스턴스**를 선언하게 되면 변수가 미치는 단락이 종료되는 시점에 **AutoCloseable 인터페이스의 close() 메서드를 자동으로 호출**한다.
- 그렇기 때문에 이전처럼 finally 구문에서 별도로 자원에 대한 해제를 하지 않아도 된다.

위의 예시코드를 try-with-resources 구문으로 바꿔보았다. 위의 코드보다는 한층 깔끔해진다.

```java

public class Copy {
    private static final int BUFFER_SIZE = 8 * 1024;

    static void copy(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src); // try-with-resources를 사용하는 부분!
             OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0)
                out.write(buf, 0, n);
        }
    }
```


### 장점

**try-with-resources의 장점**

1. 자원 관리가 수월해진다.
    - AutoCloseable의 close() 메소드를 자동으로 호출해 별도로 자원 해제를 하지 않아도 된다. (finally를 생략 가능하다)
2. 2개 이상의 자원을 사용할 시에 가독성이 향상된다.

<br/>

### AutoCloseable

- try-with-resources 구문이 추가 되었고, AutoCloseable 인터페이스가 추가되었다. 이 인터페이스가 나오고 부터 Java의 close를 지원하는 클래스에서 AutoCloseable을 구현하도록 변경되었다.
- AutoCloseable 인터페이스를 implement하게 되면 Exception을 던지는 close() 메소드를 필수적으로 구현 해야한다.

```java
package java.lang;

public interface AutoCloseable {
    void close() throws Exception;
}
```

- AutoCloseable을 구현한 예제이다.

```java
package EffectiveJava.item9;


public class CustomAutoCloseable implements AutoCloseable {

    public void doSomething(){
        System.out.println("Do something ...");
    }

    @Override
    public void close() throws Exception {
        System.out.println("CustomResource Closed!");
    }

    public static void main(String[] args) {
        try (CustomAutoCloseable customResource = new CustomAutoCloseable()){
            customResource.doSomething();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

// try-with-resources 구문이 끝나면서 close 메소드가 실행된다.
// Do something ...
// CustomResource Closed!

```


## Exception의 getSuppressed 사용하기

- Try-catch-with-resources 구문에서 2가지 이상의 자원을 `try(/* 자원 선언부 */){}` 자원 선언부 자리에서 사용하여 서로 다른 2가지 이상의 에러를 일으킬 가능성이 있을 경우에 사용한다.
- 중심이 되는 Exception 안에 Suppressed된 다른 종류의 예외를 담고 있는 형태로 이루어져 있다.

### Suppressed Exception
- 두 예외가 동시에 발생할 수는 없기 때문에 close()에서 발생되는 예외는 **억제된(Suppressed) 예외로 처리**되어 실제 발생한 예외(try-catch문에서 발생한 예외)에 저장된다.
- Throwable에는 다음과 같이 억제된 예외와 관련된 메서드가 정의되어 있다.

```java
void addSuppressed(Throwwable exception) 
Throwable[] getSuppressed()
     addSuppressed() // 억제된 예외를 추가
     getSuppressed() // 억제된 예외(배열)를 반환
```

- 다음으로 Suppressed된 raiseCustomMultiException을 일으키는 예제이다.

```java

/**
 * 숨겨진 에러를 만들 수 있는 suppressed를 사용하여 CustomMultipleException을 만들어보자
 * 두 가지 이상 자원에 try-catch-with-resources를 사용할 때 숨겨진 에러를 찾을 수 있다.
 */
public class ThrowableGetSuppressedExample {

    public static void main(String[] args) {
        try {
            raiseCustomMultiException();
        } catch (Exception e) {
            e.printStackTrace();
            Throwable[] suppExe = e.getSuppressed(); // getSuppressed로 숨겨진 예외를 가져온다.

            // print element of suppExe
            for (int i = 0; i < suppExe.length; i++) {

                System.out.println("Suppressed Exceptions:");
                System.out.println(suppExe[i]);
            }
        }
    }


    /**
     * @throws CustomMultiException
     * IOException 속의 숨겨진(suppressed) NumberFormatException이 존재하는 형태
     */
    public static void raiseCustomMultiException() throws Exception {

        // creating a suppressed Exception
        Exception suppressed = new NumberFormatException();

        // creating a IOException object
        final IOException ioe = new IOException();

        // adding suppressed Exception
        ioe.addSuppressed(suppressed); // 숨겨진 형태로 예외 추가

        // throwing IOException
        throw ioe;
    }
}

```

- 실행된 결과
    
```java
java.io.IOException
	at EffectiveJava.item9.try_catch_with_resources.raiseCustomMultiException(try_catch_with_resources.java:33)
	at EffectiveJava.item9.try_catch_with_resources.main(try_catch_with_resources.java:13)
	Suppressed: java.lang.NumberFormatException
		at EffectiveJava.item9.try_catch_with_resources.raiseCustomMultiException(try_catch_with_resources.java:30)
		... 1 more
Suppressed Exceptions:
java.lang.NumberFormatException

Process finished with exit code 0
```

----

## Reference
- [AutoCloseable official Docs](https://docs.oracle.com/javase/8/docs/api/java/lang/AutoCloseable.html)
- [Throwable offical Docs](https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html)
- [Getsuppressed example](https://www.geeksforgeeks.org/throwable-getsuppressed-method-in-java-with-examples/)


