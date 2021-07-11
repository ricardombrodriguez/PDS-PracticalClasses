package lab03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* Como compilar/correr:
- Preciso estar no diretorio-pai do "lab03"
- javac lab03/*.java (compilar)
- java lab03/ex2 (executar sem ficheiro)
- java lab03/ex2 comandos.txt (executar com ficheiro)
- não é necessário colocar "lab03/" antes do parâmetro I do programa
*/

public class ex2 {

    //Transforma uma string do tipo "3x5" num array de int com o num. de filas (index 0) e de lugares por fila (index 1)
    public static int[] getPair(String str) {

        try {
            String strPair[] = str.split("x");
            int filas = Integer.parseInt(strPair[0]);
            int lugares = Integer.parseInt(strPair[1]);

            if (filas < 1 || lugares < 1) throw new NumberFormatException();
            return new int[] {filas,lugares};

        } catch (NumberFormatException e) {
            System.out.println("Erro: O parâmetro deve ter dois inteiros (maiores que zero) separados por 'x'.");
            return null;
        }
    }

    public static String codigoVooValido(String codigo) {

        if (codigo.matches("(?<![A-Z])[A-Z]{2}\\d{4}(?<![A-Z])")) {
            return codigo;

        } else {
            System.out.println("Erro: Código de voo inválido. Este tem de ser composto por 2 letras maiúsculas seguidas de 4 números.");
            return null;
        }
    }

    public static void menu() {

        boolean not_quit = true;
        Scanner sc = new Scanner(System.in);
        
        while (not_quit) {

            System.out.println("\nEscolha uma opção: (H para ajuda)");
            String option[] = sc.nextLine().split(" ");
            not_quit = switch_function(option);
        }

        sc.close();
        System.exit(0);
    }

    public static boolean switch_function(String[] option) {

        String codigoVoo;
        boolean not_quit = true; 

        switch (option[0].toUpperCase()) {

            ///////////////////////////////////////////// AJUDA ///////////////////////////////////////////////
            case "H":
                System.out.println(
                "============================= Comandos ================================= \n" +
                "'I <ficheiro>': Lê um ficheiro de texto contendo informação sobre o voo. \n" +
                "'M <codigoVoo>': Exibe o mapa das reservas de um voo. \n" +
                "'F <codigoVoo> <lugaresTuristica> <lugaresExecutiva>': Acrescenta um novo voo, com código, lugares em turística e/ou em executiva (parâmetro opcional). \n" +
                "'R <codigoVoo> <classe> <numeroLugares>': Acrescenta uma nova reserva a um voo, com indicação do código do voo, da classe (T/E) e do número de lugares.\n" +
                "'C <codigoReserva>': Cancela uma reserva.\n" +
                "'Q': Encerra o programa.\n" +
                "========================================================================");
                break;


            //////////////////////////// LER INFORMAÇÕES DE VOO NO FICHEIRO //////////////////////////////////
            case "I":

                try {

                    File fl = new File("lab03/"+ option[1]);                                      //lê o ficheiro (2º argumento)
                    Scanner input = new Scanner(fl);

                    String infoVoo[] = new String[3];     

                    if (input.hasNextLine()) infoVoo = input.nextLine().split(" ");     //guarda em infoVoo a 1º linha separada (ex: >TP1920 3x2 15x3)

                    codigoVoo = codigoVooValido(infoVoo[0].replaceAll(">",""));         //substituir o '>' do codigo de voo e verificar validade do código                       
                    
                    int disponivelTur = 0;                                              //guardar numero de lug. turisticos
                    int disponivelExe = 0;                                              //guardar numero de lug. executivos

                    //Se não tiver classe executiva
                    if (infoVoo.length == 2) {

                        int turistica[] = getPair(infoVoo[1]);
                        Manutencao.addVoo(codigoVoo,turistica[0],turistica[1]);

                    //Se tiver classe executiva
                    } else if (infoVoo.length == 3) {   
                        
                        int executiva[] = getPair(infoVoo[1]);
                        int turistica[] = getPair(infoVoo[2]);
                        Manutencao.addVoo(codigoVoo,turistica[0],turistica[1],executiva[0],executiva[1]);

                    } else {
                        System.out.println("Erro: O ficheiro tem um número inválido de informações.");
                    }

                    //Print da informação de voo (varia consoante se existe ou não classe executiva)
                    System.out.print("Código de voo " + codigoVoo + ". Lugares disponíveis: ");
                    if (disponivelExe != 0) System.out.print(disponivelExe + " lugares em classe Executiva; ");

                    System.out.print(disponivelTur + " lugares em classe Turística.\n");
                    if (disponivelExe == 0) System.out.print("Classe executiva não disponível neste voo.\n");

                    //Ler linhas do ficheiro (realizar reservas)
                    while (input.hasNextLine()) {

                        try {
                            String reserva[] = input.nextLine().split(" ");             //separar a linha (T 2)
                            char classe = reserva[0].charAt(0);                         //guardar classe (primeiro elemento)
                            int passageiros = Integer.parseInt(reserva[1]);             //guardar num passageiros para reserva

                            Manutencao.reservarLugares(codigoVoo, classe, passageiros);
                            
                        } catch (NumberFormatException e) {
                            System.out.println("Erro: Cada elemento do par deve ser um número.");
                        }
                    }
                    input.close();

                } catch (FileNotFoundException e){
                    System.out.println("Erro: Ficheiro não existente.");
                }
                break;


            //////////////////////////// MOSTRAR MAPA DE RESERVAS DO VOO ////////////////////////////////////
            case "M":

                codigoVoo = codigoVooValido(option[1]);
                Manutencao.mapaReservas(codigoVoo);
                break;


            /////////////////////////////////// ADICIONAR NOVO VOO /////////////////////////////////////////
            case "F":                                               

                if (option.length == 3) {                           //sem classe executiva

                    try {
                        codigoVoo = codigoVooValido(option[1]);
                        int turistica[] = getPair(option[2]);
                        Manutencao.addVoo(codigoVoo,turistica[0],turistica[1]);

                    } catch (NumberFormatException e) {
                        System.out.println("Erro: O parâmetro deve ter dois inteiros (maiores que zero) separados por 'x'.");
                    }

                } else if (option.length == 4) {                    //com classe executiva

                    try {
                        codigoVoo = codigoVooValido(option[1]);
                        int turistica[] = getPair(option[2]);
                        int executiva[] = getPair(option[3]);
                        Manutencao.addVoo(codigoVoo,turistica[0],turistica[1],executiva[0],executiva[1]);

                    } catch (NumberFormatException e) {
                        System.out.println("Erro: O parâmetro deve ter dois inteiros (maiores que zero) separados por 'x'.");
                    }

                } else {
                    System.out.println("Erro: Número inválido de parâmetros (escreve H para ajuda).");
                }
                break;


            ////////////////////////////////// NOVA RESERVA /////////////////////////////////////
            case "R":

                if (option.length == 4) {

                    codigoVoo = codigoVooValido(option[1]);
                    char classe = option[2].charAt(0);
                    int numLugares = Integer.parseInt(option[3]);

                    if (numLugares < 1) {
                        System.out.println("Erro: Número de passageiros inválido."); 
                        break;
                        
                    } else {
                        Manutencao.reservarLugares(codigoVoo,classe,numLugares);
                        break;                   
                    }        
                } else {
                    System.out.println("Erro: Número de parâmetros inválido.");
                } 
                break;         

    
            ///////////////////////////////// CANCELAR RESERVA //////////////////////////////////
            case "C":
                
                String codigoReserva = option[1];
                Manutencao.cancelarReserva(codigoReserva);
                break;


            ///////////////////////////////// SAIR DO PROGRAMA //////////////////////////////////
            case "Q":
                
                not_quit = false;
                System.out.println("Fim do programa!");
                break;


            default:
                System.out.println("Parâmetro inválido. Tenta outra vez.");
        }

        return not_quit;
        
    }

    public static void main(String args[]){

        if (args.length == 0) {                 // sem ficheiro de comandos
            menu();

        } else if (args.length == 1) {          // com ficheiro de comandos

            try {
                File fl = new File("lab03/"+ args[0]);
                Scanner sc = new Scanner(fl);
    
                while (sc.hasNextLine()) {
                    String option[] = sc.nextLine().split(" ");
                    switch_function(option);
                }
    
                sc.close();
    
            } catch (FileNotFoundException e) {
                System.out.println("Ficheiro não existente, tenta outra vez.");
            }

        } else {
            System.out.println("Número inválido de argumentos.");
        }

    }
}
