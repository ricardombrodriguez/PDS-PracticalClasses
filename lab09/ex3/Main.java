import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        RemoteControl control = new RemoteControl();
        Collection collection = new ArrayList<>();

        Command add = new AddCommand(collection);
        Command remove = new RemoveCommand(collection);

        control.setCommand(add);
        control.execute("Da faizeres");
        control.execute("Artureiro");
        control.execute("Bernoso");
        control.execute("Serrulli");
        control.execute("Protocolo OSPF");
        control.execute("Halland");
        control.execute("Porto");

        System.out.println(collection);
        control.undo();
        System.out.println(collection);


        control.setCommand(remove);
        control.execute("Mary da Channels");
        control.execute("Unleal");

        control.undo();
        System.out.println(collection);
    }
}