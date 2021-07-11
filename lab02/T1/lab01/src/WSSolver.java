import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WSSolver {

    public static int[] getLines(String[] args) throws Exception {
        File file = new File(args[0]);
        Scanner scanner = new Scanner(file);
        int lines = 0;
        int key_lines = 0;
        int size = 0;
        //Verificar tamanho
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            if(size==0) {size = data.length();}
            if (data.matches("[\\s]*")) {
            	throw new Exception("Error. Empty line detected!");
            }
            if (lines<size&&!data.matches("[A-Z]+")) {
            	throw new Exception("Non uppercase letters found in the puzzle");}
            if (lines>=size&&data.matches(".*[a-z].*")) {
                key_lines = key_lines + 1;}
            else if(lines>=size){
            	throw new Exception("Invalid format for the keys. No lowercase characters detected!");
            }

            lines = lines + 1;
        }
        scanner.close();
        return new int[]{lines, key_lines};
    }


    public static ArrayList<String> getKeyWordList(String[] args, int limit, int lines, int key_lines) throws Exception {
        File file = new File(args[0]);
        Scanner sc = new Scanner(file);
        ArrayList<String> words_list = new ArrayList<>();
        String[] words;


        // Get Palavras chave
        while (sc.hasNextLine()) {
            String data = sc.nextLine();

            if (data.matches(".*[a-z].*")) {
                words = data.split(";|\\s|,");
                for (int i=0;i<words.length;i++) {
                   if(!words[i].matches("[A-Za-z]+")) {
                	   throw new Exception("Keys have non alphabetic characters!");
                   }
                }
                Collections.addAll(words_list, words);

            } else {
                if ( data.length() != lines-key_lines )
                    throw new Exception("Puzzle Format Incorrect");
                else if ( lines > 40 )
                    throw new Exception("Puzzle Limit Exceeded");
            }
        }

        sc.close();
        return words_list;
    }

    public static Character[][] buildMatrix(String[] args, int lines, int key_lines) throws FileNotFoundException {
        File file = new File(args[0]);
        Scanner sc = new Scanner(file);

        int size = lines-key_lines;
        Character[][] matrix = new Character[size][size];

        // Building matrix
        for (int x = 0; x < size; x++) {
            String data = sc.nextLine();
            if (data.matches("[A-Z]+")) {
                for (int y = 0; y < size; y++) {
                    matrix[x][y] = data.charAt(y);
                }
            }
        }

        return matrix;
    }

    public static String[] findKey(Character[][] matrix, String key, int size, Character[][] solution_matrix) throws Exception {

        int[] directions_x = { 0,  1, 1, 1, 0, -1, -1, -1};
        int[] directions_y = {-1, -1, 0, 1, 1,  1,  0, -1};

        Character firstLetter = key.charAt(0);
        int direction = 8;
        int solutions = 0;
        int x=0;
        int y=0;
        String final_direction = "";
        //Encontra a letra inicial da palavra na matriz
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix.length; c++) {
                if (matrix[r][c].equals(Character.toUpperCase(firstLetter))) {
                    int[] location = {r, c};
                    //Quando a encontra, verifica se consegue encontrar a palavra
                    //Nume das 8 direções possiveis
                    for (int idx=0 ; idx < directions_x.length ; idx++) {
                        int[] dir = {directions_y[idx], directions_x[idx]};
                        int[] nextLocation = { dir[0]+location[0], dir[1]+location[1] };
                        int n = 1;
                        //se houver overlap com a matriz solução, aumenta este valor
                        int overlap = 0;
                        if (!solution_matrix[r][c].equals('.')) {
                        	overlap++;
                        }
                        
                        if (nextLocation[0] < size && nextLocation[1] < size && nextLocation[1] >= 0 && nextLocation[0] >= 0) {
                            for ( int t=1 ; t < key.length() ; t++) {
                                Character current_letter = key.charAt(t);

                                if (matrix[nextLocation[0]][nextLocation[1]].equals(Character.toUpperCase(current_letter))) {
                                    n = n + 1;
                                    if (!solution_matrix[nextLocation[0]][nextLocation[1]].equals('.')) {
                                    	overlap++;
                                    }
                                    if (dir[0] + nextLocation[0] < size && dir[1] + nextLocation[1] < size && dir[0] + nextLocation[0] >= 0 && dir[1] + nextLocation[1] >= 0) {
                                        nextLocation = new int[]{dir[0] + nextLocation[0], dir[1] + nextLocation[1]};
                                        direction = idx;
                                    }
                                } 
                                else {
                                	break;
                                }
                            }
                            //se encontra uma solução e não houver overlap completo com a matriz solução
                            //guarda a informação da solução se foi a primeira que encontrou
                            if (n == key.length()&& overlap != n){
                              	if (solutions==0) {
                                      x=r+1;
                                      y=c+1;
                                      switch (direction){
                                          case 0:
                                              final_direction="UP";
                                              break;
                                          case 1:
                                              final_direction="UPRIGHT";
                                              break;
                                          case 2:
                                              final_direction="RIGHT";
                                              break;
                                          case 3:
                                              final_direction="DOWNRIGHT";
                                              break;
                                          case 4:
                                              final_direction="DOWN";
                                              break;
                                          case 5:
                                              final_direction="DOWNLEFT";
                                              break;
                                          case 6:
                                              final_direction="LEFT";
                                              break;
                                          case 7:
                                              final_direction="UPLEFT";
                                              break;
                          		}
                            }
                            //aumenta o numero de soluções encontradas
                      		solutions++;
                            }
                        }
                    }
                }
            }
        }
    //se o numero de soluções não for 1, houve um erro
    if (solutions!=1) {
       	throw new Exception("Error. Invalid number of solutions found!");
    }
    return new String[]{key, String.valueOf(key.length()), x+ "," + y, final_direction};
    }
    public static Character[][] initializeSolutionMatrix(int lines, int key_lines){
    	//inicializa a matriz solução com '.'
    	int size = lines-key_lines;
    	Character[][] solution_matrix = new Character[size][size];
    	for (int x = 0; x < size; x++) {
    		for(int y = 0; y < size; y++) {
    			solution_matrix[x][y] = '.';
    		}
    	}
    	return solution_matrix;
    }
    public static Character[][] addWordToSolutionMatrix(Character[][] solution_matrix ,String[] word, int lines, int key_lines, Character[][] matrix) throws FileNotFoundException {
        //convert o String[] da solução de volta para os seus componentes
        int size = lines-key_lines;
        int[] directions_x = { 0,  1, 1, 1, 0, -1, -1, -1};
        int[] directions_y = {-1, -1, 0, 1, 1,  1,  0, -1};
        	int word_length = Integer.parseInt(word[1]);
        	String[] coords = word[2].split(",");
        	int x = Integer.parseInt(coords[0])-1;
        	int y = Integer.parseInt(coords[1])-1;
        	String direction = word[3];
        	int numeric_direction = 0;
        	switch(direction) {
        	case "UP":
        		numeric_direction=0;
        		break;
        	case "UPRIGHT":
        		numeric_direction=1;
        		break;
        	case "RIGHT":
        		numeric_direction=2;
        		break;
        	case "DOWNRIGHT":
        		numeric_direction=3;
        		break;
        	case "DOWN":
        		numeric_direction=4;
        		break;
        	case "DOWNLEFT":
        		numeric_direction=5;
        		break;
        	case "LEFT":
        		numeric_direction=6;
        		break;
        	case "UPLEFT":
        		numeric_direction=7;
        		break;
            }
        	//adiciona a palavra á matriz solução
        	for (int j = 0;j<word_length;j++) {
        		solution_matrix[x][y] = matrix[x][y];
        		x = x + directions_y[numeric_direction];
        		y = y + directions_x[numeric_direction];}
        return solution_matrix;
    }
}
