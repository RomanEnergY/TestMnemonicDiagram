package ElectricityV011;

import java.util.ArrayList;

/** Объект описывает eventElectricity путь прохождения электрического тока от источника до последнего контакта
 * необходим для:
 * 1. при отключении/размыкании контактов для проверки в нутри объекта ElectricityObject
 * с какого именно контакта напряжении не снимется при реализации метода ОТКЛЮЧЕНИЯ и снимется/пропадет
 * для дальнейшей передачи объектам ElectricityObject события отключения.
 * 2. при включении/замыкании контактов необходим для проверки в нутри объекта ElectricityObject
 * с какого именно контакта напряжении будет поданно при ВКЛЮЧЕНИИ, для дальнейшей передачи объектам
 * ElectricityObject события включения.
 *
 * Зверик Роман Станиславович 12.05.2018.
 */
public class PathElectricCurrent {
    private EventElectricity eventElectricity; // событие наличия напряжения
    private ArrayList<Contact> contacts; // путь тока от генератора до контакта соединения

    PathElectricCurrent(EventElectricity eventElectricity, ArrayList<Contact> contacts){
        this.eventElectricity = eventElectricity;
        this.contacts = contacts;
    }
    /**
     * Конструктор создает новый чистый объект
     */
    PathElectricCurrent(EventElectricity eventElectricity) {
        this.eventElectricity = eventElectricity;
        this.contacts = new ArrayList<>();
    }

    EventElectricity getEventElectricity() {
        return this.eventElectricity;
    }

    public ArrayList<Contact> getContactsArrayList() {
        return this.contacts;
    }

    public Contact getContactToStart(){
        if (this.contacts.size() == 0) {
            return null;
        }

//        return this.contacts.get(this.contacts.size() - 1);
        return this.contacts.get(0);
    }

    @Override
    public String toString() {
        return "ContactEventElectricity{" +
                "eventElectricity=" + eventElectricity +
                ", contact=" + contacts +
                '}';
    }

    void addContactToStart(Contact contact){
//        this.contacts.add(contact);
        this.contacts.add(0, contact);
    }
}
