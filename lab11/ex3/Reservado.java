// package lab11.ex3;

public class Reservado implements Estado {
    
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
        System.out.println( "Mudança de estado impossível - devolve" );
    }

    public void cancelaReserva(Livro L) {
        L.newState( new Disponivel() );;
    }

    public String toString() {
        return "[Reservado]";
    }
}
