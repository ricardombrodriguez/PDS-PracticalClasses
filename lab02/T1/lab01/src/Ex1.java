import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Ex1 {

    public static void main(String[] args) throws Exception {
    	//cria a output, utilizando parte do nome do ficheiro inicial (as 5 ultimas letras) 
        char[] output_name = {'o','u','t','p','u','t',' ',' ',' ',' ',' '};
        args[0].getChars(args[0].length()-5, args[0].length(),output_name , 6);
        FileWriter out = new FileWriter(new String(output_name));
        //obtem o numero de linhas e de linhas com keys
        int[] result = WSSolver.getLines(args);
        int lines = result[0];
        int key_lines = result[1];
        int size = lines-key_lines;
        //inicializo o Array de soluções e obtenho as keys do ficheiro
        ArrayList<String[]> word_solutions = new ArrayList<>();
        ArrayList<String> words_list = WSSolver.getKeyWordList(args, 40, lines, key_lines);
        //re obtenho as keys e reordeno as keys para estarem por ordem do tamanho
        //isto, juntamente com uma verificação do overlap com a solução quando
        //procuro pelas palavras fazem com que o programa cumpra o requisito 8
        ArrayList<String> reordered_word_list = WSSolver.getKeyWordList(args, 40, lines, key_lines);
        Collections.sort(reordered_word_list, Comparator.comparing(String::length));
        //coloco as posições iniciais das keys num novo Array
        ArrayList<Integer> initial_id = new ArrayList<Integer>();
        for (int x = 0;x<words_list.size();x++) {
        	initial_id.add(reordered_word_list.indexOf(words_list.get(x)));
        }
        
        Character[][] matrix = WSSolver.buildMatrix(args, lines, key_lines);

        int counter = 0;
        //inicializo a solution matrix e encontro todas as palavras utilizando a lista reordenada
        //coloco as soluções na solution matrix após as encontrar
        Character[][] solution_matrix = WSSolver.initializeSolutionMatrix(lines,key_lines);
        for ( String key : reordered_word_list) {
        	word_solutions.add(WSSolver.findKey(matrix, key, size, solution_matrix));
        	solution_matrix = WSSolver.addWordToSolutionMatrix(solution_matrix,word_solutions.get(counter),lines,key_lines,matrix);
            counter++;
        }
        //coloco as soluções na ordem inicial das keys
        ArrayList<String[]> reordered_solutions = new ArrayList<String[]>();
        for (int x: initial_id) {
        	reordered_solutions.add(word_solutions.get(x));
        }
        //altero a String com as soluções para remover partes que não são necessárias
        //e coloco no ficheiro
        for (String[] solution: reordered_solutions) {
        	String tmp2 = Arrays.toString(solution);
        	tmp2 = tmp2.substring(1, tmp2.length()-1);
        	tmp2 = tmp2.replaceAll(",", " ");
            out.write(tmp2+"\n",0,tmp2.length()+1);
        }
        //removo as virgulas e brackets da matriz solução e coloco a no ficheiro
        for (int x = 0; x < size; x++) {
        	String tmp = Arrays.toString(solution_matrix[x]);
        	tmp = tmp.substring(1, tmp.length()-1);
        	tmp = tmp.replaceAll(",", " ");
        	out.write(tmp+"\n",0,tmp.length()+1);}
        out.flush();
        out.close();
    }
}