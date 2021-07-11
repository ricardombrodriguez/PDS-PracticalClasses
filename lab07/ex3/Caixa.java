package ex3;

import java.util.ArrayList;

public class Caixa extends ProdutoAbstract {
    
    private String name;
    private Double weight;
    private ArrayList<ProdutoAbstract> produtos;

    Caixa(String name, int weight) {
        this.name = name;
        this.weight = Double.valueOf(weight);
        this.produtos = new ArrayList<ProdutoAbstract>();
    }

    void add(ProdutoAbstract produto) {
        this.produtos.add(produto);
    }

    public void draw() {
        System.out.println(" ".repeat(Cabazes.ident) + "* Caixa '" + this.name + "' [Weight: " + this.weight + " ; Total: " + this.getTotalWeight() + "]");
        Cabazes.ident += 3;
        for (ProdutoAbstract p : produtos) {
            p.draw();
        }
        Cabazes.ident += -3;
    }

    public Double getTotalWeight() {
        Double totalWeight = this.weight;
        for (ProdutoAbstract p : produtos) {
            totalWeight += p.getTotalWeight();
        }
        return totalWeight;
    }

}
