package ElectricityV004;

import java.util.ArrayList;

/**
 * Created by user on 17.04.2018.
 */
public class Contact implements ElectricityConsumer {
    private String name;
    private String numberContact;
    private EventElectricity eventElectricity;
    private ArrayList<ElectricityConsumer> contactArrayList = new ArrayList<>();
    private ElectricityConnection electricityConnection;

    @Override
    public void electricityOn(EventElectricity eventElectricity) {
//        System.out.println(this.name + " " + this.numberContact + " " + eventElectricity.toString());
        this.eventElectricity = eventElectricity;

        for (ElectricityConsumer consumer : contactArrayList) {
            consumer.electricityOn(eventElectricity);
        }
        electricityConnection.checkStatusObject(this.name, this.numberContact, this.eventElectricity, true);

//        System.out.println("electricityOn: " + this.name + " подано напряжение от " + this.eventElectricity.toString());
    }

    @Override
    public void electricityOff(EventElectricity eventElectricity) {
//        System.out.println(this.name + " " + this.numberContact + " " + eventElectricity.toString());
        this.eventElectricity = eventElectricity;

        for (ElectricityConsumer consumer : contactArrayList) {
            consumer.electricityOff(eventElectricity);
        }
        electricityConnection.checkStatusObject(this.name, this.numberContact, this.eventElectricity, false);

//        System.out.println("electricityOff: " + this.name + " снято напряжение от " + this.eventElectricity.toString());
    }

    public void addElectricityListener(Contact listener){
        // Проверка, если передаваемый объект равен пустате
        if (listener == null)
            return;

        // Проверка, если передаваемый объект имеется в списке слушателей
        for (ElectricityConsumer consumer : contactArrayList) {
            if(consumer.equals(listener)){
                return;
            }
        }

        // Добавляем объект в список слушателей
        contactArrayList.add(listener);
    }

    void removeElectricityListener(Contact listener){
        contactArrayList.remove(listener);
    }

    public ArrayList<ElectricityConsumer> getListeners() {
        return contactArrayList;
    }

    static Contact Factory(String nameContact, String numberContact, ElectricityConnection electricityConnection) {
        Contact contact = new Contact();
        contact.name = nameContact;
        contact.numberContact = numberContact;
        contact.eventElectricity = new EventElectricity("no", Voltage.NO);
        contact.electricityConnection = electricityConnection;
        return contact;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "eventElectricity=" + eventElectricity +
                ", contactArrayList=" + contactArrayList +
                ", electricityConnection=" + electricityConnection +
                '}';
    }

    public EventElectricity getEventElectricity() {
        return eventElectricity;
    }

    public ArrayList<ElectricityConsumer> getContactArrayList() {
        return contactArrayList;
    }

    public ElectricityConnection getElectricityConnection() {
        return electricityConnection;
    }
}
