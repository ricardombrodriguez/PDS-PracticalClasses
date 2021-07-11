class Main {
    public static void main(String[] args) {
        
        String fname = "input.txt";

        TextReaderInterface r1 = new TextReader(fname);
        TextReaderInterface r2 = new NormalizationFilter(new TextReader(fname));
        TextReaderInterface r3 = new VowelFilter(new TermFilter(new TextReader(fname)));
        TextReaderInterface r4 = new VowelFilter(new NormalizationFilter(new TextReader(fname)));
        TextReaderInterface r5 = new CapitalizationFilter(new TermFilter(new TextReader(fname)));

        TextReaderInterface lista[] = {r1,r2,r3,r4,r5};

        for (TextReaderInterface  t : lista) {
            String p = t.next();
            System.out.println(p);

            p = t.next();
            System.out.println(p);

            System.out.println();

        }

    }
}
