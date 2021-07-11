package ex2;

public class YogurtCakeBuilder implements CakeBuilder {

    private Shape shape;
    private String message;
    private Cake cake;

    public void setCakeShape(Shape shape) {
        this.cake.setShape(shape);
    }

    public void addMessage(String m){
        this.cake.setMessage(m);
    }

    public void createCake() {
        this.cake = new Cake();
        this.cake.setTopping(Topping.Chocolate);
        this.cake.setTopLayerCream(Cream.Red_Berries);
        this.cake.setMidLayerCream(Cream.Vanilla);
        this.cake.setCakeLayer("Sponge");
    }

    public Cake getCake() {
        return this.cake;
    }
}
