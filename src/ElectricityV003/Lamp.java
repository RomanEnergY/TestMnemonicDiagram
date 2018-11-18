package ElectricityV003;

/**
 * Зверик Роман Станиставович 11.04.2018.
 */
public class Lamp implements ElectricityConsumer {
    private String name;

    Lamp(String name){
        this.name = name;
    }

    private void lightOn(EventElectricity eventElectricity){
        System.out.println('\'' + this.name + "' горит, напряжение " + ClassVoltage.toString(eventElectricity.getClassVoltage()));
    }

    private void lightOff(){
        System.out.println('\'' + this.name + "' перестала гореть");
    }

    @Override
    public void electricityOn(EventElectricity eventElectricity) {
        lightOn(eventElectricity);
    }

    @Override
    public void electricityOff() {
        lightOff();
    }
}
