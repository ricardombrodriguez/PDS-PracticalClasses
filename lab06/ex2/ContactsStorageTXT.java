package ex2;

import java.util.Scanner;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class ContactsStorageTXT implements ContactsStorageInterface  {  
    private File file; // por default, é criado um ficheiro "contacts.txt"
    // private List<Contact> contact_lst = new ArrayList<>();

    public ContactsStorageTXT(File readInputFile) {
        this.file = readInputFile;
    }

    public ContactsStorageTXT() {}

    public List<Contact> loadContacts() {
        
        try {

            List<Contact> contact_lst = new ArrayList<>();

            Scanner sc = new Scanner(this.file);

            while (sc.hasNextLine()){
                String line = sc.nextLine();
            
                String[] parts = line.split("\t");
                contact_lst.add( new Contact(parts[0], parts[1]) );
            }
            sc.close();
            return contact_lst;

        } catch (FileNotFoundException e) {
            System.err.println("[ERRO]: ficheiro não encontrado");
            return null;
        }
    }

    
    public boolean saveContacts(List<Contact> list){
        try {
            FileWriter wr = new FileWriter(file);
            for (Contact c : list) {
                wr.write(c.toString() +"\n");
            }
            wr.close();
            return true;

        } catch (IOException e) {
            System.err.println("[ERRO]: falha ao escrever no ficheiro");
            e.printStackTrace();
            return false;
        }
        
    }

}
