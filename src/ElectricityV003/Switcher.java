package ElectricityV003;

import java.util.ArrayList;

/**
 * Зверик Роман Станиставович 11.04.2018.
 */
public class Switcher implements ElectricityConsumer {
    private EventElectricity eventElectricity;
    private String name;
    private Boolean aSwitchOn;

    private ArrayList<ElectricityConsumer> listeners = new ArrayList<>();

    public Switcher(String name) {
        this.name = name;
        this.aSwitchOn = false;

    }

    public void addElectricityListener(ElectricityConsumer listener) {
        // Проверка, если передаваемый объект равен пустате
        if (listener == null)
            return;

        // Проверка, если передаваемый объект имеется в списке слушателей
        for (ElectricityConsumer electricity : listeners) {
            if (electricity.equals(listener)) {
                return;
            }
        }

        // Добавляем объект в список слушателей
        listeners.add(listener);
    }

    public void removeElectricityListener(ElectricityConsumer listener) {
        listeners.remove(listener);
    }

    public void switchOn() {
        System.out.print("Команда: ВКЛЮЧИТЬ '" + this.name + "\' ");
        if (eventElectricity != null) {
            if ((eventElectricity.getClassVoltage() != ClassVoltage.EARTH) && (eventElectricity.getClassVoltage() != ClassVoltage.NO)) {
                if (!this.aSwitchOn) {
                    System.out.print("УСПЕШНО: Выключатель включен ");
                    this.aSwitchOn = true;

                    System.out.println("опрашиваю потребителей");
                    for (ElectricityConsumer consumer : listeners) {
                        consumer.electricityOn(eventElectricity);
                    }
                    System.out.println();
                } else
                    System.out.println("ОТМЕНА: выключатель '" + this.name + "\' включен!!!");
            } else {
                System.out.print("УСПЕШНО: Выключатель включен ");
                this.aSwitchOn = true;
                System.out.println("напряжения нет " + ClassVoltage.toString(eventElectricity.getClassVoltage()));
            }
        } else {
            System.out.print("УСПЕШНО: Выключатель включен ");
            this.aSwitchOn = true;
            System.out.println("напряжения нет " + ClassVoltage.toString(eventElectricity.getClassVoltage()));
        }
    }

    public void switchOff() {
        System.out.print("Команда: ОТКЛЮЧИТЬ '" + this.name + "\' ");
        if (this.aSwitchOn) {
            System.out.print("УСПЕШНО: Выключатель отключен ");
            this.aSwitchOn = false;

            System.out.println("опрашиваю потребителей");
            for (ElectricityConsumer consumer : listeners) {
                consumer.electricityOff();
            }
            System.out.println();
        } else
            System.out.println("ОТМЕНА: выключатель '" + this.name + "\' отключен!!!");
    }

    @Override
    public String toString() {
        return "Switcher{" +
                "name='" + name + '\'' +
                ", aSwitchOn=" + aSwitchOn +
                '}';
    }

    @Override
    public void electricityOn(EventElectricity eventElectricity) {
        this.eventElectricity = eventElectricity;
        if (this.aSwitchOn) {
            System.out.print("Выключатель включен напряжение появилось ");
            this.aSwitchOn = true;

            System.out.println("опрашиваю потребителей");
            for (ElectricityConsumer consumer : listeners) {
                consumer.electricityOn(eventElectricity);
            }
            System.out.println();
        } else
            System.out.println("Выключатель '" + this.name + "\' отключен, на абонентов напряжение не подано!!!");
    }

    @Override
    public void electricityOff() {

    }
}
