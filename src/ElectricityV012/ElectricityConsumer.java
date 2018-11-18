package ElectricityV012;

import java.util.ArrayList;

/**
 * Зверик Роман Станиставович 17.04.2018.
 */
public interface ElectricityConsumer {

    /**
     * Метод описывает появление напряжение
     */
    void electricityOn(PathElectricCurrent pathElectricCurrent, Contact contactStartEventElectricity);

    /**
     * Метод описывает снятие напряжение
     */
    void electricityOff(EventElectricity eventElectricity, Contact contactStartEventElectricity);

}
