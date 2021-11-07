package EffectiveJava.item14;


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

        int compareResult = closetA.compareTo(closetB);
        System.out.println(compareResult);
    }
}
