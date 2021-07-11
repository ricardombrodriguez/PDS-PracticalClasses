import java.util.Arrays;

public class CapitalizationFilter extends FilterDecorator {
    
    public CapitalizationFilter(TextReaderInterface t) {
        super(t);
    }

    public boolean hasNext() { 
        return super.hasNext();
    }

    public String next() {
        String str_final = "";
        String str = super.next();
        char ch[] = str.toCharArray();
        ch[0] = Character.toUpperCase(ch[0]);
        ch[ str.length()-1 ] = Character.toUpperCase(ch[ str.length()-1 ]);
        for (char c : ch) str_final += c;
        return str_final;
    }
}
