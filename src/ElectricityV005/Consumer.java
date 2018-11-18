package ElectricityV005;

import java.util.ArrayList;

/** Класс описывающий ПОТРЕБИТЕЛЯ
 *
 * Зверик Роман Станиславович 17.04.2018.
 */
public class Consumer implements ElectricityConnection {
    private String name;
    private Contact contact_P1;
    private ArrayList<EventElectricity> eventElectricityArrayList;
    private ArrayList<Contact> contact_p1_ArrayList = new ArrayList<>();

    Consumer(String name){
        this.name = name;
        this.contact_P1 = Contact.Factory(this.name, "P_1", this);
        this.eventElectricityArrayList = new ArrayList<>();
        contact_p1_ArrayList = new ArrayList<>();

    }

    Contact getContact_P1() {
        return contact_P1;
    }



    public void addContact_P1(Contact listener){
        // Проверка, если передаваемый объект равен пустате
        if (listener == null)
            return;

        // Проверка, если передаваемый объект имеется в списке слушателей
        for (ElectricityConsumer consumer : contact_p1_ArrayList) {
            if(consumer.equals(listener)){
                return;
            }
        }

        // Добавляем передаваемый объект в список слушателей (подключаем)
        contact_p1_ArrayList.add(listener);

        // Соединяем передаваемый объект с приемником contact_P1
        listener.getElectricityConnection().connect(contact_P1);

        // Передаем данные о соединении всем в листе contact_p1_ArrayList
        for (Contact consumer : contact_p1_ArrayList) {
            if (consumer != listener)
                consumer.getElectricityConnection().connect(listener);
        }
    }

    @Override
    public void checkStatusObject(String name, String numberContact, EventElectricity eventElectricity) {
        // если появляется напряжение на контакте у абонента, абонент об этом сообщает
        boolean add = true;
        if (Voltage.isElectricity(eventElectricity.getVoltage())){
            for (EventElectricity electricity : eventElectricityArrayList) {
                if(electricity.getName().equals(eventElectricity.getName())){
                    add = false;
                    break;
                }
            }

            if (add){
                if (eventElectricityArrayList.size() == 0)
                    System.out.print(this.name + " напряжение восстановленно");

                eventElectricityArrayList.add(eventElectricity);
                System.out.println(": " + this.name + " eventElectricity=" + eventElectricity);
            }
        }
        else {
            if (eventElectricityArrayList.size() == 1)
                System.out.print(this.name + " напряжение снято");

            for (EventElectricity electricity : eventElectricityArrayList) {
                if(electricity.getName().equals(eventElectricity.getName())){
                    eventElectricityArrayList.remove(electricity);
                    break;
                }
            }
            System.out.println(": " + this.name + " eventElectricity=" + eventElectricity);
        }
    }

    @Override
    public void connect(Contact contact) {
        this.addContact_P1(contact);
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "name='" + name + '\'' +
                ", contact_P1=" + contact_P1 +
                ", eventElectricityArrayList=" + eventElectricityArrayList +
                ", contact_p1_ArrayList=" + contact_p1_ArrayList +
                '}';
    }

    public String getContact_p1_toString() {
        return "Consumer{" +
                "name='" + name + "'," +
                "contact_p1_ArrayList=" + contact_p1_ArrayList +
                '}';
    }
}
