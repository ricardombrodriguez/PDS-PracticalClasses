package lab03;

import java.lang.Math;
import java.util.HashMap;

public class Voo {

    private HashMap<String, Reserva> reservasVoo;
    private int lugaresTuristica[][];
    private int lugaresExecutiva[][];
    private int numSequencial;
    private String codigo;
    private Aviao aviao;
    private boolean hasExecutive;
    private int disponiveisTur;
    private int disponiveisExe;

    // construtores
    public Voo(String codigo, int filas_tur, int LugPorFila_tur){

        this.codigo = codigo;
        this.aviao = new Aviao(filas_tur, LugPorFila_tur);
        this.disponiveisTur = filas_tur * LugPorFila_tur;
        this.lugaresTuristica = new int[filas_tur][LugPorFila_tur];
        this.hasExecutive = false;
        this.reservasVoo = new HashMap<>();
        this.numSequencial = 1;
    }

        // construtor com os argumentos opcionais
    public Voo(String codigo, int filas_tur, int LugPorFila_tur, int filas_exec, int LugPorFila_exec){

        this.codigo = codigo;
        this.aviao = new Aviao(filas_tur, LugPorFila_tur, filas_exec, LugPorFila_exec);
        this.disponiveisTur = filas_tur * LugPorFila_tur;
        this.disponiveisExe = filas_exec * LugPorFila_exec;
        this.lugaresTuristica = new int[filas_tur][LugPorFila_tur];
        this.lugaresExecutiva = new int[filas_exec][LugPorFila_exec];
        this.hasExecutive = true;
        this.reservasVoo = new HashMap<>();
        this.numSequencial = 1;
    }

    // método get
    public String getCodigo() {
        return this.codigo;
    }

    public String lugaresDaReserva(String codigoReserva,int numeroSequencial) {

        String lugares = codigoReserva + " = ";

        for (int f = 0; f < this.aviao.getFilasExecutiva(); f++) {

            for (int l = 0; l < this.aviao.getLugPorFilaExecutiva(); l++) {

                if (this.lugaresExecutiva[f][l] == numSequencial) {
                    int ascii = 65 + l;                                             //'A' (65) + lugar (começa no 0)
                    char ultimoLugar = (char) ascii;
                    lugares += String.valueOf(f) + ultimoLugar + " | ";
                }
            }
        }

        for (int f = 0; f < this.aviao.getFilasTuristica(); f++) {

            for (int l = 0; l < this.aviao.getLugPorFilaTuristica(); l++) {

                if (this.lugaresTuristica[f][l] == numeroSequencial) {
                    int ascii = 65 + l;                                             //'A' (65) + lugar (começa no 0)
                    char ultimoLugar = (char) ascii;
                    lugares += String.valueOf(f) + ultimoLugar + " | ";
                }
            }
        }

        return lugares;

    }

    public void removerReservaMapa(char classe, int numReserva) {

        if (classe == 'E') {  // caso for da classe executiva

            for (int f = 0; f < this.aviao.getFilasExecutiva(); f++) {
                for (int l = 0; l < this.aviao.getLugPorFilaExecutiva(); l++) {

                    if (this.lugaresExecutiva[f][l] == numReserva) {
                        this.lugaresExecutiva[f][l] = 0;
                    }
                }
            }

        } else if (classe == 'T') {  // caso for da classe turística

            for (int f = 0; f < this.aviao.getFilasTuristica(); f++) {
                for (int l = 0; l < this.aviao.getLugPorFilaTuristica(); l++) {

                    if (this.lugaresTuristica[f][l] == numReserva) {
                        this.lugaresTuristica[f][l] = 0;
                    }
                }
            }

        }

    }

    // método para atribuir os lugares de reserva no mapa de voo
    public void alterarMapaReservas(char classe, int numPass, int numReserva) {

        int passageirosRestantes = numPass;
        String lugarID;

        if (classe == 'E') {

            //verificar se há filas vazias primeiro
            for (int f = 0; f < this.aviao.getFilasExecutiva(); f++) {

                boolean linhaVazia = true;
                for (int l = 0; l < this.aviao.getLugPorFilaExecutiva(); l++) {
                    if (this.lugaresExecutiva[f][l] != 0) {
                        linhaVazia = false;
                        break;
                    }
                }

                if (linhaVazia) {

                    for (int l = 0; l < this.aviao.getLugPorFilaExecutiva(); l++) {
                        if (passageirosRestantes > 0) {
                            this.lugaresExecutiva[f][l] = numReserva;
                            passageirosRestantes--;
                        }
                    }
                }
            }

            //verificar se há filas com lugares desocupados (mas não cheia)
            for (int f = 0; f < this.aviao.getFilasExecutiva(); f++) {
                for (int l = 0; l < this.lugaresExecutiva[0].length; l++) {

                    if (this.lugaresExecutiva[f][l] == 0 && passageirosRestantes > 0) { //lugar desocupado

                        this.lugaresExecutiva[f][l] = numReserva;
                        passageirosRestantes--;
                    
                    }
                }
            }

        } else if (classe == 'T') {                                                        //classe turística

            //verificar se há filas vazias primeiro
            for (int f = 0; f < this.aviao.getFilasTuristica(); f++) {

                boolean linhaVazia = true;
                for (int l = 0; l < this.aviao.getLugPorFilaTuristica(); l++) {
                    if (this.lugaresTuristica[f][l] != 0) {

                        linhaVazia = false;
                    }
                }

                if (linhaVazia) {
                    for (int l = 0; l < this.aviao.getLugPorFilaTuristica(); l++) {
                        if (passageirosRestantes > 0) {

                            this.lugaresTuristica[f][l] = numReserva;
                            passageirosRestantes--;
                        }
                    }
                }
            }

            //verificar se há filas com lugares desocupados (mas não cheia)
            for (int f = 0; f < this.aviao.getFilasTuristica(); f++) {
                for (int l = 0; l < this.lugaresTuristica[0].length; l++) {

                    if (this.lugaresTuristica[f][l] == 0 && passageirosRestantes > 0) { //lugar desocupado

                        this.lugaresTuristica[f][l] = numReserva;
                        passageirosRestantes--;
                    }
                }
            }
        }

    }

    // método para fazer reservas
    public void reservar(String codigo_Voo, char classe, int num_pass){
        
        if (classe == 'E') {

            if (this.hasExecutive) {

                if (disponiveisExe >= num_pass) {

                    disponiveisExe -= num_pass;
                    Reserva reserva = new Reserva(codigo_Voo, classe, num_pass, numSequencial);
                    String codigoReserva = reserva.getCodigoReserva();
                    reservasVoo.put(codigoReserva,reserva);

                    int numReserva = reserva.getNumReserva();
                    alterarMapaReservas('E',num_pass,numReserva);
                    String infoReserva = lugaresDaReserva(codigoReserva,numReserva);



                    System.out.println(infoReserva);
                    numSequencial++;

                } else {
                    System.out.println("Erro: Não foi possível reservar " + num_pass + " lugares. Sobram " + disponiveisExe + " lugares em classe executiva");
                }

            } else {
                System.out.println("Erro: O avião não possui lugares com classe executiva.");
            }

        } else if (classe == 'T'){

            if (disponiveisTur >= num_pass) {

                disponiveisTur -= num_pass;
                Reserva reserva = new Reserva(codigo_Voo, classe, num_pass, numSequencial);
                String codigoReserva = reserva.getCodigoReserva();
                reservasVoo.put(codigoReserva,reserva);

                int numReserva = reserva.getNumReserva();
                alterarMapaReservas('T',num_pass,numReserva);
                String infoReserva = lugaresDaReserva(codigoReserva,numReserva);


                System.out.println(infoReserva);
                numSequencial++;

            } else {
                System.out.println("Erro: Não foi possível reservar " + num_pass + " lugares. Sobram " + disponiveisTur + " lugares em classe turística");
            }

        } else {
            System.out.println("Erro: Classe inexistente. Escolha (T)urística ou (E)xecutiva.");
        }

    }
    
    // método para camcelar uma reserva
    public void cancelarReserva(String codigoReserva) {

        if (this.reservasVoo.containsKey(codigoReserva)) {

            Reserva reservaCancelada = this.reservasVoo.get(codigoReserva);
            this.reservasVoo.remove(codigoReserva);

            char classe = reservaCancelada.getClasse();
            int numLugares = reservaCancelada.getNumPass();
            int numReserva = reservaCancelada.getNumReserva();

            removerReservaMapa(classe,numReserva);

            if (classe == 'T') {
                disponiveisTur += numLugares;

            } else if (classe == 'E') {
                disponiveisExe += numLugares;
            }

            System.out.println("A reserva " + codigoReserva + " foi cancelada.");

        } else {
            System.out.println("Erro: Código de reserva inválido.");
        }

    }

    // método para exibir o mapa de voo
    public void exibirReservas() {

        int filasExecutiva = this.aviao.getFilasExecutiva();
        int filasTuristica = this.aviao.getFilasTuristica();
        int totalFilas = filasExecutiva + filasTuristica;

        int lugPorExecutiva = this.aviao.getLugPorFilaExecutiva();
        int lugPorTuristica = this.aviao.getLugPorFilaTuristica();
        
        //print das filas numeradas
        System.out.format("   ");
        for (int fila = 1; fila <= totalFilas; fila++) {
            System.out.format("%2d ",fila);
        }


        //print dos lugares e respetivo numero de reserva
        for (int lugar = 0; lugar < Math.max(lugPorExecutiva,lugPorTuristica); lugar++) {

            int ascii = 65 + lugar;                                             //'A' (65) + lugar (começa no 0)
            char ultimoLugar = (char) ascii;                                    //caractere do lugar
            System.out.format("\n%c  ", ultimoLugar);

            for (int fila = 0; fila < filasExecutiva; fila++){
                
                //coluna da executiva
                if (this.hasExecutive && lugar < lugPorExecutiva) {  
                    System.out.format("%2d ", this.lugaresExecutiva[fila][lugar] );
                    
                } else if (this.hasExecutive && lugar >= lugPorExecutiva){
                    System.out.format("   ");
                }
            
            }

            for (int fila = 0; fila < filasTuristica; fila++){
                System.out.format("%2d ", this.lugaresTuristica[fila][lugar] );
            }

        }
        System.out.format("\n");

    }

    // método toString
    public String toString() {
        return "Código de voo "+ this.codigo;
    }

}
