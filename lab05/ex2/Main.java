package ex2;

public class Main {
    
    public static void main(String args[]){
        CakeMaster cakeMaster = new CakeMaster();

        CakeBuilder chocolate = new ChocolateCakeBuilder();
        cakeMaster.setCakeBuilder(chocolate);
        cakeMaster.createCake("Congratulations");
        Cake cake = cakeMaster.getCake();
        System.out.println("Your cake is ready: " + cake);

        CakeBuilder sponge = new SpongeCakeBuilder();
        cakeMaster.setCakeBuilder(sponge);
        cakeMaster.createCake(Shape.Square, 2, "Well done");
        cake = cakeMaster.getCake();
        System.out.println("Your cake is ready: " + cake);

        CakeBuilder yogurt = new YogurtCakeBuilder();
        cakeMaster.setCakeBuilder(yogurt);
        cakeMaster.createCake(3, "The best");
        cake = cakeMaster.getCake();
        System.out.println("Your cake is ready: " + cake);

        //add other examples of CakeBuilder
    }
    
}
