package ex2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        File file = createFile("contacts.txt");
        ContactsStorageInterface store = new ContactsStorageTXT(file);
        Contacts lista_de_contactos = new Contacts();

        lista_de_contactos.openAndLoad(store);

        lista_de_contactos.add( new Contact("Joaquim Fonseca","912 513 435") );
        lista_de_contactos.add( new Contact("Francisca Dias","933 423 773") );
        lista_de_contactos.add( new Contact("Pastelaria Guimarães","256 325 521") );
        lista_de_contactos.add( new Contact("SIC","760 200 300") );

        // remover um contacto pelo nome
        lista_de_contactos.remove( lista_de_contactos.getByName("Joaquim Fonseca") );

        lista_de_contactos.openAndLoad(store);

        // verificar se um contacto existe
        Contact c = lista_de_contactos.getByName("Augusto Silva");

        if (lista_de_contactos.exist(c)) {
            System.out.println(">>> Contacto encontrado: "+ c.toString());
        } else {
            System.out.println(">>> Contacto não encontrado");
        }

        // pesquisar se existe um contacto, caso não exista, adiciona-se à lista
        Contact c2 = lista_de_contactos.getByName("Fernanda Magalhães");
        if (lista_de_contactos.exist(c2)) {
            System.out.println(">>> Contacto encontrado: "+ c.toString());
        } else {
            lista_de_contactos.add( new Contact("Fernanda Magalhães","912 135 963") );
        }

        lista_de_contactos.openAndLoad(store);

        lista_de_contactos.saveAndClose();
        
        lista_de_contactos.saveAndClose(store);

    }

    public static File createFile(String fname) {
        try {
            File file = new File(fname);
            file.createNewFile();
            FileWriter wr = new FileWriter(file);

            wr.write("João Bastos\t912 353 868\n");
            wr.write("Augusto Silva\t933 542 768\n");
            wr.write("Ricardo Reis\t919 968 123\n");
            wr.close();

            return file;

        } catch (IOException e) {
            System.out.println("[ERRO]: falha ao criar ficheiro");
            e.printStackTrace();
            return null;
        }
    }  
}
