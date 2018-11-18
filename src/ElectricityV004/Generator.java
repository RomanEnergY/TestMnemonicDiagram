package ElectricityV004;

/** Класс описывающий ГЕНЕРАТОР
 *
 * Зверик Роман Станиславович 17.04.2018.
 */
public class Generator implements ElectricityConnection {

    private String name;
    private Boolean aElectricityOn;
    private Contact contact_P1;
    private EventElectricity eventElectricity;

    Generator(String name, Voltage voltage){
        this.name = name;
        this.aElectricityOn = false;
        this.eventElectricity = new EventElectricity(this.name, voltage);
        contact_P1 = Contact.Factory(this.name, "P_1", this);

    }

    /**
     * Метод реализующий добавления в список слушателей ArrayList<ElectricityConsumer> listeners
     * объекта, который в дальнейшем при включении генератора получает событие, о появлении напряжения
     * на связанные с объектом ГЕНЕРАТОР потребителях
     *
     * @param contact ElectricityConsumer listener
     */
    void addContact_P1(Contact contact) {
        contact_P1.addElectricityListener(contact);
    }

    /**
     * Метод реализующий удаление из списока слушателей ArrayList<ElectricityConsumer> listeners
     * объекта ElectricityConsumer listener
     *
     * @param contact ElectricityConsumer listener
     */
    public void removeContact_P1(Contact contact){
        contact_P1.removeElectricityListener(contact);
    }

    /**
     * Метод реализует ЗАПУСК герератора и при успешном его запуске передает событие 'this.eventElectricity'
     * на контакт 'this.contact_P1' герератора
     */
    void run(){
        if (this.generatorElectricityOn()) {
            contact_P1.electricityOn(this.eventElectricity);
        }
    }

    /**
     * Метод реализует ОСТАНОВКУ герератора и при успешном его останове передает событие 'this.eventElectricity'
     * на контакт 'this.contact_P1' герератора
     */
    void stop(){
        if (this.generatorElectricityOff()) {
            contact_P1.electricityOff(new EventElectricity(eventElectricity.getName(), Voltage.NO));
        }
    }

    /**
     * Возвращает данные о контакте объекта
     * @return contact_P1
     */
    public Contact getContact_P1() {
        return contact_P1;
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
    public void checkStatusObject(String name, String numberContact, EventElectricity eventElectricity, boolean checkContact) {
//        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Generator{" +
                "name='" + name + '\'' +
                ", aElectricityOn=" + aElectricityOn +
                ", contact_P1=" + contact_P1 +
                ", eventElectricity=" + eventElectricity +
                '}';
    }
}
