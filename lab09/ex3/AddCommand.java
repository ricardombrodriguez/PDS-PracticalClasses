import java.util.Collection;

class AddCommand<T> implements Command<T>{
    private Collection<T> collection;
    private T lastEl;
    private boolean undo = false;

    public AddCommand(Collection<T> collection) { 
        this.collection = collection; 
    }

    public boolean execute(T element) { 
        boolean res = this.collection.add(element);
        if(res){
            this.lastEl = element;
            this.undo = true;
        }
        return res; 
    }

    public boolean undo(){
        if(undo){
            boolean res = this.collection.remove(lastEl);
            this.undo = false;
            return res;
        }
        System.out.println("Can't undo!");
        return false; 
    }
}