public class Cliente extends Observer {

    public Cliente(String nome) {
        super(nome);
    }

    public void update(Produto p, boolean novaLicitacao) {
        if (novaLicitacao) {
            System.out.println("[" + this + "] | " + p + " | " + p.getClienteAtual() + " licitou " + String.valueOf(p.getPreco()));
        } else {

            switch (p.getEstado_produto()) {
                case STOCK:
                    this.produtosLeilao.remove(p);
                    System.out.println("[" + this + "] O produto '" + p + "' não recebeu nenhuma licitação e deixou de ser leiloado.");
                    break;
                case LEILAO:
                    System.out.println("[" + this + "] O produto '" + p + "' vai ser leiloado. Preço base: " + p.getPreco());
                    this.produtosLeilao.add(p);
                    break;
                case VENDIDO:
                    this.produtosLeilao.remove(p);
                    System.out.println("[" + this + "] O produto '" + p + "' foi vendido ao cliente " + p.getClienteAtual() + " por " + p.getPreco() + "€.");
                    break;
            }
        }
    }

    public void licitar(Produto p, double preco) {
        if (produtosLeilao.contains(p)) {
            if (preco > p.getPreco()) {
                System.out.println("Nova licitação:");
                p.setClienteAtual(this);
                p.setPreco(preco);
            } else {
                System.out.println("[" + this + "] Têm de fazer uma licitação maior que " + p.getPreco() + "€ para prosseguir no leilão.");
            }
        } else {
            System.out.println("[" + this + "] O produto " + p + " não está disponível para leilão.");
        }
        System.out.println();
    }

    public void receberNotificacoes(Produto p) {
        p.attach(this);
    }

}
