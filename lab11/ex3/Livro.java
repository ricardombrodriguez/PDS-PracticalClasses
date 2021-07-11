// package lab11.ex3;

public class Livro {
    private String title;
    private String author;
    private Estado estado;

    public Livro(String title, String author) {
        this.title = title;
        this.author = author;
        this.newState( new Inventario() );
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getEstado() {
        return this.estado.toString();
    }

    public void newState(Estado e) {
        this.estado = e;
    }

    public void requesita() {
        this.estado.requisita(this);
    }

    public void reserva() {
        this.estado.reserva(this);
    }

    public void regista() {
        this.estado.regista(this);
    }

    public void devolve() {
        this.estado.devolve(this);
    }

    public void cancelaReserva() {
        this.estado.cancelaReserva(this);
    }

}
