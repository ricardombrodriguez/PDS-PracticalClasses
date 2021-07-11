import java.util.ArrayList;

public class Context {
    
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public ArrayList<Smartphone> show(ArrayList<Smartphone> smartphones) {
        return this.strategy.show(smartphones);
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

}
