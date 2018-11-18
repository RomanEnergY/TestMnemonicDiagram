package ElectricityV013;

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

    /** Метод возвращает VoltageTerminal в котором хранится данные о передаваемом напряжении через объект ElectricityObject
     * и контакт соединения, то есть как контакты включены внутри еlectricityObject
     *
     * @param eventElectricity источник напряжения
     * @param contact контакт
     */
    VoltageTerminal getVoltageTerminal(EventElectricity eventElectricity, Contact contact);

    /** Метод возвращиет контакт соединения, как контакты включены внутри еlectricityObject
     *
     * @param contact контакт опрос
     * @return противоположный контакт
     */
    Contact getContactElectricCurrent(Contact contact);

    /** Метод возвращает логическое значение, если объект electricityObject является источником напряжения
     * 1. Генератор
     * 2. Трансформатор
     * 3. Выключатель
     *
     * @return true если источник
     */
    boolean isContactSourceOfVoltage();

    /**
     * Метод вызывается при первичном появлении напряжении на контакте
     */
    void voltageTerminalAppeared(Contact contactVoltageTerminalAppeared);

    /**
     * Метод вызывается при полном исчезновении напряжения на контакте
     */
    void voltageTerminalDisAppeared(Contact contactVoltageTerminalDisAppeared);


}
