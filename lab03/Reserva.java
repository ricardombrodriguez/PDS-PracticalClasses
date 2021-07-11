package lab03;

public class Reserva {

    private char classe;
    private int num_pass;
    private String codigo_reserva;
    private int numReserva;

    // cosntrutor
    public Reserva(String codigo_Voo, char classe, int num_pass, int numReserva) {
        this.classe = classe;
        this.num_pass = num_pass;
        this.numReserva = numReserva;
        this.codigo_reserva = codigo_Voo + ":" + numReserva;
    }

    // métodos get
    public String getCodigoReserva() {
        return this.codigo_reserva;
    }

    public char getClasse() {
        return this.classe;
    }

    public int getNumPass() {
        return this.num_pass;
    }

    public int getNumReserva() {
        return this.numReserva;
    }

}