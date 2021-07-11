import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WSGenerator {

    public static void main(String[] args) throws FileNotFoundException {

        int size = 0;
        String input_file = null;
        String output_filename = null;

        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {

                switch(args[i]){
                    case "-i":
                        input_file = args[i + 1];
                        break;

                    case "-s":
                        size = Integer.parseInt(args[i + 1]);
                        if (size > 40) {   // o size não ppode ser maior que 40
                            System.out.println("Erro: tamanho da sopa excedeu o limite");
                            System.exit(0);
                        }
                        break;
                    
                    case "-o":
                        output_filename = args[i + 1];
                        break;

                    default:
                        System.out.println("Erro: Opção inválida");
                        System.exit(0);
                }
            }
        }

        if (size == 0 && input_file != null) {
            System.out.println("Erro: tamanho da sopa de letras não definido");
            System.exit(0);
        } else if (input_file == null && size != 0) {
            System.out.println("Erro: Ficheiro não indicado");
            System.exit(0);
        } else if (size == 0 && input_file == null) {
            System.out.println("Erro: falta de argumentos");
            System.exit(0);
        } 

        if (output_filename == null) {  // caso o utilizador não indique o nome pretendido para o ficheiro, será dado um por default 
            output_filename = "sdl_01.txt";
        }

        ArrayList<String> palavras = readFile(input_file, size);
        ArrayList<ArrayList<Character>> sopa_de_letras = insertWords(size, palavras);

    try {
        PrintWriter writer = new PrintWriter(output_filename);
        for (ArrayList<Character> linha : sopa_de_letras) {
            for (char c : linha) {
                writer.print(c);
            }
            writer.println();
        }

        for(String word : palavras){
            writer.println(word);
        }

        writer.close();

    } catch (NullPointerException e) {
        System.out.println("Erro: falha ao escrever ficheiro");
        System.exit(0);
    }

    }

    //////////////////////////////// LER FICHEIRO DAS PALAVRAS ////////////////////////////////

    public static ArrayList<String> readFile(String filename, int size) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(filename));
        ArrayList<String> palavras = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] lineWords = line.split("[, ;]+");

            if(lineWords.length != 1) { // se o tamanho do vetor for diferente de 1 significa que há uma lista por linha no ficheiro
                for (String word : lineWords) {

                    palavras.add(word);
                    if(word.length() > size){   // se o tamanho da palavra for maior que o tamanho da sopa de letras
                        System.out.println("Erro: Palavras maiores que o tamanho da sopa de letras");
                        System.exit(0);
                    }
                }

            } else {
                palavras.add(line);
                if(line.length() > size){
                    System.out.println("Erro: Palavras maiores que o tamanho da sopa de letras");
                    System.exit(0);
                }
            }
        }

        sc.close();

        return palavras;
    }

     //////////////////////////////// CRIAR SOPA DE LETRAS ////////////////////////////////

    public static ArrayList<ArrayList<Character>> insertWords(int size, ArrayList<String> palavras) {
        
        ArrayList<ArrayList<Character>> tabela = new ArrayList<ArrayList<Character>>();

        for (int x = 0; x < size; x++) {        // preencher a tabela inicalmente com '.''
            ArrayList<Character> linhas = new ArrayList<>();
            for (int y = 0; y < size; y++) {
                linhas.add('.');
            }
            tabela.add(linhas);
        }

        Random rand = new Random();
        for (int i = 0; i < palavras.size(); i++) { // vamos colocar cada palavra, uma de cada vez, na tabela

            int pos_x;
            int pos_y;

            do {
                pos_x = rand.nextInt(size);     // obter uma coordenada random
                pos_y = rand.nextInt(size);
            } while (tabela.get(pos_x).get(pos_y) != '.'); // caso a coordenada random já esteja ocupada por um caracter de uma palavra

            String word = palavras.get(i).toUpperCase();
            ArrayList<Integer> directions = new ArrayList<>();  // array que guardará as direções que a palavra pode ir dependendo da sua coordenada, por exemplo se a coodenada for (0,0) só pode ir Down. DownRight ou Right

            // a função check_space verifica se para uma dada direção entra em conflito com outra palavra, sobrepondo-se a ela num dos caracteres
            // 
            // "Up" --> 0
            if( (pos_x + 1) >= word.length() && check_space(word, 0, tabela, pos_x, pos_y) ) directions.add(0); 

            // "UpRight" --> 1
            if( (pos_x + 1) >= word.length() && (size - pos_y) >= word.length() && check_space(word, 1, tabela, pos_x, pos_y)) directions.add(1);

            // "UpLeft" --> 2
            if( (pos_x + 1) >= word.length() && (pos_y + 1) >= word.length() && check_space(word, 2, tabela, pos_x, pos_y) ) directions.add(2);

            // "Left" --> 3
            if( (pos_y + 1) >= word.length() && check_space(word, 3, tabela, pos_x, pos_y) ) directions.add(3);

            // "Right" --> 4
            if( (size - pos_y) >= word.length() && check_space(word, 4, tabela, pos_x, pos_y) ) directions.add(4);

            // "Down" --> 5
            if( (size - pos_x) >= word.length() && check_space(word, 5, tabela, pos_x, pos_y) ) directions.add(5);

            // "DownLeft" --> 6
            if( (size - pos_x) >= word.length() && (pos_y + 1) >= word.length() && check_space(word, 6, tabela, pos_x, pos_y) ) directions.add(6);

            // "DownRight" --> 7
            if( (size - pos_x) >= word.length() && (size - pos_y) >= word.length() && check_space(word, 7, tabela, pos_x, pos_y) ) directions.add(7);

            if (directions.size() == 0) {   // caso não haja direções para uma certa posição, repete-se todo o processo
                i--;
                
            } else {

                int index = rand.nextInt(directions.size());
                int direction_rand = directions.get(index); // direção escolhida aleatoriamente dentro das possíveis

                // colocar a palavra na tabela
                tabela.get(pos_x).set(pos_y, word.charAt(0));

                for (int c = 1; c < word.length(); c++) {
                    switch (direction_rand) {
                        case 0: //"Up"
                            pos_x--;
                            tabela.get(pos_x).set(pos_y, word.charAt(c));
                            break;
            
                        case 1: //"UpRight"
                            pos_x--;
                            pos_y++;
                            tabela.get(pos_x).set(pos_y, word.charAt(c));
                            break;
            
                        case 2: //"UpLeft"
                            pos_x--;
                            pos_y--;
                            tabela.get(pos_x).set(pos_y, word.charAt(c));
                            break;
            
                        case 3: //"Left"
                            pos_y--;
                            tabela.get(pos_x).set(pos_y, word.charAt(c));
                            break;
            
                        case 4: //"Right"
                            pos_y++;
                            tabela.get(pos_x).set(pos_y, word.charAt(c));
                            break;
            
                        case 5: //"Down"
                            pos_x++;
                            tabela.get(pos_x).set(pos_y, word.charAt(c));
                            break;
            
                        case 6: //"DownLeft"
                            pos_x++;
                            pos_y--;
                            tabela.get(pos_x).set(pos_y, word.charAt(c));
                            break;
            
                        case 7: //"DownRight"
                            pos_x++;
                            pos_y++;
                            tabela.get(pos_x).set(pos_y, word.charAt(c));
                            break;
            
                        default:
                            System.out.println("Error");
                    }
                }
            }
        }

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (tabela.get(x).get(y).equals('.')) {
                    char char_rand = (char) ('A' + rand.nextInt(26));  // completar os espaços em falta com letras aleatórias
                    tabela.get(x).set(y,char_rand);
                }
            }
        }

        return tabela;
    }

     //////////////////////////////// VERIFICA DÁ PARA COLOCAR UMA PALAVRA NUMA CERTA DIREÇÃO ////////////////////////////////

    public static boolean check_space(String word, int direction, ArrayList<ArrayList<Character>> tabela, int x, int y) {
        boolean check_space = true;

        int cont = 0;
        while(cont < word.length()) {
            if (tabela.get(x).get(y) != '.') { // caso a coordenada tiver um caracter associado então não há espaço para a palavra estar nessa direção
                check_space = false;
                break;
            }
            cont++;

            switch (direction) {
                case 0: //"Up"
                    x--;
                    break;

                case 1: //"UpRight"
                    x--;
                    y++;
                    break;

                case 2: //"UpLeft"
                    x--;
                    y--;
                    break;

                case 3: //"Left"
                    y--;
                    break;

                case 4: //"Right"
                    y++;
                    break;

                case 5: //"Down"
                    x++;
                    break;

                case 6: //"DownLeft"
                    x++;
                    y--;
                    break;

                case 7: //"DownRight"
                    x++;
                    y++;
                    break;

                default:
                    System.out.println("Error");
            }
        }
    
        return check_space;
    }
}