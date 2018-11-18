package ElectricityV009;

import java.util.ArrayList;

/**
 * Created by user on 17.04.2018.
 */
public class Contact {
    private String name;
    private ContactNumber contactNumber;
    private ElectricityConnection electricityConnection;
    private ElectricityConsumer electricityConsumer;
    private ElectricityObject electricityObject;

    private PathElectricCurrent pathElectricCurrent;

    static Contact Factory(String nameContact,
                           ContactNumber contactNumber,
                           ElectricityConnection electricityConnection,
                           ElectricityConsumer electricityConsumer,
                           ElectricityObject electricityObject) {
        Contact contact = new Contact();
        contact.name = nameContact;
        contact.contactNumber = contactNumber;
        contact.electricityConnection = electricityConnection;
        contact.electricityConsumer = electricityConsumer;
        contact.electricityObject = electricityObject;
        contact.pathElectricCurrent = null;

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

    ArrayList<Contact> getElectricityConnectionContactArrayList() {
        if (electricityConnection != null)
            return this.electricityConnection.getContactArrayList();
        else
            return null;
    }

    void addContactArrayList(ArrayList<Contact> contactArrayList) {
        if (electricityConnection != null)
            this.electricityConnection.addContactArrayList(contactArrayList);
    }

    void removeContactArrayList(Contact contactDisConnect) {
        if (electricityConnection != null)
            this.electricityConnection.removeContactArrayList(contactDisConnect);
    }

    public void electricityOn(PathElectricCurrent eventPathElectricCurrent) throws CloneNotSupportedException {
        // Проверяем если данного источника нет, добавляем и вызываем метод появления напряжения
        if (this.pathElectricCurrent != null) {
            if (eventPathElectricCurrent.getEventElectricity().equals(this.pathElectricCurrent.getEventElectricity()))
                return;
        }

        this.pathElectricCurrent = (PathElectricCurrent) eventPathElectricCurrent.clone(); // кланируем путь
        this.pathElectricCurrent.addContact(this); // добавляем в путь контакт

        // получем контакт соединения в объекте ElectricityObject согласно схемы включения
        VoltageTerminal voltageTerminal = this.electricityObject.getPathElectricCurrent(new VoltageTerminal(
                this.pathElectricCurrent.getEventElectricity(),
                this.pathElectricCurrent.getContactLast()));

        if (voltageTerminal != null) {
            voltageTerminal.getContact().pathElectricCurrent = (PathElectricCurrent) this.pathElectricCurrent.clone();
            voltageTerminal.getContact().pathElectricCurrent.addContact(voltageTerminal.getContact());
            voltageTerminal.getContact().electricityConsumer.electricityOn(this.pathElectricCurrent);
        }

    }

    public void electricityOff(EventElectricity eventElectricity) {
        // Проверяем если данный источник напряжения присудствует, его требуется удалить
//        for (EventElectricity event: eventElectricities) {
//            if (event.equals(eventElectricity)) {
//                System.out.println("Contact:electricityOff eventElectricity:" + this.toString() + ":" + eventElectricity);
//                this.eventElectricities.remove(eventElectricity);
//                this.electricityConsumer.electricityOff(eventElectricity, this);
//                break;
//            }
//        }
    }

    public ElectricityObject getElectricityObject() {
        return electricityObject;
    }

    public void addPathElectricCurrent(PathElectricCurrent eventPathElectricCurrent) {
//        eventPathElectricCurrent.addContactLast(this);
//        this.pathElectricCurrent = eventPathElectricCurrent.getClone(); // создаем клон
//        VoltageTerminal voltageTerminal = new VoltageTerminal(eventPathElectricCurrent.getEventElectricity(), this);
//        voltageTerminal = this.electricityObject.getPathElectricCurrent(voltageTerminal);
//        voltageTerminal.getContact().electricityConsumer.electricityOn(this.pathElectricCurrent);
//
//        System.out.println(this.getClass() + " " + toString() + " this.pathElectricCurrent:" + this.pathElectricCurrent);


//        for (PathElectricCurrent electricCurrent: this.pathElectricCurrents) {
//            if (!electricCurrent.getEventElectricity().equals(eventPathElectricCurrent.getEventElectricity())) {
//                this.pathElectricCurrents.add(eventPathElectricCurrent);
//                VoltageTerminal voltageTerminal = new VoltageTerminal(eventPathElectricCurrent.getEventElectricity(), this);
//                System.out.println("voltageTerminal:" + voltageTerminal);
//                voltageTerminal = this.electricityObject.getPathElectricCurrent(voltageTerminal);
//
//
//
//                System.out.println("voltageTerminal:" + voltageTerminal);
////
////
////                this.electricityObject.getPathElectricCurrent()
//            }
//        }
    }

    public PathElectricCurrent getPathElectricCurrent() {
        return this.pathElectricCurrent;
    }
}

