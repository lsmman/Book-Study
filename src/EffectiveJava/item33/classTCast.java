package EffectiveJava.item33;


public class classTCast {
    public static void main(String[] args) {
        Integer num = 2;
        Object object;

        object = Object.class.cast(num);

        object = object.getClass().cast(num);

        object = (Object) num;

        Class<Object> objectClass = Object.class;
        object = objectClass.cast(num);


    }
}
