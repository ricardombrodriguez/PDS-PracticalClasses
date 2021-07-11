package lab03;

public class Aviao {

    private int filas_tur; 
    private int LugPorFila_tur;
    private int filas_exec;
    private int LugPorFila_exec;

    // construtores
    public Aviao(int filas_tur, int LugPorFila_tur) {

        this.filas_tur = filas_tur;
        this.LugPorFila_tur = LugPorFila_tur;
        this.LugPorFila_exec = 0;
    }
    
    // construtor com os argumentos opcionais
    public Aviao(int filas_tur, int LugPorFila_tur, int filas_exec, int LugPorFila_exec) {
        
        this.filas_exec = filas_exec;
        this.filas_tur = filas_tur;
        this.LugPorFila_tur = LugPorFila_tur;
        this.LugPorFila_exec = LugPorFila_exec;
    }

    // métodos get
    public int getLugPorFilaTuristica() {
        return this.LugPorFila_tur;
    }

    public int getFilasTuristica() {
        return this.filas_tur;
    }

    public int getLugPorFilaExecutiva() {
        return this.LugPorFila_exec;
    }

    public int getFilasExecutiva() {
        return this.filas_exec;
    }
    
}
