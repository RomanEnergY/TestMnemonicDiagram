package ElectricityV009;

import java.util.ArrayList;

/** Класс описывающий ПРОВОДНИК
 *
 * Зверик Роман Станиславович 23.04.2018.
 */
public class Conductor implements ElectricityObject {
    private String name; // наименование объекта
    private boolean aBoolean_p1_p2; // состояние объекта, true - включен
    private ObjectContact objectContact_p1; // контакт №1
    private ObjectContact objectContact_p2; // контакт №2

    Conductor(String name){
        this.name = name;
        this.aBoolean_p1_p2 = true;
        this.objectContact_p1 = ObjectContact.Factory(this.name, ContactNumber.p_1, this);
        this.objectContact_p2 = ObjectContact.Factory(this.name, ContactNumber.p_2, this);
    }

    Contact getContact_p1() {
        return objectContact_p1.getMainContact();
    }
    Contact getContact_p2() {
        return objectContact_p2.getMainContact();
    }

    String toStringContactAll() {
        return "Conductor{" +
                "name='" + name + '\'' +
                ", " + toStringContact_p1_ArrayList() +
                ", " + toStringContact_p2_ArrayList() +
                '}';
    }

    String toStringContact_p1_ArrayList() {
        return "contact_p1_ArrayList=" + objectContact_p1.getContactArrayList();
    }

    String toStringContact_p2_ArrayList() {
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
    public VoltageTerminal getPathElectricCurrent(VoltageTerminal voltageTerminal) {
        if (voltageTerminal.getContact().equals(objectContact_p1.getMainContact())) { // если появилось напряжение на контакте №1
            if (this.aBoolean_p1_p2) { // если объект включен
                // передаем сообщение, о том что на контакте №2 появится напряжение точно такое же как на контакте №1
//                System.out.println(this.getClass() + " return objectContact_p2:" + objectContact_p2);
                return new VoltageTerminal(voltageTerminal.geEventElectricity(), objectContact_p2.getMainContact());
            }
        }
        else if (voltageTerminal.getContact().equals(objectContact_p2.getMainContact())) { // если появилось напряжение на контакте №2
            if (this.aBoolean_p1_p2) { // если объект включен
                // передаем сообщение, о том что на контакте №1 появится напряжение точно такое же как на контакте №2
//                System.out.println(this.getClass() + " return objectContact_p2:" + objectContact_p1);
                return new VoltageTerminal(voltageTerminal.geEventElectricity(), objectContact_p1.getMainContact());
            }

        }
        return null;
    }

    public void switchOn() {
        if (this.aBoolean_p1_p2){
            System.out.println(this.getClass() + ":switchOn " + this.name + " включен ранее");
        }
        else {
            System.out.println(this.getClass() + ":switchOn " + this.name + " включен");
            this.aBoolean_p1_p2 = true;
        }
    }

    public void switchOff() {
        if (this.aBoolean_p1_p2){
            System.out.println(this.getClass() + ":switchOn " + this.name + " отключен");
            this.aBoolean_p1_p2 = false;
        }
        else {
            System.out.println(this.getClass() + ":switchOn " + this.name + " отключен ранее");

        }
    }
}
