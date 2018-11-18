package ElectricityV008;

import java.util.ArrayList;

/**
 * Created by user on 17.04.2018.
 */
public class Contact {
    private String name;
    private ContactNumber contactNumber;
    private ElectricityConnection electricityConnection;
    private ElectricityConsumer electricityConsumer;
    private ArrayList<EventElectricity> eventElectricities;

    static Contact Factory(String nameContact, ContactNumber contactNumber, ElectricityConnection electricityConnection, ElectricityConsumer electricityConsumer) {
        Contact contact = new Contact();
        contact.name = nameContact;
        contact.contactNumber = contactNumber;
        contact.electricityConnection = electricityConnection;
        contact.electricityConsumer = electricityConsumer;
        contact.eventElectricities = new ArrayList<>();

        return contact;
    }

    void connection(Contact contactConnection){
        if (electricityConnection != null) {
            this.electricityConnection.connect(this, contactConnection);
        }
    }

    void disConnection(Contact contactConnection){
        if (electricityConnection != null) {
            this.electricityConnection.disConnect(this, contactConnection);
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

    boolean isElectricityConnection() {
        if (electricityConnection != null)
            return true;
        else
            return false;
    }

    public String getName() {
        return name;
    }

    public ContactNumber getContactNumber() {
        return contactNumber;
    }

    public ArrayList<Contact> getElectricityConnectionContactArrayList() {
        if (electricityConnection != null)
            return this.electricityConnection.getContactArrayList();
        else
            return null;
    }

    public void addContactArrayList(ArrayList<Contact> contactArrayList) {
        if (electricityConnection != null)
            this.electricityConnection.addContactArrayList(contactArrayList);
    }

    public void removeContactArrayList(Contact contactDisConnect) {
        if (electricityConnection != null)
            this.electricityConnection.removeContactArrayList(contactDisConnect);
    }

    public void electricityOn(EventElectricity eventElectricity) {
        // Проверяем если данного источника нет, добавляем и вызываем метод появления напряжения
        for (EventElectricity event: eventElectricities) {
            if (event.equals(eventElectricity)) {
                return;
            }
        }

        System.out.println("Contact:electricityOn eventElectricity:" + this.toString() + ":" + eventElectricity);
        this.eventElectricities.add(eventElectricity);
        this.electricityConsumer.electricityOn(eventElectricity, this);
    }

    public void electricityOff(EventElectricity eventElectricity) {
        // Проверяем если данный источник напряжения присудствует, его требуется удалить
        for (EventElectricity event: eventElectricities) {
            if (event.equals(eventElectricity)) {
                System.out.println("Contact:electricityOff eventElectricity:" + this.toString() + ":" + eventElectricity);
                this.eventElectricities.remove(eventElectricity);
                this.electricityConsumer.electricityOff(eventElectricity, this);
                break;
            }
        }
    }
}

