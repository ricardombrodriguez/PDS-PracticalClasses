abstract class FilterDecorator implements TextReaderInterface{
    
    protected TextReaderInterface t;
    
    public FilterDecorator(TextReaderInterface t) {
        this.t = t;
    }
    
    @Override public boolean hasNext() { return t.hasNext();}
    @Override public String next() { return t.next();}
}
