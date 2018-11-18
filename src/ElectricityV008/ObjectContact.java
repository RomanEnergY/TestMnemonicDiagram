package ElectricityV008;

import java.util.ArrayList;

/**
 * Created by user on 04.05.2018.
 */
public class ObjectContact implements ElectricityConnection, ElectricityConsumer {
    private Contact contact;
    private ArrayList<Contact> contactArrayList;
    private ElectricityObject electricityObject;

    static ObjectContact Factory(String nameContact, ContactNumber contactNumber, ElectricityObject electricityObject) {
        ObjectContact objectContact = new ObjectContact();
        objectContact.contact = Contact.Factory(nameContact, contactNumber, objectContact, objectContact);
        objectContact.contactArrayList = new ArrayList<>();
        objectContact.electricityObject = electricityObject;
        return objectContact;
    }

    public Contact getContact() {
        return contact;
    }

    @Override
    public String toString() {
        return "ObjectContact{" +
                "contact=" + contact +
                ", contactArrayList=" + contactArrayList +
                '}';
    }

    @Override // ElectricityConnection
    public void connect(Contact contactEvent, Contact contactConnect) {
        System.out.println("Contact:connect contactEvent " + contactEvent + ":--> contactConnect " + contactConnect);
        // Проверка, если передаваемые объекты равены пустате
        if (contactEvent == null || contactConnect == null) {
            if (contactEvent == null) {
                System.out.println("ERROR: Contact:connect " + contactEvent + ":contactEvent == null");
                return;
            } else if (contactConnect == null) {
                System.out.println("ERROR: Contact:connect " + contactConnect + ":contactConnect == null");
                return;
            }
        }

        // Если передаваемый объект не имеет ссылку на интерфейс ElectricityConnection
        if (!contactConnect.isElectricityConnection()) {
            System.out.println("ERROR: Contact:connect " + contactConnect + ":!contactConnect.isElectricityConnection()");
            return;
        }

        // Проверка, если передаваемый контакт для подключения является контактом объекта
        if (this.electricityObject.isEqualsContactElectricityObject(contactConnect)) {
            System.out.println("ERROR: contactEvent:" + contactEvent.getName() + " == contactConnect:" + contactConnect.getName());
            return;
        }

        // Склеиваем в один список все контакты от события и контакты соединяемого объекта
        ArrayList<Contact> contactConnectAllArrayList = new ArrayList<>();
        contactConnectAllArrayList.add(this.contact); // контакт событие
        contactConnectAllArrayList.addAll(this.contactArrayList); // все контакты от контакта события
        contactConnectAllArrayList.add(contactConnect); // контакт соединения объекта
        contactConnectAllArrayList.addAll(contactConnect.getElectricityConnectionContactArrayList()); // все контакты от контакта соединения объекта

        // передаем событие о том, что требуется во всем листе соеденить контакты
        for (Contact connectAll : contactConnectAllArrayList) {
            connectAll.addContactArrayList(contactConnectAllArrayList);
        }
    }

    @Override // ElectricityConnection
    public void disConnect(Contact contactEvent, Contact contactDisConnect) {
        System.out.println("Contact:disConnect contactEvent " + contactEvent + ":--> contactDisConnect " + contactDisConnect);
        // Проверка, если передаваемые объекты равены пустате
        if (contactEvent == null || contactDisConnect == null) {
            if (contactEvent == null) {
                System.out.println("ERROR: Contact:connect " + contactEvent + ":contactEvent == null");
                return;
            } else if (contactDisConnect == null) {
                System.out.println("ERROR: Contact:connect " + contactDisConnect + ":contactConnect == null");
                return;
            }
        }

        // Если передаваемый объект не имеет ссылку на интерфейс ElectricityConnection
        if (!contactDisConnect.isElectricityConnection()) {
            System.out.println("ERROR: Contact:connect " + contactDisConnect + ":!contactConnect.isElectricityConnection()");
            return;
        }

        // Проверка, если передаваемый контакт для подключения является контактом объекта
        if (this.electricityObject.isEqualsContactElectricityObject(contactDisConnect)) {
            System.out.println("ERROR: contactEvent:" + contactEvent.getName() + " == contactConnect:" + contactDisConnect.getName());
            return;
        }

        // Склеиваем в один список все контакты от события и контакты соединяемого объекта
        ArrayList<Contact> contactConnectAllArrayList = new ArrayList<>();
        contactConnectAllArrayList.add(this.contact); // контакт событие
        contactConnectAllArrayList.addAll(this.contactArrayList); // все контакты от контакта события
        contactConnectAllArrayList.add(contactDisConnect); // контакт соединения объекта
        contactConnectAllArrayList.addAll(contactDisConnect.getElectricityConnectionContactArrayList()); // все контакты от контакта соединения объекта

        // передаем событие о том, что требуется во всем листе соеденить контакты
        for (Contact connectAll : contactConnectAllArrayList) {
            connectAll.removeContactArrayList(contactDisConnect);
        }
    }

    @Override // ElectricityConnection
    public ArrayList<Contact> getContactArrayList() {
        return contactArrayList;
    }

    @Override // ElectricityConnection
    public void addContactArrayList(ArrayList<Contact> contactArrayList) {
        for (Contact contact : contactArrayList) {
            // Проверяем, если передоваемый контакт является самим объектом, не добавляем в список сонтактов
            if (contact.equals(this.contact))
                continue;

            // Проверяем, если передоваемый контакт в списке добавлен ранее, запрещие добавление
            boolean b = true;
            for (Contact contact1 : this.contactArrayList) {
                if (contact1.equals(contact)) {
                    b = false;
                    break;
                }
            }

            // Проверяем, если есть запрет на добавление, тогда данный контак уже был ранее добавлен
            if (b) {
                this.contactArrayList.add(contact);
            }
        }
    }

    @Override // ElectricityConnection
    public void removeContactArrayList(Contact contactDisConnect) {
        this.contactArrayList.remove(contactDisConnect);
        if (this.electricityObject.isEqualsContactElectricityObject(contactDisConnect)){
            this.electricityObject.getContactArrayListElectricityObject(contactDisConnect).clear();
        }

    }

    @Override // ElectricityConsumer
    public void electricityOn(EventElectricity eventElectricity, Contact contact) {
        // Проверяем схему включения объекта ElectricityObject и если ссылка не пуста, передаем событие на следующий контакт ElectricityObject
        ArrayList<ContactEventElectricity> contactEventElectricities = this.electricityObject.mapConnectionContactElectricityObject(eventElectricity, contact);
        if (contactEventElectricities != null) {
            for (ContactEventElectricity contactEventElectricity: contactEventElectricities){
                contactEventElectricity.getPowerContacts().electricityOn(contactEventElectricity.getPowerEventElectricity());
            }
        }

        // Передаем событие по всем точкам подключения
        for (Contact c : getContactArrayList()) {
            c.electricityOn(eventElectricity);

        }
    }

    @Override // ElectricityConsumer
    public void electricityOff(EventElectricity eventElectricity, Contact contact) {
        // Проверяем схему включения объекта ElectricityObject и если ссылка не пуста, передаем событие на следующий контакт ElectricityObject
        ArrayList<ContactEventElectricity> contactEventElectricities = this.electricityObject.mapConnectionContactElectricityObject(eventElectricity, contact);
        if (contactEventElectricities != null) {
            for (ContactEventElectricity contactEventElectricity: contactEventElectricities){
                contactEventElectricity.getPowerContacts().electricityOff(contactEventElectricity.getPowerEventElectricity());
            }
        }

        // Передаем событие по всем точкам подключения
        for (Contact c: getContactArrayList()) {
            c.electricityOff(eventElectricity);
        }

    }
}
