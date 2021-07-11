public class Main {

    public static void main(String[] args){

        //5 produtos, 3 clientes e 1 gestor
        Produto p1 = new Produto("Bola da final do Euro 2016 assinada pelo Eder", 10000.0);
        Produto p2 = new Produto("Pimenta especial do Ric Fazeres", 200.0);
        Produto p3 = new Produto("Slot machine antiga", 1250.0);
        Produto p4 = new Produto("Pintura de Van Gogh", 1500.0);
        Produto p5 = new Produto("Fiat 500 rosa choque", 5000.0);
        Produto p6 = new Produto("Tapeçaria Chinesa", 80.0);

        Cliente c1 = new Cliente("Martinha Paíga");
        Cliente c2 = new Cliente("Joãozinho Reizão");
        Cliente c3 = new Cliente("Richie Martinez");

        Gestor g1 = new Gestor("Dan Dotson");

        System.out.println("==================================== LEILOEIRA CABRAL MONCADA ====================================");
        System.out.println();

        g1.receberNotificacoes(p1);
        g1.receberNotificacoes(p2);
        g1.receberNotificacoes(p3);
        g1.receberNotificacoes(p4);
        g1.receberNotificacoes(p5);

        c1.receberNotificacoes(p1);
        c1.receberNotificacoes(p3);
        c1.receberNotificacoes(p4);

        c2.receberNotificacoes(p1);
        c2.receberNotificacoes(p3);
        c2.receberNotificacoes(p4);

        c3.receberNotificacoes(p1);
        c3.receberNotificacoes(p2);
        c3.receberNotificacoes(p5);
        c3.receberNotificacoes(p6);

        //Colocar os produtos em LEILAO:
        g1.atualizarEstado(p1, Estado.LEILAO);
        g1.atualizarEstado(p3, Estado.LEILAO);
        g1.atualizarEstado(p4, Estado.LEILAO);       //não recebe licitações     

        System.out.printf("\n==================================================================================================================\n");

        c1.licitar(p2, 250.0);      //não deve dar (não está em leilão)
        c2.licitar(p5, 600.0);      //não deve dar (não está em leilão)

        System.out.printf("\n==================================================================================================================\n");

        c1.licitar(p1, 12000.0);
        c2.licitar(p1, 11000.0);    //licitação inválida (<12000)
        c3.licitar(p1, 13000.0);
        c2.licitar(p1, 14000.0);
        g1.atualizarEstado(p1, Estado.VENDIDO);

        System.out.printf("==================================================================================================================\n\n");

        c1.licitar(p3, 1350.0);
        c2.licitar(p3, 1400.0);
        g1.atualizarEstado(p3, Estado.VENDIDO);

        System.out.printf("==================================================================================================================\n\n");

        g1.atualizarEstado(p4, Estado.STOCK);
        
        System.out.printf("==================================================================================================================\n\n");

        g1.atualizarEstado(p6, Estado.LEILAO);  
        c3.licitar(p6, 120.5);
        g1.atualizarEstado(p6, Estado.VENDIDO);
    }

}
