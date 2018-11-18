package ElectricityV014;

/**
 * Зверик Роман Станиставович 17.04.2018.
 */
public interface ElectricityConsumer {

    /**
     * Метод описывает появление напряжение
     */
    void electricityOn(PathElectricCurrent pathElectricCurrent);

    /**
     * Метод описывает снятие напряжение
     */
    void electricityOff(PathElectricCurrent pathElectricCurrent);

}
