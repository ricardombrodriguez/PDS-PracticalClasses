package ex2;

public class Contact {
    private String numContact;
    private String nome;

    public Contact(String nome, String num) {
        this.numContact = num;
        this.nome = nome;
    }

    public String getName(){
        return this.nome;
    }

    public String getPhone(){
        return this.numContact;
    }

    public String toString() {
        return String.format("%-20s\t%s", this.nome, this.numContact);
    }
    
}
