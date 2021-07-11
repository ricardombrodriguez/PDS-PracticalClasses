import java.util.Iterator;
import java.util.ListIterator;

public class Teste {
    public static void main(String[] args) {
        VectorGeneric<Integer> vector = new VectorGeneric<>();

        for (int i = 0; i < 21; i++) {
            vector.addElem(i);
        }

        System.out.println("------------Iterator------------");

        Iterator<Integer> it = vector.iterator();
        while (it.hasNext()) {
            int n = it.next();
            System.out.println("index: "+ n);
        }

        System.out.println("------------List Iterator------------");

        ListIterator<Integer> lt = vector.listIterator();
        while (lt.hasNext()) {
            int next = lt.next();
            System.out.println("index: "+ next + "| next index: " + lt.nextIndex());
        }

    }
}