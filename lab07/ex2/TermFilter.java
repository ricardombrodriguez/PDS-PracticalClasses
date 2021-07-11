public class TermFilter extends FilterDecorator{

    private int numWords = -1;
    
    public TermFilter(TextReaderInterface t) {
        super(t);
    }

    public boolean hasNext() { 
        String str = super.next();
        String words[] = str.split("\\s");
        if (this.numWords == words.length - 1) return false;
        return true;
    }

    public String next() {
        String str = super.next();
        String words[] = str.split("\\s");
        this.numWords++;
        return words[this.numWords];
    }
    
}
