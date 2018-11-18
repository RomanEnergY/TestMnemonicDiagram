package ElectricityV011;

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
                return new VoltageTerminal(eventElectricity, objectContact_p2.getMainContact());
            }
        }
        else if (contact.equals(objectContact_p2.getMainContact())) { // если появилось напряжение на контакте №2
            if (this.aBoolean_p1_p2) { // если объект включен
                // передаем сообщение, о том что на контакте №1 появится напряжение точно такое же как на контакте №2
//                System.out.println(this.getClass() + " return objectContact_p2:" + objectContact_p1);
                return new VoltageTerminal(eventElectricity, objectContact_p1.getMainContact());
            }

        }
        return null;
    }



    @Override // ElectricityObject
    public Object getElectricityObject() {
        return this;
    }

    public void switchOn() {
        if (this.aBoolean_p1_p2){
            System.out.println(this.getClass() + " public void switchOn():" + this.name + " ОТМЕНА: объект включен ранее");
        }
        else {
            System.out.println(this.getClass() + " public void switchOn():" + this.name + " ВЫПОЛНЕННА: объект включен");
            this.aBoolean_p1_p2 = true;
        }
    }

    @Override // ElectricityObject
    public boolean isContactSourceOfVoltage() {
        return false;
    }

    @Override // ElectricityObject
    public Contact getContactElectricCurrent(Contact contact) {
        if (this.aBoolean_p1_p2){
            if (contact.equals(objectContact_p1.getMainContact())) {
                return objectContact_p2.getMainContact();
            }
            else if (contact.equals(objectContact_p2.getMainContact())) {
                return objectContact_p1.getMainContact();
            }
            else
                return null;
        }
        else
            return null;
    }

    public void switchOff() {
        System.out.print(this.getClass() + " КОМАНДА - ОТКЛЮЧИТЬ:'" + this.name + "' ");
        if (this.aBoolean_p1_p2){
            if (this.getContact_p1().getArrayListPathElectricCurrents().size() == 0 && this.getContact_p2().getArrayListPathElectricCurrents().size() == 0) {
                System.out.println("ВЫПОЛНЕННА: объект отключен");
                this.aBoolean_p1_p2 = false;
            }
            else {
                System.out.println("ОТМЕНА: объект находится под напряжением");
            }
        }
        else {
            System.out.println("ОТМЕНА: объект отключен ранее");

        }
    }

    String toStringArrayListPathElectricCurrents(){
        String s = this.getContact_p1().toStringArrayListPathElectricCurrents() + "\n\r";
        s = s + this.getContact_p2().toStringArrayListPathElectricCurrents();
        return s;
    }
}
