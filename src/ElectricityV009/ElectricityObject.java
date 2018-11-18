package ElectricityV009;

import java.util.ArrayList;

/**
 * Зверик Роман Станиславович 07.05.2018.
 */
public interface ElectricityObject {

    /** Метод возвращает true, если передаваемый объект Contact contact соответствует (является контактом) хотя бы одному
     * из объекта ElectricityObject еlectricityObject
     *
     * @param contact пердаваемый контакт
     * @return true передаваемый контакт является контактом ElectricityObject, false если не равен
     */
    boolean isEqualsContactElectricityObject(Contact contact);

    /** Метод возвращает ArrayList<Contact> подключенных контактов от контакта ElectricityObject
     *
     * @param contact пердаваемый контакт
     */
    ArrayList<Contact> getContactArrayListElectricityObject(Contact contact);

    /** Метод возвращает Contact, схемы соединений ElectricityObject еlectricityObject
     * как контакты включены внутри еlectricityObject
     *
     * @param voltageTerminal
     */
    VoltageTerminal getPathElectricCurrent(VoltageTerminal voltageTerminal);
}
