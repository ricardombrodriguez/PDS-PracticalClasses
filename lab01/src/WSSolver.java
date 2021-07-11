import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class WSSolver {

    public static void main(String[] args) throws FileNotFoundException {
        
        try {
            File fl = new File(args[0]);
            ArrayList<ArrayList<Character>> table = createTable(fl);                    //creation of table (only '.' characters) with the correct dimensions
            ArrayList<String> palavras = createWordList(fl);                            //create a String array with the words we want to search
            ArrayList<ArrayList<Character>> finalTable = solvePuzzle(table, palavras);  //problem solution (it calls the recursive function)

            for (ArrayList<Character> line : finalTable) {                              //printing the final table           
                for (char c : line) {
                    System.out.print(c);
                }
                System.out.println();
            }            

        } catch(FileNotFoundException e) {
            System.out.println("Error: the file was not found.");
        }
    }


    public static ArrayList<String> createWordList(File fl) throws FileNotFoundException{

        Scanner sc = new Scanner(fl);
        ArrayList<String> palavras = new ArrayList<String>();

        while (sc.hasNextLine()) {

            try {
                String line = sc.next();
                String stringLw = line.toLowerCase();
                if (stringLw.equals(line)){  
                    String[] lineWords = line.split("[, ;]+");
                    for (String s : lineWords) {
                        palavras.add(s); //adicionar limite minimo da palavra
                    }
                }
            } catch (NoSuchElementException e) { continue; }
        }
        sc.close();

        return palavras;
    }

    //To verify if the input table is squared (returns size of the table)
    public static ArrayList<ArrayList<Character>> createTable(File fl) throws FileNotFoundException {

        ArrayList<ArrayList<Character>> table = new ArrayList<ArrayList<Character>>();
        
        int length = 0;
        int height = 0;
        
        Scanner sc = new Scanner(fl);

        while(sc.hasNextLine()) {

            try {
                String line = sc.next();
                char[] characters = line.toCharArray();
                String stringUp = line.toUpperCase();

                if (stringUp.equals(line) && line.length() < 40) {  //if stringUp == line then all the characters are uppercase
                    height++;
                    if (height == 1) {                              //store the length of the table as the length of the 1st line
                        length = line.length();
                    } else if (line.length() != length) {           //if some line has a different length of the 1st one:
                        System.out.println("Error: non-squared puzzle table");
                        System.exit(0);
                    } 
                    ArrayList<Character> line_of_chars = new ArrayList<>();
                    for (char c : characters) {         //add each character in the line array
                        line_of_chars.add(c);
                    }
                    table.add(line_of_chars);           //add the line characters array in the table
                } 
            } catch(NoSuchElementException e) { continue; }
        }
        sc.close();

        if (height != length) {
            System.out.println("Error: non-squared puzzle table");
            System.exit(0);
        }

        return table;
    }

    //sort "words" by length in decrescent order
    public static ArrayList<String> sortWords(ArrayList<String> words) {
        for (int i = 0; i < words.size(); i++){
            for (int j = 0; j < words.size(); j++){
                if (i != j && words.get(i).length() > words.get(j).length()){
                    String temp = words.get(j);
                    words.set(j,words.get(i));
                    words.set(i,temp);
                }
            }
        }
        return words;
    }

    public static ArrayList<ArrayList<Character>> solvePuzzle(ArrayList<ArrayList<Character>> table, ArrayList<String> words){

        int tableSize = table.size();                                                                //needed to control limits
        ArrayList<ArrayList<Character>> finalTable = new ArrayList<ArrayList<Character>>(tableSize); //table with the output we want
        Map<String,String> allWordsInfo = new HashMap<String,String>();                              //it stores word's info (length, first coord. and direction)
        ArrayList<ArrayList<Integer>> listCoords = new ArrayList<ArrayList<Integer>>();              //list of coords of each starting character
        
        //fill all the finalTable with dots ('.') 
        for (int x = 0; x < tableSize; x++){  
            ArrayList<Character> lineArray = new ArrayList<Character>(tableSize);
            for (int y = 0; y < tableSize; y++){
                lineArray.add('.');
            }
            finalTable.add(lineArray);
        }

        words = sortWords(words);                   //sort by length (decr.)

        for (int i = 0; i < words.size(); i++) {    //set all the words we want to search to uppercase
            words.set(i,words.get(i).toUpperCase());
        }

        //calling the recursive func. to search each word in "words"
        for (String w : words){
            recursiveCombinations(allWordsInfo,finalTable,table,words,w,tableSize,"",0,0,0,listCoords);
        }

        for (int i = 0; i < words.size(); i++) {
            if (!allWordsInfo.containsKey(words.get(i).toLowerCase())){
                System.out.format("%-15s %15s\n",words.get(i).toLowerCase(),"inexistente");
                continue;
            }
        }

        //printing all the words information (its length, its starting character position and the direction of the word)
        for (Map.Entry<String,String> entry : allWordsInfo.entrySet()) {
            String valueInfo[] = entry.getValue().split("-");
            System.out.format("%-15s %-6s %-7s %-10s\n",entry.getKey(),valueInfo[0],valueInfo[1],valueInfo[2]);
        }
        System.out.println();

        return finalTable;
    }


    //allWordsInfo -> store word information | finalTable -> final output table | table -> input table from the file
    //words -> list of words | w -> current word we're searching | tableSize -> size of the table | str -> the current string we're analising
    //direction -> numeric repres. of the direction | l -> line | c -> column | listCoords -> coords of chars we already checked

    public static void recursiveCombinations(Map<String,String> allWordsInfo, ArrayList<ArrayList<Character>> finalTable,
            ArrayList<ArrayList<Character>> table, ArrayList<String> words, String w, int tableSize, 
            String str, int direction, int l, int c, ArrayList<ArrayList<Integer>> listCoords) {     

        String directions[] = {"Up","UpRight","UpLeft","Left","Right","Down","DownLeft","DownRight"};
        char ch = table.get(l).get(c);  //current char
        str += ch;                           

        boolean checkStartsWith = false;    
        if (w.startsWith(str)) checkStartsWith = true;

        if (checkStartsWith == false) { //if no word starts with "str"

            if (str.length() == 1) {    //if str (length == 1) is not a 1st character of any of the words
                if (c == tableSize - 1 && l == tableSize - 1) { //last table character
                    ;
                } else if (c == tableSize - 1) {                //if the current position is in the last column
                    recursiveCombinations(allWordsInfo,finalTable,table,words,w,tableSize,"",0,l+1,0,listCoords);
                } else {                                        //if the current position isn't in any table limit
                    recursiveCombinations(allWordsInfo,finalTable,table,words,w,tableSize,"",0,l,c+1,listCoords);
                }
            }
        
        } else {    //if 1 or more words start with "str"

            if (str.length() == 1){    //if 1 or more words start with the 1st character "str"          
                listCoords.add(new ArrayList<Integer>(Arrays.asList(l,c))); 
                
                for (int direct = 0; direct < 8; direct++){
                    switch (direct) {
                        case 0: //"Up"
                            if (l != 0) {
                                recursiveCombinations(allWordsInfo,finalTable,table,words,w,tableSize,str,direct,l-1,c,listCoords);
                            }
                            break;
            
                        case 1: //"UpRight"
                            if (l != 0 && (l != 0 && c != tableSize - 1)) {
                                recursiveCombinations(allWordsInfo,finalTable,table,words,w,tableSize,str,direct,l-1,c+1,listCoords);
                            }
                            break;
            
                        case 2: //"UpLeft"
                            if (l != 0 && (l != 0 && c != 0)) {
                                recursiveCombinations(allWordsInfo,finalTable,table,words,w,tableSize,str,direct,l-1,c-1,listCoords);
                            }
                            break;
                        
                        case 3: //"Left"
                            if (c != 0) {
                                recursiveCombinations(allWordsInfo,finalTable,table,words,w,tableSize,str,direct,l,c-1,listCoords);
                            }            
                            break;
            
                        case 4: //"Right"
                            if (c != tableSize - 1) {
                                recursiveCombinations(allWordsInfo,finalTable,table,words,w,tableSize,str,direct,l,c+1,listCoords);
                            }    
                            break;
            
                        case 5: //"Down"
                            if (l != tableSize - 1) {
                                recursiveCombinations(allWordsInfo,finalTable,table,words,w,tableSize,str,direct,l+1,c,listCoords);
                            }    
                            break;
            
                        case 6: //"DownLeft"
                            if ((l != tableSize - 1) && ((l != tableSize - 1) && (c != 0))) {
                                recursiveCombinations(allWordsInfo,finalTable,table,words,w,tableSize,str,direct,l+1,c-1,listCoords);
                            }    
                            break;
            
                        case 7: //"DownRight"
                            if ((l != tableSize - 1) && ((l != tableSize - 1) && (c != tableSize - 1))) {
                                recursiveCombinations(allWordsInfo,finalTable,table,words,w,tableSize,str,direct,l+1,c+1,listCoords);
                            }    
                            break;
            
                        default:
                            System.out.println("Error");
                    }
                }
                listCoords.clear(); //clearing the coord list after looking for all the possibilities of the 1st char

                if (c == tableSize - 1 && l == tableSize - 1) {
                    ;    
                } else if (c == tableSize - 1) {
                    recursiveCombinations(allWordsInfo,finalTable,table,words,w,tableSize,"",0,l+1,0,listCoords);
                } else {
                    recursiveCombinations(allWordsInfo,finalTable,table,words,w,tableSize,"",0,l,c+1,listCoords);
                }

            } else {

                listCoords.add(new ArrayList<Integer>(Arrays.asList(l,c))); //add current position to the list of coords

                if (w.equals(str)) {   //encontra-se a palavra

                    //store the first character position of the word we're looking, the direction and store the information in infoKey
                    String wFirstPos = String.valueOf(listCoords.get(0).get(0)+1) + "," + String.valueOf(listCoords.get(0).get(1)+1);
                    String wDirection = directions[direction];
                    String infoKey = String.valueOf(w.length()) + "-" + wFirstPos + "-" + wDirection;
                    
                    //check if it belongs to another word 
                    boolean invalid = true;
                    for (ArrayList<Integer> coords : listCoords) {
                        if (finalTable.get(coords.get(0)).get(coords.get(1)).equals('.')) invalid = false; //if any of the table positions is '.' in those coordinates, then we can continue
                    }
                    if (invalid == false) {     //if the word is not invalid
                        for (ArrayList<Integer> coords : listCoords) {
                            finalTable.get(coords.get(0)).set(coords.get(1),table.get(coords.get(0)).get(coords.get(1))); //add the word to the table
                            allWordsInfo.put(w.toLowerCase(),infoKey);  //add word info to allWordsInfo
                        }
                    } 
                }
                
                
                switch (direction) {
                    case 0: //"Up"
                        if (l != 0) {
                            recursiveCombinations(allWordsInfo,finalTable,table,words,w,tableSize,str,direction,l-1,c,listCoords);
                        }
                        break;
        
                    case 1: //"UpRight"
                        if (l != 0 && (l != 0 && c != tableSize - 1)) {
                            recursiveCombinations(allWordsInfo,finalTable,table,words,w,tableSize,str,direction,l-1,c+1,listCoords);
                        }
                        break;
        
                    case 2: //"UpLeft"
                        if (l != 0 && (l != 0 && c != 0)) {
                            recursiveCombinations(allWordsInfo,finalTable,table,words,w,tableSize,str,direction,l-1,c-1,listCoords);
                        }
                        break;
        
                    case 3: //"Left"
                        if (c != 0) {
                            recursiveCombinations(allWordsInfo,finalTable,table,words,w,tableSize,str,direction,l,c-1,listCoords);
                        }            
                        break;
        
                    case 4: //"Right"
                        if (c != tableSize - 1) {
                            recursiveCombinations(allWordsInfo,finalTable,table,words,w,tableSize,str,direction,l,c+1,listCoords);
                        }    
                        break;
        
                    case 5: //"Down"
                        if (l != tableSize - 1) {
                            recursiveCombinations(allWordsInfo,finalTable,table,words,w,tableSize,str,direction,l+1,c,listCoords);
                        }    
                        break;
        
                    case 6: //"DownLeft"
                        if ((l != tableSize - 1) && ((l != tableSize - 1) && (c != 0))) {
                            recursiveCombinations(allWordsInfo,finalTable,table,words,w,tableSize,str,direction,l+1,c-1,listCoords);
                        }    
                        break;
        
                    case 7: //"DownRight"
                        if ((l != tableSize - 1) && (l != tableSize - 1 && c != tableSize - 1)) {
                            recursiveCombinations(allWordsInfo,finalTable,table,words,w,tableSize,str,direction,l+1,c+1,listCoords);
                        }    
                        break;
        
                    default:
                        System.out.println("Error");
                }
                ArrayList<Integer> firstPos = listCoords.get(0);
                listCoords.clear();
                listCoords.add(firstPos);   //reset the listCoords but store the 1st position
            }
        }    
    }
}