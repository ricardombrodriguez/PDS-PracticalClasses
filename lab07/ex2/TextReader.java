import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class TextReader implements TextReaderInterface{
    private String fname;
    private int numParagraph = 1;

    public TextReader(String fname) {
        this.fname = fname;
    }

    public boolean hasNext() {
        try {
            Scanner sc = new Scanner( new File(this.fname) );
            boolean b = false;
            for (int i = 0; i < this.numParagraph ; i++) {
                b = sc.hasNext();
            }
            sc.close(); 
            return b;

        } catch (FileNotFoundException e) {
            System.out.println("[ERROR]: file not found");
            return false;
        }
    }

    public String next() {
        try {
            Scanner sc = new Scanner( new File(this.fname) );
            String p = "";
            for (int i = 0; i < this.numParagraph; i++) {
                p = sc.nextLine();
            }
            this.numParagraph++;
            sc.close();
            return p;

        } catch (FileNotFoundException e) {
            System.out.println("[ERROR]: file not found");
            return null;
        }
    }
}
