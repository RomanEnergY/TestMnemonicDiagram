package ElectricityV001;

/**
 * Зверик Роман Станиставович 11.04.2018.
 */
public interface ElectricityConsumer {

    /**
     * Метод описывает появление напряжение
     */
    void electricityOn();

    /**
     * Метод описывает снятие напряжение
     */
    void electricityOff();
}
