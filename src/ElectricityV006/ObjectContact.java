package ElectricityV006;

import java.util.ArrayList;

/**
 * Created by user on 04.05.2018.
 */
public class ObjectContact implements ElectricityConnection{
    private Contact contact;
    private ArrayList<Contact> contactArrayList;

    static ObjectContact Factory(String nameContact, ContactNumber contactNumber, ElectricityConnection electricityConnection) {
        ObjectContact objectContact = new ObjectContact();
        objectContact.contact = Contact.Factory(nameContact, contactNumber, electricityConnection);
        objectContact.contactArrayList = new ArrayList<>();

        return objectContact;
    }

    public Contact getContact() {
        return contact;
    }

    public ArrayList<Contact> getContactArrayList() {
        return contactArrayList;
    }

    @Override
    public String toString() {
        return "contact=" + contact +
                ", contactArrayList=" + contactArrayList
                ;
    }

    @Override
    public void connect(Contact contact_connect, ArrayList<Contact> contactArrayList) {

    }

    @Override
    public void connect(Contact contact_This, Contact contact_connect) {

    }

    @Override
    public ArrayList<Contact> getConnectContactArrayList(Contact contact) {
        return null;
    }
}
