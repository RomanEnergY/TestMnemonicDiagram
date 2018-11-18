package ElectricityV006;

import java.util.ArrayList;

/** Класс описывающий ГЕНЕРАТОР
 *
 * Зверик Роман Станиславович 17.04.2018.
 */
public class Generator implements ElectricityConnection {

    private String name;
    private Boolean aElectricityOn;
    private Contact contact_p1;
    private EventElectricity eventElectricity;
    private ArrayList<Contact> contact_p1_ArrayList;

    Generator(String name, Voltage voltage){
        this.name = name;
        this.aElectricityOn = false;
        this.eventElectricity = new EventElectricity(this.name, voltage);
        this.contact_p1 = Contact.Factory(this.name, ContactNumber.p_1, this);

    }

    /**
     * Метод реализующий добавления в список слушателей ArrayList<Contact> contact_p1_ArrayList
     * объекта, который в дальнейшем при включении генератора получает событие о появлении напряжения
     * на связанные с объектом ГЕНЕРАТОР потребителях, так же реализованна передача данных о последующих
     * подключениях, так если ранее
     *
     * @param contactConnect Contact contactConnect
     */
    public void addContact_p1(Contact contactConnect){
        System.out.println("Generator: public void addContact_p1(Contact contactConnect) contactConnect:" + contactConnect);
        // Проверка, если передаваемый объект равен пустате
        if (contactConnect == null)
            return;

        if (contactConnect.isElectricityConnection())
            return;

        // Проверка, если передаваемый объект имеется в списке слушателей
        for (Contact consumer : contact_p1_ArrayList) {
            if(consumer.equals(contactConnect)){
                return;
            }
        }

        // Добавляем передаваемый объект в список слушателей (подключаем)
        contact_p1_ArrayList.add(contactConnect);

//        // Соединяем передаваемый объект с приемником contact_P1
//        contactConnect.connectThis(contact_p1);
//
//        // Передаем данные о соединении всем в листе contact_p1_ArrayList
//        for (Contact consumer : contact_p1_ArrayList) {
//            consumer.connectThis(contactConnect);
//        }

    }

    /**
     * Метод реализующий удаление из списока слушателей ArrayList<ElectricityConsumer> listeners
     * объекта ElectricityConsumer listener
     *
     * @param listener ElectricityConsumer listener
     */
    void removeContact_P1(Contact listener){
        contact_p1_ArrayList.remove(listener);
    }

    /**
     * Метод реализует ЗАПУСК герератора и при успешном его запуске передает событие 'this.eventElectricity'
     * на контакт 'this.contact_P1' герератора
     */
    void run(){
        if (this.generatorElectricityOn()) {
            for (Contact contact : contact_p1_ArrayList) {
                contact.electricityOn(this.eventElectricity);

            }
        }
    }

    /**
     * Метод реализует ОСТАНОВКУ герератора и при успешном его останове передает событие 'this.eventElectricity'
     * на контакт 'this.contact_P1' герератора
     */
    void stop(){
        if (this.generatorElectricityOff()) {
            for (Contact contact : contact_p1_ArrayList) {
                contact.electricityOn(new EventElectricity(eventElectricity.getName(), Voltage.NO));

            }
        }
    }

    /**
     * Возвращает данные о контакте объекта
     * @return contact_P1
     */
    public Contact getContact_p1() {
        return contact_p1;
    }

    /**
     * Метод реализует запуск герератора, если герератор ранее включен, выдает сообщение
     * об обшибке "ГЕРЕРАТОР запущен ранее"
     */
    private boolean generatorElectricityOn() {
        System.out.print(this.name + " команда: ЗАПУСТИТЬ");
        if (this.aElectricityOn) { // Если генератор запущен ранее --> выдаем ошибку
            System.out.println(" --> ОТМЕНА: '" + this.name + "\' запущен ранее!!!");
            return false;
        }
        else { // Если генератор не включен, включаем
            this.aElectricityOn = true;
            System.out.println(" --> генератор УСПЕШНО запущен, передаю данные о запуске герератора");
            return true;
        }
    }

    /**
     * Метод реализует остановку герератора, если герератор ранее остановлен, выдает сообщение
     * об обшибке "ГЕРЕРАТОР остановлен ранее"
     */
    private boolean generatorElectricityOff() {
        System.out.print(this.name + " команда: ОСТАНОВИТЬ");
        if (this.aElectricityOn) { // Если генератор запущен --> останавливаем
            this.aElectricityOn = true;
            System.out.println(" --> генератор УСПЕШНО остановлен, передаю данные об остановке герератора");
            return true;
        }
        else { // Если генератор ранее остановлен --> выдаем ошибку
            System.out.println(" --> ОТМЕНА: '" + this.name + "\' остановлен ранее!!!");
            return false;
        }
    }

    @Override
    public void connect(Contact contact_connect, ArrayList<Contact> contactArrayList) {
        System.out.println("Generator:connect(Contact contact_connect, ArrayList<Contact> contactArrayList) contact_connect:" + contact_connect + ", contactArrayList:" + contactArrayList);
    }

    @Override
    public void connect(Contact contact_This, Contact contact_connect) {

    }

    @Override
    public ArrayList<Contact> getConnectContactArrayList(Contact contact) {
        if (contact.equals(contact_p1))
            return contact_p1_ArrayList;

        return null;
    }

    @Override
    public String toString() {
        return "Generator{" +
                "name='" + name + "'," +
                "aElectricityOn=" + aElectricityOn + "," +
                "eventElectricity=" + eventElectricity + "," +
                "contact_P1=" + contact_p1 + "," +
                "contact_p1_ArrayList=" + contact_p1_ArrayList +
                '}';
    }

    public String contact_ArrayList_toString() {
        return "Generator{" +
                "name='" + name + "'," +
                "contact_p1_ArrayList=" + contact_p1_ArrayList +
                '}';
    }
}
