import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class WSGenerator {

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static ArrayList<String> readWordList(String filename, int size) throws FileNotFoundException {
        // Read file with keys
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        ArrayList<String> word_list = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            String[] words = data.split(";|\\s|,");

            for (String key : words) {
                if (key.length() > size) {
                    System.out.println(key + " -> Palavra chave demasiado grande para a sopa");
                } else if (key.length() > 2)
                    word_list.add(key.toUpperCase());
            }
        }
        scanner.close();

        return word_list;
    }

    public static Character[][] buildMatrix(int size) {
        // Build Matrix
        Character[][] matrix = new Character[size][size];

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                char random_letter = '.';

                matrix[x][y] = random_letter;
            }
        }
        return matrix;
    }

    public static Character[][] insertWords(int size, ArrayList<String> word_list, Character[][] matrix, int depth) {
        int x_rand;
        int y_rand;

        int[] directions_x = { 0,  1, 1, 1, 0, -1, -1, -1};
        int[] directions_y = {-1, -1, 0, 1, 1,  1,  0, -1};

        Character[][] final_matrix = new Character[size][size];
        Character[][] tmp_matrix;
        ArrayList<String> fails = new ArrayList<>();


        for (String key : word_list) {
            x_rand = getRandomNumber(0, size);
            y_rand = getRandomNumber(0, size);
            //x_rand = 0;
            //y_rand = 0;
            ArrayList<Integer> tmp_direction = new ArrayList<>();
            //if it can place the first letter, finds the directinos that are viable
            if ( matrix[x_rand][y_rand].equals('.') ||  matrix[x_rand][y_rand].equals(key.charAt(0)) ) {

                for( int d=0 ; d < directions_x.length ; d++) {
                    tmp_matrix = new Character[size][size];//= matrix;
                    int x = x_rand;
                    int y = y_rand;
                    int overlap = 0;
                    for (int l = 0; l < key.length(); l++) {
                        try {
                            tmp_matrix[x][y] = key.charAt(l);
                            

                            if (matrix[x][y].equals(key.charAt(l)) || matrix[x][y].equals('.')) {
                                x = x + directions_x[d];
                                y = y + directions_y[d];
                                if(matrix[x][y].equals(key.charAt(l))) {
                                	overlap++;
                                }
                                if (l == key.length()-1&& overlap!=key.length())
                                    tmp_direction.add(d);

                            } else {
                                break;
                            }

                        } catch (Exception e) {
                            break;
                        }
                    }
                }

                //Reset
                int x = x_rand;
                int y = y_rand;

                //selects a random direction out of the viable ones and adds the word
                if ( tmp_direction.size() > 0 ) {
                    int direction = tmp_direction.get(getRandomNumber(0, tmp_direction.size()));
                    for (int l = 0; l < key.length(); l++) {
                        matrix[x][y] = key.charAt(l);

                        x = x + directions_x[direction];
                        y = y + directions_y[direction];
                    }
                } else {
                    fails.add(key);
                }

            } else {
                fails.add(key);
            }

        }


        if( fails.size() > 0 && depth > 0)
            matrix = insertWords(size, fails, matrix, depth-1);

        if ( depth == 1 ) {
            System.out.print("As seguintes palavras nao foram adicionadas `a sopa: ");
            System.out.println(fails);
        }

        return matrix;
    }
    
    public static Character[][] fillMatrix(Character[][] matrix, int size){
        String filler = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
            	if (matrix[x][y].equals('.')) {
            	//selects a letter randomly, then adds it to the matrix
                Random rand = new Random();
                char random_letter = filler.charAt(rand.nextInt(filler.length()));
                matrix[x][y] = random_letter;
            	}
            }
        }
        return matrix;
    }

    public static void main(String[] args) throws Exception {

        // $ javaWSGenerator -i wordlist_1.txt -s 15
        // ou
        // $ javaWSGenerator -i wordlist_1.txt -s 15 –o sdl_01.txt

        String result_file="";
        int size = 0;

        try {
            if ( args.length != 4 && args.length != 6) {
                System.out.println("Erro");
                System.exit(0);
            }
            else if( !args[0].equals("-i") ) {
                System.out.println("Erro");
                System.exit(0);
            }
            else if( !args[2].equals("-s") ) {
                System.out.println("Erro");
                System.exit(0);
            }
            else if( args.length == 6 && !args[4].equals("-o") ) {
                System.out.println("Erro");
                System.exit(0);
            }

            //wordlist = args[1];
            size = Integer.parseInt(args[3]);
            if( size < 3 )
                System.exit(0);

            if ( args.length == 6 )
                result_file = args[5];

        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }

        // Get word list -> line 15
        ArrayList<String> word_list = readWordList(args[1], size);

        // Build matrix -> line 38
        Character[][] matrix = buildMatrix(size);


        // Insert words in matrix -> 52
        matrix = insertWords(size, word_list, matrix, 1000);
        
        //places random letters in the non filled positions -> line 137
        matrix = fillMatrix(matrix,size);
        
        for (int k = 0; k < size; k++) {
            System.out.println(Arrays.toString(matrix[k]));
        }
        
        for (int k = 0; k < word_list.size();k++) {
            System.out.println(word_list.get(k));
        }
        
        if(args.length==6) {
            FileWriter out = new FileWriter(result_file);
            for (int x = 0; x < size; x++) {
            	String tmp = Arrays.toString(matrix[x]);
            	tmp = tmp.substring(1, tmp.length()-1);
            	tmp = tmp.replaceAll(",", " ");
            	tmp = tmp.replaceAll("\\s","");
            	out.write(tmp+"\n",0,tmp.length()+1);}
            for (int x = 0;x < word_list.size();x++) {
            	out.write(word_list.get(x).toLowerCase()+"\n");
            }
            out.flush();
            out.close();
            
        }

    }
}
