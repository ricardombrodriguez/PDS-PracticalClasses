package ex2;

public class Cake {
 
    private Shape shape = Shape.Circular;
    private String cakeLayer;
    private int numCakeLayers = 1;
    private Cream midLayerCream;
    private Cream topLayerCream;
    private Topping topping;
    private String message;

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setCakeLayer(String cakeLayer) {
        this.cakeLayer = cakeLayer;
    }

    public void setNumCakeLayers(int numCakeLayers) {
        this.numCakeLayers = numCakeLayers;
    }

    public void setMidLayerCream(Cream midLayerCream) {
        this.midLayerCream = midLayerCream;
    }

    public void setTopLayerCream(Cream topLayerCream) {
        this.topLayerCream = topLayerCream;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
	public String toString() {
		return this.cakeLayer + " cake with " + this.numCakeLayers + " layers" + ((this.midLayerCream != null) ? " and " + this.midLayerCream + " cream" : "") + 
        ", topped with " + this.topLayerCream + " cream and " + this.topping + " Message says: '" + this.message + "'.";
	}

}