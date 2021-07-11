// package lab11.ex3;

public interface Estado {
    public void regista(Livro L);
    public void requisita(Livro L);
    public void devolve(Livro L);
    public void reserva(Livro L);
    public void cancelaReserva(Livro L);
}
