package ex2;

public class ChocolateCakeBuilder implements CakeBuilder{
    
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
        this.cake.setTopping(Topping.Fruit);
        this.cake.setTopLayerCream(Cream.Whipped_Cream);
        this.cake.setCakeLayer("Soft chocolate");
    }

    public Cake getCake() {
        return this.cake;
    }
    
}
