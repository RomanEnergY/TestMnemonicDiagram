package ElectricityV004;

import java.util.ArrayList;

/** Класс описывающий ПОТРЕБИТЕЛЯ
 *
 * Зверик Роман Станиславович 17.04.2018.
 */
public class Consumer implements ElectricityConnection {
    private boolean aElectricity;
    private String name;
    private ArrayList<EventElectricity> eventElectricityArrayList;
    private Contact contact_P1;

    Consumer(String name){
        this.aElectricity = false;
        this.name = name;
        this.eventElectricityArrayList = new ArrayList<>();
        this.contact_P1 = Contact.Factory(this.name, "P_1", this);

    }

    Contact getContact_P1() {
        return contact_P1;
    }

    @Override
    public void checkStatusObject(String name, String numberContact, EventElectricity eventElectricity, boolean checkContact) {
        if (checkContact) {
            boolean b = true;
            for (EventElectricity electricity : eventElectricityArrayList) {
                if (electricity.equals(eventElectricity)) { // Если в листе есть данный источник питания
                    b = false;
                    break;
                }
            }
            if (b && Voltage.isElectricity(eventElectricity.getVoltage()))
                eventElectricityArrayList.add(eventElectricity);
        } else {
            for (EventElectricity electricity : eventElectricityArrayList) {
                if (electricity.getName().equals(eventElectricity.getName())) { // Если в листе есть данный источник питания
                    eventElectricityArrayList.remove(electricity);
                    break;
                }
            }
        }

        this.setElectricity();
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "name='" + name + '\'' +
                ", eventElectricityArrayList=" + eventElectricityArrayList.toString() +
                ", contact_P1=" + contact_P1 +
                '}';
    }

    private void setElectricity() {
        if (this.eventElectricityArrayList.size() == 0) { // Если у абонента пропало напряжение
            System.out.println("Напряжение с абонента " + this.name + " снято");
            this.aElectricity = false;
        }
        else if (this.eventElectricityArrayList.size() == 1 && !this.aElectricity) { // если у абонента ранее напряжение было снято
            this.aElectricity = true;
            System.out.println("Напряжение у абонента " + this.name + " восстановленно");
        }
        else { // если сеть включется в паралель, появляется 2 и более источника напряжения у абонента
            this.aElectricity = true;
        }
    }
}
