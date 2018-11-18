package ElectricityV010;

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

    private ArrayList<PathElectricCurrent> arrayListPathElectricCurrents;

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
        contact.arrayListPathElectricCurrents = new ArrayList<>();

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
        if (this.arrayListPathElectricCurrents.size() != 0) {
            for (PathElectricCurrent pathElectricCurrent: arrayListPathElectricCurrents) {
                if (eventPathElectricCurrent.getEventElectricity().equals(pathElectricCurrent.getEventElectricity()))
                    return;
            }
        }

        PathElectricCurrent pathElectricCurrentClone = (PathElectricCurrent) eventPathElectricCurrent.clone(); // кланируем путь
        pathElectricCurrentClone.addContactToStart(this); // добавляем в путь контакт
        this.arrayListPathElectricCurrents.add(pathElectricCurrentClone);

        // получем контакт соединения в объекте ElectricityObject согласно схемы включения
        VoltageTerminal voltageTerminal = this.electricityObject.getPathElectricCurrent(new VoltageTerminal(
                pathElectricCurrentClone.getEventElectricity(),
                pathElectricCurrentClone.getContactToStart()));

        if (voltageTerminal != null) {
            // Проверяем наличие напряжения от источника напрядения eventPathElectricCurrent.getEventElectricity()
            // Если есть такой источник, ничего не делаем
            // Если нет, добавляем данные о появлении напряжения
            ArrayList<PathElectricCurrent> arrayListPathElectricCurrentsElectricityObject = voltageTerminal.getContact().arrayListPathElectricCurrents;
            if (arrayListPathElectricCurrentsElectricityObject.size() != 0) {
                for (PathElectricCurrent pathElectricCurrent: arrayListPathElectricCurrentsElectricityObject) {
                    if (voltageTerminal.getEventElectricity().equals(pathElectricCurrent.getEventElectricity()))
                        return;
                }
            }

            PathElectricCurrent pathElectricCurrentsElectricityObjectClone = (PathElectricCurrent) pathElectricCurrentClone.clone(); // кланируем путь
            arrayListPathElectricCurrentsElectricityObject.add(pathElectricCurrentsElectricityObjectClone);
            pathElectricCurrentsElectricityObjectClone.addContactToStart(voltageTerminal.getContact()); // добавляем в путь контакт
            voltageTerminal.getContact().electricityConsumer.electricityOn(pathElectricCurrentsElectricityObjectClone);

        }
    }

    public void electricityOff(PathElectricCurrent eventPathElectricCurrent) throws CloneNotSupportedException {
        PathElectricCurrent pathElectricCurrentClone = (PathElectricCurrent) eventPathElectricCurrent.clone(); // кланируем путь
        pathElectricCurrentClone.addContactToStart(this); // добавляем в путь контакт

        // Проверяем если данный источник напряжения присудствует, его требуется удалить
        if (this.arrayListPathElectricCurrents.size() != 0) {
            for (PathElectricCurrent pathElectricCurrent : arrayListPathElectricCurrents) {
                if (pathElectricCurrentClone.getEventElectricity().equals(pathElectricCurrent.getEventElectricity())) {
                    System.out.println("пропало напряжение:" + pathElectricCurrent);
                    this.arrayListPathElectricCurrents.remove(pathElectricCurrent);
                    break;
                }
            }
        }

        // получем контакт соединения в объекте ElectricityObject согласно схемы включения
        VoltageTerminal voltageTerminal = this.electricityObject.getPathElectricCurrent(new VoltageTerminal(
                pathElectricCurrentClone.getEventElectricity(),
                pathElectricCurrentClone.getContactToStart()));

        if (voltageTerminal != null) {
            PathElectricCurrent pathElectricCurrentsElectricityObjectClone = (PathElectricCurrent) pathElectricCurrentClone.clone(); // кланируем путь

            // Проверяем наличие напряжения от источника напрядения eventPathElectricCurrent.getEventElectricity()
            // Если есть такой источник, ничего не делаем
            // Если нет, добавляем данные о появлении напряжения
            ArrayList<PathElectricCurrent> arrayListPathElectricCurrentsElectricityObject = voltageTerminal.getContact().arrayListPathElectricCurrents;
            if (arrayListPathElectricCurrentsElectricityObject.size() != 0) {
                for (PathElectricCurrent pathElectricCurrent: arrayListPathElectricCurrentsElectricityObject) {
                    if (voltageTerminal.getEventElectricity().equals(pathElectricCurrent.getEventElectricity()))
                        System.out.println("пропало напряжение:" + pathElectricCurrent);
                        arrayListPathElectricCurrentsElectricityObject.remove(pathElectricCurrent);
                        break;
                }
            }

            pathElectricCurrentsElectricityObjectClone.addContactToStart(voltageTerminal.getContact()); // добавляем в путь контакт
            voltageTerminal.getContact().electricityConsumer.electricityOff(pathElectricCurrentsElectricityObjectClone);

        }

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

    public ArrayList<PathElectricCurrent> getArrayListPathElectricCurrents() {
        return this.arrayListPathElectricCurrents;
    }

    String toStringArrayListPathElectricCurrents(){
        String s = "";
        for (PathElectricCurrent pathElectricCurrent: this.arrayListPathElectricCurrents) {
            s = s + pathElectricCurrent + "\n\r\t\t";
        }

        return this.name + ":" + this.contactNumber + " " + s;
    }
}

