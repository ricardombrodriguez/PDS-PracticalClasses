package lab03;

import java.util.HashMap;

public class Manutencao {

    private static HashMap<String, Voo> voos = new HashMap<>();
    private static HashMap<String, Reserva> reservas = new HashMap<>();

    // adicionar voos 
    public static void addVoo(String codigo_Voo, int filas_tur, int LugPorFila_tur, int filas_exec, int LugPorFila_exec) {

        if (voos.containsKey(codigo_Voo)) {
            System.out.println("Erro: Voo " + codigo_Voo + " já adicionado.");

        } else {
            Voo flight = new Voo(codigo_Voo, filas_tur, LugPorFila_tur, filas_exec, LugPorFila_exec);
            voos.put(codigo_Voo, flight);
        }
    }

    public static void addVoo(String codigo_Voo, int filas_tur, int LugPorFila_tur) {

        if (voos.containsKey(codigo_Voo)) {
            System.out.println("Erro: Voo " + codigo_Voo + " já adicionado.");

        } else {
            Voo flight = new Voo(codigo_Voo, filas_tur, LugPorFila_tur);
            voos.put(codigo_Voo, flight);
        }
    }
    

    // reservar lugares num voos
    public static void reservarLugares(String codigo_Voo, char classe, int num_pass) {

        if (voos.containsKey(codigo_Voo)) {
            
            Voo voo = voos.get(codigo_Voo);
            voo.reservar(codigo_Voo, classe, num_pass);

        } else {
            System.out.println("Erro: Código de voo não existente");
        }

    }


    // cancelar uma reserva
    public static void cancelarReserva(String codigoReserva) {

        try {
            String codigoVoo = codigoReserva.split(":")[0];

            if (voos.containsKey(codigoVoo)) {
                Voo vooAssociado = voos.get(codigoVoo);
                vooAssociado.cancelarReserva(codigoReserva);
            }

        } catch (Exception e) {
            System.out.println("Erro: Código de reserva inválido");
        }

    }

    
    // construir mapa de reservas
    public static void mapaReservas(String codigoVoo) {

        if (voos.containsKey(codigoVoo)){

            Voo voo = voos.get(codigoVoo);
            voo.exibirReservas();

        } else {
            System.out.println("Erro: Código de voo inválido");
        }

    }
}
