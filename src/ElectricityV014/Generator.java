package ElectricityV014;

import java.util.ArrayList;

/**
 * Зверик Роман Станиславович 10.05.2018.
 */
public class Generator implements ElectricityObject {
    private String name;
    private boolean aGeneratorRun;

    private ObjectContact objectContact_p1;
    private EventElectricity eventElectricity;

    Generator(String name) {
        this.name = name;
        this.aGeneratorRun = false;
        this.eventElectricity = new EventElectricity(this.name, Voltage.V0_4);
        this.objectContact_p1 = ObjectContact.Factory(this.name, ContactNumber.p_1, this);
    }

    Contact getContact_p1() {
        return objectContact_p1.getMainContact();
    }

    public EventElectricity getEventElectricity() {
        return eventElectricity;
    }

    String toStringConnectContactAll() {
        return "Connect:Generator{" +
                "name='" + name + '\'' +
                ", \n\r\t\t\t" + toStringContact_p1_ArrayList() +
                '}';
    }

    private String toStringContact_p1_ArrayList() {
        return "contact_p1_ArrayList=" + objectContact_p1.getContactArrayList();
    }

    public String getName() {
        return name;
    }

    /**
     * Метод реализует ЗАПУСК герератора и при успешном его запуске передает событие 'this.eventElectricity'
     * на контакт 'this.contact_P1' герератора
     */
    void run() {
        // Запускаем генератор
        System.out.print('\'' + this.name + "': \"КОМАНДА ЗАПУСТИТЬ ГЕНЕРАТОР\"");
        if (this.aGeneratorRun) { // Если генератор запущен ранее --> выдаем ошибку
            System.out.println(" --> ОТМЕНА: '" + this.name + "\' запущен ранее!!!");
        }
        else { // Если генератор не включен, включаем
            this.aGeneratorRun = true;
            System.out.println(" --> генератор УСПЕШНО запущен, передаю данные о запуске герератора");
            this.switchOn();
        }
    }

    /**
     * Метод реализует ОСТАНОВКУ герератора и при успешном его останове передает событие 'this.eventElectricity'
     * на контакт 'this.contact_P1' герератора
     */
    void stop() {
        // Останавливаем генератор
        System.out.print('\'' + this.name + "': \"КОМАНДА ОСТАНОВИТЬ ГЕНЕРАТОР\"");
        if (this.aGeneratorRun) { // Если генератор запущен ранее --> останавливаем
            this.aGeneratorRun = false;
            System.out.println(" --> генератор УСПЕШНО остановлен, передаю данные об остановке герератора");
            this.switchOff();
        }
        else { // Если генератор не включен, включаем
            System.out.println(" --> ОТМЕНА: '" + this.name + "\' оставлен ранее!!!");
        }
    }

    public boolean isGeneratorRun() {
        return aGeneratorRun;
    }

    String toStringArrayListPathElectricCurrents(){
        String s = this.getContact_p1().toStringArrayListPathElectricCurrents();
        return s;
    }

    private void switchOn() {
        // передаем событие о появлении напряжения на контакте р_1 генератора
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(this.getContact_p1());
        PathElectricCurrent pathElectricCurrent = new PathElectricCurrent(this.eventElectricity, contacts);
        this.getContact_p1().addPathElectricCurrents(pathElectricCurrent);
        this.getContact_p1().electricityOn(pathElectricCurrent); // передаем событие
    }

    private void switchOff() {
        // передаем событие об исчезновении напряжения на контакте р_1 генератора
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(this.getContact_p1());
        PathElectricCurrent pathElectricCurrent = new PathElectricCurrent(this.eventElectricity, contacts);
        this.getContact_p1().electricityOff(pathElectricCurrent); // передаем событие

    }

    @Override
    public String toString() {
        return "Conductor{" +
                "name='" + name + '\'' +
                ", objectContact_p1=" + objectContact_p1 +
                '}';
    }

    // implements ElectricityObject

    @Override // ElectricityObject
    public boolean isEqualsContactElectricityObject(Contact contact) {
        if (contact.equals(objectContact_p1.getMainContact()))
            return true;
        else
            return false;
    }

    @Override // ElectricityObject
    public ArrayList<Contact> getContactArrayListElectricityObject(Contact contact) {
        if (contact.equals(objectContact_p1.getMainContact()))
            return this.objectContact_p1.getContactArrayList();
        else
            return null;
    }

    @Override // ElectricityObject
    public VoltageTerminal getVoltageTerminal(EventElectricity eventElectricity, Contact contact) {
        return new VoltageTerminal(this.eventElectricity, this.getContact_p1());
    }

    @Override // ElectricityObject
    public Contact getContactElectricCurrent(Contact contact) {
        return null;
    }

    @Override // ElectricityObject
    public void voltageTerminalAppeared(Contact contactVoltageTerminalAppeared) {
        System.out.println(this.getClass() + " напряжение появилось " + contactVoltageTerminalAppeared);
    }

    @Override // ElectricityObject
    public void voltageTerminalDisAppeared(Contact contactVoltageTerminalDisAppeared) {
        System.out.println(this.getClass() + " напряжение исчезло полностью " + contactVoltageTerminalDisAppeared);
    }
}
