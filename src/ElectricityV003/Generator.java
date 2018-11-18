package ElectricityV003;

import java.util.ArrayList;

/**
 * Зверик Роман Станиславович 11.04.2018.
 */
public class Generator {
    private String name;
    private ClassVoltage classVoltage;
    private Boolean aSwitchOn;


    private ArrayList<ElectricityConsumer> listeners = new ArrayList<>();

    public Generator(String name, ClassVoltage classVoltage){
        this.name = name;
        this.classVoltage = classVoltage;
        this.aSwitchOn = false;

    }

    /**
     * Метод реализующий подключения слушателя для последующей подаче напряжения по списку слушателей
     * @param listener ElectricityConsumer listener
     */
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
                consumer.electricityOn(new EventElectricity(this.name, this.classVoltage));
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
        return "Generator{" +
                "name='" + name + '\'' +
                ", classVoltage=" + classVoltage +
                ", aSwitchOn=" + aSwitchOn +
                '}';
    }
}
