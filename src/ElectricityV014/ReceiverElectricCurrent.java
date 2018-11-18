package ElectricityV014;

import java.util.ArrayList;

/** Объект описывающий "Приемник электрического тока"
 *
 * Зверик Роман Станиславович 29.05.2018.
 */
public class ReceiverElectricCurrent implements ElectricityObject {
    private String name;
    private ObjectContact objectContact_p1;

    ReceiverElectricCurrent(String name){
        this.name = name;
        this.objectContact_p1 = ObjectContact.Factory(this.name, ContactNumber.p_1, this);
    }

    Contact getContact_p1() {
        return objectContact_p1.getMainContact();
    }

    String toStringArrayListPathElectricCurrents(){
        String s = this.getContact_p1().toStringArrayListPathElectricCurrents();
        return s;
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
        return new VoltageTerminal(eventElectricity, this.getContact_p1());
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
