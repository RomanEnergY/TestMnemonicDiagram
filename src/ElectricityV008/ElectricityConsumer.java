package ElectricityV008;

/**
 * Зверик Роман Станиставович 17.04.2018.
 */
public interface ElectricityConsumer {

    /**
     * Метод описывает появление напряжение
     */
    void electricityOn(EventElectricity eventElectricity, Contact contact);

    /**
     * Метод описывает снятие напряжение
     */
    void electricityOff(EventElectricity eventElectricity, Contact contact);

}
