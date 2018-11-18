package ElectricityV011;

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

    /** Метод возвращает Object electricityObject
     *
     * @return Object electricityObject
     */
    Object getElectricityObject();

    /** Метод возвращает логическое значение, если объект electricityObject является источником напряжения
     * 1. Генератор
     * 2. Трансформатор
     * 3. Выключатель
     *
     * @return true если источник
     */
    boolean isContactSourceOfVoltage();


}
