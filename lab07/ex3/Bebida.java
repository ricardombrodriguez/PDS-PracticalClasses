package ex3;

public class Bebida extends ProdutoAbstract {
    
    private String name;
    private Double weight;

    Bebida(String name, int weight){
        this.name = name;
        this.weight = Double.valueOf(weight);
    }

    public void draw() {
        System.out.println(" ".repeat(Cabazes.ident) + "Bebida '" + this.name + "' - Weight: " + this.weight);
    }

    public Double getTotalWeight() {
        return this.weight;
    }


}
