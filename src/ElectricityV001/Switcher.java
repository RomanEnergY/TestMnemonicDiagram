package ElectricityV001;

import java.util.ArrayList;

/**
 * Зверик Роман Станиставович 11.04.2018.
 */
public class Switcher {
    private String name;
    private Boolean aSwitchOn;

    private ArrayList<ElectricityConsumer> listeners = new ArrayList<>();

    public Switcher(String name){
        this.name = name;
        this.aSwitchOn = false;

    }

    public void addElectricityListener(ElectricityConsumer listener){
        // Проверка, если передаваемый объект равен пустате
        if (listener == null)
            return;

        // Проверка, если передаваемый объект имеется в списке слушателей
        for (ElectricityConsumer electricity : listeners) {
            if(electricity.equals(listener)){
                return;
            }
        }

        // Добавляем объект в список слушателей
        listeners.add(listener);
    }

    public void removeElectricityListener(ElectricityConsumer listener){
        listeners.remove(listener);
    }

    public void switchOn(){
        System.out.print("Команда: ВКЛЮЧИТЬ '" + this.name + "\' ");
        if (!this.aSwitchOn) {
            System.out.print("УСПЕШНО: Выключатель включен ");
            this.aSwitchOn = true;

            System.out.println("опрашиваю потребителей");
            for (ElectricityConsumer consumer : listeners) {
                consumer.electricityOn();
            }
            System.out.println();
        }
        else
            System.out.println("ОТМЕНА: выключатель '" + this.name + "\' включен!!!");
    }

    public void switchOff(){
        System.out.print("Команда: ОТКЛЮЧИТЬ '" + this.name + "\' ");
        if (this.aSwitchOn){
            System.out.print("УСПЕШНО: Выключатель отключен ");
            this.aSwitchOn = false;

            System.out.println("опрашиваю потребителей");
            for (ElectricityConsumer consumer: listeners){
                consumer.electricityOff();
            }
            System.out.println();
        }
        else
            System.out.println("ОТМЕНА: выключатель '" + this.name + "\' отключен!!!");
    }

    @Override
    public String toString() {
        return "Switcher{" +
                "name='" + name + '\'' +
                ", aSwitchOn=" + aSwitchOn +
                ", listeners=" + listeners +
                '}';
    }
}
