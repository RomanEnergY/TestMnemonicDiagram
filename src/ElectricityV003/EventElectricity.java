package ElectricityV003;

/**
 * Created by user on 12.04.2018.
 */
public class EventElectricity {
    private String name;
    private ClassVoltage classVoltage;

    public EventElectricity(String name, ClassVoltage classVoltage) {
        this.name = name;
        this.classVoltage = classVoltage;
    }

    public String getName() {
        return name;
    }

    public ClassVoltage getClassVoltage() {
        return classVoltage;
    }
}
