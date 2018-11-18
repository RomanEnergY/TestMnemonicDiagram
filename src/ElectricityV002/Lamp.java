package ElectricityV002;

/**
 * Зверик Роман Станиставович 11.04.2018.
 */
public class Lamp implements ElectricityConsumer {
    private String name;

    Lamp(String name){
        this.name = name;
    }

    private void lightOn(){
        System.out.println('\'' + this.name + "' горит");
    }

    private void lightOff(){
        System.out.println('\'' + this.name + "' перестала гореть");
    }

    @Override
    public void electricityOn() {
        lightOn();
    }

    @Override
    public void electricityOff() {
        lightOff();
    }
}
