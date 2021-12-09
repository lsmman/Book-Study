package EffectiveJava.item35;

// 인스턴스 필드에 정수 데이터를 저장하는 열거 타입 (222쪽)
public enum Ensemble {
    SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5),
    SEXTET(6), SEPTET(7), OCTET(8), DOUBLE_QUARTET(8),
    NONET(9), DECTET(10), TRIPLE_QUARTET(12);

    private final int numberOfMusicians;

    Ensemble(int size) {
        this.numberOfMusicians = size;
    }

    public int numberOfMusicians() {
        return numberOfMusicians;
    }
//
//enum Ensemble {
//        SOLO, DUET, TRIO, QUARTET, QUINTET,
//        SEXTET, SEPTET, OCTET, NONET, DECTET;

    public static void main(String[] args) {
//        [SOLO, DUET, TRIO, QUARTET, QUINTET, SEXTET, SEPTET, OCTET, NONET, DECTET] -> SOLO는 0번
        System.out.println(Ensemble.SOLO.ordinal()); // 0
//        System.out.println(Ensemble.SOLO.numberOfMusicians());
    }
}
