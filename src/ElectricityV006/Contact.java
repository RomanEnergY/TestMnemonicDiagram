package ElectricityV006;

import java.util.ArrayList;

/**
 * Created by user on 17.04.2018.
 */
public class Contact implements ElectricityConsumer {
    protected String name;
    protected ContactNumber contactNumber;
    protected ElectricityConnection electricityConnection;

    @Override
    public void electricityOn(EventElectricity eventElectricity) {
//        System.out.println("void electricityOn " + this.name + " " + this.numberContact + " " + eventElectricity.toString());
    }

    @Override
    public void electricityOff(EventElectricity eventElectricity) {
//        System.out.println("void electricityOff " + this.name + " " + this.numberContact + " " + eventElectricity.toString());
    }

    static Contact Factory(String nameContact, ContactNumber contactNumber, ElectricityConnection electricityConnection) {
        Contact contact = new Contact();
        contact.name = nameContact;
        contact.contactNumber = contactNumber;
        contact.electricityConnection = electricityConnection;
        return contact;
    }

    public void connectThis(ArrayList<Contact> contactArrayList){
        if (electricityConnection != null) {
            this.electricityConnection.connect(this, contactArrayList);
        }
    }

    public void connection(Contact contactConnection){
        if (electricityConnection != null) {
            this.electricityConnection.connect(this, contactConnection);
        }
    }

    @Override
    public String toString() {
        return "Contact{" +
//                "name='" + name + "'," +
//                "numbCont='" + contactNumber  + "'"+
                '\'' + name + "'," +
                '\'' + contactNumber  + "'"+
                '}';
    }

    public boolean isElectricityConnection() {
        if (electricityConnection != null)
            return true;

        return false;
    }

    public String getName() {
        return name;
    }

    ArrayList<Contact> getConnectContactArrayList(Contact contact){
        return (ArrayList<Contact>) electricityConnection.getConnectContactArrayList(contact).clone();
    }

    public ContactNumber getContactNumber() {
        return contactNumber;
    }
}

