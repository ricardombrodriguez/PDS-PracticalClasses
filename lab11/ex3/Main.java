// package lab11.ex3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Livro L1 = new Livro("Java Anti-Stress", "Omodionah");
        Livro L2 = new Livro("A Guerra dos Padrões", "Jorge Omel");
        Livro L3 = new Livro("A Procura da Luz", "Khumatkli");

        Livro lista[] = {L1, L2, L3};

        boolean loop = true;

        while (loop) {
            String mensagem = "*** Biblioteca ***\n";

            for (int i = 0; i < lista.length; i++) {
                mensagem += String.format("%-5d %20s %20s %20s", i+1, lista[i].getTitle(), lista[i].getAuthor(), lista[i].getEstado() );
                mensagem += "\n";
            }

            mensagem += "\n<livro>, <operação>: (1)regista; (2)requisita; (3)devolve; (4)reserva; (5)cancela";
            System.out.println(mensagem);

            System.out.print(">> ");
            String input = sc.nextLine();

            if (input.equals("")) break;

            String op[] = input.split(",");

            int posicao_livro = Integer.parseInt(op[0]) - 1;
            int operacao = Integer.parseInt(op[1]);

            switch (operacao) {
                case 1:
                    lista[posicao_livro].regista();
                    break;

                case 2:
                    lista[posicao_livro].requesita();
                    break;

                case 3:
                    lista[posicao_livro].devolve();
                    break;

                case 4:
                    lista[posicao_livro].reserva();
                    break;

                case 5:
                    lista[posicao_livro].cancelaReserva();
                    break;
            }

        }

        System.out.println("Adeus!");
        sc.close();
    }
}
