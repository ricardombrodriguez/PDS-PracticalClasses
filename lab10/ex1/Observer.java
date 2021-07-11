import java.util.ArrayList;

public abstract class Observer {

    protected String nome;
    protected ArrayList<Produto> produtosLeilao; 

    public abstract void update(Produto p, boolean novaLicitacao);

    public void receberNotificacoes(Produto p) {
        p.attach(this);
    }

    public Observer(String nome) {
        this.nome = nome;
        this.produtosLeilao = new ArrayList<Produto>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString() {
        return this.nome;
    }

    

}
