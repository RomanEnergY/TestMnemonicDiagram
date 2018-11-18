package ElectricityV009;

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
public class PathElectricCurrent implements Cloneable{
    private EventElectricity eventElectricity; // событие наличия напряжения
    private ArrayList<Contact> contacts; // путь тока от генератора до контакта соединения

    PathElectricCurrent(){
        this.eventElectricity = new EventElectricity();
        this.contacts = new ArrayList<>();
    }
    /**
     * Конструктор создает новый чистый объект
     */
    PathElectricCurrent(EventElectricity eventElectricity) {
        this.eventElectricity = eventElectricity;
        this.contacts = new ArrayList<>();
    }

    /**
     * Конструктор создает копию пути электрического тока
     * @param eventPathElectricCurrent
     */
//    public PathElectricCurrent(PathElectricCurrent eventPathElectricCurrent) {
//        this.contacts = (ArrayList<Contact>) eventPathElectricCurrent.getContactsArrayList().clone();
//        this.eventElectricity = eventPathElectricCurrent.getEventElectricity();
//    }

//    public PathElectricCurrent getClone(){
//        PathElectricCurrent pathElectricCurrent = new PathElectricCurrent();
//        pathElectricCurrent.contacts = (ArrayList<Contact>) this.contacts.clone();
//        pathElectricCurrent.eventElectricity = this.eventElectricity;
//
//        return pathElectricCurrent;
//    }

    EventElectricity getEventElectricity() {
        return this.eventElectricity;
    }

    public ArrayList<Contact> getContactsArrayList() {
        return this.contacts;
    }

    public Contact getContactLast(){
        if (this.contacts.size() == 0) {
            return null;
        }

        return this.contacts.get(this.contacts.size() - 1);
    }

    @Override
    public String toString() {
        return "ContactEventElectricity{" +
                "eventElectricity=" + eventElectricity +
                ", contact=" + contacts +
                '}';
    }

    void addContact(Contact contact){
        this.contacts.add(contact);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        PathElectricCurrent pathElectricCurrent = new PathElectricCurrent();
        pathElectricCurrent.contacts = (ArrayList<Contact>) this.contacts.clone();
        pathElectricCurrent.eventElectricity = this.eventElectricity;

        return pathElectricCurrent;
    }
}
