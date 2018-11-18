package ElectricityV004;

/**
 * Зверик Роман Станиславович 17.04.2018.
 */
public class EventElectricity {
    private String name;
    private Voltage voltage;

    public EventElectricity(String name, Voltage voltage) {
        this.name = name;
        this.voltage = voltage;
    }

    public String getName() {
        return name;
    }

    public Voltage getVoltage() {
        return voltage;
    }

    @Override
    public String toString() {
        return "EventElectricity{" +
                "name='" + name + '\'' +
                ", classVoltage=" + voltage +
                '}';
    }
}
