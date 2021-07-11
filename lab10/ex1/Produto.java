import java.util.ArrayList;

public class Produto {

    private static int counter = 1;

    private int codigo;
    private String descricao;
    private Double preco;
    private Estado estado_produto;
    private Cliente clienteAtual;
    private ArrayList<Observer> observadores;

    //O código do produto deve ser gerado automaticamente e é suposto começar no Estado -> STOCK (podendo depois mudar de estado)
    public Produto(String descricao, Double preco_base) {
        this.codigo = counter++;
        this.descricao = descricao;
        this.preco = preco_base;
        this.estado_produto = Estado.STOCK;
        this.clienteAtual = null;
        this.observadores = new ArrayList<Observer>();
    }

    public void attach(Observer ob) {
        this.observadores.add(ob);
    }

    public void notifyObservers(boolean novaLicitacao) {
        for (Observer ob : observadores) {
            ob.update(this,novaLicitacao);
        }
    }

    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    public void setClienteAtual(Cliente clienteAtual) {
        this.clienteAtual = clienteAtual;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return this.preco;
    }

    //sempre que o preço é alterado, é sinal que houve uma nova licitação
    public void setPreco(Double preco) {
        this.preco = preco;
        this.notifyObservers(true);
    }

    public Estado getEstado_produto() {
        return estado_produto;
    }

    //sempre que o estado é alterado, é necessário informar os observadores dessa alteração
    public void setEstado_produto(Estado estado_produto) {
        this.estado_produto = estado_produto;
        this.notifyObservers(false);
    }

    @Override
    public String toString() {
        return "[" + codigo + "] " + descricao;
    }

    

}