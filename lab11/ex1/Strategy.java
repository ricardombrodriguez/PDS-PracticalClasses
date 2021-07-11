import java.util.ArrayList;

public interface Strategy {
    public ArrayList<Smartphone> show(ArrayList<Smartphone> smartphones);
}

class BubbleSort implements Strategy {
    
    public ArrayList<Smartphone> show(ArrayList<Smartphone> smartphones) {
        System.out.println("Implementing bubble sort:");
        for (Smartphone phone : smartphones) {
            System.out.println(phone);
        }
        System.out.println();
        return smartphones;
    }

}

class HeapSort implements Strategy {
    
    public ArrayList<Smartphone> show(ArrayList<Smartphone> smartphones) {
        System.out.println("Implementing heap sort:");
        for (Smartphone phone : smartphones) {
            System.out.println(phone);
        }
        System.out.println();
        return smartphones;
    }

}

class InsertionSort implements Strategy {
    
    public ArrayList<Smartphone> show(ArrayList<Smartphone> smartphones) {
        System.out.println("Implementing insertion sort:");
        for (Smartphone phone : smartphones) {
            System.out.println(phone);
        }
        System.out.println();
        return smartphones;
    }

}