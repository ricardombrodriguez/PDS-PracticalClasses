package ex3;

public class Doce extends ProdutoAbstract {

    private String name;
    private Double weight;

    Doce(String name, int weight){
        this.name = name;
        this.weight = Double.valueOf(weight);
    }

    public void draw() {
        System.out.println(" ".repeat(Cabazes.ident) + "Doce '" + this.name + "' - Weight: " + this.weight);
    }

    public Double getTotalWeight() {
        return this.weight;
    }
}
