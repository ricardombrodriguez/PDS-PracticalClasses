package ex3;

public class Conserva extends ProdutoAbstract {
 
    private String name;
    private Double weight;

    Conserva(String name, int weight){
        this.name = name;
        this.weight = Double.valueOf(weight);
    }

    public void draw() {
        System.out.println(" ".repeat(Cabazes.ident) + "Conserva '" + this.name + "' - Weight: " + this.weight);
    }

    public Double getTotalWeight() {
        return this.weight;
    }

}
