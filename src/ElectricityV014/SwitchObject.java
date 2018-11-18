package ElectricityV014;

import java.util.ArrayList;

/** Класс описывающий ВЫКЛЮЧАТЕЛЬ
 *
 * Зверик Роман Станиславович 23.04.2018.
 */
public class SwitchObject implements ElectricityObject {
    private String name; // наименование объекта
    private boolean aBoolean_p1_p2; // состояние объекта, true - включен
    private boolean fider;

    private ObjectContact objectContact_p1; // контакт №1
    private ObjectContact objectContact_p2; // контакт №2

    SwitchObject(String name, boolean fider) {
        this.name = name;
        this.aBoolean_p1_p2 = true;
        this.fider = fider;
        this.objectContact_p1 = ObjectContact.Factory(this.name, ContactNumber.p_1, this);
        this.objectContact_p2 = ObjectContact.Factory(this.name, ContactNumber.p_2, this);
    }

    Contact getContact_p1() {
        return objectContact_p1.getMainContact();
    }

    Contact getContact_p2() {
        return objectContact_p2.getMainContact();
    }

    String toStringConnectContactAll() {
        return "Connect:Conductor{" +
                "name='" + name + '\'' +
                ", \n\r\t\t\t" + toStringContact_p1_ArrayList() + "\n\r\t\t\t" + toStringContact_p2_ArrayList() +
                '}';
    }

    private String toStringContact_p1_ArrayList() {
        return "contact_p1_ArrayList=" + objectContact_p1.getContactArrayList();
    }

    private String toStringContact_p2_ArrayList() {
        return "contact_p2_ArrayList=" + objectContact_p2.getContactArrayList();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Conductor{" +
                "name='" + name + '\'' +
                ", objectContact_p1=" + objectContact_p1 +
                ", objectContact_p2=" + objectContact_p2 +
                '}';
    }

    @Override // ElectricityObject
    public boolean isEqualsContactElectricityObject(Contact contact) {
        if (contact.equals(objectContact_p1.getMainContact()))
            return true;
        else if (contact.equals(objectContact_p2.getMainContact()))
            return true;
        else
            return false;
    }

    @Override // ElectricityObject
    public ArrayList<Contact> getContactArrayListElectricityObject(Contact contact) {
        if (contact.equals(objectContact_p1.getMainContact()))
            return this.objectContact_p1.getContactArrayList();
        else if (contact.equals(objectContact_p2.getMainContact()))
            return this.objectContact_p2.getContactArrayList();
        else
            return null;
    }

    @Override // ElectricityObject
    public VoltageTerminal getVoltageTerminal(EventElectricity eventElectricity, Contact contact) {
        if (contact.equals(objectContact_p1.getMainContact())) { // если появилось напряжение на контакте №1
            if (this.aBoolean_p1_p2) { // если объект включен
                // передаем сообщение, о том что на контакте №2 появится напряжение точно такое же как на контакте №1
//                System.out.println(this.getClass() + " return objectContact_p2:" + objectContact_p2);
                if (fider){
                    return new VoltageTerminal(new EventElectricity(this.name, eventElectricity.getVoltage()), objectContact_p2.getMainContact());
                }
                else {
                    return new VoltageTerminal(eventElectricity, objectContact_p2.getMainContact());
                }

            }
        } else if (contact.equals(objectContact_p2.getMainContact())) { // если появилось напряжение на контакте №2
            if (this.aBoolean_p1_p2) { // если объект включен
                // передаем сообщение, о том что на контакте №1 появится напряжение точно такое же как на контакте №2
//                System.out.println(this.getClass() + " return objectContact_p1:" + objectContact_p1);
                if (fider){
                    return new VoltageTerminal(new EventElectricity(this.name, eventElectricity.getVoltage()), objectContact_p1.getMainContact());
                }
                else {
                    return new VoltageTerminal(eventElectricity, objectContact_p1.getMainContact());
                }
            }

        }
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

    @Override // ElectricityObject
    public Contact getContactElectricCurrent(Contact contact) {
        if (this.aBoolean_p1_p2) {
            if (contact.equals(objectContact_p1.getMainContact())) {
                return objectContact_p2.getMainContact();
            } else if (contact.equals(objectContact_p2.getMainContact())) {
                return objectContact_p1.getMainContact();
            } else
                return null;
        } else
            return null;
    }

    public void switchOn() {
        System.out.println();
        System.out.println(this.getClass() + " КОМАНДА - ВКЛЮЧИТЬ: '" + this.name + "' ");

        // Проверяем состояние выключателя
        if (this.aBoolean_p1_p2) {
            // Если выключатель включен
            System.out.println(this.getClass() + " КОМАНДА - ВКЛЮЧИТЬ: '" + this.name + "' ОТМЕНА: объект ВКЛЮЧЕН РАНЕЕ");
        } else {
            System.out.println();

            // Если выключатель отключен --> включаем выключатель и подаем напряжение
            this.aBoolean_p1_p2 = true;
            // Проверем наличие напряжения на контакте р_1
            if (getContact_p1().getArrayListPathElectricCurrents().size() != 0) {
                this.electricityOn(this.getContact_p2());
            }

            // Проверем наличие напряжения на контакте р_2
            if (getContact_p2().getArrayListPathElectricCurrents().size() != 0) {
                this.electricityOn(this.getContact_p1());
            }

            System.out.println(this.getClass() + " public void switchOn():" + this.name + " ВЫПОЛНЕННА: объект ВКЛЮЧЕН");
        }
    }

    private void electricityOn(Contact contactSource) {
        Contact contactInput = this.getContactElectricCurrent(contactSource);
        for (PathElectricCurrent electricCurrent: contactSource.getArrayListPathElectricCurrents()) {
            contactInput.electricityOn(electricCurrent); // передаем событие

//            PathElectricCurrent pathElectricCurrentClone = electricCurrent.getClone();
//            pathElectricCurrentClone.addContactToStart(contactSource);
//            System.out.println(pathElectricCurrentClone);
////            contactSource.electricityOn(pathElectricCurrentClone); // передаем событие
//            contactInput.addPathElectricCurrents(pathElectricCurrentClone);
//            contactSource.getElectricityConsumer().electricityOn(pathElectricCurrentClone);
        }
    }

    public void switchOff() {
        System.out.println();
        System.out.println(this.getClass() + " КОМАНДА - ОТКЛЮЧИТЬ: '" + this.name + "' ");
        // Проверяем состояние выключателя, если он включен --> отключаем
        if (this.aBoolean_p1_p2) {
            // Проверем наличие напряжения на контакте р_1
            if (getContact_p1().getArrayListPathElectricCurrents().size() != 0) {
                // если контакт р_1 источник напряжения, снимаем напряжение
                this.electricityOff(this.getContact_p1());
            }

            // Проверем наличие напряжения на контакте р_2
            if (getContact_p2().getArrayListPathElectricCurrents().size() != 0) {
                // если контакт р_2 источник напряжения, снимаем напряжение
                this.electricityOff(this.getContact_p2());
            }

            System.out.println(this.getClass() + " КОМАНДА - ОТКЛЮЧИТЬ: '" + this.name + "' ВЫПОЛНЕННА: объект ОТКЛЮЧЕН");
            this.aBoolean_p1_p2 = false;

        } else {
            System.out.println(this.getClass() + " КОМАНДА - ОТКЛЮЧИТЬ: '" + this.name + "' ОТМЕНА: объект ОТКЛЮЧЕН РАНЕЕ");

        }
    }

    private void electricityOff(Contact contactSource) {
        Contact contactInput = this.getContactElectricCurrent(contactSource);
        for (PathElectricCurrent pathElectricCurrent: contactSource.getArrayListPathElectricCurrents()) {
            if (pathElectricCurrent.getContactToPenultimate().equals(contactInput)) {
                contactSource.electricityOff(pathElectricCurrent); // передаем событие

                this.electricityOff(contactSource); // вызываем повторно метод, для исключения повторного прохождения тока через объект
                break;
            }
        }
    }

    String toStringArrayListPathElectricCurrents() {
        String s = this.getContact_p1().toStringArrayListPathElectricCurrents() + "\n\r";
        s = s + this.getContact_p2().toStringArrayListPathElectricCurrents();
        return s;
    }
}
