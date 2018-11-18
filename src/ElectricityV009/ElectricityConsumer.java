package ElectricityV009;

/**
 * Зверик Роман Станиставович 17.04.2018.
 */
public interface ElectricityConsumer {

    /**
     * Метод описывает появление напряжение
     */
    void electricityOn(PathElectricCurrent contactEventElectricity) throws CloneNotSupportedException;

    /**
     * Метод описывает снятие напряжение
     */
    void electricityOff(EventElectricity eventElectricity, Contact contact);

}
