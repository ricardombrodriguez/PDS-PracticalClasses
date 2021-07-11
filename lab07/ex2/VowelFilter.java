public class VowelFilter extends FilterDecorator{
    
    public VowelFilter(TextReaderInterface t) {
        super(t);
    }

    public boolean hasNext() { 
        return super.hasNext();
    }

    public String next() {
        String str = super.next();
        String vowels = "AEIOUaeiou";
        String str_final = "";

        for (int i = 0; i < str.length(); i++) {
            if ( str.charAt(i) == ' ' || vowels.indexOf( str.charAt(i) ) == -1) {
                str_final += str.charAt(i);
            }
        }
        return str_final;
    }
}
