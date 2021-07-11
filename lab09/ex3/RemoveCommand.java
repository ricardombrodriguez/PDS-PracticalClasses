import java.util.Collection;

class RemoveCommand<T> implements Command<T>{
    private Collection<T> collection;
    private T lastEl;
    private boolean undo = false;

    public RemoveCommand(Collection<T> collection) { 
        this.collection = collection; 
    }

    public boolean execute(T element) { 
        boolean res = this.collection.remove(element);
        if(res){
            this.lastEl = element;
            this.undo = true;
        }
        return res; 
    }

    public boolean undo() {
        if(undo){
            boolean res = this.collection.add(lastEl);
            this.undo = false;
            return res;
        }
        System.out.println("Can't undo!");
        return false; 
    }
}
