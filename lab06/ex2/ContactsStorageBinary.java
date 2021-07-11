package ex2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactsStorageBinary implements ContactsStorageInterface {
    private File file;

    public ContactsStorageBinary(File readInputFile) {
        this.file = readInputFile;
    }

    public ContactsStorageBinary() {}

    public List<Contact> loadContacts() {
        List<Contact> contact_list = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = reader.readLine();

            while (line != null) {

                String[] parts = line.split("\t");
                Contact c = new Contact(parts[0], parts[1]);
                contact_list.add(c);
                line = reader.readLine();
            }

            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contact_list;
    }

    public boolean saveContacts(List<Contact> list) {

        try {
            BufferedWriter bwr = new BufferedWriter( new FileWriter(file) );

            for (Contact c : list) {
                bwr.write(c.toString() + "\n");
            }
            bwr.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    };
}