interface Command<T> {
    public boolean execute(T element);
    public boolean undo();
}