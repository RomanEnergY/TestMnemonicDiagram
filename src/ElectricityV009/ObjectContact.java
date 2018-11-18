package ElectricityV009;

import ElectricityV008.ContactEventElectricity;

import java.util.ArrayList;

/**
 * Created by user on 04.05.2018.
 */
public class ObjectContact implements ElectricityConnection, ElectricityConsumer {
    private Contact mainContact;
    private ArrayList<Contact> contactConnectArrayList;


    static ObjectContact Factory(String nameContact, ContactNumber contactNumber, ElectricityObject electricityObject) {
        ObjectContact objectContact = new ObjectContact();
        objectContact.mainContact = Contact.Factory(nameContact, contactNumber, objectContact, objectContact, electricityObject);
        objectContact.contactConnectArrayList = new ArrayList<>();

        return objectContact;
    }

    Contact getMainContact() {
        return this.mainContact;
    }

    @Override
    public String toString() {
        return "ObjectContact{" +
                "mainContact=" + this.mainContact +
                ", contactConnectArrayList=" + this.contactConnectArrayList +
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
        if (this.getMainContact().getElectricityObject().isEqualsContactElectricityObject(contactConnect)) {
            System.out.println("ERROR: contactEvent:" + contactEvent.getName() + " == contactConnect:" + contactConnect.getName());
            return;
        }

        // Склеиваем в один список все контакты от события и контакты соединяемого объекта
        ArrayList<Contact> contactConnectAllArrayList = new ArrayList<>();
        contactConnectAllArrayList.add(getMainContact()); // контакт событие
        contactConnectAllArrayList.addAll(this.contactConnectArrayList); // все контакты от контакта события
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
        if (this.getMainContact().getElectricityObject().isEqualsContactElectricityObject(contactDisConnect)) {
            System.out.println("ERROR: contactEvent:" + contactEvent.getName() + " == contactConnect:" + contactDisConnect.getName());
            return;
        }

        // Склеиваем в один список все контакты от события и контакты соединяемого объекта
        ArrayList<Contact> contactConnectAllArrayList = new ArrayList<>();
        contactConnectAllArrayList.add(getMainContact()); // контакт событие
        contactConnectAllArrayList.addAll(this.contactConnectArrayList); // все контакты от контакта события
        contactConnectAllArrayList.add(contactDisConnect); // контакт соединения объекта
        contactConnectAllArrayList.addAll(contactDisConnect.getElectricityConnectionContactArrayList()); // все контакты от контакта соединения объекта

        // передаем событие о том, что требуется во всем листе соеденить контакты
        for (Contact connectAll : contactConnectAllArrayList) {
            connectAll.removeContactArrayList(contactDisConnect);
        }
    }

    @Override // ElectricityConnection
    public ArrayList<Contact> getContactArrayList() {
        return contactConnectArrayList;
    }

    @Override // ElectricityConnection
    public void addContactArrayList(ArrayList<Contact> contactArrayList) {
        for (Contact contact : contactArrayList) {
            // Проверяем, если передоваемый контакт является самим объектом, не добавляем в список сонтактов
            if (contact.equals(getMainContact()))
                continue;

            // Проверяем, если передоваемый контакт в списке добавлен ранее, запрещие добавление
            boolean b = true;
            for (Contact contact1 : this.contactConnectArrayList) {
                if (contact1.equals(contact)) {
                    b = false;
                    break;
                }
            }

            // Проверяем, если есть запрет на добавление, тогда данный контак уже был ранее добавлен
            if (b) {
                this.contactConnectArrayList.add(contact);
            }
        }
    }

    @Override // ElectricityConnection
    public void removeContactArrayList(Contact contactDisConnect) {
        this.contactConnectArrayList.remove(contactDisConnect);
        if (this.getMainContact().getElectricityObject().isEqualsContactElectricityObject(contactDisConnect)){
            this.getMainContact().getElectricityObject().getContactArrayListElectricityObject(contactDisConnect).clear();
        }

    }

    /** 1. Если это новый источник напряжения, получаем чистый путь и передаем появление напряжения на подключенные контакты
     *      1. генератор (источник напряжения)
     *      2. фидерный выключатель
     *      3. трансформатор
     * или
     * 2. Contact mainContact объекта ElectricityObject передает событие, о появлении на контакте напряжения, тем самым требуется
     * оповестить об этом событии всех подключенных контактов
     *
     * @param pathElectricCurrent передаваемый путь
     * @throws CloneNotSupportedException исключение, невозможно клонировать переданный путь
     */

    @Override // ElectricityConsumer
    public void electricityOn(PathElectricCurrent pathElectricCurrent) throws CloneNotSupportedException {
        PathElectricCurrent pathElectricCurrentClone = (PathElectricCurrent) pathElectricCurrent.clone(); // кланируем путь
        pathElectricCurrentClone.addContact(getMainContact()); // добавляем в путь главный контакт

        // оповещаем все подключенные контакты о появлении напряжения
        for (Contact contactConnect: this.contactConnectArrayList)
            contactConnect.electricityOn(pathElectricCurrentClone);
    }

    @Override // ElectricityConsumer
    public void electricityOff(EventElectricity eventElectricity, Contact contact) {
//        // Проверяем схему включения объекта ElectricityObject и если ссылка не пуста, передаем событие на следующий контакт ElectricityObject
//        ArrayList<ContactEventElectricity> contactEventElectricities = this.electricityObject.mapConnectionContactElectricityObject(eventElectricity, contact);
//        if (contactEventElectricities != null) {
//            for (ContactEventElectricity contactEventElectricity: contactEventElectricities){
//                contactEventElectricity.getPowerContacts().electricityOff(contactEventElectricity.getPowerEventElectricity());
//            }
//        }
//
//        // Передаем событие по всем точкам подключения
//        for (Contact c: getContactArrayList()) {
//            c.electricityOff(eventElectricity);
//        }

    }
}
