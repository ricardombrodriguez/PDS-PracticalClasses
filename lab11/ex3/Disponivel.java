// package lab11.ex3;

public class Disponivel implements Estado {
    
    public void requisita(Livro L) {
        L.newState( new Emprestado() );
    }

    public void reserva(Livro L) {
        L.newState( new Reservado() );
    }

    public void regista(Livro L) {
        System.out.println( "Mudança de estado impossível - regista" );
    }

    public void devolve(Livro L) {
        System.out.println( "Mudança de estado impossível - devolve" );
    }

    public void cancelaReserva(Livro L) {
        System.out.println( "Mudança de estado impossível - cancela reserva" );
    }

    public String toString() {
        return "[Disponível]";
    }
}
