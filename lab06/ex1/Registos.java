package ex1;

import java.util.ArrayList;
import java.util.List;

//Petiscos
public class Registos {
    
    private ArrayList<Empregado> empregados;
    
    //Inicializa o arraylist com objetos do tipo Empregado
    public Registos(){
        empregados = new ArrayList<Empregado>();
    }

    //Adiciona Empregado ao empregados
    public void insere(Empregado emp) {
        empregados.add(emp);
    }

    //Remove Empregado de empregados consoante o código inserido
    public boolean remove(int codigo) {
        for (Empregado emp : empregados) {
            if (emp.getCodigo() == codigo) {
                empregados.remove(emp);
                return true;
            }
        }
        return false;
    }

    //Verifica se existe algum empregado com esse código
    public boolean isEmpregado(int codigo) {
        boolean result = false;
        for (int e = 0; e < empregados.size(); e++){
            if (empregados.get(e).getCodigo() == codigo) {
                result = true;
            }
        }
        return result;
    }

    //Retorna uma List com objetos do tipo Empregado a partir do arraylist empregados
    public List<Empregado> listaDeEmpregados() {
        List<Empregado> listaEmp = new ArrayList<Empregado>();
        for (Empregado emp : empregados) {
            listaEmp.add(emp);
        }
        return listaEmp;
    }
}
