package ElectricityV003;

/**
 * Зверик Роман Станиставович 11.04.2018.
 */
public interface ElectricityConsumer {

    /**
     * Метод описывает появление напряжение
     */
    void electricityOn(EventElectricity eventElectricity);

    /**
     * Метод описывает снятие напряжение
     */
    void electricityOff();
}
