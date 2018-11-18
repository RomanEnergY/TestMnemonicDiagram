package ElectricityV008;

/** Объект описывает eventElectricity наличие неснимаемого источника напряжения с contact контакта.
 * 1. при отключении/размыкании контактов необходим для проверки в нутри объекта ElectricityObject
 * с какого именно контакта напряжении не снимется при реализации метода ОТКЛЮЧЕНИЯ и снимется/пропадет
 * для дальнейшей передачи объектам ElectricityObject события отключения.
 * 2. при включении/замыкании контактов необходим для проверки в нутри объекта ElectricityObject
 * с какого именно контакта напряжении будет поданно при ВКЛЮЧЕНИИ, для дальнейшей передачи объектам
 * ElectricityObject события включения.
 *
 * Зверик Роман Станиславович 12.05.2018.
 */
public class ContactEventElectricity {
    private Contact contact; // путь тока от генератора до контакта соединения
    private EventElectricity eventElectricity; // событие наличия напряжения

    public ContactEventElectricity(Contact contact, EventElectricity eventElectricity) {
        this.contact = contact;
        this.eventElectricity = eventElectricity;
    }

    public Contact getPowerContacts() {
        return contact;
    }

    public EventElectricity getPowerEventElectricity() {
        return eventElectricity;
    }

    @Override
    public String toString() {
        return "ContactEventElectricity{" +
                "contact=" + contact +
                ", eventElectricity=" + eventElectricity +
                '}';
    }
}
