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
