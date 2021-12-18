package EffectiveJava.item43;

interface G1 {
    <E extends Exception> Object m() throws E;
}

interface G2 {
    <F extends Exception> String m() throws Exception;
}


interface G extends G1, G2 {
}

class Main {
    public static void main(String[] args) {
        // 이 때 함수형 인터페이스 G를 함수 타입으로 표현하면 다음과 같다.
        // <F extends Exception> () -> String throws F // 존재하지 않는 문법
    }
}
