package ElectricityV009;

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

    String toStringContactAll() {
        return "Conductor{" +
                "name='" + name + '\'' +
                ", " + toStringContact_p1_ArrayList() +
                '}';
    }

    String toStringContact_p1_ArrayList() {
        return "contact_p1_ArrayList=" + objectContact_p1.getContactArrayList();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Conductor{" +
                "name='" + name + '\'' +
                ", objectContact_p1=" + objectContact_p1 +
                '}';
    }

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
    public VoltageTerminal getPathElectricCurrent(VoltageTerminal voltageTerminal) {
        return null;
    }

    /**
     * Метод реализует ЗАПУСК герератора и при успешном его запуске передает событие 'this.eventElectricity'
     * на контакт 'this.contact_P1' герератора
     */
    void run() throws CloneNotSupportedException {
        // Запускаем генератор
        System.out.print('\'' + this.name + "': \"КОМАНДА ЗАПУСТИТЬ ГЕНЕРАТОР\"");
        if (this.aGeneratorRun) { // Если генератор запущен ранее --> выдаем ошибку
            System.out.println(" --> ОТМЕНА: '" + this.name + "\' запущен ранее!!!");
        }
        else { // Если генератор не включен, включаем
            this.aGeneratorRun = true;
            System.out.println(" --> генератор УСПЕШНО запущен, передаю данные о запуске герератора");

            // передаем событие объекту ObjectContact о появлении напряжения от генератора
            PathElectricCurrent pathElectricCurrent = new PathElectricCurrent(this.eventElectricity); // создаем новый путь электрического тока
            this.objectContact_p1.electricityOn(pathElectricCurrent); // передаем событие

        }
    }

    /**
     * Метод реализует ОСТАНОВКУ герератора и при успешном его останове передает событие 'this.eventElectricity'
     * на контакт 'this.contact_P1' герератора
     */
    void stop(){
        // Останавливаем генератор
        System.out.print('\'' + this.name + "': \"КОМАНДА ОСТАНОВИТЬ ГЕНЕРАТОР\"");
        if (this.aGeneratorRun) { // Если генератор запущен ранее --> останавливаем
            this.aGeneratorRun = false;
            System.out.println(" --> генератор УСПЕШНО остановлен, передаю данные об остановке герератора");
            // передаем событие о снятии напряжения с генератора контакт генератора №1
            this.objectContact_p1.getMainContact().electricityOff(this.eventElectricity);
        }
        else { // Если генератор не включен, включаем
            System.out.println(" --> ОТМЕНА: '" + this.name + "\' оставлен ранее!!!");
        }
    }

    /**
     * Метод реализует остановку герератора, если герератор ранее остановлен, выдает сообщение
     * об обшибке "ГЕРЕРАТОР остановлен ранее"
     */
//    private boolean generatorElectricityOff() {
//        System.out.print(this.name + " команда: ОСТАНОВИТЬ");
//        if (this.aElectricityOn) { // Если генератор запущен --> останавливаем
//            this.aElectricityOn = true;
//            System.out.println(" --> генератор УСПЕШНО остановлен, передаю данные об остановке герератора");
//            return true;
//        }
//        else { // Если генератор ранее остановлен --> выдаем ошибку
//            System.out.println(" --> ОТМЕНА: '" + this.name + "\' остановлен ранее!!!");
//            return false;
//        }
//    }

    public boolean isGeneratorRun() {
        return aGeneratorRun;
    }
}
