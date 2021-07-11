import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {

        //Sorts não estão implementados.

        ArrayList<Smartphone> smartphones = new ArrayList<Smartphone>();
        smartphones.add(new Smartphone("iPhone X","Apple Processor 12",1000.0,64.0,"42 MP with high quality"));
        smartphones.add(new Smartphone("Xiaomi redmi note 9","XPTO PROCESSOR",200.0,128.0,"32 MP with high quality"));
        smartphones.add(new Smartphone("Samsung S8","Chipset XPTO",6000.0,64.0,"24 MP with hdr"));
        smartphones.add(new Smartphone("Nokia 3310","Potato processor",10.0,1.0,"-5 MP"));
        smartphones.add(new Smartphone("Motorola","Morad processor",100.0,32.0,"10 MP with flash"));

        BubbleSort bSort = new BubbleSort();
        Context context = new Context();
        context.setStrategy(bSort);
        context.show(smartphones);

        HeapSort hSort = new HeapSort();
        context.setStrategy(hSort);
        context.show(smartphones);

        InsertionSort iSort = new InsertionSort();
        context.setStrategy(iSort);
        context.show(smartphones);
    }

}
