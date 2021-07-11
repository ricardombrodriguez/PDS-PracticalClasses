import java.util.ArrayList;

public class Gestor extends Observer {

    private ArrayList<Produto> produtosStock;
    private ArrayList<Produto> produtosVendidos; 

    public Gestor(String nome) {
        super(nome);
        this.produtosStock = new ArrayList<Produto>();
        this.produtosVendidos = new ArrayList<Produto>();
    }

    public void update(Produto p, boolean novaLicitacao) {
        if (novaLicitacao) {
            System.out.println("[MANAGER: " + this + "] | " + p + " | " + p.getClienteAtual() + " licitou " + String.valueOf(p.getPreco()) + "€.");
        } else {
            switch (p.getEstado_produto()) {
                case STOCK:
                    this.produtosLeilao.remove(p);
                    this.produtosStock.add(p);
                    System.out.println("[MANAGER " + this + "] O produto '" + p + " não recebeu nenhuma licitação e vai para o stock.");
                    System.out.println();
                    break;
                case LEILAO:
                    System.out.println("[MANAGER " + this + "] O produto '" + p + " vai ser leiloado. Preço base: " + p.getPreco() + "€.");
                    this.produtosStock.remove(p);
                    this.produtosLeilao.add(p);
                    break;
                case VENDIDO:
                    this.produtosLeilao.remove(p);
                    this.produtosVendidos.add(p);
                    System.out.println("E vai 1....");
                    System.out.println("E vai 2....");
                    System.out.println("E vai 3....");
                    System.out.println("[MANAGER: " + this + "] Vendido ao " + p.getClienteAtual() + " por " + String.valueOf(p.getPreco()) + "€.");
                    System.out.println();
                    break;
            }
        }
    
    }

    public void atualizarEstado(Produto p, Estado e) {
        p.setEstado_produto(e);
        System.out.println();
    }
}
