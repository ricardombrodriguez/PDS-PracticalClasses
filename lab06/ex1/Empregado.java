package ex1;

//Petiscos

public class Empregado {
    
    private String nome;
    private String apelido;
    private int codigo;
    private double salario;
    
    public Empregado(String nome, String apelido, int codigo, double salario) {
        this.nome = nome;
        this.apelido = apelido;
        this.codigo = codigo;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public String getApelido() {
        return apelido;
    }

    public int getCodigo() {
        return codigo;
    }

    public double getSalario() {
        return salario;
    }

    public String toString() {
        String fullname = this.nome + " " + this.apelido;
        return String.format("%-20s (%s) | Salary: %s", fullname, this.codigo, this.salario);
    }
    
}
