package ElectricityV013;

/** Напряжение на контакте
 *
 * Зверик Роман Станиславович 14.05.2018.
 */
public class VoltageTerminal {
    private EventElectricity eventElectricity; // класс напряжения
    private Contact contact; // контакт

    VoltageTerminal(EventElectricity eventElectricity, Contact contact) {
        this.eventElectricity = eventElectricity;
        this.contact = contact;
    }

    public EventElectricity getEventElectricity() {
        return this.eventElectricity;
    }

    public Contact getContact() {
        return this.contact;
    }

    @Override
    public String toString() {
        return "ContactEventElectricity{" +
                "eventElectricity=" + this.eventElectricity +
                ", contact=" + this.contact +
                '}';
    }
}
