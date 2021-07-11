// package lab11.ex3;

public class Emprestado implements Estado {
    
    public void requisita(Livro L) {
        System.out.println( "Mudança de estado impossível - requisita" );
    }

    public void reserva(Livro L) {
        System.out.println( "Mudança de estado impossível - reserva" );
    }

    public void regista(Livro L) {
        System.out.println( "Mudança de estado impossível - regista" );
    }

    public void devolve(Livro L) {
        L.newState( new Disponivel() );
    }

    public void cancelaReserva(Livro L) {
        System.out.println( "Mudança de estado impossível - cancela reserva" );
    }

    public String toString() {
        return "[Requisitado]";
    }
}
