package ex2;

import java.util.*;

public class Contacts  implements ContactsInterface{
    private List<Contact> contact_lst = new ArrayList<>();
    private ContactsStorageInterface store;

    public void openAndLoad(ContactsStorageInterface store){
        this.store = store;

        for (Contact contact : store.loadContacts()) {
            if (!this.exist(contact)) this.contact_lst.add(contact);
        }

        System.out.println();
        System.out.println("------- LISTA DE CONTACTOS --------");
        for (Contact c : this.contact_lst) {
            System.out.println(c.toString());
        }
        System.out.println("-----------------------------------");
        System.out.println();
    }

    public void saveAndClose(){
        boolean s = this.store.saveContacts(this.contact_lst);

        if (!s) {
            System.out.println("[ERRO]: falha ao guardar");
        }
    } 
    
    public void saveAndClose(ContactsStorageInterface store) {
        boolean s = store.saveContacts(this.contact_lst);

        if (!s) {
            System.out.println("[ERRO]: falha ao guardar");
        } else {
            System.out.println("A sua lista de contactos foi guardada com sucesso.");
        }
    }

    public boolean exist(Contact contact) {
        if (contact == null) return false;
        for (Contact c : this.contact_lst) {
            if (c.toString().equals(contact.toString())) return true;
        }
        return false;
    }

    public Contact getByName(String name) {
        for (Contact c : this.contact_lst) {
            if (c.getName().equals(name)){
                System.out.println("Número de '" + name + "': " + c.getPhone());
                return c;
            }
        }
        return null;
    }

    public boolean add(Contact contact) {
        contact_lst.add(contact);
        System.out.println("Contato adicionado: " + contact);
        return true;
    }

    public boolean remove(Contact contact) {
        contact_lst.remove(contact);
        System.out.println("Contato removido: " + contact);
        return true;
    }
}
