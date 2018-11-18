package ElectricityV012;

import java.util.ArrayList;

/**
 * Зверик Роман Станиславович 19.04.2018.
 */
public interface ElectricityConnection {
    /** Медод реализующий подключение (соединение) передаваемых контактов и передачу события о необходимости соединения
     * всех контактов события и соединяемого контакта
     *
     * @param contactEvent контакт от которого произошло событие
     * @param contactConnect контакт с которым требуется подключиться
     */
    void connect(Contact contactEvent, Contact contactConnect);

    /** Медод реализующий отключение (отсоединение) передаваемого контактов и передачу события всем ранее подключенным
     * объектам к этому контакту
     *
     * @param contactEvent контакт от которого произошло событие
     * @param contactConnect контакт с которым требуется отключиться
     */
    void disConnect(Contact contactEvent, Contact contactConnect);

    /**
     * Возвращает лист контактов соединений от контакта события
     * @return ArrayList<Contact> сontactArrayList
     */
    ArrayList<Contact> getContactArrayList();

    /**
     * Метод добавляет в лист контакты из листа массива всех контактов
     * @param contactArrayList лист всех контактов, которых требуется добавить
     */
    void addContactArrayList(ArrayList<Contact> contactArrayList);

    void removeContactArrayList(Contact contactDisConnect);

}
